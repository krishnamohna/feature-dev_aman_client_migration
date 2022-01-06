package com.cardio.physician.data.remote.fitnesstracker.googlefit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cardio.physician.domain.fitness.model.*
import com.cardio.physician.network.NetworkError
import com.cardio.physician.ui.common.utils.Constants.HEALTH_LOG_PERIOD_FOR_CONNECT_FEATURE
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.HealthDataTypes
import java.util.*

class GoogleFitManager(val context: Context) {

    private val dataParser:DataParserGoogleFit by lazy {
        DataParserGoogleFit()
    }

    private val TAG = "GoogleFitManager"
    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_ACTIVITY_SEGMENT, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_HEART_RATE_SUMMARY, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_WEIGHT, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEIGHT, FitnessOptions.ACCESS_READ)
            .addDataType(HealthDataTypes.TYPE_BLOOD_PRESSURE, FitnessOptions.ACCESS_READ)
            .build()
    }

    private fun getGoogleAccount() = GoogleSignIn.getAccountForExtension(context, fitnessOptions)
    private fun oAuthPermissionsApproved() =
        GoogleSignIn.hasPermissions(getGoogleAccount(), fitnessOptions)

    enum class FitActionRequestCode {
        READ_DATA,
        UPDATE_AND_READ_DATA,
        DELETE_DATA
    }

    fun isLoggedIn(): Boolean {
        return oAuthPermissionsApproved()
    }

    fun login(fragment: Fragment) {
        GoogleSignIn.requestPermissions(
            fragment,
            FitActionRequestCode.READ_DATA.ordinal,
            getGoogleAccount(), fitnessOptions
        )
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            AppCompatActivity.RESULT_OK -> {
                val postSignInAction = FitActionRequestCode.values()[requestCode]
                postSignInAction.let {

                }
            }
            // else -> oAuthErrorMsg(requestCode, resultCode)
        }
    }

    fun getProfileData(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg: Exception) -> Unit
    ) {
        Fitness.getHistoryClient(activity, getGoogleAccount())
            .readData(queryProfileLogsFitnessData(HEALTH_LOG_PERIOD_FOR_CONNECT_FEATURE))
            .addOnSuccessListener { dataReadResult ->
                if (dataReadResult.buckets.isNotEmpty()) {
                    onSuccess.invoke(dataParser.parseSingleData(dataReadResult.buckets))
                    return@addOnSuccessListener
                } else if (dataReadResult.dataSets.isNotEmpty()) {
                    dataReadResult.dataSets.forEach {
                        for (dp in it.dataPoints) {
                            Log.i(TAG, "Data point:")
                            Log.i(TAG, "\tType: ${dp.dataType.name}")
                            /*  Log.i(TAG, "\tStart: ${dp.getStartTimeString()}")
                              Log.i(TAG, "\tEnd: ${dp.getEndTimeString()}")*/
                            dp.dataType.fields.forEach {
                                Log.i(TAG, "\tField: ${it.name} Value: ${dp.getValue(it)}")
                            }
                        }
                    }
                } else {
                    onFailure.invoke(NetworkError.noRecordFound())
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "There was a problem reading the data.", e)
                onFailure.invoke(e)
            }
    }

    fun logout() {
        // yet to find way to revoke third party access
    }

    fun getFitnessLogs(
        context: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: Exception) -> Unit,
        periodDays: Int
    ) {
        Fitness.getHistoryClient(context, getGoogleAccount())
            .readData(queryProfileLogsFitnessData(periodDays))
            .addOnSuccessListener { dataReadResult ->
                if (dataReadResult.buckets.isNotEmpty()) {
                    onSuccess.invoke(dataParser.parseLogsBucket(dataReadResult.buckets,periodDays))
                    return@addOnSuccessListener
                } else if (dataReadResult.dataSets.isNotEmpty()) {
                    onFailure.invoke(NetworkError.noRecordFound())
                } else {
                    onFailure.invoke(NetworkError.noRecordFound())
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "There was a problem reading the data.", e)
                onFailure.invoke(e)
            }
    }


}
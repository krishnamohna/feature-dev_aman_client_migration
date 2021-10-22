package com.cardio.doctor.data.remote.fitnesstracker.googlefit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cardio.doctor.domain.fitness.model.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.HealthDataTypes
import java.util.*
import java.util.concurrent.ExecutorService

class GoogleFitManager(val context: Context) {

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
        onFailure: (msg: String?) -> Unit
    ) {
        Fitness.getHistoryClient(activity, getGoogleAccount())
            .readData(queryProfileFitnessData())
            .addOnSuccessListener { dataReadResult ->
                if (dataReadResult.buckets.isNotEmpty()) {
                    onSuccess.invoke(DataParser().parseSingleData(dataReadResult))
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
                    onFailure.invoke("No Data Found")
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "There was a problem reading the data.", e)
                onFailure.invoke(e.message)
            }
    }

    fun logout(activity: Activity) {
        // yet to find way to revoke third party access
    }


    fun getFitnessLogs(
        context: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: String?) -> Unit,
        periodDays: Int
    ) {
        Fitness.getHistoryClient(context, getGoogleAccount())
            .readData(queryProfileLogsFitnessData(periodDays))
            .addOnSuccessListener { dataReadResult ->
                if (dataReadResult.buckets.isNotEmpty()) {
                    onSuccess.invoke(DataParser().parseLogsBucket(dataReadResult.buckets,periodDays))
                    return@addOnSuccessListener
                } else if (dataReadResult.dataSets.isNotEmpty()) {
                    onFailure.invoke("No Data Found")
                } else {
                    onFailure.invoke("No Data Found")
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "There was a problem reading the data.", e)
                onFailure.invoke(e.message)
            }
    }


}
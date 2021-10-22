package com.cardio.doctor.data.remote.fitnesstracker.googlefit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cardio.doctor.domain.fitness.model.*
import com.cardio.doctor.ui.common.utils.GoogleFit
import com.cardio.doctor.ui.common.utils.getDatesOfLastDays
import com.cardio.doctor.ui.common.utils.getEndTimeString
import com.cardio.doctor.ui.common.utils.getStartTimeString
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.HealthDataTypes
import java.util.*

class GoogleFitManager constructor(val context: Context) {

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
                var fitnessModel = FitnessModel()
                if (dataReadResult.buckets.isNotEmpty()) {
                    for (bucket in dataReadResult.buckets) {
                        bucket.dataSets.forEach {
                            for (dp in it.dataPoints) {
                                Log.i(TAG, "Data point:")
                                Log.i(TAG, "\tType: ${dp.dataType.name}")
                                /*  Log.i(TAG, "\tStart: ${dp.getStartTimeString()}")
                                  Log.i(TAG, "\tEnd: ${dp.getEndTimeString()}")*/
                                dp.dataType.fields.forEach {
                                    //get heart rate here
                                    if (dp.dataType.name.equals(GoogleFit.DATA_POINT_HEART) && it.name.equals(
                                            "average",
                                            true
                                        )
                                    ) {
                                        fitnessModel.heartRate = dp.getValue(it).asFloat()
                                    }
                                    if (dp.dataType.name.equals(GoogleFit.DATA_POINT_WEIGHT) && it.name.equals(
                                            "average",
                                            true
                                        )
                                    ) {
                                        fitnessModel.weight = dp.getValue(it).asFloat().toDouble()
                                    }
                                    if (dp.dataType.name.equals(GoogleFit.DATA_POINT_STEP_COUNT) && it.name.equals(
                                            "average",
                                            true
                                        )
                                    ) {
                                        fitnessModel.weight = dp.getValue(it).asFloat().toDouble()
                                    }
                                    Log.i(TAG, "\tField: ${it.name} Value: ${dp.getValue(it)}")
                                }
                            }
                        }
                    }
                    onSuccess.invoke(fitnessModel)
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
                var arrayHeartLogs: Array<HeartRateModel?> = arrayOfNulls(periodDays)
                var arrayWeight: Array<WeightModel?> = arrayOfNulls(periodDays)
                var arrayBloodPressure: Array<BloodPressureModel?> = arrayOfNulls(periodDays)
                var arrayDates= mutableListOf<DateModel>()
                getDatesOfLastDays(periodDays,arrayDates)
                val syncModel = SyncModel(arrayHeartLogs, arrayWeight, arrayBloodPressure,arrayDates)
                if (dataReadResult.buckets.isNotEmpty()) {
                    var dayIndex = 0
                    for (bucket in dataReadResult.buckets) {
                        bucket.dataSets.forEach {
                            for (dp in it.dataPoints) {
                                Log.i(TAG, "Data point:")
                                Log.i(TAG, "\tType: ${dp.dataType.name}")
                                Log.i(TAG, "\tStart: ${dp.getStartTimeString()}")
                                Log.i(TAG, "\tEnd: ${dp.getEndTimeString()}")
                                var date = dp.getStartTimeString()
                                dp.dataType.fields.forEach {
                                    //get heart rate here
                                    if (dp.dataType.name.equals(GoogleFit.DATA_POINT_HEART) && it.name.equals(
                                            "average",
                                            true
                                        )
                                    ) {
                                        arrayHeartLogs.set(
                                            dayIndex,
                                            HeartRateModel(dp.getValue(it).asFloat().toInt())
                                        )
                                    }
                                    if (dp.dataType.name.equals(GoogleFit.DATA_POINT_WEIGHT) && it.name.equals(
                                            "average",
                                            true
                                        )
                                    ) {
                                        arrayWeight.set(
                                            dayIndex,
                                            WeightModel(dp.getValue(it).asFloat().toDouble(),date)
                                        )
                                    }
                                    if (dp.dataType.name.equals(GoogleFit.DATA_POINT_BLOOD_PRESURE) && it.name.equals(
                                            "average",
                                            true
                                        )
                                    ) {
                                        arrayBloodPressure.set(
                                            dayIndex,
                                            BloodPressureModel(dp.getValue(it).asFloat().toDouble())
                                        )
                                    }
                                    Log.i(TAG, "\tField: ${it.name} Value: ${dp.getValue(it)}")
                                }
                            }
                        }
                        dayIndex++
                    }
                    onSuccess.invoke(syncModel)
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
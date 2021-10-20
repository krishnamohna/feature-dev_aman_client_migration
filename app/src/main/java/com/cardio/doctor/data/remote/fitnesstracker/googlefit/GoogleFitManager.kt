package com.cardio.doctor.data.remote.fitnesstracker.googlefit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.ui.common.utils.GoogleFit
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.DataReadRequest
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class GoogleFitManager constructor(val context: Context) {

    private val TAG="GoogleFitManager"
    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_ACTIVITY_SEGMENT, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_HEART_RATE_SUMMARY, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_WEIGHT, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_HEIGHT, FitnessOptions.ACCESS_READ)
            .build()
    }
    private fun getGoogleAccount() = GoogleSignIn.getAccountForExtension(context,fitnessOptions)
    private fun oAuthPermissionsApproved() = GoogleSignIn.hasPermissions(getGoogleAccount(), fitnessOptions)
    enum class FitActionRequestCode {
        READ_DATA,
        UPDATE_AND_READ_DATA,
        DELETE_DATA
    }
    private val dateFormat = DateFormat.getDateInstance()

    fun isLoggedIn(): Boolean {
        return oAuthPermissionsApproved()
    }

    fun login(fragment: Fragment) {
        GoogleSignIn.requestPermissions(
            fragment,
            FitActionRequestCode.READ_DATA.ordinal,
            getGoogleAccount(), fitnessOptions)
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
            .readData(queryFitnessData())
            .addOnSuccessListener { dataReadResult ->
                var fitnessModel=FitnessModel()
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
                                    if(dp.dataType.name.equals(GoogleFit.DATA_POINT_HEART) && it.name.equals("average",true)){
                                        fitnessModel.heartRate=dp.getValue(it).asFloat()
                                    }
                                    if(dp.dataType.name.equals(GoogleFit.DATA_POINT_WEIGHT) && it.name.equals("average",true)){
                                        fitnessModel.weight=dp.getValue(it).asFloat().toDouble()
                                    }
                                    if(dp.dataType.name.equals(GoogleFit.DATA_POINT_STEP_COUNT) && it.name.equals("average",true)){
                                        fitnessModel.weight=dp.getValue(it).asFloat().toDouble()
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
                    } }
                }else{
                    onFailure.invoke("No Data Found")
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "There was a problem reading the data.", e)
                onFailure.invoke(e.message)
            }
    }

    fun logout(activity: Activity) {
        TODO("Not yet implemented")
    }

    private fun queryFitnessData(): DataReadRequest {
        // [START build_read_data_request]
        // Setting a start and end date using a range of 1 week before this moment.
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val now = Date()
        calendar.time = now
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.WEEK_OF_YEAR, -1)
        val startTime = calendar.timeInMillis
        Log.i(TAG, "Range Start: ${dateFormat.format(startTime)}")
        Log.i(TAG, "Range End: ${dateFormat.format(endTime)}")
        return DataReadRequest.Builder()
            .aggregate(DataType.TYPE_HEART_RATE_BPM, DataType.AGGREGATE_HEART_RATE_SUMMARY)
            .aggregate(DataType.TYPE_WEIGHT, DataType.AGGREGATE_WEIGHT_SUMMARY)
            .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.TYPE_STEP_COUNT_DELTA)
            .bucketByTime(1, TimeUnit.DAYS)
            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
            .build()
    }

}
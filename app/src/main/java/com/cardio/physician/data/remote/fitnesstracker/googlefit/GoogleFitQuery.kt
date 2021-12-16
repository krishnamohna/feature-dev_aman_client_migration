package com.cardio.physician.data.remote.fitnesstracker.googlefit

import android.util.Log
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.HealthDataTypes
import com.google.android.gms.fitness.request.DataReadRequest
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

val TAG: String="GoogleFitQuery"

fun queryProfileFitnessData(): DataReadRequest {
     val dateFormat = DateFormat.getDateInstance()
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
        .aggregate(HealthDataTypes.TYPE_BLOOD_PRESSURE, HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY)
        .bucketByTime(1, TimeUnit.DAYS)
        .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
        .build()
}

fun queryProfileLogsFitnessData(periodDays: Int): DataReadRequest {
    val dateFormat = DateFormat.getDateInstance()
    val calendar = Calendar.getInstance(/*TimeZone.getTimeZone("UTC")*/)
    val now = Date()
    calendar.time = now
    val endTime = calendar.timeInMillis
    calendar.add(Calendar.DAY_OF_MONTH, -periodDays)
    val startTime = calendar.timeInMillis
    Log.i(TAG, "Range Start: ${dateFormat.format(startTime)}")
    Log.i(TAG, "Range End: ${dateFormat.format(endTime)}")
    return DataReadRequest.Builder()
        .aggregate(DataType.TYPE_HEART_RATE_BPM, DataType.AGGREGATE_HEART_RATE_SUMMARY)
        .aggregate(DataType.TYPE_WEIGHT, DataType.AGGREGATE_WEIGHT_SUMMARY)
        .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.TYPE_STEP_COUNT_DELTA)
        .aggregate(HealthDataTypes.TYPE_BLOOD_PRESSURE, HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY)
        .bucketByTime(1, TimeUnit.DAYS)
        .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
        .build()
}


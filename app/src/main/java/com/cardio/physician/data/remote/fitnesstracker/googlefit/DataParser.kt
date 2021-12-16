package com.cardio.physician.data.remote.fitnesstracker.googlefit

import android.util.Log
import com.cardio.physician.domain.fitness.model.*
import com.cardio.physician.ui.common.utils.GoogleFit
import com.cardio.physician.ui.common.utils.getDatesOfLastDays
import com.cardio.physician.ui.common.utils.getEndTimeString
import com.cardio.physician.ui.common.utils.getStartTimeString
import com.google.android.gms.fitness.data.Bucket

class DataParser {

    fun parseLogsBucket(buckets: List<Bucket>, periodDays: Int): SyncModel {
        var arrayHeartLogs: Array<HeartRateModel?> = arrayOfNulls(periodDays)
        var arrayStepCounts: Array<StepCountModel?> = arrayOfNulls(periodDays)
        var arrayWeight: Array<WeightModel?> = arrayOfNulls(periodDays)
        var arrayBloodPressure: Array<BloodPressureModel?> = arrayOfNulls(periodDays)
        var arrayDates = mutableListOf<DateModel>()
        getDatesOfLastDays(periodDays, arrayDates)
        val syncModel = SyncModel(arrayHeartLogs, arrayWeight, arrayBloodPressure, arrayDates,arrayStepCounts)
        var dayIndex = 0
        for (bucket in buckets) {
            bucket.dataSets.forEach { it ->
                for (dp in it.dataPoints) {
                    Log.i(TAG, "Data point:")
                    Log.i(TAG, "\tType: ${dp.dataType.name}")
                    Log.i(TAG, "\tStart: ${dp.getStartTimeString()}")
                    Log.i(TAG, "\tEnd: ${dp.getEndTimeString()}")
                    val date = dp.getStartTimeString()
                    dp.dataType.fields.forEach {
                        //get heart rate here
                        if (dp.dataType.name.equals(GoogleFit.DATA_TYPE_STEP_COUNT) && it.name.equals(
                                GoogleFit.DATA_POINT_STEPS,
                                true)
                        ) {
                            arrayStepCounts[dayIndex] =
                                StepCountModel(dp.getValue(it).toString())
                        }
                        //get heart rate here
                        if (dp.dataType.name.equals(GoogleFit.DATA_TYPE_HEART) && it.name.equals(
                                GoogleFit.DATA_POINT_TYPE_AVERAGE,
                                true)
                        ) {
                            arrayHeartLogs[dayIndex] =
                                HeartRateModel(dp.getValue(it).asFloat().toString())
                        }
                        //get weight here
                        if (dp.dataType.name.equals(GoogleFit.DATA_TYPE_WEIGHT) && it.name.equals(
                                GoogleFit.DATA_POINT_TYPE_AVERAGE,
                                true
                            )
                        ) {
                            arrayWeight[dayIndex] =
                                WeightModel(dp.getValue(it).asFloat().toString(), date)
                        }
                        //get systolic blood pressure here
                        if (dp.dataType.name.equals(GoogleFit.DATA_TYPE_BLOOD_PRESURE) && it.name.equals(
                                GoogleFit.DATA_POINT_FIELD_SYSTOLIC_AVERAGE,
                                true
                            )
                        ) {
                            arrayBloodPressure[dayIndex] =
                                arrayBloodPressure[dayIndex] ?: BloodPressureModel()
                            arrayBloodPressure[dayIndex]?.topBp =
                                dp.getValue(it).asFloat().toString()
                        }
                        //get diastolic blood pressure here
                        if (dp.dataType.name.equals(GoogleFit.DATA_TYPE_BLOOD_PRESURE) && it.name.equals(
                                GoogleFit.DATA_POINT_FIELD_DIASTOLIC_AVERAGE,
                                true
                            )
                        ) {
                            arrayBloodPressure[dayIndex] =
                                arrayBloodPressure[dayIndex] ?: BloodPressureModel()
                            arrayBloodPressure[dayIndex]?.bottomBp =
                                dp.getValue(it).asFloat().toString()
                        }
                        Log.i(TAG, "\tField: ${it.name} Value: ${dp.getValue(it)}")
                    }
                }
            }
            dayIndex++
        }
        return syncModel
    }

    fun parseSingleData(listBuckets: MutableList<Bucket>): FitnessModel {
        val syncModel = parseLogsBucket(listBuckets, 7)
        val fitnessModel = FitnessModel()
        fitnessModel.weight = (syncModel.arrayWeightLogs.findLast {
            it != null
        })?.weight
        fitnessModel.heartRate = (syncModel.arrayHeartLogs.findLast {
            it != null
        })?.restHeartRate
        fitnessModel.bloodPressureTopBp = (syncModel.arrayBloodPresure.findLast {
            it != null
        })?.topBp
        fitnessModel.bloodPressureBottomBp = (syncModel.arrayBloodPresure.findLast {
            it != null
        })?.bottomBp
        return fitnessModel
    }

}
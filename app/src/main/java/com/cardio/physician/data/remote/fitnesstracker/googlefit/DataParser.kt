package com.cardio.physician.data.remote.fitnesstracker.googlefit

import android.util.Log
import com.cardio.physician.domain.fitness.model.*
import com.cardio.physician.ui.common.utils.GoogleFit
import com.cardio.physician.ui.common.utils.getDatesOfLastDays
import com.cardio.physician.ui.common.utils.getEndTimeString
import com.cardio.physician.ui.common.utils.getStartTimeString
import com.google.android.gms.fitness.data.Bucket
import com.google.android.gms.fitness.result.DataReadResponse

class DataParser {

    fun parseLogsBucket(buckets: List<Bucket>, periodDays: Int): SyncModel {
        var arrayHeartLogs: Array<HeartRateModel?> = arrayOfNulls(periodDays)
        var arrayWeight: Array<WeightModel?> = arrayOfNulls(periodDays)
        var arrayBloodPressure: Array<BloodPressureModel?> = arrayOfNulls(periodDays)
        var arrayDates = mutableListOf<DateModel>()
        getDatesOfLastDays(periodDays, arrayDates)
        val syncModel = SyncModel(arrayHeartLogs, arrayWeight, arrayBloodPressure, arrayDates)
        var dayIndex = 0
        for (bucket in buckets) {
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
                                GoogleFit.DATA_POINT_TYPE_AVERAGE,
                                true
                            )
                        ) {
                            arrayHeartLogs.set(
                                dayIndex,
                                HeartRateModel(dp.getValue(it).asFloat().toInt())
                            )
                        }
                        if (dp.dataType.name.equals(GoogleFit.DATA_POINT_WEIGHT) && it.name.equals(
                                GoogleFit.DATA_POINT_TYPE_AVERAGE,
                                true
                            )
                        ) {
                            arrayWeight.set(
                                dayIndex,
                                WeightModel(dp.getValue(it).asFloat().toDouble(), date)
                            )
                        }
                        if (dp.dataType.name.equals(GoogleFit.DATA_POINT_BLOOD_PRESURE) && it.name.equals(
                                GoogleFit.DATA_POINT_FIELD_SYSTOLIC_AVERAGE,
                                true
                            )
                        ) {
                            arrayBloodPressure.set(dayIndex,arrayBloodPressure.get(dayIndex)?: BloodPressureModel())
                            arrayBloodPressure.get(dayIndex)?.topBp=dp.getValue(it).asFloat().toDouble()
                        }
                        if (dp.dataType.name.equals(GoogleFit.DATA_POINT_BLOOD_PRESURE) && it.name.equals(
                                GoogleFit.DATA_POINT_FIELD_DIASTOLIC_AVERAGE,
                                true
                            )
                        ) {
                            arrayBloodPressure.set(dayIndex,arrayBloodPressure.get(dayIndex)?: BloodPressureModel())
                            arrayBloodPressure.get(dayIndex)?.bottomBp=dp.getValue(it).asFloat().toDouble()
                        }
                        Log.i(TAG, "\tField: ${it.name} Value: ${dp.getValue(it)}")
                    }
                }
            }
            dayIndex++
        }
        return syncModel
    }

    fun parseSingleData(dataReadResult: DataReadResponse): FitnessModel {
        var fitnessModel = FitnessModel()
        for (bucket in dataReadResult.buckets) {
            bucket.dataSets.forEach {
                for (dp in it.dataPoints) {
                    Log.i(TAG, "Data point:")
                    Log.i(TAG, "\tType: ${dp.dataType.name}")
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
        return fitnessModel
    }

}
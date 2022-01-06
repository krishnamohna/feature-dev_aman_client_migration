package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders

import android.content.Context
import android.os.Handler
import android.util.Log
import com.cardio.physician.domain.fitness.model.*
import com.cardio.physician.ui.common.utils.getDatesOfLastDays
import java.lang.Exception
import java.util.concurrent.ExecutorService

class CompositeHealthLogsLoader(
    val activity: Context,
    val onSuccess: (SyncModel) -> Unit,
    val onFailure: (msg: Exception) -> Unit,
    val periodDays: Int,
    private val executeService: ExecutorService,
    val handler: Handler
) {

    fun load() {
        val arrayStepCounts: Array<StepCountModel?> = arrayOfNulls(periodDays)
        val arrayHeartLogs: Array<HeartRateModel?> = arrayOfNulls(periodDays)
        val arrayWeight: Array<WeightModel?> = arrayOfNulls(periodDays)
        val arrayBloodPressure: Array<BloodPressureModel?> = arrayOfNulls(periodDays)
        val arrayDates = mutableListOf<DateModel>()
        getDatesOfLastDays(periodDays, arrayDates)
        val syncModel = SyncModel(arrayHeartLogs, arrayWeight, arrayBloodPressure, arrayDates,arrayStepCounts)
        executeService.submit {
            //load weight
            WeightLogLoader({
                updateWeightLogsAsPerDate(syncModel, it)
            }, {
                Log.i("", "")
            }, periodDays).load()
            //load heart rate
            HeartRateLogsLoader({
                updateHeartLogsAsPerDate(syncModel, it)
            }, {
                Log.i("", "")
            }, periodDays).load()
            //load step counts
            StepCountLogsLoader({
                updateStepCountLogsAsPerDate(syncModel, it)
            }, {
                Log.i("", "")
            }, periodDays).load()
            onSuccess.invoke(syncModel)
        }
    }

    private fun updateStepCountLogsAsPerDate(syncModel: SyncModel, stepCounts: List<StepCountModel>) {
        syncModel.arrayDates.forEachIndexed { index, dateModel ->
            val stepCount =stepCounts.find { it.dateTime== dateModel.date }
            syncModel.arrayStepCounts.set(index,stepCount)
        }
    }

    private fun updateHeartLogsAsPerDate(syncModel: SyncModel, heartLogs: List<HeartRateModel>) {
        syncModel.arrayDates.forEachIndexed { index, dateModel ->
            val heartLog =heartLogs.find { it.date==dateModel.date}
            syncModel.arrayHeartLogs.set(index,heartLog)
        }
    }

    private fun updateWeightLogsAsPerDate(syncModel: SyncModel, weightLogs: List<WeightModel>) {
        syncModel.arrayDates.forEachIndexed { index, dateModel ->
            val weightLog =weightLogs.find { it.date==dateModel.date}
            syncModel.arrayWeightLogs.set(index,weightLog)
        }
    }

}
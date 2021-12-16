package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders

import android.content.Context
import android.os.Handler
import android.util.Log
import com.cardio.physician.domain.fitness.model.*
import com.cardio.physician.ui.common.utils.getDatesOfLastDays
import java.util.concurrent.ExecutorService

class CompositeHealthLogsLoader(
    val activity: Context,
    val onSuccess: (SyncModel) -> Unit,
    val onFailure: (msg: String?) -> Unit,
    val periodDays: Int,
    val executerService: ExecutorService,
    val handler: Handler
) {

    fun load() {
        var arrayStepCounts: Array<StepCountModel?> = arrayOfNulls(periodDays)
        var arrayHeartLogs: Array<HeartRateModel?> = arrayOfNulls(periodDays)
        var arrayWeight: Array<WeightModel?> = arrayOfNulls(periodDays)
        var arrayBloodPressure: Array<BloodPressureModel?> = arrayOfNulls(periodDays)
        var arrayDates = mutableListOf<DateModel>()
        getDatesOfLastDays(periodDays, arrayDates)
        val syncModel = SyncModel(arrayHeartLogs, arrayWeight, arrayBloodPressure, arrayDates,arrayStepCounts)
        executerService.submit {
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
        syncModel.arrayDates.forEachIndexed() { index, dateModel ->
            val stepCount =stepCounts.find { it.dateTime==dateModel?.date}
            syncModel.arrayStepCounts.set(index,stepCount)
        }
    }

    private fun updateHeartLogsAsPerDate(syncModel: SyncModel, heartLogs: List<HeartRateModel>) {
        syncModel.arrayDates.forEachIndexed() { index, dateModel ->
            val heartLog =heartLogs.find { it.date==dateModel.date}
            syncModel.arrayHeartLogs.set(index,heartLog)
        }
    }

    private fun updateWeightLogsAsPerDate(syncModel: SyncModel, weightLogs: List<WeightModel>) {
        syncModel.arrayDates.forEachIndexed() { index, dateModel ->
            val weightLog =weightLogs.find { it.date==dateModel.date}
            syncModel.arrayWeightLogs.set(index,weightLog)
        }
    }

}
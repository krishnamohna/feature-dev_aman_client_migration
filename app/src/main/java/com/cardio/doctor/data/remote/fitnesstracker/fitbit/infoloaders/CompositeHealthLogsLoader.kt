package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import android.content.Context
import android.os.Handler
import android.util.Log
import com.cardio.doctor.domain.fitness.model.*
import com.cardio.doctor.ui.common.utils.getDatesOfLastDays
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
        var arrayHeartLogs: Array<HeartRateModel?> = arrayOfNulls(periodDays)
        var arrayWeight: Array<WeightModel?> = arrayOfNulls(periodDays)
        var arrayBloodPressure: Array<BloodPressureModel?> = arrayOfNulls(periodDays)
        var arrayDates = mutableListOf<DateModel>()
        getDatesOfLastDays(periodDays, arrayDates)
        val syncModel = SyncModel(arrayHeartLogs, arrayWeight, arrayBloodPressure, arrayDates)
        executerService.submit {
            //load weight
            WeightLogLoader({
                updateWeightLogsAsPerDate(syncModel, it)
                Log.i("", "")
            }, {
                Log.i("", "")
            }, periodDays).load()
            //load heart rate
            HeartRateLogLoader({
                updateHeartLogsAsPerDate(syncModel, it)
                Log.i("", "")
            }, {
                Log.i("", "")
            }, periodDays).load()
            onSuccess.invoke(syncModel)
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
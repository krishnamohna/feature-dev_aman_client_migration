package com.cardio.doctor.ui.service

import android.app.Service
import android.util.Log
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.SyncModel
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import com.cardio.doctor.ui.common.utils.getDaysDiffrence
import com.cardio.doctor.ui.common.utils.showToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SyncHealthServiceFacade @Inject constructor(
    val service: Service,
    val fitnessRepositary: FitnessRepositary,
    val syncHealthRepositary: SyncHealthRepositary
) {

    private val DEFALT_PREVIOUS_DAY_PERIOD = 3


    fun onCreate() {

    }

    fun onDestroy() {

    }

    fun syncData() {
        //get last collection from firebase first
        loadLastCollection()
    }

    private fun loadLastCollection() {
        GlobalScope.launch {
            var lastCollection = syncHealthRepositary.getLastSavedCollectionDate()
            getHealthLogsFromWearable(lastCollection)
        }
    }

    private fun calculateDayDiff(fitnessModel: FitnessModel?): Int {
        if (fitnessModel?.date == null) {
            return DEFALT_PREVIOUS_DAY_PERIOD
        } else {
            return getDaysDiffrence(fitnessModel.date)
        }
    }

    private fun getHealthLogsFromWearable(fitnessModel: FitnessModel?) {
        if (!isTodayDataAlreadySynched(calculateDayDiff(fitnessModel))) {
            fitnessRepositary.getSyncModel(
                service,
                {
                    saveToCollection(it, fitnessModel ?: FitnessModel())
                },
                {
                    it?.let { showToast(service, it) }
                },
                calculateDayDiff(fitnessModel)
            )
        }
    }

    private fun isTodayDataAlreadySynched(calculateDayDiff: Int) = calculateDayDiff == 0

    private fun saveToCollection(syncModel: SyncModel, fitnessModel: FitnessModel) {
        GlobalScope.launch {
            syncModel.arrayDates.forEachIndexed { i, value ->
                fitnessModel.weight =
                    syncModel.arrayWeightLogs.get(i)?.weight?: fitnessModel.weight
                fitnessModel.date = syncModel.arrayDates.get(i)?.date
                fitnessModel.timeStamp = syncModel.arrayDates.get(i)?.timeStamp
                fitnessModel.heartRate = syncModel.arrayHeartLogs.get(i)?.restHeartRate?.toFloat()
                    ?: fitnessModel.heartRate
                fitnessModel.bloodPressure =
                    syncModel.arrayBloodPresure.get(i)?.bp ?: fitnessModel.bloodPressure
                try {
                    syncHealthRepositary.saveHealthData(fitnessModel)
                } catch (exp: Exception) {
                    Log.i("", "")
                }
            }
        }
    }
}
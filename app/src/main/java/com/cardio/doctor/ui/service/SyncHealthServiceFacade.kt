package com.cardio.doctor.ui.service

import android.app.Service
import android.util.Log
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.SyncModel
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SyncHealthServiceFacade @Inject constructor(
    val service: Service,
    val fitnessRepositary: FitnessRepositary,
    val syncHealthRepositary: SyncHealthRepositary
) {

    fun onCreate() {

    }

    fun onDestroy() {

    }

    fun syncData() {
        //get last collection from firebase first
      //  loadLastCollection()
    }

    private fun loadLastCollection() {
        GlobalScope.launch {
            var lastCollection = syncHealthRepositary.getLastSavedCollectionDate()
            getHealthLogsFromWearable(lastCollection)
        }
    }

    private fun calculateDayDiff(fitnessModel: FitnessModel): Int {
        return 5
    }

    private fun getHealthLogsFromWearable(fitnessModel: FitnessModel) {
        fitnessRepositary.getSyncModel(
            service,
            {
                saveToCollection(it,fitnessModel)
            },
            {
                Log.i("", "")
            },
            calculateDayDiff(fitnessModel)
        )
    }

    private fun saveToCollection(syncModel: SyncModel, fitnessModel: FitnessModel) {
        GlobalScope.launch {
            syncModel.listWeightLogs.forEachIndexed{i,value->
                fitnessModel.weight=syncModel.listWeightLogs.get(i)?.weight?:fitnessModel.weight
                fitnessModel.heartRate=syncModel.listHeartLogs.get(i)?.restHeartRate?.toFloat()?:fitnessModel.heartRate
                fitnessModel.bloodPressure=syncModel.listBloodPresure.get(i)?.bp?:fitnessModel.bloodPressure
                syncHealthRepositary.saveHealthData(fitnessModel)
            }
        }
    }
}
package com.cardio.physician.ui.service

import android.app.Service
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.SyncModel
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import com.cardio.physician.ui.common.utils.getDaysDiffrence
import com.cardio.physician.ui.common.utils.showToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SyncHealthServiceFacade @Inject constructor(
    val service: Service,
    private val fitnessRepositary: FitnessRepositary,
    private val syncHealthRepositary: SyncHealthRepositary
) {

    private val DEFALT_PREVIOUS_DAY_PERIOD = 1
    private val _syncSingleLiveData = MutableLiveData<Resource<Boolean>>()

    fun getSyncData():LiveData<Resource<Boolean>>{
        return _syncSingleLiveData
    }

    fun syncData() {
        if (fitnessRepositary.isLoggedIn())
            loadLastCollection()
    }
    /*load last collection from firestore to get last saved collection*/
    private fun loadLastCollection() {
        _syncSingleLiveData.setLoading()
        GlobalScope.launch {
            val lastCollection = syncHealthRepositary.getLastSavedCollectionDate()
            getHealthLogsFromWearable(lastCollection)
        }
    }

    private fun calculateDayDiff(fitnessModel: FitnessModel?): Int {
        return if (fitnessModel?.date == null) {
            DEFALT_PREVIOUS_DAY_PERIOD
        } else {
            getDaysDiffrence(fitnessModel.date)
        }
    }
    /*get health logs from selected wearable for given period of time*/
    private fun getHealthLogsFromWearable(fitnessModel: FitnessModel?) {
        if (!isTodayDataAlreadySynched(calculateDayDiff(fitnessModel))) {
            fitnessRepositary.getSyncModel(
                service,
                {
                    saveToCollection(it, fitnessModel ?: FitnessModel())
                },
                {error->
                    error?.let { showToast(service, error) }
                    _syncSingleLiveData.setError(error)
                },
                calculateDayDiff(fitnessModel)
            )
        }else{
            _syncSingleLiveData.setSuccess(true)
        }
    }

    private fun isTodayDataAlreadySynched(calculateDayDiff: Int) = calculateDayDiff == 0

    /*save health logs to server */
    private fun saveToCollection(syncModel: SyncModel, fitnessModel: FitnessModel) {
        GlobalScope.launch {
            syncModel.arrayDates.forEachIndexed { i, dateModel ->
                fitnessModel.weight =
                    syncModel.arrayWeightLogs[i]?.weight ?: fitnessModel.weight
                fitnessModel.date = dateModel.date
                fitnessModel.timeStamp = syncModel.arrayDates[i].timeStamp
                fitnessModel.heartRate = syncModel.arrayHeartLogs[i]?.restHeartRate
                    ?: fitnessModel.heartRate
                fitnessModel.bloodPressureTopBp =
                    syncModel.arrayBloodPresure[i]?.topBp ?: fitnessModel.bloodPressureTopBp
                fitnessModel.bloodPressureBottomBp = syncModel.arrayBloodPresure[i]?.bottomBp
                    ?: fitnessModel.bloodPressureBottomBp
                try {
                  //  syncHealthRepositary.saveHealthData(fitnessModel)
                    _syncSingleLiveData.setSuccess(true)
                    service.stopSelf()
                } catch (exp: Exception) {
                    _syncSingleLiveData.setError(exp)
                }
            }
        }
    }
}
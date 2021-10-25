package com.cardio.doctor.ui.service

import android.app.Service
import androidx.lifecycle.LiveData
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.SyncModel
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.utils.extentions.setError
import com.cardio.doctor.ui.common.utils.extentions.setLoading
import com.cardio.doctor.ui.common.utils.extentions.setSuccess
import com.cardio.doctor.ui.common.utils.getDaysDiffrence
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import com.cardio.doctor.ui.common.utils.showToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SyncHealthServiceFacade @Inject constructor(
    val service: Service,
    val fitnessRepositary: FitnessRepositary,
    val syncHealthRepositary: SyncHealthRepositary
) {

    private val DEFALT_PREVIOUS_DAY_PERIOD = 7
    private val _syncSingleLiveData = SingleLiveEvent<Resource<Boolean>>()
    val syncLiveData: LiveData<Resource<Boolean>> =
        _syncSingleLiveData

    fun onCreate() {

    }

    fun onDestroy() {

    }

    fun getSyncData()=syncLiveData

    fun syncData() {
        if (fitnessRepositary.isLoggedIn())
            loadLastCollection()
    }

    private fun loadLastCollection() {
        _syncSingleLiveData.setLoading()
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

    private fun saveToCollection(syncModel: SyncModel, fitnessModel: FitnessModel) {
        GlobalScope.launch {
            syncModel.arrayDates.forEachIndexed { i, value ->
                fitnessModel.weight =
                    syncModel.arrayWeightLogs.get(i)?.weight ?: fitnessModel.weight
                fitnessModel.date = syncModel.arrayDates.get(i)?.date
                fitnessModel.timeStamp = syncModel.arrayDates.get(i)?.timeStamp
                fitnessModel.heartRate = syncModel.arrayHeartLogs.get(i)?.restHeartRate?.toFloat()
                    ?: fitnessModel.heartRate
                fitnessModel.bloodPressureTopBp =
                    syncModel.arrayBloodPresure.get(i)?.topBp ?: fitnessModel.bloodPressureTopBp
                fitnessModel.bloodPressureBottomBp = syncModel.arrayBloodPresure.get(i)?.bottomBp
                    ?: fitnessModel.bloodPressureBottomBp
                try {
                    syncHealthRepositary.saveHealthData(fitnessModel)
                    _syncSingleLiveData.setSuccess(true)
                } catch (exp: Exception) {
                    _syncSingleLiveData.setError(exp)
                }
            }
        }
    }
}
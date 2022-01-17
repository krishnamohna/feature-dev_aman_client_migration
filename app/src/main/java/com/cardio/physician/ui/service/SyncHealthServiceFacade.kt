package com.cardio.physician.ui.service

import android.app.Service
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.domain.fitness.model.DateModel
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.SyncModel
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.NetworkError
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import com.cardio.physician.ui.common.utils.getDaysDifference
import com.cardio.physician.ui.common.utils.getDefaultDateFormatter
import com.cardio.physician.ui.common.utils.showToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class SyncHealthServiceFacade @Inject constructor(
    val service: Service,
    private val fitnessRepositary: FitnessRepositary,
    private val syncHealthRepositary: SyncHealthRepositary,
) {

    private val DEFALT_PREVIOUS_DAY_PERIOD = 30
    private val _syncSingleLiveData = MutableLiveData<Resource<Boolean>>()

    fun getSyncData(): LiveData<Resource<Boolean>> {
        return _syncSingleLiveData
    }

    fun syncData() {
        if (fitnessRepositary.isLoggedIn() && fitnessRepositary.isSelected())
            loadLastCollection()
    }

    /*load last collection from firestore to get last saved collection*/
    private fun loadLastCollection() {
        _syncSingleLiveData.setLoading()
        GlobalScope.launch {
            val lastCollection = syncHealthRepositary.getLastSavedHealthLogCollectionDate()
            getHealthLogsFromWearable(lastCollection)
        }
    }

    private fun calculateDayDiff(fitnessModel: FitnessModel?): Int {
        return if (fitnessModel?.date == null) {
            DEFALT_PREVIOUS_DAY_PERIOD
        } else {
            getDaysDifference(fitnessModel.date)
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
                { error ->
//                    error?.let { showToast(service, error) }
                    _syncSingleLiveData.setError(error)
                },
                calculateDayDiff(fitnessModel)
            )
        } else {
            _syncSingleLiveData.setError(NetworkError.noRecordFound())
        }
    }

    private fun isTodayDataAlreadySynched(calculateDayDiff: Int) =
        calculateDayDiff == 0 || calculateDayDiff == 1

    /*save health logs to server */
    private fun saveToCollection(syncModel: SyncModel, fitnessModel: FitnessModel) {
        GlobalScope.launch {
            try {
                for (i in syncModel.arrayDates.indices) {
                    //we should not sync data for today as user may not have updated latest values yet
                    if (isTodayDate(syncModel.arrayDates[i]))
                        continue
                    fitnessModel.weight =
                        syncModel.arrayWeightLogs[i]?.weight
                    fitnessModel.date = syncModel.arrayDates[i].date
                    fitnessModel.timeStamp = syncModel.arrayDates[i].timeStamp
                    fitnessModel.heartRate = syncModel.arrayHeartLogs[i]?.restHeartRate
                    fitnessModel.bloodPressureTopBp = syncModel.arrayBloodPresure[i]?.topBp
                    fitnessModel.bloodPressureBottomBp = syncModel.arrayBloodPresure[i]?.bottomBp
                    fitnessModel.stepCount = syncModel.arrayStepCounts[i]?.stepCount
                    syncHealthRepositary.saveHealthData(fitnessModel)
                }
                _syncSingleLiveData.setSuccess(true)
                service.stopSelf()
            } catch (exp: Exception) {
                _syncSingleLiveData.setError(exp)
            }
        }
    }

    private fun isTodayDate(dateModel: DateModel): Boolean {
        var now = Date()
        now.time = Calendar.getInstance().timeInMillis
        var dateToday = getDefaultDateFormatter().format(now)
        return dateModel.date.equals(dateToday, true)
    }
}
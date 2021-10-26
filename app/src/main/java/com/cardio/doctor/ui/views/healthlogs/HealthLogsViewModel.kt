package com.cardio.doctor.ui.views.healthlogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.extentions.setError
import com.cardio.doctor.ui.common.utils.extentions.setLoading
import com.cardio.doctor.ui.common.utils.extentions.setSuccess
import com.cardio.doctor.ui.common.utils.validation.Validater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HealthLogsViewModel @Inject constructor(
    val validater: Validater,
    val syncHealthRepositary: SyncHealthRepositary
) : BaseViewModel() {

    var updateLogLiveData = MutableLiveData<Resource<Boolean>>()

    fun getUpdateLiveData(): LiveData<Resource<Boolean>> {
        return updateLogLiveData
    }

    fun checkValidation(
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        selectedDate: Date?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        if (selectedDate == null) {
            onFailure.invoke("Please select date first !!")
            return
        }
        if (weight.isEmpty() && heartRate.isEmpty() && topBp.isEmpty() && bottomBp.isEmpty()) {
            onFailure.invoke("Please fill any field first !!")
            return
        }
        onSuccess.invoke()
    }

    fun updateData(
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        selectedDate: String,
        timeStamp: Long?
    ) {
        var fitnessModel = FitnessModel()
        if (weight.isNotEmpty()) fitnessModel.weight = weight.toDouble()
        if (heartRate.isNotEmpty()) fitnessModel.heartRate = heartRate.toFloat()
        if (topBp.isNotEmpty()) fitnessModel.bloodPressureTopBp = topBp.toDouble()
        if (bottomBp.isNotEmpty()) fitnessModel.bloodPressureBottomBp = bottomBp.toDouble()
        if (selectedDate.isNotEmpty()) fitnessModel.date = selectedDate
        fitnessModel.timeStamp = timeStamp
        viewModelScope.launch {
            try {
                updateLogLiveData.setLoading()
                syncHealthRepositary.updateHealthLogByDate(fitnessModel)
                updateLogLiveData.setSuccess(true)
            } catch (exp: Exception) {
                updateLogLiveData.setError(exp)
            }
        }
    }

}
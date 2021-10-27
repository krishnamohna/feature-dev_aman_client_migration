package com.cardio.doctor.ui.views.healthlogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.domain.common.repository.UserAuthRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.extentions.setError
import com.cardio.doctor.ui.common.utils.extentions.setLoading
import com.cardio.doctor.ui.common.utils.extentions.setSuccess
import com.cardio.doctor.ui.common.utils.validation.FieldType
import com.cardio.doctor.ui.common.utils.validation.Validater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HealthLogsViewModel @Inject constructor(
    val validater: Validater,
    val syncHealthRepositary: SyncHealthRepositary,
    val userAuthRepositary: UserAuthRepositary
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
        onFailure: (String, ValidationModelV2) -> Unit,
        onFailure2: (ValidationModelV2) -> Unit
    ) {
        if (selectedDate == null) {
            onFailure2.invoke(
                ValidationModelV2(
                    Status.ERROR,
                    "Please select date.",
                    FieldType.LOG_DATE
                )
            )
            return
        }
        if (weight.isEmpty() && heartRate.isEmpty() && topBp.isEmpty() && bottomBp.isEmpty()) {
            onFailure.invoke(
                "Please fill any field first !!",
                ValidationModelV2(Status.SUCCESS, "", FieldType.LOG_DATE)
            )
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

    fun getUserSignedUpDate(): Long? {
        return userAuthRepositary.getUserCreatedTime()
    }
}
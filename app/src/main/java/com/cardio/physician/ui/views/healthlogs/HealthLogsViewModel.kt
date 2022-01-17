package com.cardio.physician.ui.views.healthlogs

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.domain.common.model.validation.ValidationModelV2
import com.cardio.physician.domain.common.repository.UserAuthRepositary
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.network.Status
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import com.cardio.physician.ui.common.utils.textwatcher.alphaVisibility.AlphaVisibilityHelper
import com.cardio.physician.ui.common.utils.validation.FieldType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HealthLogsViewModel @Inject constructor(
    val syncHealthRepositary: SyncHealthRepositary,
    val userAuthRepositary: UserAuthRepositary,
) : BaseViewModel() {

    var updateLogLiveData = MutableLiveData<Resource<Boolean>>()
    val alphaVisibilityHelper = AlphaVisibilityHelper()

    fun getUpdateLiveData(): LiveData<Resource<Boolean>> {
        return updateLogLiveData
    }
    var mutableLiveDataHealthLogs = MutableLiveData<Resource<FitnessModel>>()

    fun getHealthlogsLiveData(): LiveData<Resource<FitnessModel>> {
        return mutableLiveDataHealthLogs
    }


    fun checkValidation(
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        stepCount: String,
        selectedDate: Date?,
        onSuccess: () -> Unit,
        onFailure: (String, ValidationModelV2) -> Unit,
        onFailure2: (ValidationModelV2) -> Unit,
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
        if (weight.isEmpty() && heartRate.isEmpty() && topBp.isEmpty() && bottomBp.isEmpty() && stepCount.isBlank()) {
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
        timeStamp: Long?,
        stepCount: String,
        userId:String?
    ) {
        var fitnessModel = FitnessModel()
        if (weight.isNotEmpty()) fitnessModel.weight = weight
        if (heartRate.isNotEmpty()) fitnessModel.heartRate = heartRate
        if (topBp.isNotEmpty()) fitnessModel.bloodPressureTopBp = topBp
        if (bottomBp.isNotEmpty()) fitnessModel.bloodPressureBottomBp = bottomBp
        if (stepCount.isNotEmpty()) fitnessModel.stepCount = stepCount
        if (selectedDate.isNotEmpty()) fitnessModel.date = selectedDate
        fitnessModel.timeStamp = timeStamp
        viewModelScope.launch {
            try {
                updateLogLiveData.setLoading()
                syncHealthRepositary.updateHealthLogByDate(fitnessModel, userId)
                updateLogLiveData.setSuccess(true)
            } catch (exp: Exception) {
                updateLogLiveData.setError(exp)
            }
        }
    }

    fun getUserSignedUpDate(): Long? {
        return userAuthRepositary.getUserCreatedTime()
    }

    fun addTextChangeListener(
        list: List<EditText>,
        onTextChanged: () -> Unit,
    ) {
        alphaVisibilityHelper.addEditTextWatcher(list, onTextChanged)
    }
    fun getHealthLogsByDate(date: String, userId: String?) {
        viewModelScope.launch {
            try {
                mutableLiveDataHealthLogs.setLoading()
                mutableLiveDataHealthLogs.setSuccess(syncHealthRepositary.getHealthLogByDate(date, userId))
            } catch (exp: Exception) {
                mutableLiveDataHealthLogs.setError(exp)
            }
        }
    }
}
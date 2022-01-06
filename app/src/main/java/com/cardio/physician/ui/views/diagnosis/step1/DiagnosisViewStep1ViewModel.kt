package com.cardio.physician.ui.views.diagnosis.step1

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.data.remote.profile.UserProfileRepository
import com.cardio.physician.di.DEFAULT_ALPHA_VALIDATOR
import com.cardio.physician.di.DEFAULT_VALIDATOR
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.common.model.validation.ValidationModelV2
import com.cardio.physician.domain.connection.ConnectionRepo
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.notifications.NotificationRepo
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.network.api.Constants
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import com.cardio.physician.ui.common.utils.textwatcher.alphaVisibility.AlphaVisibilityHelper
import com.cardio.physician.ui.common.utils.validation.Validater
import com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DiagnosisViewStep1ViewModel @Inject constructor(
    @Named(DEFAULT_VALIDATOR) val validater: Validater,
    @Named(DEFAULT_ALPHA_VALIDATOR) val validaterAlpha: Validater,
    private val userProfileRepository: UserProfileRepository,
    override val diagnosisRepo: DiagnosisRepo,
    override val connectionRepo: ConnectionRepo,
    override val notificationRepo: NotificationRepo
) : DiagnosisFragmentStep4ViewModel(diagnosisRepo, connectionRepo, notificationRepo) {

    private var alphaVisibilityHelper = AlphaVisibilityHelper()
    private val _userDetailDocument = SingleLiveEvent<Resource<UserModel>>()
    val userDetailDocument: LiveData<Resource<UserModel>> =
        _userDetailDocument

    fun checkValidation(
        ailmentPosition: Int,
        firstName: String,
        lastName: String,
        age: String,
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        stepCount: String,
        succcess: () -> Unit,
        failed: (validations: List<ValidationModelV2>) -> Unit,
        failed2: (msg: String) -> Unit
    ) {
        var validations = validater.validateDiagnosisFirstStep(
            ailmentPosition,
            firstName,
            lastName,
            age
        )
        if (validater.areAllFieldValidated(validations) && isAnyHealthDataIsFilled(weight,
                heartRate,
                topBp,
                bottomBp,
                stepCount)
        )
            succcess.invoke()
        else if (!isAnyHealthDataIsFilled(weight,
                heartRate,
                topBp,
                bottomBp,
                stepCount)) {
            failed2.invoke("Please fill any Health field first !!")
        } else
            failed.invoke(validations)
    }

    private fun isAnyHealthDataIsFilled(
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        stepCount: String,
    ): Boolean {
        return weight.isNotBlank() || heartRate.isNotBlank() || topBp.isNotBlank() || bottomBp.isNotBlank() || stepCount.isNotBlank()
    }

    fun checkAlphaValidation(
        ailmentPosition: Int,
        firstName: String,
        lastName: String,
        age: String,
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        succcess: () -> Unit,
        failed: (validations: List<ValidationModelV2>) -> Unit,
    ) {
        var validations = validaterAlpha.validateAlphaDiagnosisFirstStep(
            ailmentPosition,
            firstName,
            lastName,
            age
        )
        if (validater.areAllFieldValidated(validations) )
            succcess.invoke()
        else
            failed.invoke(validations)
    }


    fun getUserProfile(userId: String?) {
        try {
            _userDetailDocument.postValue(Resource.loading(Constants.USER_DETAIL, null))
            viewModelScope.launch {
                //fetch user detail now
                var userDetail = userProfileRepository.fetchUserDetailByModel(userId)
                _userDetailDocument.postValue(Resource.success(Constants.USER_DETAIL, userDetail))
            }
        } catch (e: Exception) {
            _userDetailDocument.setError(e)
        }
    }

    fun addTextChangeListener(
        list: List<EditText>,
        onTextChanged: () -> Unit,
    ) {
        alphaVisibilityHelper.addEditTextWatcher(list, onTextChanged)
    }

    fun setAlphaValidationListener(
        listEdtFields: List<EditText>,
        onTextChanged: () -> Unit,
    ) {
        alphaVisibilityHelper.addDiagnosisStep1Listner(listEdtFields, onTextChanged)
    }


}
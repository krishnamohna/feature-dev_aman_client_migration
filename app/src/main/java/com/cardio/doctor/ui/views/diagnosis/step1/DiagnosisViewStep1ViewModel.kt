package com.cardio.doctor.ui.views.diagnosis.step1

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.data.remote.profile.UserProfileRepository
import com.cardio.doctor.domain.common.model.UserModel
import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import com.cardio.doctor.ui.common.utils.validation.Validater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisViewStep1ViewModel @Inject constructor(
    val validater: Validater,
    val userProfileRepository: UserProfileRepository,
    userManager: UserManager,
    application: Application
): BaseAuthViewModel(userManager, application){

    private val _userDetailDocument = SingleLiveEvent<Resource<UserModel>>()
    val userDetailDocument: LiveData<Resource<UserModel>> =
        _userDetailDocument

    fun checkValidation(
        ailmentPosition:Int,
        firstName: String,
        lastName: String,
        age: String,
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        succcess: () -> Unit,
        failed: (validations:List<ValidationModelV2>) -> Unit
    ) {
        var validations = validater.validateDiagnosisFirstStep(
            ailmentPosition,
            firstName,
            lastName,
            age,
            weight,
            heartRate,
            topBp,
            bottomBp
        )
        if(validater.areAllFieldValidated(validations))
            succcess.invoke()
        else
            failed.invoke(validations)
    }

    fun getUserProfile(){
        try {
            _userDetailDocument.postValue(Resource.loading(Constants.USER_DETAIL, null))
            viewModelScope.launch {
                //fetch user detail now
                var userDetail = userProfileRepository.fetchUserDetailByModel(_firebaseException)
                _userDetailDocument.postValue(Resource.success(Constants.USER_DETAIL, userDetail))
            }
        } catch (e: Exception) {
            _userDetailDocument.value =
                Resource.error(Constants.USER_DETAIL, 0, getExceptionMessage(e), null)
        }
    }

}
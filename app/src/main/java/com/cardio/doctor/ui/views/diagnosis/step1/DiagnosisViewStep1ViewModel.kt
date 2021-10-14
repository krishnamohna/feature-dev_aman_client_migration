package com.cardio.doctor.ui.views.diagnosis.step1

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.data.remote.profile.UserProfileRepository
import com.cardio.doctor.domain.common.model.UserModel
import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import com.cardio.doctor.ui.common.utils.validation.Validater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DiagnosisViewStep1ViewModel @Inject constructor(
    val validater: Validater,
    val userProfileRepository: UserProfileRepository,
    userManager: UserManager,
    application: Application,
    @Named("selectedFinessRepo") val fitnessRepositary: FitnessRepositary
): BaseAuthViewModel(userManager, application){


    private val _userSingleLiveData = SingleLiveEvent<Resource<FitnessModel>>()
    val userLiveData: LiveData<Resource<FitnessModel>> =
        _userSingleLiveData
    private val _heartRateSingleLiveData = SingleLiveEvent<Resource<HeartRateModel>>()
    val _heartRateLiveData: LiveData<Resource<HeartRateModel>> =
        _heartRateSingleLiveData
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

    fun getUserFitnessData(): LiveData<Resource<FitnessModel>> {
        return userLiveData
    }

    fun getHeartRateLiveData(): LiveData<Resource<HeartRateModel>> {
        return _heartRateLiveData
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


    fun getUserData(activity: Activity) {
        _userSingleLiveData.postValue(Resource.setLoading())
        fitnessRepositary.getProfileData(activity,
            onSuccess = {
                _userSingleLiveData.postValue(Resource.success("", it))
            },
            onFailure = {
                _userSingleLiveData.postValue(Resource.error(0, it))
            }
        )
    }

    fun getHeartRate(activity: Activity) {
        _heartRateSingleLiveData.postValue(Resource.setLoading())
        fitnessRepositary.getHeartRate(activity,
            onSuccess = {
                _heartRateSingleLiveData.postValue(Resource.success("", it))
            },
            onFailure = {
                _heartRateSingleLiveData.postValue(Resource.error(0, it))
            }
        )
    }


    fun isLoggedIn(): Boolean {
        return fitnessRepositary.isLoggedIn()
    }

    fun login(activity: Activity) {
        fitnessRepositary.login(activity)
    }


}
package com.cardio.doctor.ui.views.diagnosis

import android.app.Activity
import androidx.lifecycle.LiveData
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DiagnosisActivityViewModel @Inject constructor(@Named("selectedFinessRepo") val fitnessRepositary: FitnessRepositary) :
    BaseViewModel() {

    private val _userSingleLiveData = SingleLiveEvent<Resource<FitnessModel>>()
    val userLiveData: LiveData<Resource<FitnessModel>> =
        _userSingleLiveData

    fun getUserFitnessData(): LiveData<Resource<FitnessModel>> {
        return userLiveData
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

    fun isLoggedIn(): Boolean {
        return fitnessRepositary.isLoggedIn()
    }

    fun login(diagnosisActivity: DiagnosisActivity) {
        fitnessRepositary.login(diagnosisActivity)
    }

}
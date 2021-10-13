package com.cardio.doctor.ui.views.diagnosis

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import com.cardio.doctor.domain.common.model.UserModel
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiagnosisActivityViewModel @Inject constructor(val fitnessRepositary: FitnessRepositary) :
    BaseViewModel() {

    private val _userSingleLiveData = SingleLiveEvent<Resource<UserModel>>()
    val userLiveData: LiveData<Resource<UserModel>> =
        _userSingleLiveData

    fun getUserFitnessData(): LiveData<Resource<UserModel>> {
        return userLiveData
    }

    fun getUserData(activity: Activity) {
        fitnessRepositary.getProfileData(activity,
            onSuccess = {
                Log.i("", "")
            },
            onFailure = {
                Log.i("", "")
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
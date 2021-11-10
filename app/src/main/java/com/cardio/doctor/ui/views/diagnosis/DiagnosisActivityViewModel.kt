package com.cardio.doctor.ui.views.diagnosis

import android.app.Activity
import androidx.lifecycle.LiveData
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiagnosisActivityViewModel @Inject constructor(/*@Named(REPO_FITNESS_SELECTED) val fitnessRepositary: FitnessRepositary*/) :
    BaseViewModel() {

    private val _userSingleLiveData = SingleLiveEvent<Resource<FitnessModel>>()
    val userLiveData: LiveData<Resource<FitnessModel>> =
        _userSingleLiveData

    fun getUserFitnessData(): LiveData<Resource<FitnessModel>> {
        return userLiveData
    }

    fun getUserData(activity: Activity) {
        _userSingleLiveData.postValue(Resource.setLoading())
        /*fitnessRepositary.getProfileData(activity,
            onSuccess = {
                _userSingleLiveData.postValue(Resource.success("", it))
            },
            onFailure = {
                _userSingleLiveData.postValue(Resource.error(0, it))
            }
        )*/
    }

    fun isLoggedIn(): Boolean {
        TODO()
      //  return fitnessRepositary.isLoggedIn()
    }

    fun login(diagnosisActivity: DiagnosisActivity) {
        TODO()
    //    fitnessRepositary.login(diagnosisActivity)
    }

    fun getDiagnosis(date:String){

    }

}
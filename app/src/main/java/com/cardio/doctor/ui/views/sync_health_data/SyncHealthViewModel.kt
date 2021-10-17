package com.cardio.doctor.ui.views.sync_health_data

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LiveData
import com.cardio.doctor.R
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.di.REPO_FITBIT
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.Preference.Companion.SYNC_HEALTH
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SyncHealthViewModel @Inject constructor(
    val userManager: UserManager, @Named(REPO_FITBIT) val fitnessRepositary: FitnessRepositary,
    val application: Application
) : BaseViewModel() {

    fun storeSyncSelectionInPreference(name: String) {
        userManager.setString(SYNC_HEALTH, name)
    }

    fun getSelectedHealthKit(): String {
        var selectedTab = userManager.getString(SYNC_HEALTH)
        if (selectedTab.isEmpty())
            selectedTab = application.getString(R.string.fitbit)
        return selectedTab
    }

    fun connectWithFitbit(resultLauncher: ActivityResultLauncher<Intent>,context: Context) {
        if(!fitnessRepositary.isLoggedIn()){
            fitnessRepositary.login(resultLauncher,context)
        }
    }

    fun connectWithGoogleFit() {

    }

    fun isFitbitLoggedIn(): Boolean=fitnessRepositary.isLoggedIn()


    /*get fitness data */

    private val _userSingleLiveData = SingleLiveEvent<Resource<FitnessModel>>()
    val userLiveData: LiveData<Resource<FitnessModel>> =
        _userSingleLiveData
    private val _heartRateSingleLiveData = SingleLiveEvent<Resource<HeartRateModel>>()
    val _heartRateLiveData: LiveData<Resource<HeartRateModel>> =
        _heartRateSingleLiveData

    fun getUserFitnessData(): LiveData<Resource<FitnessModel>> {
        return userLiveData
    }

    fun getHeartRateLiveData(): LiveData<Resource<HeartRateModel>> {
        return _heartRateLiveData
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


}

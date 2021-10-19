package com.cardio.doctor.ui.views.sync_health_data

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.cardio.doctor.R
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.di.REPO_FITBIT
import com.cardio.doctor.di.REPO_GOOGLE
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
    val userManager: UserManager, @Named(REPO_FITBIT) val fitbitRepositary: FitnessRepositary,
    @Named(REPO_GOOGLE) val googlefitRepositary: FitnessRepositary,
    val application: Application
) : BaseViewModel() {

    fun storeSyncSelectionInPreference(name: String) {
        userManager.setString(SYNC_HEALTH, name)
    }

    fun getSelectedHealthKit(): String {
        var selectedTab = userManager.getString(SYNC_HEALTH,"")
        if (selectedTab.isEmpty())
            selectedTab = application.getString(R.string.fitbit)
        return selectedTab
    }

    fun connectWithFitbit(resultLauncher: ActivityResultLauncher<Intent>,context: Context) {
        if(!fitbitRepositary.isLoggedIn()){
            fitbitRepositary.login(resultLauncher,context)
        }
    }

    fun connectWithGooglefit(fragment: Fragment) {
        if(!fitbitRepositary.isLoggedIn()){
            fitbitRepositary.login(fragment)
        }
    }

    fun isFitbitLoggedIn(): Boolean=fitbitRepositary.isLoggedIn()
    fun isGooglefitLoggedIn(): Boolean=googlefitRepositary.isLoggedIn()

    /*--------------------get fitness data ----------------------*/
    /*fitbit*/
    private val _userFitbitSingleLiveData = SingleLiveEvent<Resource<FitnessModel>>()
    val userFitbitLiveData: LiveData<Resource<FitnessModel>> =
        _userFitbitSingleLiveData
    private val _heartRateFitbitSingleLiveData = SingleLiveEvent<Resource<HeartRateModel>>()
    val _heartRateFitbitLiveData: LiveData<Resource<HeartRateModel>> =
        _heartRateFitbitSingleLiveData
    /*googlefit*/
    private val _userGoogleSingleLiveData = SingleLiveEvent<Resource<FitnessModel>>()
    val userGoogleLiveData: LiveData<Resource<FitnessModel>> =
        _userGoogleSingleLiveData
    private val _heartRateGoogleSingleLiveData = SingleLiveEvent<Resource<HeartRateModel>>()
    val _heartRateGoogleLiveData: LiveData<Resource<HeartRateModel>> =
        _heartRateGoogleSingleLiveData

    fun getUserFitbitData(): LiveData<Resource<FitnessModel>> {
        return userFitbitLiveData
    }

    fun getHeartRateFitbitLiveData(): LiveData<Resource<HeartRateModel>> {
        return _heartRateFitbitLiveData
    }

    fun getUserGoogleFitLiveData(): LiveData<Resource<FitnessModel>> {
        return userGoogleLiveData
    }

    fun getHeartRateGoogleLiveData(): LiveData<Resource<HeartRateModel>> {
        return _heartRateGoogleLiveData
    }

    fun getFitbitUserData(activity: Activity) {
        _userFitbitSingleLiveData.postValue(Resource.setLoading())
        fitbitRepositary.getProfileData(activity,
            onSuccess = {
                _userFitbitSingleLiveData.postValue(Resource.success("", it))
            },
            onFailure = {
                _userFitbitSingleLiveData.postValue(Resource.error(0, it))
            }
        )
    }

    fun getFitbitHeartRate(activity: Activity) {
        _heartRateFitbitSingleLiveData.postValue(Resource.setLoading())
        fitbitRepositary.getHeartRate(activity,
            onSuccess = {
                _heartRateFitbitSingleLiveData.postValue(Resource.success("", it))
            },
            onFailure = {
                _heartRateFitbitSingleLiveData.postValue(Resource.error(0, it))
            }
        )
    }

    fun getGoogleUserData(activity: Activity) {
        _userGoogleSingleLiveData.postValue(Resource.setLoading())
        googlefitRepositary.getProfileData(activity,
            onSuccess = {
                _userGoogleSingleLiveData.postValue(Resource.success("", it))
            },
            onFailure = {
                _userGoogleSingleLiveData.postValue(Resource.error(0, it))
            }
        )
    }

    fun getGoogleHeartRate(activity: Activity) {
        _heartRateGoogleSingleLiveData.postValue(Resource.setLoading())
        googlefitRepositary.getHeartRate(activity,
            onSuccess = {
                _heartRateGoogleSingleLiveData.postValue(Resource.success("", it))
            },
            onFailure = {
                _heartRateGoogleSingleLiveData.postValue(Resource.error(0, it))
            }
        )
    }


}

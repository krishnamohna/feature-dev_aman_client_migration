package com.cardio.physician.ui.views.sync_health_data

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.di.REPO_FITBIT
import com.cardio.physician.di.REPO_GOOGLE
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel
import com.cardio.physician.ui.common.utils.Preference.Companion.SYNC_HEALTH
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SyncHealthViewModel @Inject constructor(
    val userManager: UserManager, @Named(REPO_FITBIT) val fitbitRepositary: FitnessRepositary,
    @Named(REPO_GOOGLE) val googlefitRepositary: FitnessRepositary,
    val application: Application
) : BaseViewModel() {

    /*fitbit*/
    private val _userFitbitSingleLiveData = SingleLiveEvent<Resource<FitnessModel>>()
    val userFitbitLiveData: LiveData<Resource<FitnessModel>> =
        _userFitbitSingleLiveData

    /*googlefit*/
    private val _userGoogleSingleLiveData = SingleLiveEvent<Resource<FitnessModel>>()
    val userGoogleLiveData: LiveData<Resource<FitnessModel>> =
        _userGoogleSingleLiveData

    fun storeSyncSelectionInPreference(name: String) {
        userManager.setString(SYNC_HEALTH, name)
    }

    fun getSelectedHealthKit(): String {
        var selectedTab = userManager.getString(SYNC_HEALTH, "")
        return selectedTab
    }

    fun connectWithFitbit(resultLauncher: ActivityResultLauncher<Intent>, context: Context) {
        if (!fitbitRepositary.isLoggedIn()) {
            fitbitRepositary.login(resultLauncher, context)
        }
    }

    fun connectWithGooglefit(fragment: Fragment) {
        if (!googlefitRepositary.isLoggedIn()) {
            googlefitRepositary.login(fragment)
        }
    }

    fun isFitbitLoggedIn(): Boolean = fitbitRepositary.isLoggedIn()
    fun isGooglefitLoggedIn(): Boolean = googlefitRepositary.isLoggedIn()

    /*--------------------get fitness data ----------------------*/
    fun getUserFitbitData(): LiveData<Resource<FitnessModel>> {
        return userFitbitLiveData
    }

    fun getUserGoogleFitLiveData(): LiveData<Resource<FitnessModel>> {
        return userGoogleLiveData
    }

    fun getFitbitUserData(activity: Activity) {
        _userFitbitSingleLiveData.setLoading()
        // _userFitbitSingleLiveData.postValue(Resource.setLoading())
        fitbitRepositary.getProfileData(activity,
            onSuccess = {
                _userFitbitSingleLiveData.setSuccess(it)
                // _userFitbitSingleLiveData.postValue(Resource.success("", it))
            },
            onFailure = {
                _userFitbitSingleLiveData.setError(it)
                //  _userFitbitSingleLiveData.postValue(Resource.error(0, it))
            }
        )
    }

    fun getGoogleUserData(activity: Activity) {
        _userGoogleSingleLiveData.setLoading()
        googlefitRepositary.getProfileData(activity,
            onSuccess = {
                _userGoogleSingleLiveData.setSuccess(it)
            },
            onFailure = {
                _userGoogleSingleLiveData.setError(it)
            }
        )
    }


}

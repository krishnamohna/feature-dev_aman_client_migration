package com.cardio.doctor.ui.views.fragment.profile.profile

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.data.remote.profile.UserProfileRepository
import com.cardio.doctor.domain.common.model.ValidationModel
import com.cardio.doctor.network.NetworkHelper
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.doctor.ui.common.utils.ENUM
import com.cardio.doctor.ui.common.utils.Preference
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNUSED_PARAMETER")
@HiltViewModel
class UserProfileViewModel @Inject constructor(
    userManager: UserManager, private val repository: UserProfileRepository,
    application: Application, private val networkHelper: NetworkHelper,
) : BaseAuthViewModel(userManager, application) {

    private val _userDetailDocument = SingleLiveEvent<Resource<DocumentSnapshot>>()
    val userDetailDocument: LiveData<Resource<DocumentSnapshot>> =
        _userDetailDocument

    private val _getUserProfilePic = SingleLiveEvent<Resource<Uri>>()
    val getUserProfilePic: LiveData<Resource<Uri>> =
        _getUserProfilePic


    val validationChannel = Channel<ValidationModel>(ENUM.INT_10)

    fun getUserDetail() {
        try {
            _userDetailDocument.postValue(Resource.loading(Constants.USER_DETAIL, null))
            viewModelScope.launch {
                //fetch user detai now
                var userDetail = repository.fetchUserDetail(_firebaseException)
                _userDetailDocument.postValue(Resource.success(Constants.USER_DETAIL, userDetail))
            }
        } catch (e: Exception) {
            _userDetailDocument.value =
                Resource.error(Constants.USER_DETAIL, 0, getExceptionMessage(e), null)
        }
    }

    fun getImageDownloadUrl(url: String) {
        try {
            //_getUserProfilePic.value = Resource.loading(Constants.USER_PROFILE_PIC, null)
            viewModelScope.launch {
                val firebaseUriDeferred = async { repository.getImageUrl(url, _firebaseException) }
                val firebaseUri = firebaseUriDeferred.await()
                if (firebaseUri != null) {
                    _getUserProfilePic.value =
                        Resource.success(Constants.USER_PROFILE_PIC, firebaseUri)
                } else {
                    _userDetailDocument.value =
                        Resource.error(Constants.USER_PROFILE_PIC, 0, "", null)
                }
            }
        } catch (e: Exception) {
            _userDetailDocument.value =
                Resource.error(Constants.USER_PROFILE_PIC, 0, getExceptionMessage(e), null)
        }
    }


    fun getSelectedHealthKit(): String {
        var selectedTab = userManager.getString(Preference.SYNC_HEALTH)
        return selectedTab
    }

}

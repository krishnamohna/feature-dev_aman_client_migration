package com.cardio.physician.ui.views.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.data.remote.profile.UserProfileRepository
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import com.cardio.physician.ui.common.utils.extentions.toUserModel
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardActivityViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
) : BaseViewModel() {

    /*for user profile*/
    private val userSingleLiveData = SingleLiveEvent<Resource<UserModel>>()
    val liveUserData: LiveData<Resource<UserModel>> =
            userSingleLiveData

    fun getUserDetail(userId: String?=null) {
        viewModelScope.launch {
            try {
                userSingleLiveData.setLoading()
                userProfileRepository.fetchUserDetailByModelListener(userId){value, error ->
                    if(value != null) userSingleLiveData.setSuccess(value.toUserModel())
                    else error?.let { userSingleLiveData.setError(error) }
                }
            } catch (e: Exception) {
                userSingleLiveData.setError(e)
            }
        }
    }
}
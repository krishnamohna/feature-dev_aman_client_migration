package com.cardio.physician.ui.views.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.domain.notifications.NotificationModel
import com.cardio.physician.domain.notifications.NotificationRepo
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificatonsViewModel @Inject constructor(private val notificationRepo: NotificationRepo) :
    BaseViewModel() {
    private val mutalbleLiveDataNotifications = MutableLiveData<Resource<List<NotificationModel>>>()
    private val mutalbleRequestAction = MutableLiveData<Resource<NotificationModel>>()

    fun getLiveDataNotifications(): LiveData<Resource<List<NotificationModel>>> {
        return mutalbleLiveDataNotifications
    }

    fun getLiveDataRequestAction(): LiveData<Resource<NotificationModel>> {
        return mutalbleRequestAction
    }

    fun getNotifications(lastNotificationModel: NotificationModel?) {
        viewModelScope.launch {
            try {
                mutalbleLiveDataNotifications.setLoading()
                mutalbleLiveDataNotifications.setSuccess(notificationRepo.getNotifications(lastNotificationModel))
            } catch (exp: Exception) {
                exp.printStackTrace()
                mutalbleLiveDataNotifications.setError(exp)
            }
        }
    }

    fun acceptRequest(senderId: String, documentId: String, notificationModel: NotificationModel) {
        viewModelScope.launch {
            try {
                mutalbleRequestAction.setLoading()
                mutalbleRequestAction.setSuccess(notificationRepo.acceptRequest(senderId,documentId,notificationModel))
            } catch (exp: Exception) {
                exp.printStackTrace()
                mutalbleRequestAction.setError(exp)
            }
        }
    }

    fun rejectRequest(senderId: String, documentId: String, notificationModel: NotificationModel) {
        viewModelScope.launch {
            try {
                mutalbleRequestAction.setLoading()
                mutalbleRequestAction.setSuccess(notificationRepo.rejectRequest(senderId,documentId,notificationModel))
            } catch (exp: Exception) {
                exp.printStackTrace()
                mutalbleRequestAction.setError(exp)
            }
        }
    }

}

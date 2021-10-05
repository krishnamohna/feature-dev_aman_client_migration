package com.cardio.doctor.ui.views.change_email

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.R
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.domain.common.model.ValidationModel
import com.cardio.doctor.domain.common.repository.UserAuthRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.network.api.Constants.Companion.CHANGE_EMAIL
import com.cardio.doctor.ui.AppCardioPatient
import com.cardio.doctor.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.doctor.ui.common.utils.ENUM
import com.cardio.doctor.ui.common.utils.isValidPassword
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeEmailViewModel @Inject constructor(
    userManager: UserManager, private val userAuthRepo: UserAuthRepositary,
    application: Application,
) : BaseAuthViewModel(userManager, application) {

    private val _changeEmailResponse = SingleLiveEvent<Resource<Boolean>>()
    val changeEmailResponse: LiveData<Resource<Boolean>> = _changeEmailResponse

    val validationChannel = Channel<ValidationModel>(ENUM.INT_10)

    fun validateFieldsToSetAlpha(
        oldPassword: String,
    ) {
        when {
            TextUtils.isEmpty(oldPassword)  -> {
                setObserverForAlpha(R.string.alpha_false)
                return
            }
            else -> setObserverForAlpha(R.string.alpha_true)
        }
    }

    private fun setObserverForAlpha(resourceId: Int) {
        _changeEmailResponse.value = Resource.setAlpha(CHANGE_EMAIL, resourceId)
    }

    fun validatePassword(oldPassword: String, newEmail: String?) {
        val context = getApplication<AppCardioPatient>()
        viewModelScope.launch {

            val isValidOldPassword = when {
                oldPassword.isEmpty() -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.please_enter_your_old_password),
                        R.id.edtOldPassword, R.id.tvOldPasswordError)
                    false
                }
                !isValidPassword(oldPassword) -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.err_valid_password),
                        R.id.edtOldPassword, R.id.tvOldPasswordError)
                    false
                }
                else -> {
                    queueValidationRequest(Status.SUCCESS, "",
                        R.id.edtOldPassword, R.id.tvOldPasswordError)
                    true
                }
            }

            if (isValidOldPassword ) {
                changePassword(oldPassword,newEmail!!)
            }
        }
    }


    private fun changePassword(oldPass: String, newEmail: String) {
        viewModelScope.launch {
            _changeEmailResponse.postValue(Resource.loading(CHANGE_EMAIL, null))
            val isUpdated=userAuthRepo.updateEmailAddress(newEmail,oldPass,_firebaseException)
            isUpdated.let {
                if(isUpdated)
                _changeEmailResponse.postValue(Resource.success(CHANGE_EMAIL, true))
            }
        }
    }


    private suspend fun queueValidationRequest(
        status: Status, message: String,
        edtResource: Int, tvResourceId: Int,
    ) {
        validationChannel.send(
            ValidationModel(
            edtResource, tvResourceId, status, message
        )
        )
    }

}

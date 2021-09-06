package com.cardio.doctor.ui.fragment.forgot_password

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.api.Constants.Companion.FORGOT_PASSWORD
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.model.ValidationModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.ENUM
import com.cardio.doctor.utils.isValidEmail
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    userManager: UserManager, baseRepository: BaseRepository,
    application: Application
) : BaseViewModel(userManager, baseRepository, application) {

    private val _validationObserver = SingleLiveEvent<Resource<String>>()
    val validationObserver: LiveData<Resource<String>> = _validationObserver

    val validationChannel = Channel<ValidationModel>(ENUM.INT_1)

    suspend fun validateFields(email: String) {
        val context = getApplication<AppCardioPatient>()
        val isValidEmail = if (email.isEmpty()) {
            queueValidationRequest(Status.ERROR,
                context.getString(R.string.enter_email_address),
                R.id.edtEmailId, R.id.tvEmailError)
            false
        } else if (!isValidEmail(email)) {
            queueValidationRequest(Status.ERROR,
                context.getString(R.string.err_valid_email),
                R.id.edtEmailId, R.id.tvEmailError)
            false
        } else {
            queueValidationRequest(Status.SUCCESS, "",
                R.id.edtEmailId, R.id.tvEmailError)
            true
        }

        if(isValidEmail) callForgotPasswordApi(email)

       /* when {
            email.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_email_address))
                return
            }

            !isValidEmail(email) -> {
                showValidationMessage(context.getString(R.string.err_valid_email))
                return
            }
        }
        callForgotPasswordApi(email)*/
    }

    private fun callForgotPasswordApi(email: String) {
        try {
            viewModelScope.launch {
                _validationObserver.value = Resource.loading(FORGOT_PASSWORD, null)
                auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _validationObserver.value = Resource.success(
                            FORGOT_PASSWORD,
                            getApplication<AppCardioPatient>().getString(R.string.forgot_password_email_sent)
                        )
                    } else task.exception?.let {
                        _validationObserver.value = Resource.error(
                            FORGOT_PASSWORD,
                            0, getExceptionMessage(it), null
                        )
                    }
                }
            }
        } catch (e: Exception) {
            _validationObserver.value = Resource.error(
                FORGOT_PASSWORD, 0,
                getExceptionMessage(e), null
            )
        }
    }

    private fun showValidationMessage(message: String) {
        _validationObserver.value = Resource.error(Constants.LOGIN, 0, message, null)
    }

    private suspend fun queueValidationRequest(
        status: Status, message: String,
        edtResource: Int, tvResourceId: Int,
    ) {
        validationChannel.send(ValidationModel(
            edtResource, tvResourceId, status, message
        ))
    }

    override fun onCleared() {
        validationChannel.close()
        super.onCleared()
    }
}

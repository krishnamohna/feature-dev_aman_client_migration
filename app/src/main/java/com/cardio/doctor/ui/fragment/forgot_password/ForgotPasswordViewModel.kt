package com.cardio.doctor.ui.fragment.forgot_password

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.api.Constants.Companion.FORGOT_PASSWORD
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.fragment.login.LoginFragment
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.isValidEmail
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthMultiFactorException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    userManager: UserManager, baseRepository: BaseRepository,
    application: Application
) : BaseViewModel(userManager, baseRepository, application) {

    private val _validationObserver = SingleLiveEvent<Resource<String>>()
    val validationObserver: LiveData<Resource<String>> = _validationObserver

    fun validateFields(email: String) {
        val context = getApplication<AppCardioPatient>()
        when {
            email.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_email_address))
                return
            }

            !isValidEmail(email) -> {
                showValidationMessage(context.getString(R.string.err_valid_email))
                return
            }
        }
        callForgotPasswordApi(email)
    }

    private fun callForgotPasswordApi(email: String) {
        _validationObserver.value = Resource.loading(FORGOT_PASSWORD, null)
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _validationObserver.value = Resource.success(
                    FORGOT_PASSWORD,
                    getApplication<AppCardioPatient>().getString(R.string.forgot_password_email_sent)
                )
            } else
                task.exception?.let {
                    _validationObserver.value =
                        Resource.error(FORGOT_PASSWORD, 0, it.localizedMessage ?:
                        getApplication<AppCardioPatient>().getString(R.string.getting_some_error), null)
                }
        }
    }

    private fun showValidationMessage(message: String) {
        _validationObserver.value = Resource.error(Constants.LOGIN, 0, message, null)
    }

}

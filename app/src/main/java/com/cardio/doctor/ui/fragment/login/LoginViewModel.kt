package com.cardio.doctor.ui.fragment.login

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.isNumericValue
import com.cardio.doctor.utils.isValidEmail
import com.cardio.doctor.utils.isValidMobileNumber
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userManager: UserManager, private val loginRepository: LoginRepository,
    application: Application,
) : BaseViewModel(userManager, loginRepository, application) {

    private val _loginApiResponse = SingleLiveEvent<Resource<String>>()
    val loginApiResponse: LiveData<Resource<String>> = _loginApiResponse

    fun validateFieldsToSetAlpha(userId: String, password: String) {
        if (userId.isEmpty()) setObserverForAlpha(R.string.alpha_false)
        else if (!isNumericValue(userId) && password.isEmpty()) setObserverForAlpha(R.string.alpha_false)
        else setObserverForAlpha(R.string.alpha_true)
    }

    private fun setObserverForAlpha(resourceId: Int) {
        _loginApiResponse.value = Resource.setAlpha(Constants.SIGNUP, resourceId)
    }

    fun validateFields(userId: String, password: String, countryCode: String) {
        val context = getApplication<AppCardioPatient>()
        if (isNumericValue(userId)) checkValidationForPhoneNumber(context, userId, countryCode)
        else checkValidationForEmailAndPassword(context, userId, password)
    }

    private fun checkValidationForEmailAndPassword(
        context: AppCardioPatient, email: String,
        password: String,
    ) {
        when {
            email.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_email_address))
                return
            }

            !isValidEmail(email) -> {
                showValidationMessage(context.getString(R.string.err_valid_email))
                return
            }

            password.isEmpty() -> {
                showValidationMessage(context.getString(R.string.err_empty_password))
                return
            }
            else -> _loginApiResponse.value = Resource.success(Constants.VALIDATION, null)
        }
    }

    private fun checkValidationForPhoneNumber(
        context: AppCardioPatient,
        phoneNumber: String,
        countryCode: String,
    ) {
        when {
            phoneNumber.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_valid_mobile))
                return
            }

            !isValidMobileNumber(phoneNumber) -> {
                showValidationMessage(context.getString(R.string.err_valid_mobile_number))
                return
            }
            else -> isUserExist(countryCode, phoneNumber)
        }
    }

    private fun isUserExist(countryCode: String, phoneNumber: String) {
        try {
            _loginApiResponse.value = Resource.loading(Constants.VALIDATION, null)
            viewModelScope.launch {
                if (!loginRepository.isPhoneNumberExist(countryCode.plus(phoneNumber),
                        _firebaseException)!!
                ) {
                    showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_user_does_not_exist))
                } else {
                    _loginApiResponse.value = Resource.success(Constants.VALIDATION, null)
                }
            }
        } catch (e: Exception) {
            _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                getExceptionMessage(e), null)
        }
    }

    private fun showValidationMessage(message: String) {
        _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0, message, null)
    }

    fun signWithEmailAndPassword(email: String, password: String) {
        try {
            viewModelScope.launch {
                _loginApiResponse.value = Resource.loading(Constants.LOGIN, null)
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) _loginApiResponse.value =
                        Resource.success(Constants.LOGIN, null)
                    else checkForMultiFactorFailure(task.exception!!)
                }
            }
        } catch (e: Exception) {
            _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                getExceptionMessage(e), null)
        }
    }

    fun getGoogleSignedAccount(task: Task<GoogleSignInAccount>?) {
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task?.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                getExceptionMessage(e), null
            )
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        try {
            viewModelScope.launch {
                _loginApiResponse.value = Resource.loading(Constants.LOGIN, null)
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            if (task.isSuccessful) _loginApiResponse.value =
                                Resource.success(Constants.LOGIN, null)
                            else checkForMultiFactorFailure(task.exception!!)
                        }
                    }
            }
        } catch (e: Exception) {
            _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                getExceptionMessage(e), null)
        }
    }

/*
    fun apiLogin() {
        viewModelScope.launch {
            networkCall(LOGIN) {
                loginRepository.loginUser(loginRequest)
            }.collect {
                _loginApiResponse.value = it
            }
        }
    }*/

}

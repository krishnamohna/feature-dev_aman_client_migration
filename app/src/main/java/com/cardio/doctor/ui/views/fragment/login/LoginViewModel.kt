package com.cardio.doctor.ui.views.fragment.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.R
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.domain.common.model.ValidationModel
import com.cardio.doctor.domain.login.LoginRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.ui.AppCardioPatient
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.isNumericValue
import com.cardio.doctor.ui.common.utils.isValidEmail
import com.cardio.doctor.ui.common.utils.isValidMobileNumber
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userManager: UserManager, private val loginRepository: LoginRepositary,
    application: Application,
) : BaseViewModel(userManager, application) {

    private val _loginApiResponse = SingleLiveEvent<Resource<String>>()
    val loginApiResponse: LiveData<Resource<String>> = _loginApiResponse

    val validationChannel = Channel<ValidationModel>(2)


    fun validateFieldsToSetAlpha(userId: String, password: String) {
        if (userId.isEmpty()) setObserverForAlpha(R.string.alpha_false)
        else if (!isNumericValue(userId) && password.isEmpty()) setObserverForAlpha(R.string.alpha_false)
        else setObserverForAlpha(R.string.alpha_true)
    }

    private fun setObserverForAlpha(resourceId: Int) {
        _loginApiResponse.value = Resource.setAlpha(Constants.SIGNUP, resourceId)
    }

    suspend fun validateFields(userId: String, password: String, countryCode: String) {
        val context = getApplication<AppCardioPatient>()
        if (isNumericValue(userId)) checkValidationForPhoneNumber(context, userId, countryCode)
        else checkValidationForEmailAndPassword(context, userId, password)
    }

    private suspend fun checkValidationForEmailAndPassword(
        context: AppCardioPatient, email: String,
        password: String,
    ) {

        val isValidEmail = if (email.isEmpty()) {
            queueValidationRequest(Status.ERROR,
                context.getString(R.string.enter_email_address),
                R.id.edtUserName, R.id.tvEmailError)
            false
        } else if (!isValidEmail(email)) {
            queueValidationRequest(Status.ERROR,
                context.getString(R.string.err_valid_email),
                R.id.edtUserName, R.id.tvEmailError)
            false
        } else {
            queueValidationRequest(Status.SUCCESS, "",
                R.id.edtUserName, R.id.tvEmailError)
            true
        }

        if (isValidEmail) {
            _loginApiResponse.value = Resource.success(Constants.VALIDATION, null)
        }

        /* when {
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
         }*/
    }

    private suspend fun checkValidationForPhoneNumber(
        context: AppCardioPatient,
        phoneNumber: String,
        countryCode: String,
    ) {
        val isValidPhoneNumber = when {
            phoneNumber.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_valid_mobile),
                    R.id.edtUserName, R.id.tvEmailError)
                false
            }
            !isValidMobileNumber(phoneNumber) -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_valid_phone_number),
                    R.id.edtUserName, R.id.tvEmailError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtUserName, R.id.tvEmailError)
                true
            }
        }

        if (isValidPhoneNumber) isUserExist(countryCode, phoneNumber)

        /* when {
             phoneNumber.isEmpty() -> {
                 showValidationMessage(context.getString(R.string.enter_valid_mobile))
                 return
             }

             !isValidMobileNumber(phoneNumber) -> {
                 showValidationMessage(context.getString(R.string.err_valid_phone_number))
                 return
             }
             else -> isUserExist(countryCode, phoneNumber)
         }*/
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
            // Google Sign In was successful, lets check if this email address already exists
            viewModelScope.launch {
                var isExist=loginRepository.isEmailExist("amanattri09@gmail.com",_firebaseException)
                if(isExist==null || isExist)
                    _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                        "Email address already exists !!", null)
                else{
                    //if email address does not exist then sign up with google
                    /*  val account = task?.getResult(ApiException::class.java)!!
                      firebaseAuthWithGoogle(account.idToken!!)*/
                }
            }
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
                val authCredential =
                    loginRepository.signInWithCredential(credential, _firebaseException)
                if (authCredential != null) {
                    _loginApiResponse.value = Resource.success(Constants.LOGIN, null)
                    /* val isAccLinked = loginRepository.linkGoogleCredentialWithExistingAcc(credential,
                         _firebaseException)
                     if(isAccLinked !=null && isAccLinked) _loginApiResponse.value = Resource.success(Constants.LOGIN, null)*/
                }
            }
        } catch (e: Exception) {
            _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                getExceptionMessage(e), null)
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

    override fun onCleared() {
        validationChannel.close()
        super.onCleared()
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
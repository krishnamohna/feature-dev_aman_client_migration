package com.cardio.physician.ui.views.auth.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.common.model.ValidationModel
import com.cardio.physician.domain.login.LoginRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.network.Status
import com.cardio.physician.network.api.Constants
import com.cardio.physician.ui.AppCardioPatient
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.extentions.toUserModel
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
        userManager: UserManager, private val loginRepository: LoginRepositary,
        application: Application,
) : BaseAuthViewModel(userManager, application) {

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

    fun onGoogleSignIn(task: Task<GoogleSignInAccount>?) {
        // Google Sign In was successful, lets check if this email address already exists
        viewModelScope.launch {
            try {
                task?.result?.email?.let {
                    _loginApiResponse.postValue(Resource.loading(Constants.LOGIN, null))
                    //check if user is already signed up with normal account
                    var isExist = loginRepository.isEmailExistForNormalSignUp(it, _firebaseException)
                    if (isExist == null || isExist) {
                        _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                                applicationContext.getString(R.string.err_email_exist), null)
                        return@launch
                    }
                    //Login with Google now
                    val account = task?.getResult(ApiException::class.java)!!
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    val uuid = loginRepository.googleSignInWithCredential(credential, _firebaseException)
                    uuid.let {
                        if (it != null) {
                            //check if collection already exists
                            var isCollectionExist = loginRepository.isCollectionExist(it,_firebaseException)
                            isCollectionExist?.let {
                                if(isCollectionExist) {
                                    _loginApiResponse.value =
                                        Resource.success(Constants.LOGIN, null)
                                    return@launch
                                }
                            }
                            //save data to collections
                            var userModel = account.toUserModel()
                            userModel.uid = it
                            if (storeUserDetailInFireStore(userModel))
                                _loginApiResponse.value = Resource.success(Constants.GOOGLE_SIGNUP, null)
                            else
                                throw Exception(applicationContext.getString(R.string.unable_to_save_values_to_database))
                        } else
                            throw Exception(applicationContext.getString(R.string.unable_to_create_google_account))
                    }
                }
            } catch (e: Exception) {
                _loginApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                        getExceptionMessage(e), null
                )
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

    override fun onCleared() {
        validationChannel.close()
        super.onCleared()
    }

    private suspend fun storeUserDetailInFireStore(
            user: UserModel,
    ): Boolean {
        user.let {
            val user: HashMap<String, Any?> =
                    hashMapOf(
                            FireStoreDocKey.USER_ID to user.uid,
                            FireStoreDocKey.FIRST_NAME to user.firstName,
                            FireStoreDocKey.LAST_NAME to user.lastName,
                            FireStoreDocKey.COUNTRY_CODE to user.countryCode,
                            FireStoreDocKey.PHONE_NUMBER to user.phoneNumber,
                            FireStoreDocKey.EMAIL to user.email,
                            FireStoreDocKey.IMAGE_URL to user.imagePath,
                            FireStoreDocKey.USER_TYPE to UserType.USER_TYPE_PHYSICIAN,
                            FireStoreDocKey.SEARCH_NAME to (user.firstName?.lowercase() +" "+ user.lastName?.lowercase()),
                            FireStoreDocKey.SIGN_UP_TYPE to user.userType.name
                    )
            return loginRepository.storeUserDataInFireStore("", user)
        }
    }

}

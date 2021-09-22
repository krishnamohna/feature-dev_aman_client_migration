package com.cardio.doctor.ui.fragment.signup

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.model.ValidationModel
import com.cardio.doctor.network.NetworkHelper
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.*
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    userManager: UserManager, private val signUpRepository: SignUpRepository,
    application: Application, private val networkHelper: NetworkHelper,
) : BaseViewModel(userManager, signUpRepository, application) {

    private val _signUpApiResponse = SingleLiveEvent<Resource<String>>()
    val signUpApiResponse: LiveData<Resource<String>> = _signUpApiResponse

    val validationChannel = Channel<ValidationModel>(ENUM.INT_10)

    //var firebaseUri: Uri? = null
    var deviceUri: Uri? = null
    //var fileName: String? = null

    fun validateFieldsToSetAlpha(
        isChecked: Boolean, firstName: String, email: String,
        password: String, confirmPassword: String, phoneNumber: String,
    ) {
        when {
            firstName.isEmpty() || !firstName.validUserNameLength() -> {
                setObserverForAlpha(R.string.alpha_false)
            }
            email.isEmpty() -> {
                setObserverForAlpha(R.string.alpha_false)
            }

            password.isEmpty() || !password.validPasswordLength() -> {//2 -1
                setObserverForAlpha(R.string.alpha_false)
            }

            confirmPassword.isEmpty() || !confirmPassword.validPasswordLength() -> {//8 -1
                setObserverForAlpha(R.string.alpha_false)
            }
            phoneNumber.isEmpty() || !validPhoneLength(phoneNumber) -> {//8 -1
                setObserverForAlpha(R.string.alpha_false)
            }
            !isChecked -> {
                setObserverForAlpha(R.string.alpha_false)
            }
            else -> setObserverForAlpha(R.string.alpha_true)
        }
    }

    private fun setObserverForAlpha(resourceId: Int) {
        _signUpApiResponse.value = Resource.setAlpha(Constants.SIGNUP, resourceId)
    }

    suspend fun validateFields(
        firstName: String, lastName: String,
        phoneNumber: String, countryCode: String, email: String,
        password: String, confirmPassword: String,
    ) {
        val context = getApplication<AppCardioPatient>()

        /*
        * Signup button is not enable for the empty fields and for that reason
        * some validation are not required in this method
        * */

        val isValidFirstName = when {
            firstName.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_first_name),
                    R.id.edtFirstName, R.id.tvFirstNameError)
                false
            }
            firstName.length < ENUM.INT_3 -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_valid_first_name),
                    R.id.edtFirstName, R.id.tvFirstNameError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtFirstName, R.id.tvFirstNameError)
                true
            }
        }

        val isValidLastName = when {
            lastName.isNotEmpty() && lastName.length < ENUM.INT_3 -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_valid_last_name),
                    R.id.edtLastName, R.id.tvLastName)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtLastName, R.id.tvLastName)
                true
            }
        }

        val isValidEmail = when {
            email.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_email_address),
                    R.id.edtEmailId, R.id.tvEmailError)
                false
            }
            !isValidEmail(email) -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_valid_email),
                    R.id.edtEmailId, R.id.tvEmailError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtEmailId, R.id.tvEmailError)
                true
            }
        }

        val isValidPhoneNumber = when {
            phoneNumber.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_valid_mobile),
                    R.id.edtPhoneNumber, R.id.tvPhoneNoError)
                false
            }
            !isValidMobileNumber(phoneNumber) -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_valid_phone_number),
                    R.id.edtPhoneNumber, R.id.tvPhoneNoError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtPhoneNumber, R.id.tvPhoneNoError)
                true
            }
        }


        val isValidPassword = when {
            password.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_empty_password),
                    R.id.edtPassword, R.id.tvPasswordError)
                false
            }
            !isValidPassword(password) -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_valid_password),
                    R.id.edtPassword, R.id.tvPasswordError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtPassword, R.id.tvPasswordError)
                true
            }
        }

        val isValidConfirmPassword = when {
            confirmPassword.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_confirm_password),
                    R.id.edtConfirmPassword, R.id.tvConfirmPasswordError)
                false
            }
            !isValidPassword(confirmPassword) -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_valid_password),
                    R.id.edtConfirmPassword, R.id.tvConfirmPasswordError)
                false
            }
            !password.equals(confirmPassword, true) -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.password_does_not_match),
                    R.id.edtConfirmPassword, R.id.tvConfirmPasswordError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtConfirmPassword, R.id.tvConfirmPasswordError)
                true
            }
        }

        if (isValidFirstName && isValidEmail && isValidPassword && isValidConfirmPassword && isValidPhoneNumber && isValidLastName) {
            checkIsUserExist(countryCode, phoneNumber, email)
        }
    }

    private fun checkIsUserExist(countryCode: String, phoneNumber: String, email: String) {
        try {
            viewModelScope.launch {
                val phoneNumberDeferred = async {
                    signUpRepository.isPhoneNumberExist(countryCode.plus(phoneNumber),
                        _firebaseException)
                }
                val emailDeferred =
                    async { signUpRepository.isEmailAlreadyExist(email, _firebaseException) }
              /*  var isImageSelected = false
                if (deviceUri != null && firebaseUri == null) {
                    val firebaseUriDeferred = async {
                        signUpRepository.uploadImageOnFirebaseStorage(
                            deviceUri, fileName ?: UUID.randomUUID().toString(),
                            _firebaseException
                        )
                    }
                    isImageSelected = true
                    firebaseUri = firebaseUriDeferred.await()
                }*/
                val isPhoneNumberExist = phoneNumberDeferred.await()
                val isEmailExist = emailDeferred.await()

                if ((isPhoneNumberExist != null && !isPhoneNumberExist)
                    && (isEmailExist != null && !isEmailExist)/*
                    && (!isImageSelected || firebaseUri != null)*/
                ) {
                    _signUpApiResponse.value = Resource.success(Constants.SIGNUP, null)
                }else if (isEmailExist != null && isEmailExist) {
                    //showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_email_exist))
                    queueValidationRequest(Status.ERROR,
                        getApplication<AppCardioPatient>().getString(R.string.err_email_exist),
                        R.id.edtEmailId, R.id.tvEmailError)
                } else if (isPhoneNumberExist != null && isPhoneNumberExist) {
                    queueValidationRequest(Status.ERROR,
                        getApplication<AppCardioPatient>().getString(R.string.err_phonenumber_already_exist),
                        R.id.edtPhoneNumber, R.id.tvPhoneNoError)
                   // showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_phonenumber_already_exist))
                }  /*else if (isImageSelected && firebaseUri == null) {
                    showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_email_exist))
                }*/
            }
        } catch (e: Exception) {
            showValidationMessage(getExceptionMessage(e))
        }
    }

    /* fun storeUserDetailInFireStore(phoneNumber: String) {
         viewModelScope.launch {
             val user: HashMap<String, Any> =
                 hashMapOf(FireStoreDocKey.PHONE_NUMBER to phoneNumber)
             signUpRepository.storeUserDataInFireStore("", user)
         }
     }*/

    private fun showValidationMessage(message: String) {
        _signUpApiResponse.value = Resource.error(Constants.VALIDATION, 0, message, null)
    }

    /*fun uploadProfileImage(resultUri: Uri?, fileName: String?) {
        try {
            viewModelScope.launch {
                firebaseUri = signUpRepository.uploadImageOnFirebaseStorage(resultUri,
                    fileName!!, _firebaseException)
            }
        } catch (e: Exception) {
            showValidationMessage(getExceptionMessage(e))
        }
    }*/

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

    fun isNetworkConnected(): Boolean {
        return if (!networkHelper.isNetworkConnected()) {
            _signUpApiResponse.value = Resource.error(Constants.VALIDATION, 0,
                getApplication<AppCardioPatient>().getString(R.string.err_no_network_available),
                null)
            false
        } else {
            _signUpApiResponse.value = Resource.loading(Constants.SIGNUP, null)
            true
        }
    }
}

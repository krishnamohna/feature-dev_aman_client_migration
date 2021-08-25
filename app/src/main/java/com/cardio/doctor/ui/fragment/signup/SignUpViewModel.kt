package com.cardio.doctor.ui.fragment.signup

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.*
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    userManager: UserManager, private val signUpRepository: SignUpRepository,
    application: Application
) : BaseViewModel(userManager, signUpRepository, application) {

    private val _signUpApiResponse = SingleLiveEvent<Resource<String>>()
    val signUpApiResponse: LiveData<Resource<String>> = _signUpApiResponse

    fun validateFields(fullName: String, phoneNumber: String, countryCode : String ,email: String,
                       password: String) {
        val context = getApplication<AppCardioPatient>()

        when {
            fullName.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_valid_full_name))
            }

            phoneNumber.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_valid_mobile))
            }

            !isValidMobileNumber(phoneNumber) -> {
                showValidationMessage(context.getString(R.string.err_valid_mobile_number))
            }

            email.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_email_address))
            }

            !isValidEmail(email) -> {
                showValidationMessage(context.getString(R.string.err_valid_email))
            }
            password.isEmpty() -> {
                showValidationMessage(context.getString(R.string.err_empty_password))
            }

            !isValidPassword(password) -> {
                showValidationMessage(context.getString(R.string.err_valid_password))
            }

            else -> {
                checkIsUserExist(countryCode,phoneNumber,email)
            }
        }
    }

    private fun checkIsUserExist(countryCode : String, phoneNumber: String, email: String) {
        viewModelScope.launch {
            val phoneNumberDeferred =
                async { signUpRepository.isPhoneNumberExist(countryCode.plus(phoneNumber)) }
            val emailDeferred= async { signUpRepository.isEmailAlreadyExist(email) }
            val isPhoneNumberExist = phoneNumberDeferred.await()
            val isEmailExist = emailDeferred.await()
            if (!isPhoneNumberExist && !isEmailExist) {
                _signUpApiResponse.value = Resource.success(Constants.SIGNUP, null)
            } else if (isPhoneNumberExist) {
                showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_phonenumber_already_exist))
            } else if (isEmailExist) {
                showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_email_exist))
            }
        }
    }

    fun storeUserDetailInFireStore(phoneNumber: String) {
        viewModelScope.launch {
            val user: HashMap<String, Any> =
                hashMapOf(FireStoreDocKey.PHONE_NUMBER to phoneNumber)
            signUpRepository.storeUserDataInFireStore("", user)
        }
    }

    private fun showValidationMessage(message: String) {
        _signUpApiResponse.value = Resource.error(Constants.VALIDATION, 0, message, null)
    }
}

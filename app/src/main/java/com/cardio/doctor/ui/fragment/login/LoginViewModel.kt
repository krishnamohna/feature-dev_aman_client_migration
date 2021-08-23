package com.cardio.doctor.ui.fragment.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.utils.*
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthMultiFactorException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userManager: UserManager,private val loginRepository: LoginRepository,
    application: Application
) : BaseViewModel(userManager,loginRepository,application) {

    private val _loginApiResponse = SingleLiveEvent<Resource<String>>()
    val signUpApiResponse: LiveData<Resource<String>> = _loginApiResponse

    fun validateFields(userId: String, password: String,countryCode: String) {
        val context = getApplication<AppCardioPatient>()
        if(isNumericValue(userId)) checkValidationForPhoneNumber(context,userId,countryCode)
        else checkValidationForEmailAndPassword(context,userId,password)
    }

    private fun checkValidationForEmailAndPassword(context: AppCardioPatient, email: String,
                                                   password: String) {
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

            /*!isValidPassword(password) -> {
                showValidationMessage(context.getString(R.string.err_valid_password))
                return
            }*/
            else -> _loginApiResponse.value = Resource.success(Constants.LOGIN, null)

        }
    }

    private fun checkValidationForPhoneNumber(context: AppCardioPatient, phoneNumber: String,countryCode: String) {
        when {
            phoneNumber.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_valid_mobile))
                return
            }

            !isValidMobileNumber(phoneNumber) -> {
                showValidationMessage(context.getString(R.string.err_valid_mobile_number))
                return
            }
            else -> isPhoneNumberExist(countryCode.plus(phoneNumber))/*if(isPhoneNumberExist(countryCode.plus(phoneNumber))){
                _loginApiResponse.value = Resource.success(Constants.LOGIN, null)

            }*/
        }
    }

    private fun showValidationMessage(message: String) {
        _loginApiResponse.value = Resource.error(Constants.LOGIN,0,message,null)
    }

    fun checkForMultiFactorFailure(e: Exception) {
        val context = getApplication<AppCardioPatient>()
        when (e) {
            is FirebaseAuthMultiFactorException -> {
                val resolver = e.resolver
            }
            is FirebaseAuthInvalidCredentialsException -> {
                showValidationMessage(context.getString(R.string.invalid_password))
            }
            is FirebaseAuthInvalidUserException -> {
                showValidationMessage(context.getString(R.string.email_not_in_use))
            }
            is FirebaseAuthUserCollisionException -> {
                showValidationMessage(context.getString(R.string.email_already_in_use))
            }
            else -> {
                showValidationMessage(context.getString(R.string.user_not_valid))
            }
        }
    }

    fun isPhoneNumberExist(phoneNumber: String) : Boolean{
        var isPhoneNumberExist = false
        db.collection(FireStoreCollection.USERS).get().addOnSuccessListener { result ->
            for (document in result) {
                if (phoneNumber.contains(document.data[FireStoreDocKey.PHONE_NUMBER] as String,
                        true)) {
                    showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_phonenumber_already_exist))
                    isPhoneNumberExist = true
                }
            }
            if(!isPhoneNumberExist){
                _loginApiResponse.value = Resource.success(Constants.LOGIN, null)
            }

        }.addOnFailureListener { exception ->
            isPhoneNumberExist = true
            showValidationMessage(
                exception.localizedMessage
                    ?: getApplication<AppCardioPatient>().getString(R.string.getting_some_error)
            )
        }
        return isPhoneNumberExist
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

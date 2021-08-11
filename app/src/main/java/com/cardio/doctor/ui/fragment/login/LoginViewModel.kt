package com.cardio.doctor.ui.fragment.login

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.utils.isValidEmail
import com.cardio.doctor.utils.isValidPassword
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userManager: UserManager,private val loginRepository: LoginRepository,
    application: Application
) : BaseViewModel(userManager,loginRepository,application) {

    private val _loginApiResponse = SingleLiveEvent<Resource<String>>()
    val signUpApiResponse: LiveData<Resource<String>> = _loginApiResponse

    fun validateFields(email: String, password: String) {
        val context = getApplication<AppCardioPatient>()
        when {
            email.isEmpty()->{
                showValidationMessage(context.getString(R.string.enter_email_address))
                return
            }

            !isValidEmail(email) -> {
                showValidationMessage(context.getString(R.string.err_valid_email))
                return
            }

            password.isEmpty()->{
                showValidationMessage(context.getString(R.string.err_empty_password))
                return
            }

            /*!isValidPassword(password) -> {
                showValidationMessage(context.getString(R.string.err_valid_password))
                return
            }*/
            else -> _loginApiResponse.value = Resource.success(Constants.LOGIN,null)

        }
    }

    private fun showValidationMessage(message: String) {
        _loginApiResponse.value = Resource.error(Constants.LOGIN,0,message,null)
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

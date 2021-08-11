package com.cardio.doctor.ui.fragment.signup

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
import com.cardio.doctor.utils.isValidMobileNumber
import com.cardio.doctor.utils.isValidPassword
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.cardio.doctor.utils.validPhoneLength
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    userManager: UserManager, signUpRepository: SignUpRepository,
    application: Application
) : BaseViewModel(userManager,signUpRepository,application) {

    private val _signUpApiResponse = SingleLiveEvent<Resource<String>>()
    val signUpApiResponse: LiveData<Resource<String>> = _signUpApiResponse

    fun validateFields(fullName: String, phoneNumber: String,email: String, password: String) {
        val context = getApplication<AppCardioPatient>()
        when {
            fullName.isEmpty()->{
                showValidationMessage(context.getString(R.string.enter_valid_full_name))
                return
            }

            phoneNumber.isEmpty()->{
                showValidationMessage(context.getString(R.string.enter_valid_mobile))
                return
            }

            !isValidMobileNumber(phoneNumber) -> {
                showValidationMessage(context.getString(R.string.err_valid_mobile_number))
                return
            }

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

            !isValidPassword(password) -> {
                showValidationMessage(context.getString(R.string.err_valid_password))
                return
            }
            else -> _signUpApiResponse.value = Resource.success(Constants.SIGNUP,null)

        }
    }

    private fun showValidationMessage(message: String) {
        _signUpApiResponse.value = Resource.error(Constants.SIGNUP,0,message,null)
    }
}

package com.cardio.doctor.ui.fragment.phone_verification

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.utils.isValidEmail
import com.cardio.doctor.utils.isValidPassword
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneVerificationViewModel @Inject constructor(
    userManager: UserManager, baseRepository: BaseRepository,
    application: Application
) : BaseViewModel(userManager,baseRepository,application) {

    private val _validateForgotPasswordResponse = SingleLiveEvent<Resource<String>>()
    val validateForgotPasswordResponse: LiveData<Resource<String>> = _validateForgotPasswordResponse


    fun validateFieldsToSetAlpha(otp : String){
        when {
            otp.isEmpty() || otp.length <6 -> {
                setObserverForAlpha(R.string.alpha_false)
                return
            }
            else -> setObserverForAlpha(R.string.alpha_true)
        }
    }

    private fun setObserverForAlpha(resourceId: Int) {
        _validateForgotPasswordResponse.value = Resource.setAlpha(Constants.PHONE_VERIFICATION, resourceId)
    }


}

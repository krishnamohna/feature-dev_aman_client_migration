package com.cardio.doctor.ui.fragment.phone_verification

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.model.request.PhoneVerificationDetails
import com.cardio.doctor.network.Resource
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.FireStoreDocKey.Companion.EMAIL
import com.cardio.doctor.utils.FireStoreDocKey.Companion.FULL_NAME
import com.cardio.doctor.utils.FireStoreDocKey.Companion.IMAGE_URL
import com.cardio.doctor.utils.FireStoreDocKey.Companion.PHONE_NUMBER
import com.cardio.doctor.utils.FireStoreDocKey.Companion.USER_ID
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneVerificationViewModel @Inject constructor(
    userManager: UserManager, private val phoneVerificationRepository: PhoneVerificationRepository,
    application: Application
) : BaseViewModel(userManager, phoneVerificationRepository, application) {

    private val _validateForgotPasswordResponse = SingleLiveEvent<Resource<FirebaseUser>>()
    val validateForgotPasswordResponse: LiveData<Resource<FirebaseUser>> =
        _validateForgotPasswordResponse

    fun validateFieldsToSetAlpha(otp: String) {
        when {
            otp.isEmpty() || otp.length < 6 -> {
                setObserverForAlpha(R.string.alpha_false)
                return
            }
            else -> setObserverForAlpha(R.string.alpha_true)
        }
    }

    private fun setObserverForAlpha(resourceId: Int) {
        _validateForgotPasswordResponse.value =
            Resource.setAlpha(Constants.PHONE_VERIFICATION, resourceId)
    }

    fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    fun updateEmailAndPassword(
        user: FirebaseUser?,
        phoneVerificationDetail: PhoneVerificationDetails?
    ) {
        try {
            viewModelScope.launch {
                _validateForgotPasswordResponse.value =
                    Resource.loading(Constants.UPDATE_EMAIL_AND_PASSWORD, null)
                user?.let {
                    it.updateEmail(phoneVerificationDetail!!.email).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user.updatePassword(phoneVerificationDetail.password)
                                .addOnCompleteListener { resultPassword ->
                                    if (resultPassword.isSuccessful) {
                                        viewModelScope.launch {
                                            phoneVerificationRepository.sendVerificationEmail(it)
                                        }
                                        storeUserDetailInFireStore(user, phoneVerificationDetail)
                                        _validateForgotPasswordResponse.value =
                                            Resource.success(
                                                Constants.UPDATE_EMAIL_AND_PASSWORD,
                                                null
                                            )

                                    }
                                }
                        }
                    }.addOnFailureListener {
                        showFailureException(it)
                    }
                }
            }
        } catch (e: Exception) {
            showFailureException(e)
        }
    }

    private fun showFailureException(exception: Exception) {
        _validateForgotPasswordResponse.value = Resource.error(
            Constants.VALIDATION,
            0, getExceptionMessage(exception),
            null
        )
    }

    private fun storeUserDetailInFireStore(
        user: FirebaseUser?,
        userDetail: PhoneVerificationDetails?
    ) {
        try {
            viewModelScope.launch {
                userDetail.let {
                    val user: HashMap<String, Any> =
                        hashMapOf(
                            USER_ID to user!!.uid,
                            FULL_NAME to userDetail!!.fullName,
                            PHONE_NUMBER to userDetail.phoneNumber,
                            EMAIL to userDetail.phoneNumber,
                            IMAGE_URL to userDetail.imageUrl
                        )
                    phoneVerificationRepository.storeUserDataInFireStore("", user)
                }
            }
        } catch (e: Exception) {
            showFailureException(e)
        }
    }
}

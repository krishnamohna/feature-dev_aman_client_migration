package com.cardio.doctor.ui.views.fragment.phone_verification

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.R
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.domain.login.request.PhoneVerificationDetails
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.ui.AppCardioPatient
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.FireStoreDocKey.Companion.COUNTRY_CODE
import com.cardio.doctor.ui.common.utils.FireStoreDocKey.Companion.EMAIL
import com.cardio.doctor.ui.common.utils.FireStoreDocKey.Companion.FIRST_NAME
import com.cardio.doctor.ui.common.utils.FireStoreDocKey.Companion.IMAGE_URL
import com.cardio.doctor.ui.common.utils.FireStoreDocKey.Companion.LAST_NAME
import com.cardio.doctor.ui.common.utils.FireStoreDocKey.Companion.PHONE_NUMBER
import com.cardio.doctor.ui.common.utils.FireStoreDocKey.Companion.USER_ID
import com.cardio.doctor.ui.common.utils.getFileName
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PhoneVerificationViewModel @Inject constructor(
    userManager: UserManager, private val phoneVerificationRepository: PhoneVerificationRepository,
    application: Application,
) : BaseViewModel(userManager, application) {

    private val _validatePhoneVerification = SingleLiveEvent<Resource<FirebaseUser>>()
    val validateForgotPasswordResponse: LiveData<Resource<FirebaseUser>> =
        _validatePhoneVerification
   // private var firebaseUriDeferred: Deferred<Uri?>? = null
    private var imagePath = ""

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
        _validatePhoneVerification.value =
            Resource.setAlpha(Constants.PHONE_VERIFICATION, resourceId)
    }

    fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        try {
            viewModelScope.launch {
                _phoneAuthenticationResponse.value =
                    Resource.loading(Constants.PHONE_VERIFICATION, null)
                val authResult =
                    phoneVerificationRepository.signInWithCredential(credential, _firebaseException)
                if (authResult != null) {
                    // phoneVerificationRepository.enableAccountLinking(credential,_firebaseException)
                    _phoneAuthenticationResponse.value =
                        Resource.success(Constants.PHONE_VERIFICATION, authResult.user)
                } else {
                    _phoneAuthenticationResponse.value =
                        Resource.error(Constants.PHONE_VERIFICATION,
                            0,
                            getApplication<AppCardioPatient>().getString(R.string.getting_some_error),
                            null
                        )
                }

                /*auth.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _phoneAuthenticationResponse.value =
                            Resource.success(Constants.PHONE_VERIFICATION, task.result?.user)
                    } else {
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            _phoneAuthenticationResponse.value = Resource.error(
                                Constants.PHONE_VERIFICATION, 0,
                                task.exception?.localizedMessage
                                    ?: getApplication<AppCardioPatient>().getString(R.string.getting_some_error),
                                null
                            )
                        }
                    }
                }*/
            }
        } catch (e: Exception) {
            _phoneAuthenticationResponse.value =
                Resource.error(Constants.VALIDATION, 0, getExceptionMessage(e), null)
        }
    }


    fun updateEmailAndPassword(
        user: FirebaseUser?,
        phoneVerificationDetail: PhoneVerificationDetails?,
    ) {
        try {
            viewModelScope.launch {
                _validatePhoneVerification.value =
                    Resource.loading(Constants.UPDATE_EMAIL_AND_PASSWORD, null)
                user?.let {
                    //  if (deviceUri != null && firebaseUri == null) {
                    if(!phoneVerificationDetail?.imageUrl.isNullOrEmpty()){
                        val uri = Uri.parse(phoneVerificationDetail?.imageUrl)
                        val firebaseUriDeferred = async {
                            phoneVerificationRepository.uploadImageOnFirebaseStorage(
                                uri,
                                getFileName(getApplication(), uri) ?: UUID.randomUUID().toString(),
                                _firebaseException
                            )
                        }
                        val firebaseUri = firebaseUriDeferred.await()
                        firebaseUri?.let { imagePath = it.toString() }
                    }

                    it.updateEmail(phoneVerificationDetail!!.email).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user.updatePassword(phoneVerificationDetail.password)
                                .addOnCompleteListener { resultPassword ->
                                    if (resultPassword.isSuccessful) {
                                        viewModelScope.launch {
                                            phoneVerificationRepository.sendVerificationEmail(it,
                                                _firebaseException)
                                        }
                                        storeUserDetailInFireStore(user, phoneVerificationDetail)
                                        _validatePhoneVerification.value = Resource.success(
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
        _validatePhoneVerification.value = Resource.error(
            Constants.VALIDATION,
            0, getExceptionMessage(exception),
            null
        )
    }

    private fun storeUserDetailInFireStore(
        user: FirebaseUser?,
        userDetail: PhoneVerificationDetails?,
    ) {
        try {
            viewModelScope.launch {
                userDetail.let {
                    val user: HashMap<String, Any> =
                        hashMapOf(
                            USER_ID to user!!.uid,
                            FIRST_NAME to userDetail!!.firstName,
                            LAST_NAME to userDetail.lastName,
                            COUNTRY_CODE to userDetail.countryCode,
                            PHONE_NUMBER to userDetail.phoneNumber,
                            EMAIL to userDetail.email,
                            IMAGE_URL to imagePath
                        )
                    phoneVerificationRepository.storeUserDataInFireStore("", user)
                }
            }
        } catch (e: Exception) {
            showFailureException(e)
        }
    }
}
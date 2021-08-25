package com.cardio.doctor.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.model.request.PhoneVerificationDetails
import com.cardio.doctor.network.Resource
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val userManager: UserManager,
    private val baseRepository: BaseRepository,
    application: Application
) : AndroidViewModel(application) {

    val auth: FirebaseAuth = Firebase.auth
    protected val db = Firebase.firestore

    private val _phoneVerificationResponse = SingleLiveEvent<Resource<PhoneAuthCredential>>()
    val phoneVerificationResponse: LiveData<Resource<PhoneAuthCredential>> =
        _phoneVerificationResponse

    internal lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    internal var verificationInProgress = false
    internal var storedVerificationId: String = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    private val _navDirectionLiveData = SingleLiveEvent<NavDirections>()
    val navDirectionLiveData: LiveData<NavDirections> = _navDirectionLiveData

    fun setDirection(navDirections: NavDirections) {
        _navDirectionLiveData.value = navDirections
    }

    fun initializePhoneAuthCallBack() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                verificationInProgress = false
                _phoneVerificationResponse.value =
                    Resource.success(Constants.PHONE_VERIFICATION, credential)
                //signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                verificationInProgress = false
                //hideProgress()
                phoneVerificationFailException(e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                resendToken = token
                _phoneVerificationResponse.value = Resource.success(Constants.SEND_OTP, null)
            }
        }
    }

    fun phoneVerificationFailException(e: FirebaseException) {
        val context = getApplication<AppCardioPatient>()
        if (e is FirebaseAuthInvalidCredentialsException) {
            showPhoneValidation(context.getString(R.string.invalid_mobile_number))
        } else if (e is FirebaseTooManyRequestsException) {
            showPhoneValidation(context.getString(R.string.quota_exceeded))
        }
    }

    fun createModelForPhoneVerification(
        phoneNumber: String, countryCode: String, email: String,
        password: String): PhoneVerificationDetails {
        return PhoneVerificationDetails(
            countryCode.plus(phoneNumber), email, password,
            storedVerificationId, resendToken
        )
    }

    private fun showPhoneValidation(message: String) {
        _phoneVerificationResponse.value =
            Resource.error(Constants.PHONE_VERIFICATION, 0, message, null)
    }

}




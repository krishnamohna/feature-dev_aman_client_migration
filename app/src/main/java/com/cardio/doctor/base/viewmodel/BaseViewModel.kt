package com.cardio.doctor.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    userManager: UserManager,
    baseRepository: BaseRepository,
    application: Application
) : AndroidViewModel(application) {

    val auth: FirebaseAuth = Firebase.auth
    //protected val db = Firebase.firestore

    private val _phoneVerificationResponse = SingleLiveEvent<Resource<PhoneAuthCredential>>()
    val phoneVerificationResponse: LiveData<Resource<PhoneAuthCredential>> =
        _phoneVerificationResponse

    private val _phoneAuthenticationResponse = SingleLiveEvent<Resource<FirebaseUser>>()
    val phoneAuthenticationResponse: LiveData<Resource<FirebaseUser>> =
        _phoneAuthenticationResponse

    protected val _firebaseException = SingleLiveEvent<Resource<Exception>>()
    val firebaseException: LiveData<Resource<Exception>> =
        _firebaseException

    internal lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    internal var verificationInProgress = false
    internal var storedVerificationId: String = ""
    internal lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

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
            }

            override fun onVerificationFailed(e: FirebaseException) {
                verificationInProgress = false
                phoneVerificationFailException(e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId
                resendToken = token
                _phoneAuthenticationResponse.value = Resource.success(Constants.SEND_OTP, null)
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

    private fun showPhoneValidation(message: String) {
        _phoneAuthenticationResponse.value =
            Resource.error(Constants.PHONE_VERIFICATION, 0, message, null)
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        try {
            viewModelScope.launch {
                _phoneAuthenticationResponse.value =
                    Resource.loading(Constants.PHONE_VERIFICATION, null)
                auth.signInWithCredential(credential).addOnCompleteListener { task ->
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
                }
            }
        } catch (e: Exception) {
            _phoneAuthenticationResponse.value =
                Resource.error(Constants.VALIDATION, 0, getExceptionMessage(e), null)
        }
    }

    fun createModelForPhoneVerification(fullName :String,
        phoneNumber: String, countryCode: String, email: String,
        password: String, imageUrl : String
    ): PhoneVerificationDetails {
        return PhoneVerificationDetails(fullName,
            countryCode.plus(phoneNumber), email, password,
            storedVerificationId, imageUrl,resendToken
        )
    }

    protected fun checkForMultiFactorFailure(e: Exception) {
        val context = getApplication<AppCardioPatient>()
        when (e) {
            is FirebaseAuthInvalidCredentialsException -> {
                showFirebaseException(context.getString(R.string.invalid_password))
            }
            is FirebaseAuthInvalidUserException -> {
                showFirebaseException(context.getString(R.string.email_not_in_use))
            }
            is FirebaseAuthUserCollisionException -> {
                showFirebaseException(context.getString(R.string.email_already_in_use))
            }
            is FirebaseNetworkException ->{
                showFirebaseException(context.getString(R.string.err_no_network_available))
            }
            else -> {
                showFirebaseException(
                    e.localizedMessage ?: context.getString(R.string.getting_some_error)
                )
            }
        }
    }

    private fun showFirebaseException(message: String) {
        _phoneAuthenticationResponse.value =
            Resource.error(Constants.VALIDATION, 0, message, null)
    }

    protected fun getExceptionMessage(e: Exception): String {
        return e.localizedMessage
            ?: getApplication<AppCardioPatient>().getString(R.string.getting_some_error)
    }
}




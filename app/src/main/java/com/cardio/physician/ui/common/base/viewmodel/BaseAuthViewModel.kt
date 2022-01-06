package com.cardio.physician.ui.common.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.domain.login.request.PhoneVerificationDetails
import com.cardio.physician.network.Resource
import com.cardio.physician.network.api.Constants
import com.cardio.physician.ui.AppCardioPatient
import com.cardio.physician.ui.common.utils.Preference
import com.cardio.physician.ui.common.utils.Timer
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
open class BaseAuthViewModel @Inject constructor(
    protected val userManager: UserManager,
    application: Application,
) : AndroidViewModel(application) {

    companion object {
        var timeStampCodeSent: Long = 0
        var storedVerificationId: String = ""
            get() = field                     // getter
            set(value) {
                field = value
            }

        fun remainingTimeOut() =
            Calendar.getInstance().timeInMillis - timeStampCodeSent
    }

    val applicationContext: Application by lazy {
        getApplication<AppCardioPatient>()
    }

    val auth: FirebaseAuth = Firebase.auth
    //protected val db = Firebase.firestoreprivate val

    private val _phoneVerificationResponse = SingleLiveEvent<Resource<PhoneAuthCredential>>()
    val phoneVerificationResponse: LiveData<Resource<PhoneAuthCredential>> =
        _phoneVerificationResponse

    protected val _phoneAuthenticationResponse = SingleLiveEvent<Resource<FirebaseUser>>()
    val phoneAuthenticationResponse: LiveData<Resource<FirebaseUser>> =
        _phoneAuthenticationResponse

    protected val _firebaseException = SingleLiveEvent<Resource<Exception>>()
    val firebaseException: LiveData<Resource<Exception>> =
        _firebaseException

    internal lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    internal var verificationInProgress = false
    internal lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    private val _navDirectionLiveData = SingleLiveEvent<NavDirections>()
    val navDirectionLiveData: LiveData<NavDirections> = _navDirectionLiveData

    fun setDirection(navDirections: NavDirections) {
        _navDirectionLiveData.value = navDirections
    }

    fun clearPreference() {
        userManager.clearAllPreference()
        userManager.setBoolean(Preference.IS_TUTORIAL_SHOWN, true)
    }

    fun initializePhoneAuthCallBack() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
            }

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
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                timeStampCodeSent = Calendar.getInstance().timeInMillis
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

    fun createModelForPhoneVerification(
        firstName: String, lastName: String, countryCode: String,
        phoneNumber: String, email: String,
        password: String, imageUrl: String,
    ): PhoneVerificationDetails {
        return PhoneVerificationDetails(
            firstName, lastName,
            countryCode, phoneNumber, email, password,
            storedVerificationId, imageUrl, resendToken, remainingTimeOut()
        )
    }

    protected fun checkForMultiFactorFailure(e: Exception) {
        val context = getApplication<AppCardioPatient>()
        when (e) {
            /*is FirebaseAuthInvalidCredentialsException -> {
                showFirebaseException(context.getString(R.string.invalid_password))
            }
            is FirebaseAuthInvalidUserException -> {
                showFirebaseException(context.getString(R.string.email_not_in_use))
            }
            is FirebaseAuthUserCollisionException -> {
                showFirebaseException(context.getString(R.string.email_already_in_use))
            }*/
            is FirebaseNetworkException -> {
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
        var message = ""
        message = when (e) {
            is FirebaseNetworkException -> {
                getApplication<AppCardioPatient>().getString(R.string.err_no_network_available)
            }
            else -> {
                e.localizedMessage
                    ?: getApplication<AppCardioPatient>().getString(R.string.getting_some_error)
            }
        }
        return message
    }

    fun isLastOtpRequestStillValid() =
        TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance().timeInMillis - timeStampCodeSent) < Timer.OTP_EXPIRED && !getStoreVerificationId().isNullOrBlank();

    fun getStoreVerificationId(): String = storedVerificationId
    fun setStoreVerificationId(id: String) {
        storedVerificationId = id
    }
}




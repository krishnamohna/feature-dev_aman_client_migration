package com.cardio.doctor.domain.login.request

import android.os.Parcelable
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.parcel.Parcelize

@Parcelize
class PhoneVerificationDetails (
    var firstName: String = "",
    var lastName: String = "",
    var countryCode: String = "",
    var phoneNumber: String = "",
    var email : String = "",
    var password : String = "",
    var verificationId: String = "",
    var imageUrl: String = "",
    var token: PhoneAuthProvider.ForceResendingToken? = null
) : Parcelable
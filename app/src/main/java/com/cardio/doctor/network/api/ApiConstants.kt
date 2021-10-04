@file:JvmName("ApiConstants")
@file:JvmMultifileClass

package com.cardio.doctor.network.api

interface ApiHeader{
    companion object {
        const val CONTENT_TYPE = "Content-Type"
        const val ACCEPT="accept"
        const val APPLICATION_JSON="application/json"
        const val PLATFORM="platform"
        const val AUTHORIZATION="authorization"
        const val BEARER="Bearer "
        const val PLATFORM_TYPE="1"
    }
}

interface ApiStatus{
    companion object {
        const val STATUS_200=200
        const val STATUS_201=201
        const val STATUS_400=400
        const val STATUS_403=403
        const val STATUS_404=400
        const val STATUS_500=500
    }
}

interface Constants{
    companion object {
        const val LOGIN = "Login"
        const val SIGNUP = "Signup"
        const val VALIDATION = "Validation"
        const val FORGOT_PASSWORD = "ForgotPassword"
        const val PHONE_VERIFICATION = "PhoneVerification"
        const val PHONE_AUTHENTICATION = "PhoneAuthentication"
        const val SEND_OTP = "SendOtp"
        const val UPDATE_EMAIL_AND_PASSWORD = "UpdateEmailAndPassword"
        const val USER_DETAIL = "UserDetail"
        const val USER_PROFILE_PIC = "UserProfilePic"
        const val UPLOAD_PROFILE_PIC = "UploadProfilePic"
        const val USER_GENDER = "UserGender"
        const val EDIT_PROFILE = "EditProfile"
        const val CHANGE_PASSWORD = "ChangePassword"
        const val CHANGE_EMAIL = "Change_email"
        const val EMAIL_SEND_VERIFICATION = "email_send_verification"
    }

}

object EXTRAS{
    const val NEW_EMAIL_ADDRESS= "new_email_address"
}

object OTHER_CONST{
    const val DELAY_MINIMUM=700L
}



@file:JvmName("ApiConstants")
@file:JvmMultifileClass

package com.cardio.doctor.api

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
        const val SEND_OTP = "SendOtp"

    }
}




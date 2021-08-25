@file:JvmName("AppConstants")
@file:JvmMultifileClass

package com.cardio.doctor.utils

interface Preference{
    companion object {
        const val APP = "DoctorPreferences"
    }
}
interface Timer{
    companion object {
        const val DOUBLE_CLICK_TIME_DELTA: Long = 1500
        const val OTP_EXPIRED: Long = 120

    }
}

interface Log{
    companion object {
        const val TAG = "com.cardio.doctor"
    }
}

interface ENUM{
    companion object {
        const val INT_1: Int = 1
        const val INT_2: Int = 2

    }
}

interface FireStoreCollection{
    companion object {
        const val USERS = "Users"
    }
}
interface FireStoreDocKey{
    companion object {
        const val PHONE_NUMBER = "PhoneNumber"
    }
}





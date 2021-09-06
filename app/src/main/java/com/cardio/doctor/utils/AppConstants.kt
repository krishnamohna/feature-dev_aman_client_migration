@file:JvmName("AppConstants")
@file:JvmMultifileClass

package com.cardio.doctor.utils

interface Preference{
    companion object {
        const val APP = "DoctorPreferences"
        const val IS_TUTORIAL_SHOWN = "IsTutorialShown"

    }
}
interface Timer{
    companion object {
        const val SPLASH_TIME: Long = 1000
        const val OTP_EXPIRED: Long = 60
        const val INPUT_DELAY: Long = 500
        const val DOUBLE_CLICK_TIME_DELTA: Long = 1500

        const val OTP_EXPIRE_IN_MILISECONDS: Long =60000
        const val COUNT_DOWN_INTERVAL: Long =1000
        const val OTP_TIME_FORMAT = "00"
        const val MINUTES_IN_MILIS: Long =60000
        const val MINUTES: Long =60

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
        const val INT_3: Int = 3
        const val INT_10: Int = 10

    }
}

interface FireStoreCollection{
    companion object {
        const val USERS = "Users"
    }
}
interface FireStoreDocKey{
    companion object {
        const val USER_ID = "UserId"
        const val FIRST_NAME = "FullName"
        const val LAST_NAME = "LastName"
        const val EMAIL = "email"
        const val IMAGE_URL = "ImageUrl"
        const val PHONE_NUMBER = "PhoneNumber"
    }
}





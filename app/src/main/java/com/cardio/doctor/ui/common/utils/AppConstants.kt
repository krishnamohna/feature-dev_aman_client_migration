@file:JvmName("AppConstants")
@file:JvmMultifileClass

package com.cardio.doctor.ui.common.utils

interface Preference{
    companion object {
        const val APP = "DoctorPreferences"
        const val IS_TUTORIAL_SHOWN = "IsTutorialShown"
        const val SYNC_HEALTH = "SyncHealth"

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

        const val API_TIMEOUT: Long =60000

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

interface WEBURL{
    companion object {
        const val PRIVACY_POLICY = "google.com"
        const val TERMS_AND_CONDITION = "google.com"
        const val ABOUT_US = "google.com"
        const val FAQ = "google.com"
        const val DEFAULT_URL = "google.com"

    }
}

interface FireStoreDocKey{
    companion object {
        const val USER_ID = "userId"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val EMAIL = "email"
        const val IMAGE_URL = "imageUrl"
        const val COUNTRY_CODE = "countryCode"
        const val PHONE_NUMBER = "phoneNumber"
        const val DOB = "dob"
        const val SIGN_UP_TYPE="signupType"
        const val GENDER = "gender"
        const val HEIGHT = "height"
        const val WEIGHT = "weight"

    }
}





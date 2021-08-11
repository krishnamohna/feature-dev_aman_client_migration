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
    }
}






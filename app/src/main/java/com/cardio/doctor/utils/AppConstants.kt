@file:JvmName("AppConstants")
@file:JvmMultifileClass

package com.cardio.doctor.utils

/*Preferences*/
interface PREFERENCES{
    companion object {
        const val APP = "PatientPreferences"
    }
}
/*Timer*/
interface TIMER{
    companion object {
        const val DOUBLE_CLICK_TIME_DELTA: Long = 1500
    }
}

/*LOG*/
interface LOG{
    companion object {
        const val TAG = "com.cardio.patient"
    }
}



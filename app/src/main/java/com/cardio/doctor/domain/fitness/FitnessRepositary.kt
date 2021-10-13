package com.cardio.doctor.domain.fitness

import android.app.Activity

interface FitnessRepositary {
    fun getProfileData(activity: Activity, onSuccess: (FitnessModel) -> Unit, onFailure: () -> Unit)
    fun isLoggedIn():Boolean
    fun login(activity: Activity)
    fun logout()
}
package com.cardio.doctor.domain.fitness

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel

interface FitnessRepositary {
    fun getProfileData(activity: Activity, onSuccess: (FitnessModel) -> Unit, onFailure: (msg:String?) -> Unit)
    fun getHeartRate(activity: Activity, onSuccess: (HeartRateModel) -> Unit, onFailure: (msg:String?) -> Unit)
    fun isLoggedIn():Boolean
    fun login(activity: Activity)
    fun login(activity: ActivityResultLauncher<Intent>,context: Context)
    fun logout(activity: Activity)
}
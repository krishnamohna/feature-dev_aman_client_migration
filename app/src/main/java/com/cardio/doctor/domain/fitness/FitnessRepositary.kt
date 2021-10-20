package com.cardio.doctor.domain.fitness

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.cardio.doctor.domain.fitness.model.FitnessModel

interface FitnessRepositary {
    fun getProfileData(activity: Activity, onSuccess: (FitnessModel) -> Unit, onFailure: (msg:String?) -> Unit)
    fun isLoggedIn():Boolean
    fun login(activity: ActivityResultLauncher<Intent>,context: Context)
    fun login(fragment:Fragment)
    fun logout(activity: Activity)
}
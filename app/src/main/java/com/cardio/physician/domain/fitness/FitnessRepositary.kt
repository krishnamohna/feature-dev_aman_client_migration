package com.cardio.physician.domain.fitness

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.SyncModel

interface FitnessRepositary {
    fun getProfileData(activity: Activity, onSuccess: (FitnessModel) -> Unit, onFailure: (msg:String?) -> Unit)
    fun getSyncModel(
        activity: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: String?) -> Unit,
        periodDays: Int
    )
    fun isLoggedIn():Boolean
    fun login(activity: ActivityResultLauncher<Intent>,context: Context)
    fun login(fragment:Fragment)
    fun logout(activity: Activity)
}
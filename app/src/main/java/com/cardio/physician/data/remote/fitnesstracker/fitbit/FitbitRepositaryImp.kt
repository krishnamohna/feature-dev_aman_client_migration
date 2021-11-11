package com.cardio.physician.data.remote.fitnesstracker.fitbit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.SyncModel
import javax.inject.Inject

class FitbitRepositaryImp @Inject constructor(val fitbitManager:FitbitManager) : FitnessRepositary {

    override fun getProfileData(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg:String?) -> Unit
    ) {
        fitbitManager.getUserProfile(activity,onSuccess,onFailure)
    }

    override fun getSyncModel(
        activity: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: String?) -> Unit,
        periodDays: Int
    ) {
        fitbitManager.getFitnessLogs(activity,onSuccess,onFailure,periodDays)
    }

    override fun isLoggedIn(): Boolean {
       return com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager.isLoggedIn()
    }

    override fun login(activity: ActivityResultLauncher<Intent>,context: Context) {
        com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager.login(activity,context)
    }

    override fun login(fragment: Fragment) {
        TODO("Not yet implemented")
    }

    override fun logout(activity: Activity) {
        com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager.logout(activity)
    }

}
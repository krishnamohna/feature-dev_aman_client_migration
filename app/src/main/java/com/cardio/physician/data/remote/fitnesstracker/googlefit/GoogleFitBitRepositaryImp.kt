package com.cardio.physician.data.remote.fitnesstracker.googlefit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.SyncModel
import javax.inject.Inject

class GoogleFitBitRepositaryImp @Inject constructor(val googleFitManager: GoogleFitManager) :
    FitnessRepositary {

    override fun getProfileData(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        googleFitManager.getProfileData(activity,onSuccess,onFailure)
    }

    override fun getSyncModel(
        activity: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: String?) -> Unit,
        periodDays: Int
    ) {
        googleFitManager.getFitnessLogs(activity,onSuccess,onFailure,periodDays)
    }

    override fun isLoggedIn(): Boolean {
        return googleFitManager.isLoggedIn()
    }

    override fun login(activity: ActivityResultLauncher<Intent>, context: Context) {
        TODO("Not yet implemented")
    }

    override fun login(fragment: Fragment) {
        return googleFitManager.login(fragment)
    }

    override fun logout(activity: Activity) {
        googleFitManager.logout(activity)
    }

}
package com.cardio.physician.data.remote.fitnesstracker.googlefit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.SyncModel
import com.cardio.physician.ui.common.utils.Preference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GoogleFitBitRepositaryImp @Inject constructor(
    private  val googleFitManager: GoogleFitManager,
    private val userManager: UserManager,
    @ApplicationContext val context: Context,
) :
    FitnessRepositary {

    override fun getProfileData(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg: Exception) -> Unit,
    ) {
        googleFitManager.getProfileData(activity, onSuccess, onFailure)
    }

    override fun getSyncModel(
        activity: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: Exception) -> Unit,
        periodDays: Int,
    ) {
        googleFitManager.getFitnessLogs(activity, onSuccess, onFailure, periodDays)
    }

    override fun isLoggedIn(): Boolean {
        return googleFitManager.isLoggedIn()
    }

    override fun isSelected(): Boolean {
        return userManager.getString(Preference.SYNC_HEALTH, "").equals(context.getString(
            R.string.google_fit))
    }

    override fun login(activity: ActivityResultLauncher<Intent>, context: Context) {
        TODO("Not yet implemented")
    }

    override fun login(fragment: Fragment) {
        return googleFitManager.login(fragment)
    }

    override fun logout() {
        googleFitManager.logout()
    }

}
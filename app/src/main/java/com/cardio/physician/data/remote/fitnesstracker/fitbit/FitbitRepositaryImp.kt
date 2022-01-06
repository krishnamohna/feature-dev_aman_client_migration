package com.cardio.physician.data.remote.fitnesstracker.fitbit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.SyncModel
import com.cardio.physician.ui.common.utils.Preference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FitbitRepositaryImp @Inject constructor(
    private val fitbitManager: FitbitManager,
    private val userManager: UserManager,
    @ApplicationContext val context: Context
) : FitnessRepositary {

    override fun getProfileData(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg:Exception) -> Unit
    ) {
        fitbitManager.getUserProfile(activity,onSuccess,onFailure)
    }

    override fun getSyncModel(
        activity: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: Exception) -> Unit,
        periodDays: Int
    ) {
        fitbitManager.getFitnessLogs(activity,onSuccess,onFailure,periodDays)
    }

    override fun isLoggedIn(): Boolean {
       return AuthenticationManager.isLoggedIn()
    }

    override fun isSelected(): Boolean {
        return userManager.getString(Preference.SYNC_HEALTH, "").equals(context.getString(R.string.fitbit))
    }

    override fun login(activity: ActivityResultLauncher<Intent>,context: Context) {
        AuthenticationManager.login(activity,context)
    }

    override fun login(fragment: Fragment) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        AuthenticationManager.logout()
    }

}
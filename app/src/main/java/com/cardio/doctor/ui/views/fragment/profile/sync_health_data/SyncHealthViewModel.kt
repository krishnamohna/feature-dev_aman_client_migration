package com.cardio.doctor.ui.views.fragment.profile.sync_health_data

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.cardio.doctor.R
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.di.REPO_FITBIT
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.Preference.Companion.SYNC_HEALTH
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SyncHealthViewModel @Inject constructor(
    val userManager: UserManager, @Named(REPO_FITBIT) val fitnessRepositary: FitnessRepositary,
    val application: Application
) : BaseViewModel() {

    fun storeSyncSelectionInPreference(name: String) {
        userManager.setString(SYNC_HEALTH, name)
    }

    fun getSelectedHealthKit(): String {
        var selectedTab = userManager.getString(SYNC_HEALTH)
        if (selectedTab.isEmpty())
            selectedTab = application.getString(R.string.fitbit)
        return selectedTab
    }

    fun connectWithFitbit(resultLauncher: ActivityResultLauncher<Intent>,context: Context) {
        if(!fitnessRepositary.isLoggedIn()){
            fitnessRepositary.login(resultLauncher,context)
        }
    }

    fun connectWithGoogleFit() {

    }

    fun isFitbitLoggedIn(): Boolean=fitnessRepositary.isLoggedIn()

}

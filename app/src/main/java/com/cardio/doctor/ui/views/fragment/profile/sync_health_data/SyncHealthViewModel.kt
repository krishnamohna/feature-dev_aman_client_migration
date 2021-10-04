package com.cardio.doctor.ui.views.fragment.profile.sync_health_data

import android.app.Application
import com.cardio.doctor.ui.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.domain.common.repository.BaseRepository
import com.cardio.doctor.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.ui.common.utils.Preference.Companion.SYNC_HEALTH
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SyncHealthViewModel @Inject constructor(
    userManager: UserManager, baseRepository: BaseRepository,
    application: Application
) : BaseAuthViewModel(userManager, application) {

    fun storeSyncSelectionInPreference(name : String){
        userManager.setString(SYNC_HEALTH,name)
    }

    fun getSelectedHealthKit(): String {
        var selectedTab = userManager.getString(SYNC_HEALTH)
        if (selectedTab.isEmpty())
            selectedTab = getApplication<AppCardioPatient>().getString(R.string.fitbit)
        return selectedTab
    }

}

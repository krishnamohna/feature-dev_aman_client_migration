package com.cardio.physician.ui.views.profile.setting

import android.app.Activity
import com.cardio.physician.di.REPO_FITBIT
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SettingViewModel @Inject constructor(
      @Named(REPO_FITBIT)  val fitbit:FitnessRepositary
) : BaseViewModel() {

    fun logout(activity: Activity) {
        fitbit.logout(activity)
    }

}

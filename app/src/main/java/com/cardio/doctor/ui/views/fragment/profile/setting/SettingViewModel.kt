package com.cardio.doctor.ui.views.fragment.profile.setting

import android.app.Application
import com.cardio.doctor.domain.common.repository.BaseRepository
import com.cardio.doctor.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.doctor.data.local.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
        userManager: UserManager, baseRepository: BaseRepository,
        application: Application
) : BaseAuthViewModel(userManager, application) {


}

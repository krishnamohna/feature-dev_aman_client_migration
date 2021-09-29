package com.cardio.doctor.ui.views.fragment.dashboard

import android.app.Application
import com.cardio.doctor.domain.common.repository.BaseRepository
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.data.local.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
        userManager: UserManager, baseRepository: BaseRepository,
        application: Application
) : BaseViewModel(userManager, application) {


}

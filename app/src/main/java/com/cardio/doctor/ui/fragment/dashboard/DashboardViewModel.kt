package com.cardio.doctor.ui.fragment.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.api.Constants.Companion.FORGOT_PASSWORD
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.model.ValidationModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.ENUM
import com.cardio.doctor.utils.isValidEmail
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    userManager: UserManager, baseRepository: BaseRepository,
    application: Application
) : BaseViewModel(userManager, baseRepository, application) {


}

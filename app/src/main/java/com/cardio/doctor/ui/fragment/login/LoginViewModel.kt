package com.cardio.doctor.ui.fragment.login

import android.app.Application
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userManager: UserManager,loginRepository: LoginRepository,
    application: Application
) : BaseViewModel(userManager,loginRepository,application) {


}

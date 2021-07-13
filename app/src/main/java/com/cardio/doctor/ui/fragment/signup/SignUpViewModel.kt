package com.cardio.doctor.ui.fragment.signup

import android.app.Application
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    userManager: UserManager, signUpRepository: SignUpRepository,
    application: Application
) : BaseViewModel(userManager,signUpRepository,application) {



}

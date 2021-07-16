package com.cardio.doctor.ui.fragment.login

import android.app.Application
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    userManager: UserManager,private val loginRepository: LoginRepository,
    application: Application
) : BaseViewModel(userManager,loginRepository,application) {


/*  private val _loginApiResponse = SingleLiveEvent<Resource<UserAuthenticationResponse>>()
    val userAuthenticationApiResponse: LiveData<Resource<UserAuthenticationResponse>> =
        _loginApiResponse

    fun apiLogin() {
        viewModelScope.launch {
            networkCall(LOGIN) {
                loginRepository.loginUser(loginRequest)
            }.collect {
                _loginApiResponse.value = it
            }
        }
    }*/

}

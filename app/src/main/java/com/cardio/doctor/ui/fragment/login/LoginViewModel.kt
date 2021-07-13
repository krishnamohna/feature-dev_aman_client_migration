package com.cardio.doctor.ui.fragment.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.ui.base.viewmodel.BaseViewModel
import com.cardio.doctor.utils.network.networkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

package com.cardio.doctor.ui.fragment.login

import com.cardio.doctor.api.ApiService
import com.cardio.doctor.ui.base.repository.BaseRepository
import javax.inject.Inject

class LoginRepository @Inject constructor(apiService: ApiService) : BaseRepository(apiService){

}


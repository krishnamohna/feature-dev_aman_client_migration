package com.cardio.doctor.ui.fragment.login

import com.cardio.doctor.api.ApiService
import com.cardio.doctor.base.repository.BaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class LoginRepository @Inject constructor(firebaseAuth : FirebaseAuth,
                                          fireStore : FirebaseFirestore,
                                          apiService: ApiService) : BaseRepository(
    firebaseAuth, fireStore, apiService){

    /* suspend fun loginUser(loginRequest: LoginRequest): Response<UserAuthenticationResponse>
     =apiService.userLogin(loginRequest)*/

}


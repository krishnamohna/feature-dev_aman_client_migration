package com.cardio.doctor.ui.fragment.login

import com.cardio.doctor.api.ApiService
import com.cardio.doctor.base.repository.BaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class LoginRepository @Inject constructor(firebaseAuth : FirebaseAuth,
                                          private val fireStore : FirebaseFirestore,
                                          private val storageReference: StorageReference,
                                          apiService: ApiService) : BaseRepository(
    firebaseAuth, fireStore, storageReference, apiService
){


    /* suspend fun loginUser(loginRequest: LoginRequest): Response<UserAuthenticationResponse>
     =apiService.userLogin(loginRequest)*/

}


package com.cardio.doctor.ui.fragment.login

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.api.ApiService
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.network.Resource
import com.cardio.doctor.utils.firebaseQuery
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class LoginRepository @Inject constructor(
    firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) : BaseRepository(
    firebaseAuth, fireStore, storageReference, apiService
) {


    suspend fun signInWithCredential(
        authCredential: AuthCredential,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<AuthResult,AuthCredential>(
        operation = {
            firebaseAuth.signInWithCredential(authCredential)
        }, parse = {
            return@firebaseQuery it.credential!!
        }, errorLiveData

    )


    suspend fun linkGoogleCredentialWithExistingAcc(
        @NonNull credential: AuthCredential, errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<AuthResult?, Boolean>(
        operation = {
            firebaseAuth.currentUser?.linkWithCredential(credential)!!
        }, parse = {
            Log.d("TAG", "linkGoogleCredentialWithExistingAcc() called ${it.toString()}")
            return@firebaseQuery it !=null
        }, errorLiveData
    )

    /* suspend fun loginUser(loginRequest: LoginRequest): Response<UserAuthenticationResponse>
     =apiService.userLogin(loginRequest)*/

}


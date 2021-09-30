package com.cardio.doctor.domain.login

import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.network.Resource
import com.google.firebase.auth.AuthCredential
import java.util.*


interface LoginRepositary {
    suspend fun isPhoneNumberExist(
            phoneNumber: String,
            errorLiveData: MutableLiveData<Resource<Exception>>
    ): Boolean?

    /*
    Sign in with Google with firebase after login success with Google
     */
    suspend fun signInWithCredential(
            authCredential: AuthCredential,
            errorLiveData: MutableLiveData<Resource<Exception>>,
    ):String?

    suspend fun isEmailExist(
        email: String,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ):Boolean?

    suspend fun storeUserDataInFireStore(
            childName: String,
            hashMap: HashMap<String, Any?>,
    ): Boolean

    suspend fun linkGoogleCredentialWithExistingAcc(
            @NonNull credential: AuthCredential, errorLiveData: MutableLiveData<Resource<Exception>>,
    ):Boolean?
}
package com.cardio.doctor.domain.login

import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.network.Resource
import com.google.firebase.auth.AuthCredential


interface LoginRepositary {
    suspend fun isPhoneNumberExist(
            phoneNumber: String,
            errorLiveData: MutableLiveData<Resource<Exception>>
    ): Boolean?

    suspend fun signInWithCredential(
            authCredential: AuthCredential,
            errorLiveData: MutableLiveData<Resource<Exception>>,
    ):AuthCredential?

    suspend fun isEmailExist(
        email: String,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ):Boolean?
}
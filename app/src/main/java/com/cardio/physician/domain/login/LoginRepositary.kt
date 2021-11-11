package com.cardio.physician.domain.login

import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.cardio.physician.network.Resource
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
    suspend fun googleSignInWithCredential(
            authCredential: AuthCredential,
            errorLiveData: MutableLiveData<Resource<Exception>>,
    ):String?

    suspend fun isEmailExistForNormalSignUp(
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

    suspend fun isCollectionExist(uuid: String, errorLiveData: MutableLiveData<Resource<Exception>>): Boolean?
}
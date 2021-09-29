package com.cardio.doctor.ui.views.fragment.login

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.network.api.ApiService
import com.cardio.doctor.domain.login.LoginRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.utils.FireStoreCollection
import com.cardio.doctor.ui.common.utils.FireStoreDocKey
import com.cardio.doctor.ui.common.utils.firebaseQuery
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) : LoginRepositary{


    override suspend fun signInWithCredential(
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

    suspend fun isEmailExist(){

    }

    override suspend fun isPhoneNumberExist(
            phoneNumber: String,
            errorLiveData: MutableLiveData<Resource<Exception>>
    ) = firebaseQuery<QuerySnapshot, Boolean>(
            operation = { fireStore.collection(FireStoreCollection.USERS).get() },
            parse = { querySnapshot ->
                for (document in querySnapshot) {
                    if (phoneNumber.contains(document.data[FireStoreDocKey.PHONE_NUMBER] as String)) {
                        return@firebaseQuery true
                    }
                }
                return@firebaseQuery false
            },
            errorLiveData = errorLiveData
    )



}


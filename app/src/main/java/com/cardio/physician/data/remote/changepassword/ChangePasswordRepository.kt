package com.cardio.physician.data.remote.changepassword

import androidx.lifecycle.MutableLiveData
import com.cardio.physician.domain.common.repository.BaseRepository
import com.cardio.physician.network.Resource
import com.cardio.physician.network.api.ApiService
import com.cardio.physician.ui.common.utils.firebaseQuery
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class ChangePasswordRepository @Inject constructor(
    firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) : BaseRepository(
    firebaseAuth, fireStore, storageReference, apiService
) {

    suspend fun reAuthenticateUser(
        firebaseUser: FirebaseUser,
        oldPassword: String,
        errorLiveData: MutableLiveData<Resource<Exception>>
    ) = firebaseQuery<Void,Boolean>(
        operation = {
            val credential =
                EmailAuthProvider.getCredential(firebaseUser.email ?: "", oldPassword)
            firebaseUser.reauthenticate(credential)
        }, parse = {
            return@firebaseQuery true
        }, errorLiveData
    )

    suspend fun updatePassword(
        firebaseUser: FirebaseUser,
        newPassword: String,
        errorLiveData: MutableLiveData<Resource<Exception>>
    ) = firebaseQuery<Void,Boolean>(
        operation = {
            firebaseUser.updatePassword(newPassword)
        }, parse = {
            return@firebaseQuery true
        }, errorLiveData
    )

}


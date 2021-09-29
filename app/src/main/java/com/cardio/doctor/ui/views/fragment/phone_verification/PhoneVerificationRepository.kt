package com.cardio.doctor.ui.views.fragment.phone_verification

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.network.api.ApiService
import com.cardio.doctor.domain.common.repository.BaseRepository
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.utils.FireStoreCollection
import com.cardio.doctor.ui.common.utils.firebaseQuery
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PhoneVerificationRepository @Inject constructor(
    firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) : BaseRepository(firebaseAuth, fireStore, storageReference, apiService) {

    suspend fun storeUserDataInFireStore(
        childName: String,
        hashMap: HashMap<String, Any>,
    ): Boolean {
        return try {
            val userId= firebaseAuth.currentUser?.uid
            fireStore.collection(FireStoreCollection.USERS)
                .document(userId ?: "")
                .set(hashMap)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun sendVerificationEmail(
        firebaseUser: FirebaseUser,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<Void, Void>(
       operation = { firebaseUser.sendEmailVerification() },
        parse = { it },errorLiveData
    )

    suspend fun signInWithCredential(
        authCredential: AuthCredential,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<AuthResult,AuthResult>(
        operation = {
            firebaseAuth.signInWithCredential(authCredential)
        }, parse = {
            return@firebaseQuery it
        }, errorLiveData

    )

    suspend fun enableAccountLinking(credential: AuthCredential?, errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<AuthResult?, Boolean>(
        operation = {
            firebaseAuth.currentUser?.linkWithCredential(credential!!)!!
        }, parse = {
            Log.d("TAG", "linkGoogleCredentialWithExistingAcc() called ${it.toString()}")
            return@firebaseQuery it !=null
        }, errorLiveData
    )
/*
    suspend fun uploadImageOnFirebaseStorage(
        fileUri: Uri?, fileName: String,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseDocumentQuery<StorageReference, Uri>(
        operation = {
            val ref = storageReference.child("images/" + fileName)
            ref.putFile(fileUri!!).await()
            ref
        },
        parse = { result ->
            return@firebaseDocumentQuery result.downloadUrl.await()
        }, errorLiveData
    )

    suspend fun sendVerificationEmail1(firebaseUser: FirebaseUser): Boolean {
        return try {
            val await = firebaseUser.sendEmailVerification().await()
            true
        } catch (e: Exception) {
            false
        }
    }*/
}


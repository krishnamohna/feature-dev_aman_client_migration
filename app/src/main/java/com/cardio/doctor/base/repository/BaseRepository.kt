package com.cardio.doctor.base.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.api.ApiService
import com.cardio.doctor.network.Resource
import com.cardio.doctor.utils.FireStoreCollection
import com.cardio.doctor.utils.FireStoreDocKey
import com.cardio.doctor.utils.firebaseDocumentQuery
import com.cardio.doctor.utils.firebaseQuery
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) {
    suspend fun fetchUserDetail(
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseDocumentQuery(
        operation = {
            val userId = firebaseAuth.currentUser?.uid
           fireStore.collection(FireStoreCollection.USERS).document(userId ?: "")
                    .get()
                    .await()
        }, parse = {
            return@firebaseDocumentQuery it
        }, errorLiveData
    )

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

    suspend fun getImageUrl(imageUrl : String,
                            errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseDocumentQuery(
        operation = {
            storageReference.storage.getReferenceFromUrl(imageUrl)
        }, parse = {
            return@firebaseDocumentQuery it.downloadUrl.await()
        }, errorLiveData
    )

    suspend fun isPhoneNumberExist(
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




    /*val credential = EmailAuthProvider.getCredential(auth.currentUser?.email ?: "", oldPass)
    auth.currentUser?.reauthenticate(credential)?.addOnCompleteListener {
        if (it.isSuccessful) {
            auth.currentUser?.updatePassword(newPass)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _changePasswordResponse.value = Resource.success(Constants.CHANGE_PASSWORD, true)
                } else {
                    Log.d("TAG", "Error password not updated")
                }
            }
        } else {
            Log.d("TAG", "Error auth failed")
        }
    }*/
}

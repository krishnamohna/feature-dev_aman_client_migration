package com.cardio.doctor.domain.common.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.api.ApiService
import com.cardio.doctor.ui.common.utils.FireStoreCollection
import com.cardio.doctor.ui.common.utils.FireStoreDocKey
import com.cardio.doctor.ui.common.utils.extentions.toUserModel
import com.cardio.doctor.ui.common.utils.firebaseDocumentQuery
import com.cardio.doctor.ui.common.utils.firebaseQuery
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    open val firebaseAuth: FirebaseAuth,
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
                .get().await()
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

    suspend fun getImageUrl(
            imageUrl: String,
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
            errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<QuerySnapshot, Boolean>(
            operation = { fireStore.collection(FireStoreCollection.USERS).get() },
            parse = { querySnapshot ->
                for (document in querySnapshot) {
                    var phoneNo = document.data[FireStoreDocKey.PHONE_NUMBER] as? String?
                    phoneNo?.let {
                        if(it.isEmpty()) return@firebaseQuery false
                        if (phoneNumber.contains(it)) {
                            return@firebaseQuery true
                        }
                    }
                }
                return@firebaseQuery false
            },
            errorLiveData = errorLiveData
    )
}

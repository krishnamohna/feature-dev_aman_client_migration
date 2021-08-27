package com.cardio.doctor.base.repository

import com.cardio.doctor.api.ApiService
import com.cardio.doctor.utils.FireStoreCollection
import com.cardio.doctor.utils.FireStoreDocKey
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    storageReference: StorageReference,
    apiService: ApiService,
) {

    suspend fun isPhoneNumberExist(phoneNumber: String) : Boolean{
        try {
            val result = fireStore.collection(FireStoreCollection.USERS).get().await()
            for (document in result) {
                if (phoneNumber.contains(
                        document.data[FireStoreDocKey.PHONE_NUMBER] as String,
                        true
                    )
                ) {
                    return true
                }
            }
        }catch (e : Exception){
        }
        return false
    }
}

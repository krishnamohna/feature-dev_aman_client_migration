package com.cardio.doctor.base.repository

import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.api.ApiService
import com.cardio.doctor.network.Resource
import com.cardio.doctor.utils.FireStoreCollection
import com.cardio.doctor.utils.FireStoreDocKey
import com.cardio.doctor.utils.firebaseQuery
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    storageReference: StorageReference,
    apiService: ApiService,
) {

  /*  suspend fun isPhoneNumberExist(phoneNumber: String) : Boolean{
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

    fun isPhoneNumberExist1(phoneNumber: String) = fireStore.collection(FireStoreCollection.USERS).get()*/

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


}

package com.cardio.doctor.ui.fragment.phone_verification

import com.cardio.doctor.api.ApiService
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.utils.FireStoreCollection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PhoneVerificationRepository @Inject constructor(firebaseAuth : FirebaseAuth,
                                                      private val fireStore : FirebaseFirestore,
                                                      private val storageReference: StorageReference,
                                                      apiService: ApiService
) : BaseRepository(firebaseAuth,fireStore,storageReference,apiService){

    suspend fun storeUserDataInFireStore(childName: String, hashMap: HashMap<String, Any>) : Boolean{
        return try{
            fireStore.collection(FireStoreCollection.USERS)
                .document()
                .set(hashMap)
                .await()
            true
        }catch (e : Exception){
            false
        }
    }

    suspend fun sendVerificationEmail(firebaseUser: FirebaseUser) : Boolean{
        return try{
            firebaseUser.sendEmailVerification().await()
            true
        }catch (e : Exception){
            false
        }
    }
}


package com.cardio.doctor.ui.fragment.signup

import com.cardio.doctor.api.ApiService
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.utils.FireStoreCollection
import com.cardio.doctor.utils.FireStoreDocKey
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRepository @Inject constructor(firebaseAuth : FirebaseAuth ,
                                           private val fireStore : FirebaseFirestore,
                                           apiService: ApiService
) : BaseRepository(firebaseAuth,fireStore,apiService){

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

    suspend fun isEmailAlreadyExist(email: String): Boolean {
        try {
            val result = firebaseAuth.fetchSignInMethodsForEmail(email).await()
            if (result?.signInMethods?.size!! > 0) {
                return true
            }
        } catch (e: Exception) {
        }
        return false
    }
}


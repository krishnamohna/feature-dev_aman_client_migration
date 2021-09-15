package com.cardio.doctor.ui.fragment.profile.profile

import com.cardio.doctor.api.ApiService
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.utils.FireStoreCollection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserProfileRepository @Inject constructor(
    firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) : BaseRepository(
    firebaseAuth, fireStore, storageReference, apiService
) {

    suspend fun storeUserDataInFireStore(hashMap: HashMap<String, Any>): Boolean {
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


}

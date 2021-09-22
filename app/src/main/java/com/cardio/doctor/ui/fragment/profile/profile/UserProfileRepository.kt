package com.cardio.doctor.ui.fragment.profile.profile

import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.api.ApiService
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.network.Resource
import com.cardio.doctor.utils.FireStoreCollection
import com.cardio.doctor.utils.firebaseDocumentQuery
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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

    /*suspend fun fetchUserDetail(
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
    )*/

    suspend fun storeUserDataInFireStore(
        firebaseUser: FirebaseUser,
        hashMap: HashMap<String, Any>,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseDocumentQuery(
        operation = {
            val userId = firebaseUser.uid
            fireStore.collection(FireStoreCollection.USERS).document(userId ?: "")
                .set(hashMap)
                .await()
        }, parse = {
            return@firebaseDocumentQuery true
        }, errorLiveData
    )


    /*  return try {
          val userId= firebaseAuth.currentUser?.uid
          fireStore.collection(FireStoreCollection.USERS)
              .document(userId ?: "")
              .set(hashMap)
              .await()
          true
      } catch (e: Exception) {
          false
      }*/


}

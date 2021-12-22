package com.cardio.physician.data.remote.addpatient

import androidx.lifecycle.MutableLiveData
import com.cardio.physician.domain.addpatient.PatientModel
import com.cardio.physician.domain.common.repository.BaseRepository
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.network.NetworkError
import com.cardio.physician.network.Resource
import com.cardio.physician.network.api.ApiService
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.UserType
import com.cardio.physician.ui.common.utils.extentions.toCPatientModel
import com.cardio.physician.ui.common.utils.extentions.toConnectionModel
import com.cardio.physician.ui.common.utils.extentions.toPatientModel
import com.cardio.physician.ui.common.utils.firebaseQuery
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AddPatientRepositoryImp @Inject constructor(override val firebaseAuth: FirebaseAuth,
                                                  private val fireStore: FirebaseFirestore,
                                                  private val storageReference: StorageReference,
                                                  apiService: ApiService) : BaseRepository(firebaseAuth, fireStore, storageReference, apiService) {
    suspend fun getPatientList(searchString : String): List<PatientModel> {
        return fireStore.collection(FireStoreCollection.USERS).
        orderBy(FireStoreDocKey.SEARCH_NAME, Query.Direction.ASCENDING).
        startAt(searchString.lowercase()).endAt(searchString + "\uf8ff").
        whereEqualTo(FireStoreDocKey.USER_TYPE, UserType.USER_TYPE_PATIENT)
            .limit(25)
            .get().await().toPatientModel()
    }

    suspend fun getConnectedPatientList(): List<PatientModel> {
        return firebaseAuth.currentUser?.uid?.let {
            fireStore.collection(FireStoreCollection.CONNECTIONS)
                .document(UserType.USER_TYPE_PHYSICIAN)
                .collection(it)
                .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
        }!!.get().await().toCPatientModel()
    }

    suspend fun getAllPatientList(): List<PatientModel> {
        return fireStore.collection(FireStoreCollection.USERS).
        whereEqualTo(FireStoreDocKey.USER_TYPE, UserType.USER_TYPE_PATIENT)
            .limit(25)
            .get().await().toPatientModel()
    }

    suspend fun getPatientListByDate(date: String): List<PatientModel> {
        val query = firebaseAuth.currentUser?.uid?.let {
            fireStore.collection(FireStoreCollection.CONNECTIONS)
                .document(UserType.USER_TYPE_PHYSICIAN)
                .collection(it)
                .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
        }
        val querySnapshot = query?.get()?.await()
        return if(querySnapshot == null || querySnapshot.isEmpty){
            throw NetworkError(404,"No record found")
        }else{
            querySnapshot.toCPatientModel()
        }
    }


    suspend fun getPatientListWithEmail(searchString : String): List<PatientModel> {
        return fireStore.collection(FireStoreCollection.USERS).
        orderBy(FireStoreDocKey.EMAIL, Query.Direction.ASCENDING).
        startAt(searchString.lowercase()).endAt(searchString + "\uf8ff").
        whereEqualTo(FireStoreDocKey.USER_TYPE, UserType.USER_TYPE_PATIENT)
            .limit(25)
            .get().await().toPatientModel()
    }

    fun addDataToFirestore(patientId: String?, hashMap: HashMap<String, Any>) {
        val userId= firebaseAuth.currentUser?.uid
        fireStore.collection(FireStoreCollection.CONNECTIONS).document(UserType.USER_TYPE_PHYSICIAN).collection(userId?:"").document(patientId?:"").set(hashMap)
        fireStore.collection(FireStoreCollection.CONNECTIONS).document(UserType.USER_TYPE_PATIENT).collection(patientId?:"").document(userId?:"").set(hashMap)
    }
}
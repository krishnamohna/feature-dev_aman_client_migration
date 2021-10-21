package com.cardio.doctor.data.remote.synchealth

import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SyncHealthRepositoryImp @Inject constructor(
    private val fireStore: FirebaseFirestore
) : SyncHealthRepositary {

    suspend override fun getLastSavedCollectionDate(): FitnessModel {
        TODO("Not yet implemented")
      //  fireStore.collection(FireStoreCollection.USERS).document(uuid ?: "").get()
    }

    suspend override fun saveHealthData(fitnessModel: FitnessModel) {
        TODO("Not yet implemented")
    }


}


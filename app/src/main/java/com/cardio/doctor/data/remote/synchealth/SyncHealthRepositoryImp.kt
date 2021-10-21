package com.cardio.doctor.data.remote.synchealth

import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import com.cardio.doctor.ui.common.utils.FireStoreCollection
import com.cardio.doctor.ui.common.utils.FireStoreDocKey
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class SyncHealthRepositoryImp @Inject constructor(
    private val fireStore: FirebaseFirestore
) : SyncHealthRepositary {

    override suspend fun getLastSavedCollectionDate(): FitnessModel? {
        val query: Query = fireStore.collection(FireStoreCollection.HEALTH_LOGS)
            .orderBy("time_stamp", Query.Direction.DESCENDING)
            .limit(1)
        query.get().result.let {
            if (it.isEmpty) {
                return null
            }
        }
        return FitnessModel()
    }

    override suspend fun saveHealthData(fitnessModel: FitnessModel) {
        val mapHealth: HashMap<String, Any?> =
            hashMapOf(
                FireStoreDocKey.WEIGHT to fitnessModel.weight,
                FireStoreDocKey.HEART_RATE to fitnessModel.heartRate,
                FireStoreDocKey.BLOOD_PRESURE to fitnessModel.bloodPressure,
                FireStoreDocKey.TIME_STAMP to fitnessModel.date,
            )
        fireStore.collection(FireStoreCollection.HEALTH_LOGS)
            .document()
            .set(mapHealth)
            .await()
    }


}


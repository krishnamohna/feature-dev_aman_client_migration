package com.cardio.doctor.data.remote.synchealth

import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import com.cardio.doctor.ui.common.utils.FireStoreCollection
import com.cardio.doctor.ui.common.utils.FireStoreDocKey
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class SyncHealthRepositoryImp @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
) : SyncHealthRepositary {

    override suspend fun getLastSavedCollectionDate(): FitnessModel? {
        val query: Query = fireStore.collection(FireStoreCollection.HEALTH_LOGS)
            .document(firebaseAuth.currentUser?.uid!!)
            .collection(FireStoreCollection.LOGS)
            .orderBy(FireStoreDocKey.TIME_STAMP, Query.Direction.DESCENDING)
            .limit(1)
        var querySnapshot = query.get().await()
        if (querySnapshot.isEmpty) {
            return null
        } else {
            var fitnessModel = FitnessModel()
            for (document in querySnapshot) {
                fitnessModel.weight = document.data[FireStoreDocKey.WEIGHT] as? Double?
                fitnessModel.heartRate = document.data[FireStoreDocKey.HEART_RATE] as? Float?
                fitnessModel.bloodPressure = document.data[FireStoreDocKey.BLOOD_PRESURE] as? Double?
                fitnessModel.date = document.data[FireStoreDocKey.DATE] as? String?
                fitnessModel.timeStamp = document.data[FireStoreDocKey.DATE] as? Long?
            }
            return fitnessModel
        }
    }

    override suspend fun saveHealthData(fitnessModel: FitnessModel) {
        val mapHealth: HashMap<String, Any?> =
            hashMapOf(
                FireStoreDocKey.WEIGHT to fitnessModel.weight,
                FireStoreDocKey.HEART_RATE to fitnessModel.heartRate,
                FireStoreDocKey.BLOOD_PRESURE to fitnessModel.bloodPressure,
                FireStoreDocKey.TIME_STAMP to fitnessModel.timeStamp,
                FireStoreDocKey.DATE to fitnessModel.date,
            )
        firebaseAuth.currentUser?.uid?.let {
            fitnessModel.date?.let { date ->
              /* fireStore.collection(FireStoreCollection.HEALTH_LOGS)
                    .document(it)
                    .collection(FireStoreCollection.LOGS)
                    .document(date).set(mapHealth)
                    .await()*/
            }
        }
    }


}


package com.cardio.physician.data.remote.synchealth

import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.api.ApiStatus.Companion.STATUS_NOT_FOUND
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
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
        val querySnapshot = query.get().await()
        return if (querySnapshot.isEmpty) {
            null
        } else {
            val fitnessModel = FitnessModel()
            for (document in querySnapshot) {
                fitnessModel.weight = document.data[FireStoreDocKey.WEIGHT] as? Double?
                fitnessModel.heartRate = document.data[FireStoreDocKey.HEART_RATE] as? Float?
                fitnessModel.bloodPressureTopBp =
                    document.data[FireStoreDocKey.BLOOD_PRESURE] as? Double?
                fitnessModel.date = document.data[FireStoreDocKey.DATE] as? String?
                fitnessModel.timeStamp = document.data[FireStoreDocKey.DATE] as? Long?
            }
            fitnessModel
        }
    }

    override suspend fun saveHealthData(fitnessModel: FitnessModel) {
        val mapHealth: HashMap<String, Any?> =
            hashMapOf(
                FireStoreDocKey.WEIGHT to fitnessModel.weight,
                FireStoreDocKey.HEART_RATE to fitnessModel.heartRate,
                FireStoreDocKey.BLOOD_SYSTOLIC_BP to fitnessModel.bloodPressureTopBp,
                FireStoreDocKey.TIME_STAMP to fitnessModel.timeStamp,
                FireStoreDocKey.DATE to fitnessModel.date,
                FireStoreDocKey.BLOOD_DIASTOLIC_BP to fitnessModel.bloodPressureBottomBp
            )
        firebaseAuth.currentUser?.uid?.let {
            fitnessModel.date?.let { date ->
                fireStore.collection(FireStoreCollection.HEALTH_LOGS)
                    .document(it)
                    .collection(FireStoreCollection.LOGS)
                    .document(date).set(mapHealth)
                    .await()
            }
        }
    }

    override suspend fun updateHealthLogByDate(fitnessModel: FitnessModel) {
        val mapHealth= mutableMapOf<String,Any?>()
        fitnessModel.weight?.let {mapHealth.put( FireStoreDocKey.WEIGHT,it)}
        fitnessModel.heartRate?.let {mapHealth.put( FireStoreDocKey.HEART_RATE,it)}
        fitnessModel.bloodPressureTopBp?.let {mapHealth.put( FireStoreDocKey.BLOOD_SYSTOLIC_BP,it)}
        fitnessModel.timeStamp?.let {mapHealth.put( FireStoreDocKey.TIME_STAMP,it)}
        fitnessModel.bloodPressureBottomBp?.let {mapHealth.put( FireStoreDocKey.BLOOD_DIASTOLIC_BP,it)}
        firebaseAuth.currentUser?.uid?.let {uid->
            fitnessModel.date?.let { date ->
                try{
                    //TRY UPDATE VALUE FIRST
                    fireStore.collection(FireStoreCollection.HEALTH_LOGS)
                        .document(uid)
                        .collection(FireStoreCollection.LOGS)
                        .document(date).update(mapHealth)
                        .await()
                }catch (exp:Exception){
                    //INSERT VALUE IF UPDATE FAIL
                    (exp as? FirebaseFirestoreException)?.code?.name?.let {
                        if(it.equals(STATUS_NOT_FOUND)){
                            fireStore.collection(FireStoreCollection.HEALTH_LOGS)
                                .document(uid)
                                .collection(FireStoreCollection.LOGS)
                                .document(date).set(mapHealth)
                                .await()
                        }
                    }
                }
            }
        }
    }


}


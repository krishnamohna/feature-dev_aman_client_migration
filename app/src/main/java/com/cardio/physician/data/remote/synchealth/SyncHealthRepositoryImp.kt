package com.cardio.physician.data.remote.synchealth

import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.NetworkError
import com.cardio.physician.network.api.ApiStatus.Companion.STATUS_NOT_FOUND
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.extentions.toFitNessModel
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

    override suspend fun getLastSavedHealthLogCollectionDate(): FitnessModel? {
        val query: Query = fireStore.collection(FireStoreCollection.HEALTH_LOGS)
            .document(firebaseAuth.currentUser?.uid!!)
            .collection(FireStoreCollection.LOGS)
            .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
            .limit(1)
        val querySnapshot = query.get().await()
        return if (querySnapshot.isEmpty) {
            null
        } else {
            val fitnessModel = FitnessModel()
            for (document in querySnapshot) {
                fitnessModel.weight = document.data[FireStoreDocKey.WEIGHT] as? String?
                fitnessModel.heartRate = document.data[FireStoreDocKey.HEART_RATE] as? String?
                fitnessModel.bloodPressureTopBp =
                    document.data[FireStoreDocKey.BLOOD_PRESURE] as? String?
                fitnessModel.date = document.data[FireStoreDocKey.DATE] as? String?
                fitnessModel.timeStamp = document.data[FireStoreDocKey.DATE] as? Long?
                fitnessModel.bloodPressureTopBp =
                    document.data[FireStoreDocKey.BLOOD_SYSTOLIC_BP] as? String?
                fitnessModel.bloodPressureBottomBp =
                    document.data[FireStoreDocKey.BLOOD_DIASTOLIC_BP] as? String?
                fitnessModel.stepCount =
                    document.data[FireStoreDocKey.STEP_COUNT] as? String?
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
                FireStoreDocKey.BLOOD_DIASTOLIC_BP to fitnessModel.bloodPressureBottomBp,
                FireStoreDocKey.STEP_COUNT to fitnessModel.stepCount
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


    override suspend fun getHealthLogByDate(date: String): FitnessModel {
        var result = fireStore.collection(FireStoreCollection.HEALTH_LOGS)
            .document(firebaseAuth.currentUser?.uid!!)
            .collection(FireStoreCollection.LOGS).document(date).get().await()
        if (result.exists()) {
            return result.toFitNessModel()
        }
        throw NetworkError.noRecordFound()
    }

    //updating heath logs only parameter which are set
    override suspend fun updateHealthLogByDate(fitnessModel: FitnessModel, userId:String?) {
        val mapHealth = mutableMapOf<String, Any?>()
        fitnessModel.weight?.let { mapHealth.put(FireStoreDocKey.WEIGHT, it) }
        fitnessModel.heartRate?.let { mapHealth.put(FireStoreDocKey.HEART_RATE, it) }
        fitnessModel.bloodPressureTopBp?.let {
            mapHealth.put(FireStoreDocKey.BLOOD_SYSTOLIC_BP,
                it)
        }
        fitnessModel.timeStamp?.let { mapHealth.put(FireStoreDocKey.TIME_STAMP, it) }
        fitnessModel.date?.let { mapHealth.put(FireStoreDocKey.DATE, it) }
        fitnessModel.bloodPressureBottomBp?.let {
            mapHealth.put(FireStoreDocKey.BLOOD_DIASTOLIC_BP,
                it)
        }
        fitnessModel.stepCount?.let { mapHealth.put(FireStoreDocKey.STEP_COUNT, it) }
        userId?:firebaseAuth.currentUser?.uid?.let { uid ->
            fitnessModel.date?.let { date ->
                try {
                    //TRY UPDATE VALUE FIRST
                    fireStore.collection(FireStoreCollection.HEALTH_LOGS)
                        .document(uid)
                        .collection(FireStoreCollection.LOGS)
                        .document(date).update(mapHealth)
                        .await()
                } catch (exp: Exception) {
                    //INSERT VALUE IF UPDATE FAIL
                    (exp as? FirebaseFirestoreException)?.code?.name?.let {
                        if (it.equals(STATUS_NOT_FOUND)) {
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

    override suspend fun getHealthLogs(days: Long, userId: String?): List<FitnessModel> {
        val patientId = userId?: firebaseAuth.currentUser?.uid!!
        val query: Query = fireStore.collection(FireStoreCollection.HEALTH_LOGS)
            .document(patientId)
            .collection(FireStoreCollection.LOGS)
            .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.ASCENDING)
            .limit(days)
        val querySnapshot = query.get().await()
        return if (querySnapshot.isEmpty) {
            throw NetworkError.noRecordFound()
        } else {
            var listFitnessModel = mutableListOf<FitnessModel>()
            for (document in querySnapshot) {
                val fitnessModel = FitnessModel()
                fitnessModel.weight = document.data[FireStoreDocKey.WEIGHT] as? String?
                fitnessModel.heartRate = document.data[FireStoreDocKey.HEART_RATE] as? String?
                fitnessModel.bloodPressureTopBp =
                    document.data[FireStoreDocKey.BLOOD_SYSTOLIC_BP] as? String?
                fitnessModel.bloodPressureBottomBp =
                    document.data[FireStoreDocKey.BLOOD_DIASTOLIC_BP] as? String?
                fitnessModel.date = document.data[FireStoreDocKey.DATE] as? String?
                fitnessModel.timeStamp = document.data[FireStoreDocKey.DATE] as? Long?
                fitnessModel.stepCount = document.data[FireStoreDocKey.STEP_COUNT] as? String
                listFitnessModel.add(fitnessModel)
            }
            listFitnessModel
        }
    }


}


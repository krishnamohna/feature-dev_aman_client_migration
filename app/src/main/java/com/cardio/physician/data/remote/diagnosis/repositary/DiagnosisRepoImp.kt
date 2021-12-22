package com.cardio.physician.data.remote.diagnosis.repositary

import com.cardio.physician.domain.common.model.BaseModel
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.diagnosis.MedicineModel
import com.cardio.physician.network.NetworkError
import com.cardio.physician.network.api.ApiService
import com.cardio.physician.network.api.ApiStatus
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.UserType
import com.cardio.physician.ui.common.utils.extentions.toConnectionModel
import com.cardio.physician.ui.common.utils.extentions.toDiagnosisModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class DiagnosisRepoImp @Inject constructor(
    val apiService: ApiService,
    private val fireStoreDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
) : DiagnosisRepo {

    override suspend fun searchMedicine(name: String): BaseModel<MedicineModel> {
        apiService.searchMedicine(name)?.body()?.let { entity ->
            entity?.drugGroup?.let {
                var baseModel = entity.toModel(name)
                baseModel.data.drugName?.let {
                    updateCollectionIfMedDoesNotExist(it, baseModel.data.drugGroupName!!)
                }
                return baseModel
            }
        }
        return throw NetworkError(ApiStatus.STATUS_400, "No Medicine found")
    }

    override suspend fun updateCollectionIfMedDoesNotExist(
        name: String,
        category: String,
    ): Boolean {
        var queryList = name.split(" ").toList()
        return queryList.isNotEmpty().let {
            var finalQueryList = mutableListOf<String>()
            var medName = queryList.get(0).capitalize()
            finalQueryList.add(medName)
            var result = fireStoreDb.collection(FireStoreCollection.DRUGS)
                .whereIn(FireStoreDocKey.NAME, finalQueryList)
                .get().await()
            var doesMedExist = result.documents.isNotEmpty()
            //if med does not exist in collection so add it
            result.documents.isEmpty()?.let {
                if (it) {
                    saveMedToCollection(medName)
                }
            }
            doesMedExist
        }
    }

    override suspend fun submitReport(diagnosisModel: DiagnosisModel, userId: String?): Boolean {
        var json = Gson().toJson(diagnosisModel)
        var mapDiagnosis: Map<String, Any> = HashMap()
        mapDiagnosis = Gson().fromJson(json, mapDiagnosis.javaClass)
        val patientId = userId ?: firebaseAuth.currentUser?.uid
        patientId?.let { uuid ->
            fireStoreDb.collection(FireStoreCollection.DIAGNOSIS)
                .document(uuid)
                .collection(diagnosisModel.ailment!!)
                /*  .document(getCurrentDate())
                  .collection(FireStoreCollection.LOGS)*/
                .document()
                .set(mapDiagnosis)
                .await()
            return true
        }
        return false
    }

    override suspend fun getDiagnosisByDate(date: String, ailment: String): List<DiagnosisModel> {
        val query: Query = fireStoreDb.collection(FireStoreCollection.DIAGNOSIS)
            .document(firebaseAuth.currentUser?.uid!!)
            .collection(ailment)
            .whereEqualTo(FireStoreDocKey.DATE,date)
            .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
            .limit(1)
        val querySnapshot = query.get().await()
        return if (querySnapshot.isEmpty) {
            throw NetworkError(404,"No record found")
        } else {
            querySnapshot.toDiagnosisModel()
        }
    }

    override suspend fun getPatientListByDate(date: String): List<ConnectionModel> {
        val query = firebaseAuth.currentUser?.uid?.let {
            fireStoreDb.collection(FireStoreCollection.CONNECTIONS)
                .document(UserType.USER_TYPE_PHYSICIAN)
                .collection(it)
                .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
        }
        val querySnapshot = query?.get()?.await()
        return if(querySnapshot == null || querySnapshot.isEmpty){
            throw NetworkError(404,"No record found")
        }else{
            querySnapshot.toConnectionModel()
        }
    }

    override suspend fun getPatientListByRange(startDate: Long, endDate: Long): List<ConnectionModel> {
        val query = firebaseAuth.currentUser?.uid?.let {
            fireStoreDb.collection(FireStoreCollection.CONNECTIONS)
                .document(UserType.USER_TYPE_PHYSICIAN)
                .collection(it)
                .whereGreaterThan(FireStoreDocKey.TIME_STAMP_CAMEL, startDate)
                .whereLessThan(FireStoreDocKey.TIME_STAMP_CAMEL, endDate)
                .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
        }
        val querySnapshot = query?.get()?.await()
        return if(querySnapshot == null || querySnapshot.isEmpty){
            throw NetworkError(404,"No record found")
        }else{
            querySnapshot.toConnectionModel()
        }
    }

    private suspend fun saveMedToCollection(medName: String) {
        var mapDrugs = mutableMapOf<String, String>()
        mapDrugs.put(FireStoreDocKey.NAME, medName)
        mapDrugs.put(FireStoreDocKey.CATEGORY, FireStoreDocKey.OTHER)
        fireStoreDb.collection(FireStoreCollection.DRUGS).document().set(mapDrugs).await()
    }


}

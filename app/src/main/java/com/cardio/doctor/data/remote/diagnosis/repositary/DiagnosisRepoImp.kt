package com.cardio.doctor.data.remote.diagnosis.repositary

import com.cardio.doctor.domain.common.model.BaseModel
import com.cardio.doctor.domain.diagnosis.DiagnosisModel
import com.cardio.doctor.domain.diagnosis.DiagnosisRepo
import com.cardio.doctor.domain.diagnosis.MedicineModel
import com.cardio.doctor.network.NetworkError
import com.cardio.doctor.network.api.ApiService
import com.cardio.doctor.network.api.ApiStatus
import com.cardio.doctor.ui.common.utils.FireStoreCollection
import com.cardio.doctor.ui.common.utils.FireStoreDocKey
import com.cardio.doctor.ui.common.utils.getCurrentDate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
                var baseModel = entity.toModel()
                baseModel.data.drugName?.let {
                    updateCollectionIfMedDoesNotExist(it, baseModel.data.drugGroupName)
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

    override suspend fun submitReport(diagnosisModel: DiagnosisModel): Boolean {
        var json = Gson().toJson(diagnosisModel)
        var mapDiagnosis: Map<String, Any> = HashMap()
        mapDiagnosis = Gson().fromJson(json, mapDiagnosis.javaClass)
        firebaseAuth.currentUser?.uid?.let { uuid ->
             fireStoreDb.collection(FireStoreCollection.DIAGNOSIS)
                 .document(uuid)
                 .collection(FireStoreCollection.LOGS).document(getCurrentDate())
                 .set(mapDiagnosis)
                 .await()
             return true
         }
        return false
    }

    private suspend fun saveMedToCollection(medName: String) {
        var mapDrugs = mutableMapOf<String, String>()
        mapDrugs.put(FireStoreDocKey.NAME, medName)
        mapDrugs.put(FireStoreDocKey.CATEGORY, FireStoreDocKey.OTHER)
        fireStoreDb.collection(FireStoreCollection.DRUGS).document().set(mapDrugs).await()
    }


}

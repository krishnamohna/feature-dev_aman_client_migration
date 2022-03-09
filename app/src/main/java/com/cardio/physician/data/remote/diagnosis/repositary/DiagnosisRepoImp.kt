package com.cardio.physician.data.remote.diagnosis.repositary

import android.content.Context
import android.os.Parcel
import com.cardio.physician.data.remote.diagnosis.DiagnosisRepoUtil
import com.cardio.physician.data.remote.diagnosis.entity.medicines.MedicineFireStoreEntity
import com.cardio.physician.data.remote.fcm.FcmManager
import com.cardio.physician.domain.common.model.BaseModel
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.diagnosis.MedicineModel
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.network.NetworkError
import com.cardio.physician.network.api.ApiService
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.UserType
import com.cardio.physician.ui.common.utils.extentions.*
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.tasks.await
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap


class DiagnosisRepoImp @Inject constructor(
    val apiService: ApiService,
    private val fireStoreDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val fcmManager: FcmManager,
) : DiagnosisRepo {

    private var medicineModelLast: MedicineModel?=null
    private val diagnosisRepoUtil : DiagnosisRepoUtil by lazy {
        DiagnosisRepoUtil()
    }



    /*override suspend fun fetchMedicine(name: String, isPreExistedMed: Boolean): BaseModel<MedicineModel> {
        if(isPreExistedMed){
            //if medicine is already there in collection manually added  no need to search it through api
            var baseModel=BaseModel(MedicineModel(name,name,name))
            updateCollectionIfMedDoesNotExist(baseModel.data.name!!, baseModel.data)
            return baseModel
        }else{
            apiService.searchMedicine(name)?.body()?.let { entity ->
                entity?.drugGroup?.let {
                    var baseModel = entity.toModel(name)
                    updateCollectionIfMedDoesNotExist(baseModel.data.name!!, baseModel.data)
                    return baseModel
                }
            }
            return throw NetworkError.noRecordFound()
        }
    }*/

    override suspend fun fetchMedicine(
        name: String,
        isPreExistedMed: Boolean,
    ): BaseModel<MedicineModel> {
        return if (isPreExistedMed || doesMedExistInFirestore(name)) {
            //if med is selected from dropdown or searched med exist in firestore
            try {
                apiService.getMedicineDosages(name).body()?.let { entity ->
                    entity.drugGroup.let {
                        return entity.toBaseModel(name).apply {
                            mergeMedicineDataFromOurCollection(name, data)
                        }
                    }
                }
            } catch (exp: Exception) {
                return addMedWithNameOnly(name)
            }
            throw NetworkError.noRecordFound()
        } else {
            //get drugname from api  queried with  tradename/drugname
            val medicineModel = getDrugFromTradeName(name)
            val drugName=medicineModel?.data?.name
            if (medicineModel != null && drugName != null) {
                if (doesMedExistInFirestore(drugName)) {
                    try {
                        apiService.getMedicineDosages(drugName).body()?.let { entity ->
                            return entity.toBaseModel(drugName).apply {
                                mergeMedicineDataFromOurCollection(drugName, data)
                            }
                        }
                    }catch (exp:Exception){
                        return addMedWithNameOnly(name)
                    }
                    throw NetworkError.noRecordFound()
                } else {
                    addMedWithNameOnly(name)
                }
            } else {
                addMedWithNameOnly(name)
            }
        }
    }

    override fun searchMedicine(queryString: String): List<MedicineModel> {
        val query: Query = fireStoreDb.collection(FireStoreCollection.DRUGS)
            .orderBy(FireStoreDocKey.SEARCH_KEY_MEDICINE, Query.Direction.ASCENDING)
            .startAt(queryString.toLowerCase()).endAt(queryString.toLowerCase() + "\uf8ff")
        val querySnapshot = Tasks.await(query.get())
        return if (querySnapshot.isEmpty) {
            throw NetworkError.noRecordFound()
        } else {
            querySnapshot.toMedicineListModel()
        }
    }

    override suspend fun getPatientListByDate(date: String, listener: EventListener<QuerySnapshot>) {
        firebaseAuth.currentUser?.uid?.let {
            fireStoreDb.collection(FireStoreCollection.CONNECTIONS)
                .document(UserType.USER_TYPE_PHYSICIAN)
                .collection(it)
                .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
        }?.addSnapshotListener(listener)

/*
        val querySnapshot = query?.get()?.await()
        return if(querySnapshot == null || querySnapshot.isEmpty){
            throw NetworkError(404,"No record found")
        }else{
            querySnapshot.toConnectionModel()
        }
*/
    }



    private suspend fun updateCollectionIfMedDoesNotExist(
        name: String,
        medicineModel: MedicineModel,
    ) {
        var queryList = name.split(" ").toList()
        queryList.isNotEmpty().let {
            var finalQueryList = mutableListOf<String>()
            var medName = queryList.get(0).capitalize()
            finalQueryList.add(medName)
            var result = fireStoreDb.collection(FireStoreCollection.DRUGS)
                .whereIn(FireStoreDocKey.NAME, finalQueryList)
                .get().await()
            //if med does not exist in collection so add it
            result.documents.isEmpty()?.let {
                if (it) {
                    //  saveMedToCollection(medName)
                }
            }
            var doesMedExist = result.documents.isNotEmpty()
            if (doesMedExist) addMedicineInfoToModel(result.documents.get(0), medicineModel)
        }
    }

    private fun addMedicineInfoToModel(
        documentSnapshot: DocumentSnapshot,
        medicineModel: MedicineModel,
    ) {
        documentSnapshot.toMedicineModel(medicineModel)
    }

    override suspend fun submitReport(diagnosisModel: DiagnosisModel, userId: String?, isEdit: Boolean) {
        val json = Gson().toJson(diagnosisModel)
        var mapDiagnosis: Map<String, Any> = HashMap()
        mapDiagnosis = Gson().fromJson(json, mapDiagnosis.javaClass)

        userId?.let { uuid ->
            fireStoreDb.collection(FireStoreCollection.DIAGNOSIS)
                .document(uuid)
                .collection(diagnosisModel.ailment!!)
                .document()
                .set(mapDiagnosis)
                .await()
            val refDiagnosisRef = if (diagnosisModel.documentId.isNullOrBlank())
                fireStoreDb.collection(FireStoreCollection.DIAGNOSIS)
                    .document(uuid)
                    .collection(diagnosisModel.ailment!!)
                    .document()
            else
                fireStoreDb.collection(FireStoreCollection.DIAGNOSIS)
                    .document(uuid)
                    .collection(diagnosisModel.ailment!!)
                    .document(diagnosisModel.documentId!!)
            diagnosisModel.documentId = null
            val refHealthLogs = fireStoreDb.collection(FireStoreCollection.HEALTH_LOGS)
                .document(uuid)
                .collection(FireStoreCollection.LOGS)
                .document(diagnosisModel.date!!)
            var refProfile = fireStoreDb.collection(FireStoreCollection.USERS).document(uuid)
            fireStoreDb.runTransaction { transaction ->
                transaction.set(refDiagnosisRef, mapDiagnosis, SetOptions.merge())
                transaction.set(refHealthLogs, getHealthLogMap(diagnosisModel), SetOptions.merge())
                transaction.update(refProfile, getUserModelMap(diagnosisModel))
            }.await()

            //notify users through notifications
//            fcmManager.notifyUsersForDiagnosis(isEdit, userId)
        }

    }

    override suspend fun getDiagnosisByDate(date: String, ailment: String, userId: String?, listener: EventListener<QuerySnapshot>){
        fireStoreDb.collection(FireStoreCollection.DIAGNOSIS)
            .document(userId?:firebaseAuth.currentUser?.uid!!)
            .collection(ailment)
            .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
            .limit(1).addSnapshotListener(listener)
    }

    /*this method is only used to upload medicines to firestore rather than adding them manually */
    override suspend fun saveMedicineToCollections(context: Context) {
        var medicinesJson = getJsonDataFromAsset(context, "medicines.json")
        val listPersonType = object : TypeToken<MedicineFireStoreEntity>() {}.type
        var medicinesFireStore: MedicineFireStoreEntity =
            Gson().fromJson(medicinesJson, listPersonType)
        medicinesFireStore.Sheet1.forEachIndexed { index, medicineModel ->
            val documentId=index+1
            fireStoreDb.collection(FireStoreCollection.DRUGS).document(documentId.toString())
                .set(medicineModel).await()
        }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


    private suspend fun saveMedToCollection(medName: String) {
        var mapDrugs = mutableMapOf<String, String>()
        mapDrugs[FireStoreDocKey.NAME] = medName
        mapDrugs[FireStoreDocKey.SEARCH_KEY_MEDICINE_OTHER] = medName.toLowerCase()
        mapDrugs[FireStoreDocKey.CATEGORY] = FireStoreDocKey.OTHER
        fireStoreDb.collection(FireStoreCollection.DRUGS).document().set(mapDrugs).await()
    }


    private fun getHealthLogMap(diagnosisModel: DiagnosisModel): MutableMap<String, Any?> {
        val fitnessModel = FitnessModel()
        diagnosisModel.weight?.let { weight ->
            if (weight.isNotEmpty()) fitnessModel.weight = weight
        }
        diagnosisModel.heartRate?.let { heartRate ->
            if (heartRate.isNotEmpty()) fitnessModel.heartRate = heartRate
        }
        diagnosisModel.topBp?.let { topBp ->
            if (topBp.isNotEmpty()) fitnessModel.bloodPressureTopBp = topBp
        }
        diagnosisModel.bottomBp?.let { bottomBp ->
            if (bottomBp.isNotEmpty()) fitnessModel.bloodPressureBottomBp = bottomBp
        }
        diagnosisModel.stepCount?.let { stepCount ->
            if (stepCount.isNotEmpty()) fitnessModel.stepCount = stepCount
        }
        diagnosisModel.date?.let { date -> if (date.isNotEmpty()) fitnessModel.date = date }
        diagnosisModel.timeStamp?.let { timeStamp -> fitnessModel.timeStamp = timeStamp }
        //create map from fitnessmodel
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
        return mapHealth
    }

    private fun getUserModelMap(diagnosisModel: DiagnosisModel): MutableMap<String, Any?> {
        val userModel= UserModel.Builder.apply {
            firstName = diagnosisModel.firstName
            lastName = diagnosisModel.lastName
            if (diagnosisModel.age != null && !diagnosisModel.age!!.isAllDigits())
                dob = diagnosisModel.age
            weight = diagnosisModel.weight
        }.build()
        val mapUserProfile= mutableMapOf<String,Any?>()
        userModel.weight?.let {mapUserProfile.put( FireStoreDocKey.WEIGHT,it)}
        userModel.dob?.let {mapUserProfile.put( FireStoreDocKey.DOB_PROFILE,it)}
        userModel.firstName?.let {mapUserProfile.put( FireStoreDocKey.FIRST_NAME,it)}
        userModel.lastName?.let {mapUserProfile.put( FireStoreDocKey.LAST_NAME,it)}
        return mapUserProfile
    }

    private fun addMedWithNameOnly(name: String): BaseModel<MedicineModel> {
        return BaseModel(MedicineModel(Parcel.obtain()).also {
            it.name = name
            it.searchedMed = name
        })
    }

    private suspend fun mergeMedicineDataFromOurCollection(
        name: String,
        medicineModel: MedicineModel,
    ) {
        if(medicineModelLast?.name.equals(name.capitalize(Locale.ROOT))) {
            medicineModel.category=medicineModelLast?.category
            medicineModel.dosage=medicineModelLast?.dosage
            medicineModel.diuretics=medicineModelLast?.diuretics
            medicineModel.tradeName=medicineModelLast?.tradeName
            medicineModel.rateControlAgent=medicineModelLast?.rateControlAgent
        }else{
            val finalQueryList = mutableListOf<String>()
            finalQueryList.add(name.capitalize(Locale.ROOT))
            val result = fireStoreDb.collection(FireStoreCollection.DRUGS)
                .whereIn(FireStoreDocKey.NAME, finalQueryList)
                .get().await()
            if (result.documents.isNotEmpty()){
                result.documents[0].toMedicineModel(medicineModel)
            }
        }
    }

    private suspend fun getDrugFromTradeName(name: String): BaseModel<MedicineModel>? {
        return apiService.getMedicineDosages(name).body()?.let { entity ->
            entity.toBaseModel(name).apply {
                data.name = entity.getDrugName()
                data.searchedMed = entity.getDrugName()
            }
        }
    }

    private suspend fun doesMedExistInFirestore(medName: String): Boolean {
        return medName.let {
            val finalQueryList = mutableListOf<String>()
            finalQueryList.add(it)
            val result = fireStoreDb.collection(FireStoreCollection.DRUGS)
                .whereIn(FireStoreDocKey.NAME, finalQueryList)
                .get().await()
            if (result.documents.isNotEmpty()) {
                //lets keep this in variable for reusing it
                medicineModelLast= result.documents[0].toMedicineModel()
            }
            result.documents.isNotEmpty()
        }
    }

}


package com.cardio.physician.domain.diagnosis

import android.content.Context
import com.cardio.physician.domain.common.model.BaseModel
import com.cardio.physician.domain.connection.ConnectionModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

interface DiagnosisRepo {
    suspend fun fetchMedicine(name: String, isPreExistedMed: Boolean): BaseModel<MedicineModel>
    fun searchMedicine(name: String): List<MedicineModel>
    suspend fun submitReport(diagnosisModel: DiagnosisModel, userId : String?, isEdit: Boolean)
    suspend fun getDiagnosisByDate(date: String, ailment: String, userId: String?):List<DiagnosisModel>
    suspend fun saveMedicineToCollections(context:Context)
    suspend fun getPatientListByDate(date: String, listener: EventListener<QuerySnapshot>)
}
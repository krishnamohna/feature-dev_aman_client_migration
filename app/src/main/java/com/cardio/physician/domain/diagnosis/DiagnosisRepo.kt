package com.cardio.physician.domain.diagnosis

import com.cardio.physician.domain.common.model.BaseModel
import com.cardio.physician.domain.connection.ConnectionModel

interface DiagnosisRepo {
    suspend fun searchMedicine(name: String): BaseModel<MedicineModel>
    suspend fun updateCollectionIfMedDoesNotExist(name: String, drugGroupName: String): Boolean?
    suspend fun submitReport(diagnosisModel: DiagnosisModel, userId: String?): Boolean
    suspend fun getDiagnosisByDate(date: String, ailment: String):List<DiagnosisModel>
    suspend fun getPatientListByDate(date: String):List<ConnectionModel>
}
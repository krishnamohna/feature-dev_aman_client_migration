package com.cardio.physician.domain.diagnosis

import com.cardio.physician.domain.common.model.BaseModel

interface DiagnosisRepo {

    suspend fun searchMedicine(name: String): BaseModel<MedicineModel>
    suspend fun updateCollectionIfMedDoesNotExist(name: String, drugGroupName: String): Boolean?
    suspend fun submitReport(diagnosisModel: DiagnosisModel): Boolean
    suspend fun getDiagnosisByDate(date:String):List<DiagnosisModel>
}
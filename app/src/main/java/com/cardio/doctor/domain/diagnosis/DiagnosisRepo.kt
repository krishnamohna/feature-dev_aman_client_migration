package com.cardio.doctor.domain.diagnosis

import com.cardio.doctor.domain.common.model.BaseModel

interface DiagnosisRepo {

   suspend fun searchMedicine(name: String): BaseModel<MedicineModel>

}
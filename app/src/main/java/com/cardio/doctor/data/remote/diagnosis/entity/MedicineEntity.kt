package com.cardio.doctor.data.remote.diagnosis.entity

import com.cardio.doctor.domain.common.model.BaseModel
import com.cardio.doctor.domain.diagnosis.MedicineModel

data class MedicineEntity(
    val drugGroup: DrugGroup
) {

    fun toModel(): BaseModel<MedicineModel> {
       return BaseModel(MedicineModel(drugGroup.name))
    }
}
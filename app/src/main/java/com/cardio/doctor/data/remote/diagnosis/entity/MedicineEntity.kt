package com.cardio.doctor.data.remote.diagnosis.entity

import com.cardio.doctor.domain.common.model.BaseModel
import com.cardio.doctor.domain.diagnosis.MedicineModel

data class MedicineEntity(
    var drugGroup: DrugGroup
) {

    fun toModel(): BaseModel<MedicineModel> {
        var drugName=drugGroup.conceptGroup?.find {
            it.conceptProperties!=null
        }?.conceptProperties?.get(0)?.name
       return BaseModel(MedicineModel(drugGroup.name,drugName))
    }
}
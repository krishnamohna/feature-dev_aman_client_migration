package com.cardio.doctor.data.remote.diagnosis.entity.medicinesearch

import com.cardio.doctor.domain.common.model.BaseModel
import com.cardio.doctor.domain.diagnosis.MedicineModel

data class MedicineEntity(
    var drugGroup: DrugGroup
) {

    fun toModel(): BaseModel<MedicineModel> {
        var drugName=drugGroup.conceptGroup?.find {
            it.conceptProperties!=null
        }?.conceptProperties?.get(0)?.name?:drugGroup.name
        drugName?.split(" ")?.let {
            if(it.isNotEmpty()) {
                drugName=it.get(0)
            }
        }
       return BaseModel(MedicineModel(drugGroup.name,drugName))
    }
}
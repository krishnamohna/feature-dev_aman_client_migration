package com.cardio.physician.data.remote.diagnosis.entity.medicinesearch

import com.cardio.physician.domain.common.model.BaseModel
import com.cardio.physician.domain.diagnosis.MedicineModel

data class MedicineEntity(
    var drugGroup: DrugGroup
) {

    fun toModel(): BaseModel<MedicineModel> {
        var drugName=drugGroup.conceptGroup?.find {
            it.conceptProperties!=null
        }?.conceptProperties?.get(0)?.name
        drugName?.split(" ")?.let {
            if(it.isNotEmpty()) {
                drugName=it.get(0).capitalize()
            }
        }
       return BaseModel(MedicineModel(drugGroup.name,drugName?:drugGroup.name))
    }
}
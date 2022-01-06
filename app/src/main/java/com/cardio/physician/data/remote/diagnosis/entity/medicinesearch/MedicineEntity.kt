package com.cardio.physician.data.remote.diagnosis.entity.medicinesearch

import com.cardio.physician.domain.common.model.BaseModel
import com.cardio.physician.domain.diagnosis.MedicineModel

data class MedicineEntity(
    var drugGroup: DrugGroup
) {

    fun toModel(searchedMed: String): BaseModel<MedicineModel> {
        var drugName=drugGroup.conceptGroup?.find {
            !it.conceptProperties.isNullOrEmpty() && it.conceptProperties?.get(0)?.name.get(0).isLetter()
        }?.conceptProperties?.get(0)?.name?.split(" ")?.firstOrNull()?.capitalize()
        //medicine name can not be null even if it is not found
       return BaseModel(MedicineModel(drugGroup.name,drugName?:searchedMed,searchedMed))
    }
}
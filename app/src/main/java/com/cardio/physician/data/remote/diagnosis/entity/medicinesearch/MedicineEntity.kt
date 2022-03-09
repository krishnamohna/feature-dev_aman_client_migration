package com.cardio.physician.data.remote.diagnosis.entity.medicinesearch

import com.cardio.physician.domain.common.model.BaseModel
import com.cardio.physician.domain.diagnosis.MedicineModel
import java.util.*

data class MedicineEntity(
    var drugGroup: DrugGroup
) /*{

    fun toModel(searchedMed: String): BaseModel<MedicineModel> {
        var drugName=drugGroup.conceptGroup?.find {
            !it.conceptProperties.isNullOrEmpty() && it.conceptProperties?.get(0)?.name.get(0).isLetter()
        }?.conceptProperties?.get(0)?.name?.split(" ")?.firstOrNull()?.capitalize()
        //medicine name can not be null even if it is not found
       return BaseModel(MedicineModel(drugGroup.name,drugName?:searchedMed,searchedMed))
    }
}*/
{

    fun toBaseModel(searchedMed: String): BaseModel<MedicineModel> {
        //get list of dosage
        val listDosage = mutableListOf<String>()
        drugGroup.conceptGroup?.filter {
            !it.conceptProperties.isNullOrEmpty()
        }?.forEach {
            it.conceptProperties?.forEach { it ->
                parseDosage(it.name)?.let { dosage ->
                    if (!listDosage.contains(dosage))
                        listDosage.add(dosage)
                }
            }
        }
        val medModel = MedicineModel(searchedMed, searchedMed)
        medModel.listDosage = listDosage
        return BaseModel(medModel)
    }

    private fun parseDosage(name: String): String? {
        return name.indexOf("MG ").let { index ->
            var dosage: String? = null
            if (index != -1 && index > 4) {
                dosage = name.substring(index - 5, index).trim()
                if (dosage[0].isLetter())
                    dosage = dosage.removeRange(0, 1).trim()
                if (dosage[0].isLetter())
                    dosage = dosage.removeRange(0, 1).trim()
            }
            dosage
        }
    }

    fun getDrugName(): String? {
        return drugGroup.conceptGroup?.find {
            !it.conceptProperties.isNullOrEmpty()
        }?.conceptProperties?.find {
            it.name.isNotBlank() && it.name[0].isLetter()
        }?.name?.split(" ")?.let {
            if (it.isNotEmpty()) {
                it.get(0).capitalize(Locale.ROOT)
            } else
                null
        }
    }
}
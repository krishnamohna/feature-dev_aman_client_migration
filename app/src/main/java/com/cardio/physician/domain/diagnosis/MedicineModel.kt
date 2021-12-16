package com.cardio.physician.domain.diagnosis

data class MedicineModel(@Transient val drugGroupName:String?, val drugName: String?, @Transient val  searchedMed:String?) {
}
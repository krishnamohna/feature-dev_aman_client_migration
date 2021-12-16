package com.cardio.physician.domain.addpatient

data class PatientModel(val userId:String?, val firstName:String?, val lastName:String?, val imageUrl:String?){
    var isAdded: Boolean = false
}

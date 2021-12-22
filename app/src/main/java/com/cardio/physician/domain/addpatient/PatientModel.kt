package com.cardio.physician.domain.addpatient

data class PatientModel(val userId:String?, val firstName:String?, val lastName:String?, val imageUrl:String?){
    var isAdded: Int = 0 //0 -> not requested, 1 -> added, 2 -> requested
}

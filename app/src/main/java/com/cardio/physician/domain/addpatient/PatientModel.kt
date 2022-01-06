package com.cardio.physician.domain.addpatient

data class PatientModel(val userId:String?, var firstName:String?, var lastName:String?, val imageUrl:String?, val email:String?, ){
    var isAdded: Int = 0 //0 -> not requested, 1 -> added, 2 -> requested
}

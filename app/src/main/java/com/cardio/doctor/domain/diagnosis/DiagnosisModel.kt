package com.cardio.doctor.domain.diagnosis

import com.cardio.doctor.domain.questionare.model.QuestionModel

class DiagnosisModel(

) {
    var timeStamp: Long?=null
    var questionnaire: List<QuestionModel>?=null
    var ailment: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var age: String? = null
    var weight: Double? = null
    var heartRate: Int? = null
    var topBp: Int? = null
    var bottomBp: Int? = null
    var mediciene: String? = null
    var medications: List<MedicineModel>? = null
}

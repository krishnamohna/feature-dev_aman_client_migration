package com.cardio.physician.domain.diagnosis

import com.cardio.physician.domain.questionare.model.QuestionModel

class DiagnosisModel(

) {
    var date: String?=null
    var timeStamp: Long?=null
    var questionnaire: List<QuestionModel>?=null
    var ailment: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var age: String? = null
    var weight: String? = null
    var heartRate: String? = null
    var topBp: String? = null
    var bottomBp: String? = null
    var mediciene: String? = null
    var medications: List<MedicineModel>? = null
}

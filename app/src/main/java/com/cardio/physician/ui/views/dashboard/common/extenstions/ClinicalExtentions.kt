package com.cardio.physician.ui.views.dashboard.common.extenstions

import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.CHFConsideration
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.QuestionTypes

fun DiagnosisModel.getPacemakerQuestion(): QuestionModel? {
    return questionnaire?.firstOrNull {
        it.isPacemakerQuestion()
    }
}

fun DiagnosisModel.getDefibrillatorQuestion(): QuestionModel? {
    return questionnaire?.getDefibrillatorQuestion()
}

fun DiagnosisModel.getEjectionFractionQuestion(): QuestionModel? {
    return questionnaire?.firstOrNull {
        it.type == QuestionTypes.TYPE_2 && it.id == FireStoreDocKey.EJECTION_FRACTION_QUESTION_ID
    }
}

fun DiagnosisModel.getDryWeight(): QuestionModel? {
    return questionnaire?.firstOrNull {
        it.type == QuestionTypes.TYPE_2 && it.id == FireStoreDocKey.DRY_WEIGHT_QUESTION_ID
    }
}

fun QuestionModel.isPacemakerQuestion(): Boolean {
    return type == QuestionTypes.TYPE_3 && id == FireStoreDocKey.PACEMAKER_QUESTION_ID
}

fun List<QuestionModel>.getDefibrillatorQuestion(): QuestionModel? {
    return firstOrNull {
        it.type == QuestionTypes.TYPE_3 && it.id == FireStoreDocKey.DEFIBRILLAOR_QUESTION_ID
    }
}

fun DiagnosisModel.isEjectionFractionEqualGreaterThan45(): Boolean {
    var isTrue = false
    getEjectionFractionQuestion()?.let { ef ->
        isTrue = !ef.answer.equals(FireStoreDocKey.UNKNOWN) && ef.answer!!.toInt() >= CHFConsideration.EJECTION_FRACTION_REFRENCE_45
    }
    return isTrue
}

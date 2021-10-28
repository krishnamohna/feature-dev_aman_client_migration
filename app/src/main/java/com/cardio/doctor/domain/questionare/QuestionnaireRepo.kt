package com.cardio.doctor.domain.questionare

import com.cardio.doctor.domain.questionare.model.QuestionModel

interface QuestionnaireRepo {
    suspend fun  getQuestionnaires(): List<QuestionModel>
}
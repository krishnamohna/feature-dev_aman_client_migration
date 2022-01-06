package com.cardio.physician.domain.questionare

import com.cardio.physician.domain.questionare.model.QuestionModel

interface QuestionnaireRepo {
    suspend fun getQuestionnaires(): List<QuestionModel>
}
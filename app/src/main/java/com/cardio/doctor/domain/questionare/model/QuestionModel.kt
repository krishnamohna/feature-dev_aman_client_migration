package com.cardio.doctor.domain.questionare.model

data class QuestionModel(
    val type: Long?,
    val question: String?,
    val option_1: String?,
    val option_2: String?,
    val option_3: String?
)
package com.cardio.physician.domain.questionare.model

data class QuestionModel(
    val type: Long?,
    val question: String?,
    val option_1: String?,
    val option_2: String?,
    val option_3: String?,
    val option_4: String?,
    val position: Long?,
    val secondary_option_1: String?,
    val secondary_option_2: String?,
    val secondary_option_3: String?,
    var answer:String?=null,
    var answerSecondary:String?=null
)
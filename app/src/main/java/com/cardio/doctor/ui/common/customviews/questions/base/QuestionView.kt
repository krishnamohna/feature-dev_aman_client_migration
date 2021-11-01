package com.cardio.doctor.ui.common.customviews.questions.base

interface QuestionView {
    fun isQuestionAnswered():Boolean
    fun registerOnAnswerChange(callback:(Boolean)->Unit)
}
package com.cardio.physician.ui.common.customviews.questions.base

interface QuestionView {
    fun isQuestionAnswered():Boolean
    fun registerOnAnswerChange(callback:(Boolean)->Unit)
}
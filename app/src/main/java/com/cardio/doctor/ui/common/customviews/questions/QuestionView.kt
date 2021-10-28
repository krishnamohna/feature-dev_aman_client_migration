package com.cardio.doctor.ui.common.customviews.questions

import com.cardio.doctor.domain.questionare.model.QuestionModel

interface QuestionView {
    fun showQuestion(questionModel: QuestionModel)
    fun getAnswer()
    fun isQuestionValid(questionModel: QuestionModel):Boolean
}
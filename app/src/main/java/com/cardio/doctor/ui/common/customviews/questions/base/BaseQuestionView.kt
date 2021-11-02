package com.cardio.doctor.ui.common.customviews.questions.base

import android.content.Context
import android.widget.FrameLayout
import com.cardio.doctor.domain.questionare.model.QuestionModel

abstract class BaseQuestionView @JvmOverloads constructor(
    context: Context,
    val question: QuestionModel,
) :
    FrameLayout(context), QuestionView {
    var callback: ((Boolean) -> Unit?)? = null

    abstract fun showQuestion(question: QuestionModel)
    abstract fun isQuestionValid(question: QuestionModel): Boolean

    fun answerChanged() {
        callback?.invoke(isQuestionAnswered())
    }

    override fun registerOnAnswerChange(callback: (Boolean) -> Unit) {
        this.callback = callback
    }
}
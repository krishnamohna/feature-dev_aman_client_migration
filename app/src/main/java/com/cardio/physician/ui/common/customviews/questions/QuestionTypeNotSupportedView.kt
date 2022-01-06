package com.cardio.physician.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import com.cardio.physician.databinding.CompoundQuestionTypeNotSupportedLayoutBinding
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView

class QuestionTypeNotSupportedView @JvmOverloads constructor(
    context: Context,
    question: QuestionModel,
    isEnabled:Boolean=true
) :
    BaseQuestionView(context,question) {
    private var binding: CompoundQuestionTypeNotSupportedLayoutBinding =
        CompoundQuestionTypeNotSupportedLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    override fun showQuestion(questionModel: QuestionModel) {

    }

    override fun isQuestionValid(question: QuestionModel): Boolean {
        return true
    }

    override fun setViewEnabled(isEnabled: Boolean) {

    }

    override fun isQuestionAnswered(): Boolean {
        return true
    }

}
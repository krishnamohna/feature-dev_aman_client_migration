package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import com.cardio.doctor.databinding.CompoundQuestionTypeNotSupportedLayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.ui.common.customviews.questions.base.BaseQuestionView

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
        TODO("Not yet implemented")
    }

    override fun setViewEnabled(isEnabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isQuestionAnswered(): Boolean {
        TODO("Not yet implemented")
    }

}
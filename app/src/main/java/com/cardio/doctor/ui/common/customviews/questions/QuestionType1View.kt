package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.cardio.doctor.databinding.CompoundQuestionType1LayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel

class QuestionType1View @JvmOverloads constructor(context: Context, question: QuestionModel) :
    FrameLayout(context), QuestionView {
    private var binding: CompoundQuestionType1LayoutBinding =
        CompoundQuestionType1LayoutBinding.inflate(LayoutInflater.from(context), this, true)
    init {
        showQuestion(question)
    }

    override fun showQuestion(questionModel: QuestionModel) {
        if (isQuestionValid(questionModel)) {
            binding.tvQuestion.setText(questionModel.question)
            binding.rbOption1.setText(questionModel.option_1)
            binding.rbOption2.setText(questionModel.option_2)
            binding.rbOption3.setText(questionModel.option_3)
        }
    }

    override fun isQuestionValid(questionModel: QuestionModel): Boolean {
        //check all parameter here first
        return true
    }

    override fun getAnswer() {
        TODO("Not yet implemented")
    }
}
package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.cardio.doctor.databinding.CompoundQuestionType2LayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel

class QuestionType2View @JvmOverloads constructor(
    context: Context,
    val question: QuestionModel,
) :
    FrameLayout(context), QuestionView {
    private val binding: CompoundQuestionType2LayoutBinding =
        CompoundQuestionType2LayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        showQuestion(question)
    }

    override fun showQuestion(questionModel: QuestionModel) {
        if (isQuestionValid(questionModel)) {
            binding.tvQuestionType2.setText("${questionModel.position}. ${questionModel.question}")
            binding.rbOption2Type2.setText(questionModel.option_2)
        }
    }

    override fun getAnswer() {
        TODO("Not yet implemented")
    }

    override fun isQuestionValid(questionModel: QuestionModel): Boolean {
        return true
    }
}
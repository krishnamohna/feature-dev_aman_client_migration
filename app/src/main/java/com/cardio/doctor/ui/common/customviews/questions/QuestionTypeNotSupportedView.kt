package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.cardio.doctor.databinding.CompoundQuestionTypeNotSupportedLayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel

class QuestionTypeNotSupportedView @JvmOverloads constructor(context: Context, val queston: QuestionModel) :
    FrameLayout(context), QuestionView {
    private var binding: CompoundQuestionTypeNotSupportedLayoutBinding =
        CompoundQuestionTypeNotSupportedLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    override fun showQuestion(questionModel: QuestionModel) {

    }

    override fun getAnswer() {
        TODO("Not yet implemented")
    }

    override fun isQuestionValid(questionModel: QuestionModel): Boolean {
        //check all parameter here first
        return true
    }
}
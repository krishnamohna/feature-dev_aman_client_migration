package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import com.cardio.doctor.databinding.CompoundQuestionType2LayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.ui.common.customviews.questions.base.BaseQuestionView

class QuestionType2View @JvmOverloads constructor(
    context: Context,
    question: QuestionModel,
) :
    BaseQuestionView(context,question) {
    private val binding: CompoundQuestionType2LayoutBinding =
        CompoundQuestionType2LayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setListeners()
        showQuestion(question)
    }

    private fun setListeners() {
        binding.rbOption2Type2.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                answerChanged()
                question.answer = binding.rbOption2Type2.text.toString()
                binding.edtInput.setText("")
            }
        }
        binding.edtInput.addTextChangedListener { editable->
            editable?.isNotEmpty()?.let {
                binding.rbOption2Type2.isChecked = false
                if(it){
                    answerChanged()
                    question.answer=editable.toString()
                }
            }
        }
    }


    override fun showQuestion(questionModel: QuestionModel) {
        if (isQuestionValid(questionModel)) {
            binding.tvQuestionType2.setText("${questionModel.position}. ${questionModel.question}")
            binding.rbOption2Type2.setText(questionModel.option_2)
            setSelection()
        }
    }

    private fun setSelection() {
        question.answer?.let {
            if (it == binding.rbOption2Type2.text.toString()) {
                binding.rbOption2Type2.isChecked = true
            } else {
                binding.edtInput.setText(it)
            }
        }
    }

    override fun isQuestionValid(questionModel: QuestionModel): Boolean {
        return true
    }

    override fun isQuestionAnswered(): Boolean {
        return !question.answer.isNullOrBlank()
    }

}
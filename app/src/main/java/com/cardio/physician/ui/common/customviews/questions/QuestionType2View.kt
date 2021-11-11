package com.cardio.physician.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import com.cardio.physician.databinding.CompoundQuestionType2LayoutBinding
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView

class QuestionType2View @JvmOverloads constructor(
    context: Context,
    question: QuestionModel,
    isEnabled:Boolean=true
) :
    BaseQuestionView(context,question) {
    private val binding: CompoundQuestionType2LayoutBinding =
        CompoundQuestionType2LayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setListeners()
        showQuestion(question)
        setViewEnabled(isEnabled)
    }

    private fun setListeners() {
        binding.rbOption2Type2.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                question.answer = binding.rbOption2Type2.text.toString()
                clearEdtField()
                answerChanged()
            }
        }
        binding.edtInput.addTextChangedListener { editable->
            editable?.isNotEmpty()?.let {
                if (binding.edtInput.hasFocus()) {
                    binding.rbOption2Type2.isChecked = false
                    if(it){
                        question.answer=editable.toString()
                        answerChanged()
                    }
                }
            }
        }
    }

    private fun clearEdtField() {
        binding.edtInput.clearFocus()
        binding.edtInput.text?.clear()
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

    override fun setViewEnabled(isEnabled: Boolean) {
         binding.edtInput.isEnabled=isEnabled
         binding.rbOption2Type2.isClickable=isEnabled
    }

    override fun isQuestionAnswered(): Boolean {
        return !question.answer.isNullOrBlank()
    }

}
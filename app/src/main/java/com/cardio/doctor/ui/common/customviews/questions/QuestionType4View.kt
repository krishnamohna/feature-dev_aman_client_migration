package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import android.widget.RadioButton
import com.cardio.doctor.databinding.CompoundQuestionType4LayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.ui.common.customviews.questions.base.BaseQuestionView

class QuestionType4View @JvmOverloads constructor(
    context: Context,
    question: QuestionModel,
    isEnabled:Boolean=true
) :
    BaseQuestionView(context,question) {

    private var binding: CompoundQuestionType4LayoutBinding =
        CompoundQuestionType4LayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setListeners()
        showQuestion(question)
        setViewEnabled(isEnabled)
    }

    private fun setListeners() {
        binding.radioGroupType4.setOnCheckedChangeListener { radioGroup, id ->
            if((radioGroup.findViewById<RadioButton>(id)).isChecked){
                var answer=(radioGroup.findViewById<RadioButton>(id)).text.toString()
                question.answer=answer
                answerChanged()
            }else if(radioGroup.checkedRadioButtonId==-1){
                question.answer=null
                answerChanged()
            }
        }
    }

    override fun showQuestion(questionModel: QuestionModel) {
        if (isQuestionValid(questionModel)) {
            binding.tvQuestionType4.setText("${questionModel.position}. ${questionModel.question}")
            binding.rbOption1.setText(questionModel.option_1)
            binding.rbOption2.setText(questionModel.option_2)
            binding.rbOption3.setText(questionModel.option_3)
            binding.rbOption4.setText(questionModel.option_1)
            setSelection()
        }
    }

    private fun setSelection() {
        question.answer?.let {
            for (i in 0..binding.radioGroupType4.childCount) {
                var radioButton=binding.radioGroupType4.getChildAt(i) as RadioButton
                if(it.equals(radioButton.text)){
                    radioButton.isChecked=true
                    break
                }
            }
        }
    }

    override fun isQuestionValid(questionModel: QuestionModel): Boolean {
        //check all parameter here first
        return true
    }

    override fun setViewEnabled(isEnabled: Boolean) {
        binding.rbOption1.isEnabled=isEnabled
        binding.rbOption2.isEnabled=isEnabled
        binding.rbOption3.isEnabled=isEnabled
        binding.rbOption4.isEnabled=isEnabled
    }

    override fun isQuestionAnswered(): Boolean {
        return !question.answer.isNullOrBlank()
    }

}
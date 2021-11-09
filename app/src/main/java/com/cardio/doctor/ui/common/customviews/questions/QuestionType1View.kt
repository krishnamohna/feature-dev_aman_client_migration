package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import android.widget.RadioButton
import com.cardio.doctor.databinding.CompoundQuestionType1LayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.ui.common.customviews.questions.base.BaseQuestionView

class QuestionType1View @JvmOverloads constructor(context: Context,  question: QuestionModel,isEnabled:Boolean=true) :
    BaseQuestionView(context,question){
    private var binding: CompoundQuestionType1LayoutBinding =
        CompoundQuestionType1LayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setListeners()
        showQuestion(question)
        setViewEnabled(isEnabled)
    }

    private fun setListeners() {
        binding.radioGroupQuestion.setOnCheckedChangeListener { radioGroup, id ->
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
            binding.tvQuestion.setText("${questionModel.position}. ${questionModel.question}")
            binding.rbOption1.setText(questionModel.option_1)
            binding.rbOption2.setText(questionModel.option_2)
            binding.rbOption3.setText(questionModel.option_3)
            setSelection()
        }
    }

    private fun setSelection() {
        question.answer?.let {
            for (i in 0..binding.radioGroupQuestion.childCount) {
                var radioButton=binding.radioGroupQuestion.getChildAt(i) as RadioButton
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
        binding.rbOption1.isClickable=isEnabled
        binding.rbOption2.isClickable=isEnabled
        binding.rbOption3.isClickable=isEnabled
        binding.rbLead2.isClickable=isEnabled
        binding.rbLead3.isClickable=isEnabled
        binding.rbUnknown.isClickable=isEnabled
    }

    override fun isQuestionAnswered(): Boolean {
        return !question.answer.isNullOrBlank()
    }

}
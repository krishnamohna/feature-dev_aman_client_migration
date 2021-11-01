package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.RadioButton
import com.cardio.doctor.databinding.CompoundQuestionType1LayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel

class QuestionType3View @JvmOverloads constructor(context: Context, val question: QuestionModel) :
    FrameLayout(context), QuestionView {
    private var binding: CompoundQuestionType1LayoutBinding=
        CompoundQuestionType1LayoutBinding.inflate(LayoutInflater.from(context), this, true)
    init {
        showQuestion(question)
        setListeners()
    }

    private fun setListeners() {
        binding.radioGroupQuestion.setOnCheckedChangeListener { radioGroup, id ->
            if((radioGroup.findViewById<RadioButton>(id)).isChecked){
                var answer=(radioGroup.findViewById<RadioButton>(id)).text.toString()
                question.answer=answer
            }else if(radioGroup.checkedRadioButtonId==-1){
                question.answer=null
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

    override fun getAnswer() {
        TODO("Not yet implemented")
    }

    override fun isQuestionValid(questionModel: QuestionModel): Boolean {
        //check all parameter here first
        return true
    }
}
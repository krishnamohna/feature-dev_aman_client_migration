package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.RadioButton
import com.cardio.doctor.databinding.CompoundQuestionType4LayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel

class QuestionType4View @JvmOverloads constructor(context: Context, val question: QuestionModel) :
    FrameLayout(context), QuestionView {

    private var binding: CompoundQuestionType4LayoutBinding =
        CompoundQuestionType4LayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        showQuestion(question)
        setListeners()
    }

    private fun setListeners() {
        binding.radioGroupType4.setOnCheckedChangeListener { radioGroup, id ->
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

    override fun getAnswer() {
        TODO("Not yet implemented")
    }

    override fun isQuestionValid(questionModel: QuestionModel): Boolean {
        //check all parameter here first
        return true
    }
}
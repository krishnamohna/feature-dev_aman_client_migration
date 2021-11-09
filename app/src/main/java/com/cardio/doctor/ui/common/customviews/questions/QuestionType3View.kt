package com.cardio.doctor.ui.common.customviews.questions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import com.cardio.doctor.R
import com.cardio.doctor.databinding.CompoundQuestionType1LayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.ui.common.customviews.questions.base.BaseQuestionView

class QuestionType3View @JvmOverloads constructor(
    context: Context,
    question: QuestionModel,
    isEnabled: Boolean = true,
) :
    BaseQuestionView(context, question) {
    private var binding: CompoundQuestionType1LayoutBinding =
        CompoundQuestionType1LayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setListeners()
        showQuestion(question)
        setViewEnabled(isEnabled)
    }

    private fun setListeners() {
        binding.radioGroupQuestion.setOnCheckedChangeListener { radioGroup, id ->
            if ((radioGroup.findViewById<RadioButton>(id)).isChecked) {
                var answer = (radioGroup.findViewById<RadioButton>(id)).text.toString()
                question.answer = answer
                answerChanged()
                if (id == R.id.rbOption1) {
                    showSecondaryOptions(true)
                } else {
                    showSecondaryOptions(false)
                }
            } else if (radioGroup.checkedRadioButtonId == -1) {
                question.answer = null
                answerChanged()
            }
        }

        binding.rbGroupLeads.setOnCheckedChangeListener { radioGroup, id ->
            if ((radioGroup.findViewById<RadioButton>(id)).isChecked) {
                var answer = (radioGroup.findViewById<RadioButton>(id)).text.toString()
                question.answerSecondary = answer
            } else if (radioGroup.checkedRadioButtonId == -1) {
                question.answerSecondary = null
            }
        }
    }

    private fun showSecondaryOptions(show: Boolean) {
        if (show) {
            binding.tvLeadsLabel.visibility = View.VISIBLE
            binding.rbGroupLeads.visibility = View.VISIBLE
        } else {
            binding.tvLeadsLabel.visibility = View.GONE
            binding.rbGroupLeads.visibility = View.GONE
        }
    }

    override fun showQuestion(questionModel: QuestionModel) {
        if (isQuestionValid(questionModel)) {
            binding.tvQuestion.setText("${questionModel.position}. ${questionModel.question}")
            binding.rbOption1.setText(questionModel.option_1)
            binding.rbOption2.setText(questionModel.option_2)
            binding.rbOption3.setText(questionModel.option_3)
            binding.rbLead3.setText(questionModel.secondary_option_1)
            binding.rbLead2.setText(questionModel.secondary_option_2)
            binding.rbUnknown.setText(questionModel.secondary_option_3)
            setSelection()
        }
        //keep secondary option invisible till user select yes
        // showSecondaryOptions(false)
    }

    private fun setSelection() {
        question.answer?.let {
            for (i in 0..binding.radioGroupQuestion.childCount) {
                var radioButton = binding.radioGroupQuestion.getChildAt(i) as RadioButton
                if (it.equals(radioButton.text.toString())) {
                    radioButton.isChecked = true
                    if (radioButton.id == R.id.rbOption1) {
                        showSecondaryOptions(true)
                    }
                    break
                }
            }
        }
        question.answerSecondary?.let {
            for (j in 0..binding.rbGroupLeads.childCount) {
                var radioButton = binding.rbGroupLeads.getChildAt(j) as RadioButton
                if (it.equals(radioButton.text.toString())) {
                    radioButton.isChecked = true
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
        binding.rbOption1.isClickable = isEnabled
        binding.rbOption2.isClickable = isEnabled
        binding.rbOption3.isClickable = isEnabled
        binding.rbLead2.isClickable = isEnabled
        binding.rbLead3.isClickable = isEnabled
        binding.rbUnknown.isClickable = isEnabled
    }

    override fun isQuestionAnswered(): Boolean {
        return !question.answer.isNullOrBlank() && hasGivenSecondaryAns()
    }

    private fun hasGivenSecondaryAns(): Boolean {
        if (binding.rbOption1.isChecked) {
            if (binding.rbGroupLeads.checkedRadioButtonId == -1)
                return false
        }
        return true
    }
}
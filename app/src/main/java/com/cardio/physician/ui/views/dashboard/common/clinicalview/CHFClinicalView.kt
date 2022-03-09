package com.cardio.physician.ui.views.dashboard.common.clinicalview

import android.view.View
import android.widget.TextView
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.ui.common.utils.Constants
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.DEFIBRILLAOR_QUESTION_ID
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.DRY_WEIGHT_QUESTION_ID
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.EJECTION_FRACTION_QUESTION_ID
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.PACEMAKER_QUESTION_ID
import com.cardio.physician.ui.common.utils.QuestionTypes
import com.cardio.physician.ui.views.dashboard.common.extenstions.getPacemakerQuestion

class CHFClinicalView : BaseClinicalView() {

    companion object {
        const val FAILURE_TYPE = "Heart Failure"
    }

    fun showClinicalData(diagnosisModel: DiagnosisModel, binding: FragmentDashboardBinding) {
        //manage visibility of views
        manageVisibilityOfViews(binding)
        setChFDiagnosisData(binding, diagnosisModel)
    }

    private fun setChFDiagnosisData(
        binding: FragmentDashboardBinding,
        diagnosisModel: DiagnosisModel,
    ) {
        if (binding.includeBasicInfo.tvDiagnosis.text.toString()
                .isBlank() || binding.includeBasicInfo.tvDiagnosis.text.toString()
                .equals(FAILURE_TYPE, true)
        )
            binding.includeBasicInfo.tvDiagnosis.setText(FAILURE_TYPE)
        else
            binding.includeBasicInfo.tvDiagnosis.append(", $FAILURE_TYPE")
        //lets add try catch incase any variable is null or something
        try {
            getChfType(diagnosisModel)?.let { chfType ->
                binding.includeChf.tvChfType.text = chfType
                manageTextViewColor(chfType, binding.includeChf.tvChfType)
            }
            getEf(diagnosisModel)?.let { ef ->
                binding.includeChf.tvEfDash.text = " ${ef}"
                manageTextViewColor(ef, binding.includeChf.tvEfDash)
            }
            getNyhaClass(diagnosisModel)?.let { nyhaClass ->
                binding.includeChf.tvNyhaClass.text = nyhaClass
            }
            getDryWeight(diagnosisModel)?.let { dryWeight ->
                binding.includeChf.tvDryWeightDash.text = dryWeight
                manageTextViewColor(dryWeight, binding.includeChf.tvDryWeightDash)
            }
           /* getPaceMakerLeads(diagnosisModel)?.let { lead ->
                binding.includeChf.tvCardiacDevices.text = lead
            }
            getDefibrillatorLeads(diagnosisModel)?.let { lead ->
                binding.includeChf.tvCardiacDevices.append(lead)
            }*/
            //clear text first
            binding.includeChf.tvCardiacDevices.text=""
            getDefibrillatorLeads(diagnosisModel)?.let { lead ->
                binding.includeChf.tvCardiacDevices.text = lead
            }
            getPaceMakerLeads(diagnosisModel)?.let { lead ->
                if(binding.includeChf.tvCardiacDevices.text.isNotBlank())
                    binding.includeChf.tvCardiacDevices.append(" & ")
                binding.includeChf.tvCardiacDevices.append(lead)
            }
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
    }

    private fun manageTextViewColor(chfType: String, tvChfType: TextView) {
        if (chfType.equals(tvChfType.context.getString(R.string.incomplete), true))
            tvChfType.setTextColor(tvChfType.context.getColor(R.color.colorPrimary))
        else
            tvChfType.setTextColor(tvChfType.context.getColor(R.color.black))
    }

    override fun manageVisibilityOfViews(binding: FragmentDashboardBinding) {
        super.manageVisibilityOfViews(binding)
        binding.includeChf.cvChf.visibility = View.VISIBLE
    }

    /*private fun getPaceMakerLeads(diagnosisModel: DiagnosisModel): String? {
        return diagnosisModel.questionnaire?.firstOrNull {
            it.type == QuestionTypes.TYPE_3 && it.id == PACEMAKER_QUESTION_ID
        }?.let { question ->
            if (!question.answer.equals(FireStoreDocKey.UNKNOWN, true) && !question.answer.equals(
                    FireStoreDocKey.NO,
                    true)
            ) {
                "${question.answerSecondary} lead pacemaker"
            } else if (question.answer.equals(
                    FireStoreDocKey.NO,
                    true)
            ) {
                "${question.answer} lead pacemaker"
            } else {
                "Unknown pacemaker"
            }
        }
    }*/

    private fun getPaceMakerLeads(diagnosisModel: DiagnosisModel): String? {
        return diagnosisModel.getPacemakerQuestion()?.let { question ->
            if(!question.isAnswered())return null
            if (!question.answer.equals(FireStoreDocKey.UNKNOWN, true) && !question.answer.equals(
                    FireStoreDocKey.NO,
                    true)
            ) {
                "${question.answerSecondary} lead pacemaker"
            } else if (question.answer.equals(
                    FireStoreDocKey.NO,
                    true)
            ) {
                "${question.answer} lead pacemaker"
            } else {
                "Unknown pacemaker"
            }
        }
    }

    private fun getDefibrillatorLeads(diagnosisModel: DiagnosisModel): String? {
        return diagnosisModel.questionnaire?.firstOrNull {
            it.type == QuestionTypes.TYPE_3 && it.id == DEFIBRILLAOR_QUESTION_ID
        }?.let { question ->
            if (!question.answer.equals(FireStoreDocKey.UNKNOWN, true) && !question.answer.equals(
                    FireStoreDocKey.NO,
                    true)
            ) {
                "${question.answerSecondary} lead defibrillator"
            } else if (question.answer.equals(
                    FireStoreDocKey.NO,
                    true)
            ) {
                "${question.answer} lead defibrillator"
            } else {
                "Unknown defibrillator"
            }
        }
    }


    private fun getDryWeight(diagnosisModel: DiagnosisModel): String? {
        return diagnosisModel.questionnaire?.firstOrNull {
            it.type == QuestionTypes.TYPE_2 && it.id == DRY_WEIGHT_QUESTION_ID
        }?.let { question ->
            if (!question.answer.equals(FireStoreDocKey.UNKNOWN, true)) {
                "${question.answer} lbs"
            } else {
                context?.getString(R.string.incomplete) ?: Constants.ANSWER_UNKOWN
            }
        }
    }

    private fun getNyhaClass(diagnosisModel: DiagnosisModel): String? {
        return diagnosisModel.questionnaire?.firstOrNull {
            it.type == QuestionTypes.TYPE_4
        }?.let { question ->
            when (question.answer) {
                question.option_1 -> "1"
                question.option_2 -> "2"
                question.option_3 -> "3"
                question.option_4 -> "4"
                else -> ""
            }
        }
    }

    private fun getEf(diagnosisModel: DiagnosisModel): String? {
        return diagnosisModel.questionnaire?.firstOrNull {
            it.type == QuestionTypes.TYPE_2 && it.id == EJECTION_FRACTION_QUESTION_ID
        }?.let { question ->
            if (!question.answer.equals(FireStoreDocKey.UNKNOWN, true)) {
                question.answer?.toIntOrNull()?.toString()
            } else {
                context?.getString(R.string.incomplete) ?: Constants.ANSWER_UNKOWN
            }
        }
    }

    private fun getChfType(diagnosisModel: DiagnosisModel): String? {
        return diagnosisModel.questionnaire?.firstOrNull {
            it.type == QuestionTypes.TYPE_2 && it.id == EJECTION_FRACTION_QUESTION_ID
        }?.let { question ->
            if (question.answer.equals(FireStoreDocKey.UNKNOWN, true)) {
                context?.getString(R.string.incomplete) ?: Constants.ANSWER_UNKOWN
            } else {
                var ejectionFraction = question.answer?.toIntOrNull()
                ejectionFraction?.let {
                    if (ejectionFraction < 45) "HFrEF(<45%)" else "HFrEF(>45%)"
                }
            }
        }
    }

}
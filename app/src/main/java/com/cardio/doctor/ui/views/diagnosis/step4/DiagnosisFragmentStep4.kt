package com.cardio.doctor.ui.views.diagnosis.step4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDiagnosisPart4Binding
import com.cardio.doctor.databinding.ItemMedicationPreviewLayoutBinding
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.ui.common.customviews.questions.*
import com.cardio.doctor.ui.common.utils.QuestionTypes
import com.cardio.doctor.ui.common.utils.showToast
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiagnosisFragmentStep4 : BaseDiagnosisFragment<FragmentDiagnosisPart4Binding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDiagnosisPart4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setListeners()
    }

    private fun setViews() {
        binding.cvDiagnosisBottomContainer.btCancel.setText(getString(R.string.back))
        setStepView(binding.stepView.stepView)
        diagnosisActivity?.getDiagnosisModel()?.firstName?.let {
            binding.clPatientDetail.edtFirstName.setText(it)
        }
        diagnosisActivity?.getDiagnosisModel()?.lastName?.let {
            binding.clPatientDetail.edtLastName.setText(it)
        }
        diagnosisActivity?.getDiagnosisModel()?.heartRate?.let {
            binding.clPatientDetail.edtHeartRate2.setText(it.toString())
        }
        diagnosisActivity?.getDiagnosisModel()?.weight?.let {
            binding.clPatientDetail.edtWeight2.setText(it.toString())
        }
        diagnosisActivity?.getDiagnosisModel()?.ailment?.let {
            binding.clPatientDetail.edtAilment.setText(it)
        }
        diagnosisActivity?.getDiagnosisModel()?.age?.let { binding.clPatientDetail.edtAgePreview.setText(it) }
        diagnosisActivity?.getDiagnosisModel()?.topBp?.let { binding.clPatientDetail.edtTopBpPreview.setText(it.toString()) }
        diagnosisActivity?.getDiagnosisModel()?.bottomBp?.let { binding.clPatientDetail.edtBottomBpPreview.setText(it.toString()) }
        showMedicationData()
        showQuestionare()
    }

    private fun showQuestionare() {
        binding.llQuestionareContainer.removeAllViews()
        diagnosisActivity?.getDiagnosisModel()?.questionnaire?.forEach {
            it?.run {
                showQuestion(getQuestionView(this))
            }
        }
    }

    private fun showQuestion(questionView: View) {
        binding.llQuestionareContainer.addView(questionView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    private fun getQuestionView(question: QuestionModel): View {
        return when (question.type) {
            QuestionTypes.TYPE_1 -> {
                QuestionType1View(requireActivity(), question,false)
            }
            QuestionTypes.TYPE_2 -> {
                QuestionType2View(requireActivity(), question,false)
            }
            QuestionTypes.TYPE_3 -> {
                QuestionType3View(requireActivity(), question,false)
            }
            QuestionTypes.TYPE_4 -> {
                QuestionType4View(requireActivity(), question,false)
            }
            else -> QuestionTypeNotSupportedView(requireActivity(), question)
        }
    }


    private fun showMedicationData() {
        binding.clMedication.llMedicationPreview.removeAllViews()
        diagnosisActivity?.getDiagnosisModel()?.medications?.forEach {
            var bindingItemMedication =
                ItemMedicationPreviewLayoutBinding.inflate(LayoutInflater.from(context),
                    null,
                    false)
            binding.clMedication.appCompatTextView.visibility=View.VISIBLE
            bindingItemMedication.tvMedicationPreview.setText("\\u2022 ${it.drugName}")
            binding.clMedication.llMedicationPreview.addView(bindingItemMedication.root,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT))
        }
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            showToast(parentActivity!!, R.string.coming_soon)
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
/*
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtFirstName,
            binding.clPatientDetail.tvFirstNameError,
            binding.clPatientDetail.firstNameLabel,
            binding.scrollViewStep4, parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtLastName,
            binding.clPatientDetail.tvErrorLastName,
            binding.clPatientDetail.txtLastName,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtAge,
            binding.clPatientDetail.tvAgeError,
            binding.clPatientDetail.txtAge,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtWeight,
            binding.clPatientDetail.tvWeightError,
            binding.clPatientDetail.tvWeight,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtHeightAilment,
            binding.clPatientDetail.tvHeightErorAilment,
            binding.clPatientDetail.txtHeightAilment,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtHeartRateAilment,
            binding.clPatientDetail.tvHearRateErorAilment,
            binding.clPatientDetail.txtHeartRateAilment,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtEnergy,
            binding.clPatientDetail.tvErrorEnergy,
            binding.clPatientDetail.tvEnergyLabel,
            binding.scrollViewStep4,
            parentActivity
        )
*/
    }


}
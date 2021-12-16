package com.cardio.physician.ui.views.diagnosis.step4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDiagnosisPart4Binding
import com.cardio.physician.databinding.ItemMedicationPreviewLayoutBinding
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.customviews.questions.*
import com.cardio.physician.ui.common.utils.QuestionTypes
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
import com.cardio.physician.ui.common.utils.getDateFromTimeMills
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.physician.ui.views.diagnosis.step2.DiagnosisFragmentStep2Args
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiagnosisFragmentStep4 : BaseDiagnosisFragment<FragmentDiagnosisPart4Binding>() {

    val viewModel: DiagnosisFragmentStep4ViewModel by viewModels()
    val args: DiagnosisFragmentStep4Args by navArgs()

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
        setObserver()
        setListeners()
    }


    private fun setObserver() {
        viewModel.liveSubmitDiagnosis.customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = {
                it?.let {
                    diagnosisActivity?.let { activity ->
                        showToast(activity, "Report submitted !!")
                        activity.finish()
                    }
                }
            },
            onError = ::onError
        )
    }

    private fun setViews() {
        binding.cvDiagnosisBottomContainer.btCancel.setText(getString(R.string.back))
        binding.cvDiagnosisBottomContainer.btNext.setText(getString(R.string.submit_lower_caps))
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
        diagnosisActivity?.getDiagnosisModel()?.age?.let {
            binding.clPatientDetail.edtAgePreview.setText(it)
        }
        diagnosisActivity?.getDiagnosisModel()?.topBp?.let {
            binding.clPatientDetail.edtTopBpPreview.setText(it.toString())
        }
        diagnosisActivity?.getDiagnosisModel()?.bottomBp?.let {
            binding.clPatientDetail.edtBottomBpPreview.setText(it.toString())
        }
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
                QuestionType1View(requireActivity(), question, false)
            }
            QuestionTypes.TYPE_2 -> {
                QuestionType2View(requireActivity(), question, false)
            }
            QuestionTypes.TYPE_3 -> {
                QuestionType3View(requireActivity(), question, false)
            }
            QuestionTypes.TYPE_4 -> {
                QuestionType4View(requireActivity(), question, false)
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
            binding.clMedication.clMedication.visibility = View.VISIBLE
            bindingItemMedication.tvMedicationPreview.setText("\u2022 ${it.drugName}")
            binding.clMedication.llMedicationPreview.addView(bindingItemMedication.root,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT))
        }
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            diagnosisActivity?.getDiagnosisModel()?.let {
                //set current times millis to model
                it.timeStamp=System.currentTimeMillis()
                it.date= getDateFromTimeMills(System.currentTimeMillis())
                isConnectedOrThrowMsg {
                    viewModel.submitDiagnosisReport(it, userId = args.userId)
                }
            }
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}
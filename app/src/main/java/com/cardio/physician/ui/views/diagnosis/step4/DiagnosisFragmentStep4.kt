package com.cardio.physician.ui.views.diagnosis.step4

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cardio.doctor.ui.views.diagnosis.step2.adapter.bindMedicineData
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDiagnosisPart4Binding
import com.cardio.physician.databinding.ItemMedicineAddedBinding
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.customviews.questions.*
import com.cardio.physician.ui.common.utils.QuestionTypes
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment
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
                diagnosisActivity?.let { activity ->
                    showToast(activity, "Report submitted !!")
                    activity.finish()
                    DiagnosisActivity.start(activity, args.userId)
                }
            },
            onError = ::onError
        )
    }

    private fun setViews() {
        binding.cvDiagnosisBottomContainer.btCancel.setText(getString(R.string.back))
        binding.cvDiagnosisBottomContainer.btNext.setText(getString(R.string.submit_lower_caps))
        setStepView(binding.stepView.stepView)
        diagnosisActivity?.diagnosisModel?.firstName?.let {
            binding.clPatientDetail.edtFirstName.setText(it)
        }
        diagnosisActivity?.diagnosisModel?.lastName?.let {
            binding.clPatientDetail.edtLastName.setText(it)
        }
        diagnosisActivity?.diagnosisModel?.heartRate?.let {
            binding.clPatientDetail.edtHeartRate2.setText(it.toString())
        }
        diagnosisActivity?.diagnosisModel?.weight?.let {
            binding.clPatientDetail.edtWeight2.setText(it.toString())
        }
        diagnosisActivity?.diagnosisModel?.ailment?.let {
            binding.clPatientDetail.edtAilment.setText(it)
        }
        diagnosisActivity?.diagnosisModel?.age?.let {
            binding.clPatientDetail.edtAgePreview.setText(it)
        }
        diagnosisActivity?.diagnosisModel?.topBp?.let {
            binding.clPatientDetail.edtTopBpPreview.setText(it.toString())
        }
        diagnosisActivity?.diagnosisModel?.bottomBp?.let {
            binding.clPatientDetail.edtBottomBpPreview.setText(it.toString())
        }
        diagnosisActivity?.diagnosisModel?.stepCount?.let {
            binding.clPatientDetail.edtStepCount.setText(it.toString())
        }
        showMedicationData()
        showQuestionare()
    }

    private fun showQuestionare() {
        binding.llQuestionareContainer.removeAllViews()
        diagnosisActivity?.diagnosisModel?.questionnaire?.forEach {
            if (it.isAnswered())
                showQuestion(getQuestionView(it))
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


    /*private fun showMedicationData() {
        binding.clMedication.llMedicationPreview.removeAllViews()
        diagnosisActivity?.diagnosisModel?.medications?.forEach {
            var bindingItemMedication =
                ItemMedicationPreviewLayoutBinding.inflate(LayoutInflater.from(context),
                    null,
                    false)
            binding.clMedication.clMedication.visibility = View.VISIBLE
            bindingItemMedication.tvMedicationPreview.setText("\u2022 ${it.name}")
            var params=LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(0,requireActivity().resources.getDimension(R.dimen._2sdp).toInt(),0,0)
            binding.clMedication.llMedicationPreview.addView(bindingItemMedication.root,
                params )
        }
    }*/

    private fun showMedicationData() {
        binding.clMedication.llMedicationPreview.removeAllViews()
        diagnosisActivity?.diagnosisModel?.medications?.forEach { model ->
            val bindingItemMedication =
                ItemMedicineAddedBinding.inflate(
                    LayoutInflater.from(context),
                    binding.clMedication.llMedicationPreview,
                    false
                )
            binding.clMedication.clMedication.visibility = View.VISIBLE
            bindingItemMedication.ivRemoveMed.visibility = View.GONE
            bindingItemMedication.materialCardViewMedicine.isClickable = false
            bindMedicineData(bindingItemMedication, model, requireContext())
            val params = LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(
                0,
                0,
                0,
                requireActivity().resources.getDimension(R.dimen._2sdp).toInt()
            )
            binding.clMedication.llMedicationPreview.addView(
                bindingItemMedication.root,
                params
            )
        }
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            diagnosisActivity?.diagnosisModel?.let {
                //set current times millis to model
                isConnectedOrThrowMsg {
                    viewModel.submitDiagnosisReport(it, args.userId, false)
                }
            }
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}
package com.cardio.doctor.ui.views.diagnosis.step3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.databinding.FragmentDiagnosisPart3Binding
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.ui.common.customviews.questions.*
import com.cardio.doctor.ui.common.utils.QuestionTypes
import com.cardio.doctor.ui.common.utils.extentions.customObserver
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiagnosisFragmentStep3 : BaseDiagnosisFragment<FragmentDiagnosisPart3Binding>() {

    private var questionList: List<QuestionModel>? = null
    private val viewModel: DiagnosisFragmentStep3ViewModel by viewModels()
    private var lastQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDiagnosisPart3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setViews()
        setObservers()
        init()
    }

    private fun init() {
        viewModel.getQuestions()
    }

    private fun setObservers() {
        viewModel.getQuestionLiveData().customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = {
                questionList = it
                setQuestionsInViews(questionList)
            },
            onError = ::onError
        )
    }

    private fun setQuestionsInViews(questionList: List<QuestionModel>?) {
        questionList?.isNotEmpty().let {
            var question = questionList?.get(lastQuestionIndex)
            question?.run {
                showQuestion(getQuestionView(question))
            }
        }
    }

    private fun showQuestion(questionView: View) {
        binding.tvQuestionTotalVsCurrent.setText("${(lastQuestionIndex+1)}/${questionList?.size}")
        binding.frameLayoutQuestionContainer.removeAllViews()
        binding.frameLayoutQuestionContainer.addView(questionView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT))
    }

    private fun getQuestionView(question: QuestionModel): View {
        return when (question.type) {
            QuestionTypes.TYPE_1 -> {
                QuestionType1View(requireActivity(), question)
            }
            QuestionTypes.TYPE_2 -> {
                QuestionType2View(requireActivity(), question)
            }
            QuestionTypes.TYPE_3 -> {
                QuestionType3View(requireActivity(), question)
            }
            QuestionTypes.TYPE_4 -> {
                QuestionType4View(requireActivity(), question)
            }
            else -> QuestionTypeNotSupportedView(requireActivity(), question)
        }
    }

    private fun setViews() {
        setStepView(binding.stepView.stepView)
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            findNavController().navigate(DiagnosisFragmentStep3Directions.actionDiagnosisFragmentPart3ToDiagnosisFragmentPart4())
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.imageViewQuestionRight.setOnClickListener {
            showNextQuestion()
        }
        binding.imageViewQuestionLeft.setOnClickListener {
            showPreviousQuestion()
        }
    }

    private fun showPreviousQuestion() {
        questionList?.let {
            if (lastQuestionIndex > 0) {
                lastQuestionIndex--
                var question = it.get(lastQuestionIndex)
                showQuestion(getQuestionView(question))
            }
        }
    }

    private fun showNextQuestion() {
        questionList?.let {
            if (lastQuestionIndex < it.size - 1) {
                lastQuestionIndex++
                var question = it.get(lastQuestionIndex)
                showQuestion(getQuestionView(question))
            }
        }
    }

}
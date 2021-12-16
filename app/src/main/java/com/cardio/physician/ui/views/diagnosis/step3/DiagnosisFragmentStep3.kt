package com.cardio.physician.ui.views.diagnosis.step3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDiagnosisPart3Binding
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.customviews.questions.*
import com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView
import com.cardio.physician.ui.common.customviews.questions.base.QuestionView
import com.cardio.physician.ui.common.utils.QuestionTypes
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.physician.ui.views.diagnosis.step2.DiagnosisFragmentStep2Args
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiagnosisFragmentStep3 : BaseDiagnosisFragment<FragmentDiagnosisPart3Binding>() {

    private var questionList: List<QuestionModel>? = null
    private val viewModel: DiagnosisFragmentStep3ViewModel by viewModels()
    val args: DiagnosisFragmentStep3Args by navArgs()

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
        //check if question list already exist then do not load from api
        if (diagnosisActivity?.questionList != null) {
            setQuestionsInViews(diagnosisActivity?.questionList)
        } else {
            viewModel.getQuestions()
        }
    }

    private fun setObservers() {
        viewModel.getQuestionLiveData().customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = {
                diagnosisActivity?.questionList = it
                setQuestionsInViews(it)
            },
            onError = ::onError
        )
    }

    private fun setQuestionsInViews(it: List<QuestionModel>?) {
        questionList = it
        questionList?.isNotEmpty().let {
            var question = questionList?.get(diagnosisActivity?.lastQuestionIndex!!)
            question?.run {
                showQuestion(getQuestionView(question))
            }
        }
    }

    private fun showQuestion(questionView: View) {
        manageNextBackButtonBackground()
        binding.tvQuestionTotalVsCurrent.setText("${(diagnosisActivity?.lastQuestionIndex?.plus(1))}/${questionList?.size}")
        binding.frameLayoutQuestionContainer.removeAllViews()
        binding.frameLayoutQuestionContainer.addView(questionView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT))
    }

    private fun manageNextBackButtonBackground() {
        diagnosisActivity?.lastQuestionIndex?.let { lastIndex ->
            questionList?.let { list ->
                if (lastIndex == 0) enableLeftButton(false)
                if (lastIndex == list.size - 1) enableRightButton(false)
                if (lastIndex < list.size - 1) enableRightButton(true)
                if (lastIndex > 0) enableLeftButton(true)
            }
        }
    }

    private fun enableLeftButton(isEnable: Boolean) {
        if (isEnable) {
            binding.imageViewQuestionLeft.setImageResource(R.drawable.ic_left_white)
            binding.imageViewQuestionLeft.setBackgroundResource(R.drawable.drawable_green_circular)
        } else {
            binding.imageViewQuestionLeft.setImageResource(R.drawable.ic_left_green)
            binding.imageViewQuestionLeft.setBackgroundResource(R.drawable.drawable_green_circular_border)
        }
    }

    private fun enableRightButton(isEnable: Boolean) {
        if (isEnable) {
            binding.imageViewQuestionRight.setImageResource(R.drawable.ic_right_white)
            binding.imageViewQuestionRight.setBackgroundResource(R.drawable.drawable_green_circular)
        } else {
            binding.imageViewQuestionRight.setImageResource(R.drawable.ic_right_green)
            binding.imageViewQuestionRight.setBackgroundResource(R.drawable.drawable_green_circular_border)
        }
    }

    private fun getQuestionView(question: QuestionModel): View {
        return when (question.type) {
            QuestionTypes.TYPE_1 -> {
                QuestionType1View(requireActivity(), question).registerAnswerchange()
            }
            QuestionTypes.TYPE_2 -> {
                QuestionType2View(requireActivity(), question).registerAnswerchange()
            }
            QuestionTypes.TYPE_3 -> {
                QuestionType3View(requireActivity(), question).registerAnswerchange()
            }
            QuestionTypes.TYPE_4 -> {
                QuestionType4View(requireActivity(), question).registerAnswerchange()
            }
            else -> QuestionTypeNotSupportedView(requireActivity(), question).registerAnswerchange()
        }
    }


    private fun setViews() {
        setStepView(binding.stepView.stepView)
        binding.cvDiagnosisBottomContainer.btCancel.setText(getString(R.string.back))
        enableButtonClick(hasUserGivenAllAnswers())
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            if (hasUserGivenAllAnswers()) {
                //save all questions to diagnoss model in diagnosis activity
                diagnosisActivity?.getDiagnosisModel()?.questionnaire = questionList
                findNavController().navigate(DiagnosisFragmentStep3Directions.actionDiagnosisFragmentPart3ToDiagnosisFragmentPart4(args.userId))
            } else {
                context?.let { it1 -> showToast(it1, getString(R.string.validate_all_questions)) }
            }
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

    private fun hasUserGivenAllAnswers(): Boolean {
        if (questionList == null) return false
        questionList?.forEach {
            if (it.answer.isNullOrBlank()) return false
        }
        return true
    }

    private fun showPreviousQuestion() {
        questionList?.let {
            if (diagnosisActivity?.lastQuestionIndex!! > 0) {
                diagnosisActivity?.lastQuestionIndex = diagnosisActivity?.lastQuestionIndex!! - 1
                var question = it.get(diagnosisActivity?.lastQuestionIndex!!)
                showQuestion(getQuestionView(question))
            }
        }
    }

    private fun showNextQuestion() {
        if (!hasAnsweredCurrentQuestion()) {
            showToast(requireContext(), getString(R.string.vaidation_answer_current_question_first))
            return
        }
        questionList?.let {
            if (diagnosisActivity?.lastQuestionIndex!! < it.size - 1) {
                diagnosisActivity?.lastQuestionIndex = diagnosisActivity?.lastQuestionIndex!! + 1
                var question = it.get(diagnosisActivity?.lastQuestionIndex!!)
                showQuestion(getQuestionView(question))
            }
        }
    }

    private fun hasAnsweredCurrentQuestion(): Boolean {
        if (binding.frameLayoutQuestionContainer.childCount > 0) {
            var question = binding.frameLayoutQuestionContainer.getChildAt(0) as? QuestionView?
            question?.let {
                if (!question.isQuestionAnswered())
                    return false
            }
        }
        return true
    }

    fun BaseQuestionView.registerAnswerchange(): View {
        registerOnAnswerChange {
            enableButtonClick(hasUserGivenAllAnswers())
        }
        return this
    }

    private fun enableButtonClick(isEnable: Boolean) {
        if (isEnable) {
            binding.cvDiagnosisBottomContainer.btNext.isEnabled = true
            binding.cvDiagnosisBottomContainer.btNext.alpha = 1.0f
        } else {
            binding.cvDiagnosisBottomContainer.btNext.isEnabled = false
            binding.cvDiagnosisBottomContainer.btNext.alpha = 0.3f
        }
    }

}
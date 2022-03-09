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
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.QuestionTypes
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.dashboard.common.extenstions.getDefibrillatorQuestion
import com.cardio.physician.ui.views.dashboard.common.extenstions.isPacemakerQuestion
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.physician.ui.views.diagnosis.step1.DiagnosisFragmentStep1Args
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class DiagnosisFragmentStep3 : BaseDiagnosisFragment<FragmentDiagnosisPart3Binding>() {

    private var questionList: List<QuestionModel>? = null
    val viewModel: DiagnosisFragmentStep3ViewModel by viewModels()
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

    open fun setObservers() {
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
        questionList?.isNotEmpty()?.let { isNonEmpty ->
            if (isNonEmpty) {
                var question = questionList?.get(diagnosisActivity?.lastQuestionIndex!!)
                question?.run {
                    showQuestion(getQuestionView(question))
                }
            }
        }
    }

    open fun showQuestion(questionView: View) {
        manageNextBackButtonBackground()
        binding.tvQuestionTotalVsCurrent.text = "${(diagnosisActivity?.lastQuestionIndex?.plus(1))}/${getTotalQuestionSize()}"
        binding.frameLayoutQuestionContainer.removeAllViews()
        binding.frameLayoutQuestionContainer.addView(
            questionView,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        //set next button alpha visibility
        enableButtonClick(hasAnsweredCurrentQuestion())
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
            binding.imageViewQuestionLeft.setBackgroundResource(R.drawable.drawable_green_gradient_circle)
        } else {
            binding.imageViewQuestionLeft.setImageResource(R.drawable.ic_left_green)
            binding.imageViewQuestionLeft.setBackgroundResource(R.drawable.drawable_green_circular_border)
        }
    }

    private fun enableRightButton(isEnable: Boolean) {
        if (isEnable) {
            binding.imageViewQuestionRight.setImageResource(R.drawable.ic_right_white)
            binding.imageViewQuestionRight.setBackgroundResource(R.drawable.drawable_green_gradient_circle)
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


    open fun setViews() {
        setStepView(binding.stepView.stepView)
        binding.cvDiagnosisBottomContainer.btCancel.setText(getString(R.string.back))
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            if (hasNextQuestion()) {
                //show next question if has any question
                binding.imageViewQuestionRight.performClick()
            } else {
                if (true/*hasUserGivenAllAnswers()*/) {
                    //save all questions to diagnoss model in diagnosis activity
                    diagnosisActivity?.diagnosisModel?.questionnaire = questionList
                    onNextButtonClick()
                } else {
                    context?.let { it1 ->
                        showToast(it1,
                            getString(R.string.validate_all_questions))
                    }
                }
            }
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            if (hasPreviousQuestion()) binding.imageViewQuestionLeft.performClick()
            else
                onBottomCancelButtonclick()
        }
        binding.imageViewQuestionRight.setOnClickListener {
            showNextQuestion()
        }
        binding.imageViewQuestionLeft.setOnClickListener {
            showPreviousQuestion()
        }
    }

    open fun onBottomCancelButtonclick() {
        findNavController().popBackStack()
    }

    open fun onNextButtonClick() {
        findNavController().navigate(DiagnosisFragmentStep3Directions.actionDiagnosisFragmentPart3ToDiagnosisFragmentPart4(args.userId))
    }

    private fun hasUserGivenAllAnswers(): Boolean {
        if (questionList == null) return false
        questionList?.forEach {
           // if (it.answer.isNullOrBlank()) return false
            if (!it.isAnswered()) return false
        }
        return true
    }

    private fun showNextQuestion() {
        if (!hasAnsweredCurrentQuestion()) {
            showToast(requireContext(), getString(R.string.vaidation_answer_current_question_first))
            return
        }
        questionList?.let {
            if (hasNextQuestion()) {
                diagnosisActivity!!.lastQuestionIndex++
                var question = it[diagnosisActivity?.lastQuestionIndex!!]
                //if defibrillator is selected then skip pacemaker question
                if (isPaceMakerQuestion(question) && isDefibrillatorSelected()) {
                    question.answer = null
                    question.answerSecondary = null
                    diagnosisActivity!!.lastQuestionIndex++
                    question = it[diagnosisActivity?.lastQuestionIndex!!]
                }
                showQuestion(getQuestionView(question))

            }
        }
    }

    private fun isPaceMakerQuestion(question: QuestionModel) = question.isPacemakerQuestion()

 /*   private fun showPreviousQuestion() {
        questionList?.let {
            if (hasPreviousQuestion()) {
                diagnosisActivity?.lastQuestionIndex = diagnosisActivity?.lastQuestionIndex!! - 1
                var question = it.get(diagnosisActivity?.lastQuestionIndex!!)
                showQuestion(getQuestionView(question))
            }
        }
    }*/

    private fun showPreviousQuestion() {
        questionList?.let {
            if (hasPreviousQuestion()) {
                diagnosisActivity!!.lastQuestionIndex--
                var question = it[diagnosisActivity?.lastQuestionIndex!!]
                //if defibrillator is selected then skip pacemaker question
                if (isPaceMakerQuestion(question) && isDefibrillatorSelected()) {
                    diagnosisActivity!!.lastQuestionIndex--
                    question = it[diagnosisActivity?.lastQuestionIndex!!]
                }
                showQuestion(getQuestionView(question))
            }
        }
    }

    private fun hasPreviousQuestion(): Boolean {
        return diagnosisActivity?.lastQuestionIndex!! > 0
    }

    private fun hasNextQuestion(): Boolean {
        return if (questionList.isNullOrEmpty()) false
        else diagnosisActivity?.lastQuestionIndex!! < questionList!!.size - 1
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

    private fun BaseQuestionView.registerAnswerchange(): View {
        registerOnAnswerChange {
            enableButtonClick(hasAnsweredCurrentQuestion())
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

    open fun isLastQuestion(): Boolean {
        questionList?.let {
            if (diagnosisActivity!!.lastQuestionIndex == it.size - 1) {
                return true
            }
        }
        return false
    }

    private fun isDefibrillatorSelected(): Boolean {
        questionList?.getDefibrillatorQuestion()?.let { it ->
            if (it.answer.equals(FireStoreDocKey.YES, true)) {
                return true
            }
        }
        return false
    }

    open fun isFirstQuestion()=diagnosisActivity!!.lastQuestionIndex==0

    private fun getTotalQuestionSize(): Int {
        var size=questionList?.size?:0
        /* if(isDefibrillatorSelected())
             size--*/
        return size
    }

}
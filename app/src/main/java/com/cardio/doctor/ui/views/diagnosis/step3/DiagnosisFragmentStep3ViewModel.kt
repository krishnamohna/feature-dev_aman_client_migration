package com.cardio.doctor.ui.views.diagnosis.step3

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.domain.questionare.QuestionnaireRepo
import com.cardio.doctor.domain.questionare.model.QuestionModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.extentions.setError
import com.cardio.doctor.ui.common.utils.extentions.setLoading
import com.cardio.doctor.ui.common.utils.extentions.setSuccess
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisFragmentStep3ViewModel @Inject constructor(val questionnaireRepo: QuestionnaireRepo) :
    BaseViewModel() {

    private val singleLiveDataQuestions = SingleLiveEvent<Resource<List<QuestionModel>>>()
    val liveDataQuestions: LiveData<Resource<List<QuestionModel>>> = singleLiveDataQuestions

    fun getQuestionLiveData() = liveDataQuestions

    fun getQuestions() {
        viewModelScope.launch {
            try {
                singleLiveDataQuestions.setLoading()
                singleLiveDataQuestions.setSuccess(questionnaireRepo.getQuestionnaires())
            } catch (exp: Exception) {
                singleLiveDataQuestions.setError(exp)
            }
        }
    }

}
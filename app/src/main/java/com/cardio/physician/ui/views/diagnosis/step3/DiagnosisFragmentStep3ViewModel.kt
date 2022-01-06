package com.cardio.physician.ui.views.diagnosis.step3

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.data.remote.profile.UserProfileRepository
import com.cardio.physician.domain.connection.ConnectionRepo
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.notifications.NotificationRepo
import com.cardio.physician.domain.questionare.QuestionnaireRepo
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisFragmentStep3ViewModel @Inject constructor(
    private val questionnaireRepo: QuestionnaireRepo,
    override val diagnosisRepo: DiagnosisRepo,
    override val connectionRepo: ConnectionRepo,
    override val notificationRepo: NotificationRepo
) :
    DiagnosisFragmentStep4ViewModel(diagnosisRepo, connectionRepo, notificationRepo) {

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
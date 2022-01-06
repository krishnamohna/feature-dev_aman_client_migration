package com.cardio.physician.ui.views.dashboard.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.data.remote.profile.UserProfileRepository
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val diagnosisRepo: DiagnosisRepo,
    private val userProfileRepository: UserProfileRepository,
    private val syncRepositary: SyncHealthRepositary,
) : BaseViewModel() {

    /*for user profile*/
    private val userSingleLiveData = SingleLiveEvent<Resource<UserModel>>()
    val liveUserData: LiveData<Resource<UserModel>> =
        userSingleLiveData

    /*for chib diagnosis */
    private val singleEventDiagnosisChib = SingleLiveEvent<Resource<List<DiagnosisModel>>>()
    val liveDataDiagnosisChib: LiveData<Resource<List<DiagnosisModel>>> =
        singleEventDiagnosisChib

    /*for afib diagnosis */
    private val singleEventDiagnosisAfib = SingleLiveEvent<Resource<List<DiagnosisModel>>>()
    val liveDataDiagnosisAfib: LiveData<Resource<List<DiagnosisModel>>> =
        singleEventDiagnosisAfib

    /*health logs */
    private val singleEventHealthLogs = SingleLiveEvent<Resource<List<FitnessModel>>>()
    val liveDataHealthLogs: LiveData<Resource<List<FitnessModel>>> =
        singleEventHealthLogs

    private val singleEventConnections = SingleLiveEvent<Resource<List<ConnectionModel>>>()
    val liveDataConnections: LiveData<Resource<List<ConnectionModel>>> =
        singleEventConnections

    fun getPatientList(currentDate: String) {
        viewModelScope.launch {
            try {
                singleEventConnections.setLoading()
                singleEventConnections.setSuccess(diagnosisRepo.getPatientListByDate(currentDate))
            }catch (exp:Exception){
                singleEventConnections.setError(exp)
            }
        }
    }

    fun  getDiagnosisChib(currentDate: String, ailment: String, userId: String?) {
        viewModelScope.launch {
            try {
                singleEventDiagnosisChib.setLoading()
                singleEventDiagnosisChib.setSuccess(diagnosisRepo.getDiagnosisByDate(currentDate,
                    ailment, userId))
            } catch (exp: Exception) {
                singleEventDiagnosisChib.setError(exp)
            }
        }
    }

    fun  getDiagnosisAfib(currentDate: String, ailment: String, userId: String?) {
        viewModelScope.launch {
            try {
                singleEventDiagnosisAfib.setLoading()
                singleEventDiagnosisAfib.setSuccess(diagnosisRepo.getDiagnosisByDate(currentDate,
                    ailment, userId))
            } catch (exp: Exception) {
                singleEventDiagnosisAfib.setError(exp)
            }
        }
    }

    fun getUserDetail(userId: String?) {
        try {
            userSingleLiveData.setLoading()
            viewModelScope.launch {
                userSingleLiveData.setSuccess(userProfileRepository.fetchUserDetailByModel(userId))
            }
        } catch (e: Exception) {
            userSingleLiveData.setError(e)
        }
    }

    fun getHealthLogs(days: Long, userId: String?) {
        viewModelScope.launch {
            try {
                singleEventHealthLogs.setLoading()
                singleEventHealthLogs.setSuccess(syncRepositary.getHealthLogs(days, userId))
            } catch (e: Exception) {
                singleEventHealthLogs.setError(e)
            }
        }
    }


}

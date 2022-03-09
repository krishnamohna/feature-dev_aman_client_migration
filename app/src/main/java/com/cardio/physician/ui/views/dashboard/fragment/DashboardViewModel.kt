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
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.extentions.*
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
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

    /*health logs for considerations */
    private val singleEventHealthLogsConsiderations = SingleLiveEvent<Resource<List<FitnessModel>>>()
    val liveDataHealthLogsConsiderations: LiveData<Resource<List<FitnessModel>>> =
        singleEventHealthLogsConsiderations

    private val singleEventConnections = SingleLiveEvent<Resource<List<ConnectionModel>>>()
    val liveDataConnections: LiveData<Resource<List<ConnectionModel>>> =
        singleEventConnections

    fun getPatientList(currentDate: String) {
        viewModelScope.launch {
            try {
                diagnosisRepo.getPatientListByDate(currentDate
                ) { value, error ->
                    if (value != null)
                        singleEventConnections.setSuccess(value.toConnectionModel())
                    else
                        error?.let { singleEventConnections.setError(it) }
                }
            }catch (exp:Exception){
                singleEventConnections.setError(exp)
            }
        }
    }

    fun  getDiagnosisChib(currentDate: String, ailment: String, userId: String?) {
        viewModelScope.launch {
            try {
                singleEventDiagnosisChib.setLoading()
                diagnosisRepo.getDiagnosisByDate(currentDate,
                    ailment, userId) { value, error ->
                    if (value != null)
                        singleEventDiagnosisChib.setSuccess(value.toDiagnosisModel())
                    else
                        error?.let { singleEventDiagnosisChib.setError(it) }
                }
            } catch (exp: Exception) {
                singleEventDiagnosisChib.setError(exp)
            }
        }
    }

    fun  getDiagnosisAfib(currentDate: String, ailment: String, userId: String?) {
        viewModelScope.launch {
            try {
                singleEventDiagnosisAfib.setLoading()
                diagnosisRepo.getDiagnosisByDate(currentDate,
                    ailment, userId){ value, error ->
                    if (value != null)
                        singleEventDiagnosisAfib.setSuccess(value.toDiagnosisModel())
                    else
                        error?.let { singleEventDiagnosisAfib.setError(it) }
                }
            } catch (exp: Exception) {
                singleEventDiagnosisAfib.setError(exp)
            }
        }
    }

    fun getUserDetail(userId: String?) {
        viewModelScope.launch {
            try {
                userSingleLiveData.setLoading()
                userProfileRepository.fetchUserDetailByModelListener(userId){value, error ->
                    if(value != null) userSingleLiveData.setSuccess(value.toUserModel())
                    else error?.let { userSingleLiveData.setError(error) }
                }
            } catch (e: Exception) {
                userSingleLiveData.setError(e)
            }
        }
    }

  /*  fun getHealthLogs(days: Long,userId: String?) {
        viewModelScope.launch {
            try {
                singleEventHealthLogs.setLoading()
                singleEventHealthLogs.setSuccess(syncRepositary.getHealthLogs1(days,userId))
            } catch (e: Exception) {
                singleEventHealthLogs.setError(e)
            }
        }
    }*/
    fun getHealthLogs(days: Long, userId: String?) {
        viewModelScope.launch {
            try {
                singleEventHealthLogs.setLoading()
                syncRepositary.getHealthLogs(days, userId){value, error ->
                    if (value != null){
                        var listFitnessModel = mutableListOf<FitnessModel>()
                        for (document in value) {
                            val fitnessModel = FitnessModel()
                            fitnessModel.weight = document.data[FireStoreDocKey.WEIGHT] as? String?
                            fitnessModel.heartRate = document.data[FireStoreDocKey.HEART_RATE] as? String?
                            fitnessModel.bloodPressureTopBp =
                                document.data[FireStoreDocKey.BLOOD_SYSTOLIC_BP] as? String?
                            fitnessModel.bloodPressureBottomBp =
                                document.data[FireStoreDocKey.BLOOD_DIASTOLIC_BP] as? String?
                            fitnessModel.date = document.data[FireStoreDocKey.DATE] as? String?
                            fitnessModel.timeStamp = document.data[FireStoreDocKey.DATE] as? Long?
                            fitnessModel.stepCount = document.data[FireStoreDocKey.STEP_COUNT] as? String
                            listFitnessModel.add(fitnessModel)
                        }
                        singleEventHealthLogs.setSuccess(listFitnessModel)
                    }
                    else
                        error?.let { singleEventHealthLogs.setError(it) }
                }
            } catch (e: Exception) {
                singleEventHealthLogs.setError(e)
            }
        }
    }

    fun getHealthLogsForConsiderations(userId: String?) {
        viewModelScope.launch {
            try {
                singleEventHealthLogsConsiderations.setLoading()
                singleEventHealthLogsConsiderations.setSuccess(syncRepositary.getHealthLogs1(DashboardFragment.Filter.NINETY.value,userId))
            } catch (e: Exception) {
                singleEventHealthLogsConsiderations.setError(e)
            }
        }
    }
}

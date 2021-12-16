package com.cardio.physician.ui.views.dashboard.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.data.remote.profile.UserProfileRepository
import com.cardio.physician.domain.addpatient.PatientModel
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
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
    private val userProfileRepository: UserProfileRepository
) : BaseViewModel() {

    /*for user profile*/
    private val userSingleLiveData = SingleLiveEvent<Resource<UserModel>>()
    val liveUserData: LiveData<Resource<UserModel>> =
        userSingleLiveData
    /*for diagnosis */
    private val singleEventConnections = SingleLiveEvent<Resource<List<ConnectionModel>>>()
    val liveDataConnections: LiveData<Resource<List<ConnectionModel>>> =
        singleEventConnections

    fun getPatientList(currentDate: String) {
        viewModelScope.launch {
            try {
                singleEventConnections.setLoading()
                singleEventConnections.setSuccess(diagnosisRepo.getPatientListByDate(currentDate))
            }catch (exp:Exception){
                singleEventConnections.setError(exp.message)
            }
        }
    }

    fun getUserDetail() {
        try {
            userSingleLiveData.setLoading()
            viewModelScope.launch {
                userSingleLiveData.setSuccess(userProfileRepository.fetchUserDetailByModel(null))
            }
        } catch (e: Exception) {
            userSingleLiveData.setError(e.message)
        }
    }


}

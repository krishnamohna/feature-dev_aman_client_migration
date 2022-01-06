package com.cardio.physician.ui.views.diagnosis.step4

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.data.remote.profile.UserProfileRepository
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.connection.ConnectionRepo
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.notifications.NotificationRepo
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.extentions.isAllDigits
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.extentions.setSuccess
import com.cardio.physician.ui.common.utils.getDateFromTimeMills
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class DiagnosisFragmentStep4ViewModel @Inject constructor(
    open val diagnosisRepo: DiagnosisRepo,
    open val connectionRepo: ConnectionRepo,
    open val notificationRepo: NotificationRepo
) : BaseViewModel() {

    var mutableSubmitDiagnosis = SingleLiveEvent<Resource<Unit>>()
    var liveSubmitDiagnosis: LiveData<Resource<Unit>> =
        mutableSubmitDiagnosis

    //this is the only method though which diagnosis added or updated
    fun submitDiagnosisReport(diagnosisModel: DiagnosisModel, userId : String?, isEdit: Boolean) {
        //set current date and timestamp diagnosis first
        diagnosisModel.timeStamp = System.currentTimeMillis()
        diagnosisModel.date = getDateFromTimeMills(System.currentTimeMillis())
        viewModelScope.launch {
            mutableSubmitDiagnosis.setLoading()
            var isDiagnosisSaved = false
            try {
                //save diagnosis
                var resultDiagnosis = diagnosisRepo.submitReport(diagnosisModel, userId, isEdit)
                mutableSubmitDiagnosis.setSuccess(resultDiagnosis)
                notifyConnectionOnDiagnosisAddedOrEdited(isEdit, userId)
                isDiagnosisSaved = true
                //save health logs
//                syncHealthRepository.updateHealthLogByDate(getHealthModel(diagnosisModel), userId)
                //update in profile
//                userProfileRepository.updateProfile(getUserModel(diagnosisModel), userId)
                mutableSubmitDiagnosis.setSuccess(resultDiagnosis)
            } catch (exp: Exception) {
                if (isDiagnosisSaved) mutableSubmitDiagnosis.setSuccess(Unit)
                else
                    mutableSubmitDiagnosis.setError(exp)
            }
        }
    }

    private fun notifyConnectionOnDiagnosisAddedOrEdited(isEdit: Boolean, userId: String?) {
        //notify connections or illness added or edited
        GlobalScope.launch {
            try{
                val notificationType =
                    if (isEdit) FireStoreDocKey.NOTIFICATION_TYPE_EDIT_DIAGNOSIS else FireStoreDocKey.NOTIFICATION_TYPE_ADD_DIAGNOSIS
                notificationRepo.addNotificationsToConnections(connectionRepo.getMyConnections(),
                    notificationType)
            }catch (exp:Exception){

            }
        }
    }

    private fun getUserModel(diagnosisModel: DiagnosisModel): UserModel {
        return UserModel.Builder.apply {
            firstName = diagnosisModel.firstName
            lastName = diagnosisModel.lastName
            if (diagnosisModel.age != null && !diagnosisModel.age!!.isAllDigits())
                dob = diagnosisModel.age
            weight = diagnosisModel.weight
        }.build()
    }

    private fun getHealthModel(diagnosisModel: DiagnosisModel): FitnessModel {
        val fitnessModel = FitnessModel()
        diagnosisModel.weight?.let { weight ->
            if (weight.isNotEmpty()) fitnessModel.weight = weight
        }
        diagnosisModel.heartRate?.let { heartRate ->
            if (heartRate.isNotEmpty()) fitnessModel.heartRate = heartRate
        }
        diagnosisModel.topBp?.let { topBp ->
            if (topBp.isNotEmpty()) fitnessModel.bloodPressureTopBp = topBp
        }
        diagnosisModel.bottomBp?.let { bottomBp ->
            if (bottomBp.isNotEmpty()) fitnessModel.bloodPressureBottomBp = bottomBp
        }
        diagnosisModel.stepCount?.let { stepCount ->
            if (stepCount.isNotEmpty()) fitnessModel.stepCount = stepCount
        }
        diagnosisModel.date?.let { date -> if (date.isNotEmpty()) fitnessModel.date = date }
        diagnosisModel.timeStamp?.let { timeStamp -> fitnessModel.timeStamp = timeStamp }
        return fitnessModel
    }

}
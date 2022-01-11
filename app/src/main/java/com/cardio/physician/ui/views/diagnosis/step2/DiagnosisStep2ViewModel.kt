package com.cardio.physician.ui.views.diagnosis.step2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.data.remote.profile.UserProfileRepository
import com.cardio.physician.domain.connection.ConnectionRepo
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.diagnosis.MedicineModel
import com.cardio.physician.domain.notifications.NotificationRepo
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.utils.extentions.observeApi
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisStep2ViewModel @Inject constructor(
    override val diagnosisRepo: DiagnosisRepo,
    override val connectionRepo: ConnectionRepo,
    override val notificationRepo: NotificationRepo
) :
    DiagnosisFragmentStep4ViewModel(diagnosisRepo, connectionRepo, notificationRepo) {

    var mutableMedicineData = SingleLiveEvent<Resource<MedicineModel>>()
    var liveMedicineData:LiveData<Resource<MedicineModel>> =
        mutableMedicineData

    fun getMedicineLiveData(): LiveData<Resource<MedicineModel>> {
        return liveMedicineData
    }

    fun fetchMed(query: String, isPreExistedMed: Boolean) {
        viewModelScope.launch {
            try {
                mutableMedicineData.setLoading()
                observeApi(diagnosisRepo.fetchMedicine(query,isPreExistedMed), mutableMedicineData)
            } catch (exp: Exception) {
                mutableMedicineData.setError(exp)
            }
        }
    }

    fun searchMedicine(query: String,) : List<MedicineModel>{
       return try {
           diagnosisRepo.searchMedicine(query)
        } catch (exp: Exception) {
           arrayListOf()
        }
    }

    //method is used to save medicines from json file in assert to firestore collection for step 2 of diagnosis
    fun saveMediciensToFirestore(context: Context){
        viewModelScope.launch {
            diagnosisRepo.saveMedicineToCollections(context)
        }
    }
}




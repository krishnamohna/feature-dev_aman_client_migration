package com.cardio.physician.ui.views.diagnosis.step2

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.diagnosis.MedicineModel
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel
import com.cardio.physician.ui.common.utils.extentions.observeApi
import com.cardio.physician.ui.common.utils.extentions.setError
import com.cardio.physician.ui.common.utils.extentions.setLoading
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisStep2ViewModel @Inject constructor(val diagnosisRepo: DiagnosisRepo) :
    BaseViewModel() {

    var mutableMedicineData = SingleLiveEvent<Resource<MedicineModel>>()
    var liveMedicineData:LiveData<Resource<MedicineModel>> =
        mutableMedicineData

    fun getMedicineLiveData(): LiveData<Resource<MedicineModel>> {
        return liveMedicineData
    }

    fun searchMed(query: String) {
        viewModelScope.launch {
            try {
                mutableMedicineData.setLoading()
                observeApi(diagnosisRepo.searchMedicine(query), mutableMedicineData)
            } catch (exp: Exception) {
                mutableMedicineData.setError(exp)
            }
        }
    }

}




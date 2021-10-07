package com.cardio.doctor.ui.views.diagnosis.step2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.domain.diagnosis.DiagnosisRepo
import com.cardio.doctor.domain.diagnosis.MedicineModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.extentions.observeApi
import com.cardio.doctor.ui.common.utils.extentions.setError
import com.cardio.doctor.ui.common.utils.extentions.setLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiagnosisStep2ViewModel @Inject constructor(val diagnosisRepo: DiagnosisRepo) :
    BaseViewModel() {

    var mutableMedicineData = MutableLiveData<Resource<MedicineModel>>()

    fun getMedidicneLiveData() = mutableMedicineData as LiveData<Resource<MedicineModel>>

    fun searchMed(query: String) {
        viewModelScope.launch {
            try {
                mutableMedicineData.setLoading()
                observeApi(diagnosisRepo.searchMedicine(query), mutableMedicineData)
            }catch (exp:Exception){
                mutableMedicineData.setError(exp)
            }
        }
    }

}




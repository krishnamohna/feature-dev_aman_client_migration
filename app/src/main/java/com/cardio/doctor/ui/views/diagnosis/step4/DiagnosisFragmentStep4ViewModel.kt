package com.cardio.doctor.ui.views.diagnosis.step4

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.domain.diagnosis.DiagnosisModel
import com.cardio.doctor.domain.diagnosis.DiagnosisRepo
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
class DiagnosisFragmentStep4ViewModel @Inject constructor(val diagnosisRepo: DiagnosisRepo):BaseViewModel() {

    var mutableSubmitDiagnosis = SingleLiveEvent<Resource<Boolean>>()
    var liveSubmitDiagnosis: LiveData<Resource<Boolean>> =
        mutableSubmitDiagnosis

    fun submitDiagnosisReport(diagnosisModel: DiagnosisModel){
        viewModelScope.launch {
            mutableSubmitDiagnosis.setLoading()
            try{
                mutableSubmitDiagnosis.setSuccess(diagnosisRepo.submitReport(diagnosisModel))
            }catch (exp:Exception){
                mutableSubmitDiagnosis.setError(exp)
            }
        }
    }

}
package com.cardio.doctor.ui.views.dashboard.fragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.domain.diagnosis.DiagnosisRepo
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val diagnosisRepo: DiagnosisRepo,
) : BaseViewModel() {

    fun getDiagnosis(currentDate: String) {
        viewModelScope.launch {
           try {
               var list = diagnosisRepo.getDiagnosisByDate(currentDate)
               Log.i("",""+list)
           }catch (exp:Exception){
              Log.i("", exp.message?:"error")
           }
        }
    }


}

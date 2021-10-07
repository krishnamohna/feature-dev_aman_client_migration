package com.cardio.doctor.ui.views.diagnosis.common

import androidx.viewbinding.ViewBinding
import com.cardio.doctor.ui.common.base.fragment.BaseFragment
import com.cardio.doctor.ui.common.customviews.StepView
import com.cardio.doctor.ui.views.diagnosis.DiagnosisActivity

open abstract class BaseDiagnosisFragment <Binding : ViewBinding>: BaseFragment<Binding>() {

     val diagnosisActivity: DiagnosisActivity?
     get() = activity as DiagnosisActivity

    fun setStepView(stepView: StepView){
        diagnosisActivity?.setStepView(stepView)
    }

}
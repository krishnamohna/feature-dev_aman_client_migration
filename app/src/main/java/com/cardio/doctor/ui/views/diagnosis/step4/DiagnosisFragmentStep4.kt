package com.cardio.doctor.ui.views.diagnosis.step4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cardio.doctor.databinding.FragmentDiagnosisPart4Binding
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment

class DiagnosisFragmentStep4 : BaseDiagnosisFragment<FragmentDiagnosisPart4Binding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDiagnosisPart4Binding.inflate(inflater,container,false)
        return binding.root
    }


}
package com.cardio.doctor.ui.views.diagnosis.step1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cardio.doctor.databinding.FragmentDiagnosisPart1Binding
import com.cardio.doctor.ui.common.base.fragment.BaseFragment

class DiagnosisFragmentStep1 :BaseFragment<FragmentDiagnosisPart1Binding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDiagnosisPart1Binding.inflate(inflater,container,false)
        return binding.root
    }

}
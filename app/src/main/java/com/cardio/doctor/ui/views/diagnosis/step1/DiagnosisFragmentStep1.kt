package com.cardio.doctor.ui.views.diagnosis.step1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.databinding.FragmentDiagnosisPart1Binding
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment

class DiagnosisFragmentStep1 : BaseDiagnosisFragment<FragmentDiagnosisPart1Binding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDiagnosisPart1Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            findNavController().navigate(DiagnosisFragmentStep1Directions.actionDiagnosisFragmentPart1ToDiagnosisFragmentPart2())
        }
    }

}
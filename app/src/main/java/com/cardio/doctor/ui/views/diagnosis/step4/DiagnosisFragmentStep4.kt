package com.cardio.doctor.ui.views.diagnosis.step4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.databinding.FragmentDiagnosisPart4Binding
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import com.cardio.doctor.ui.views.diagnosis.step3.DiagnosisFragmentStep3Directions

class DiagnosisFragmentStep4 : BaseDiagnosisFragment<FragmentDiagnosisPart4Binding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDiagnosisPart4Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setListeners()
    }

    private fun setViews() {
        setStepView(binding.stepView.stepView)
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            findNavController().navigate(DiagnosisFragmentStep3Directions.actionDiagnosisFragmentPart3ToDiagnosisFragmentPart4())
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}
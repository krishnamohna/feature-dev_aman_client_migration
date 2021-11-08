package com.cardio.doctor.ui.views.diagnosis.step4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDiagnosisPart4Binding
import com.cardio.doctor.ui.common.utils.showToast
import com.cardio.doctor.ui.views.diagnosis.common.BaseDiagnosisFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        diagnosisActivity?.getDiagnosisModel()?.firstName?.let { binding.clPatientDetail.edtFirstName.setText(it)}
        diagnosisActivity?.getDiagnosisModel()?.lastName?.let { binding.clPatientDetail.edtLastName.setText(it)}
        diagnosisActivity?.getDiagnosisModel()?.heartRate?.let { binding.clPatientDetail.edtHeartRateAilment.setText(it.toString())}
        diagnosisActivity?.getDiagnosisModel()?.weight?.let { binding.clPatientDetail.edtWeight.setText(it.toString())}
        diagnosisActivity?.getDiagnosisModel()?.ailment?.let { binding.clPatientDetail.edtAilment.setText(it)}
        diagnosisActivity?.getDiagnosisModel()?.age?.let { binding.clPatientDetail.edtAge.setText(it.toString())}
    }

    private fun setListeners() {
        binding.cvDiagnosisBottomContainer.btNext.setOnClickListener {
            showToast(parentActivity!!, R.string.coming_soon)
        }
        binding.cvDiagnosisBottomContainer.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }
/*
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtFirstName,
            binding.clPatientDetail.tvFirstNameError,
            binding.clPatientDetail.firstNameLabel,
            binding.scrollViewStep4, parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtLastName,
            binding.clPatientDetail.tvErrorLastName,
            binding.clPatientDetail.txtLastName,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtAge,
            binding.clPatientDetail.tvAgeError,
            binding.clPatientDetail.txtAge,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtWeight,
            binding.clPatientDetail.tvWeightError,
            binding.clPatientDetail.tvWeight,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtHeightAilment,
            binding.clPatientDetail.tvHeightErorAilment,
            binding.clPatientDetail.txtHeightAilment,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtHeartRateAilment,
            binding.clPatientDetail.tvHearRateErorAilment,
            binding.clPatientDetail.txtHeartRateAilment,
            binding.scrollViewStep4,
            parentActivity
        )
        labelVisiblityHelper.addView(
            binding.clPatientDetail.edtEnergy,
            binding.clPatientDetail.tvErrorEnergy,
            binding.clPatientDetail.tvEnergyLabel,
            binding.scrollViewStep4,
            parentActivity
        )
*/
    }


}
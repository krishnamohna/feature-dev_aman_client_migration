package com.cardio.physician.ui.views.dashboard.common.clinicalview

import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.diagnosis.DiagnosisModel

class AFibClinicalView : BaseClinicalView() {

    companion object {
        const val FAILURE_TYPE = "Afib"
    }

    fun showClinicalData(diagnosisModel: DiagnosisModel, binding: FragmentDashboardBinding) {
        //manage visibility of views
        manageVisibilityOfViews(binding)
        setAFibDiagnosisData(binding, diagnosisModel)
    }

    private fun setAFibDiagnosisData(
        binding: FragmentDashboardBinding,
        diagnosisModel: DiagnosisModel,
    ) {
        var value = if (binding.includeBasicInfo.tvDiagnosis.text.toString()
                .isBlank() || binding.includeBasicInfo.tvDiagnosis.text.toString()
                .equals(FAILURE_TYPE, true)
        ) {
            binding.includeBasicInfo.tvDiagnosis.text=FAILURE_TYPE
        } else {
            binding.includeBasicInfo.tvDiagnosis.append(", $FAILURE_TYPE")
        }
    }

    override fun manageVisibilityOfViews(binding: FragmentDashboardBinding) {
        super.manageVisibilityOfViews(binding)
        //   binding.includeAfib.cvAfib.visibility = View.VISIBLE
    }
}
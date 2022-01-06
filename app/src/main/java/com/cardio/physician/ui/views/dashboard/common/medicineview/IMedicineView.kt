package com.cardio.physician.ui.views.dashboard.common.medicineview

import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.diagnosis.DiagnosisModel

interface IMedicineView {
    fun showMedicationData(diagnosisModel: DiagnosisModel, binding: FragmentDashboardBinding)
}
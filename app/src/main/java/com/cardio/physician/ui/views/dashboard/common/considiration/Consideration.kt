package com.cardio.doctor.ui.views.dashboard.common.considiration

import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.fitness.model.FitnessModel


interface Consideration {
    fun showData(
        binding: FragmentDashboardBinding,
        it: List<FitnessModel>,
        diagnosisModelChib: DiagnosisModel?
    )
}
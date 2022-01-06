package com.cardio.physician.ui.views.dashboard.common.graph.base

import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.fitness.model.FitnessModel

interface WeightGraph : BaseGraph {
    fun showGraph(listHealthLogs: List<FitnessModel>?)
    fun setDryWeight(dryWeight: DiagnosisModel)
}
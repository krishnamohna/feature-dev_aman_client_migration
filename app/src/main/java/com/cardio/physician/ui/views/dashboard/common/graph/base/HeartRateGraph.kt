package com.cardio.physician.ui.views.dashboard.common.graph.base

import com.cardio.physician.domain.fitness.model.FitnessModel

interface HeartRateGraph : BaseGraph {
    fun showGraph(listHealthLogs: List<FitnessModel>?)
}
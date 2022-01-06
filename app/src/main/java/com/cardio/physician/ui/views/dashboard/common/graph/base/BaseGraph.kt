package com.cardio.physician.ui.views.dashboard.common.graph.base

import android.view.ViewGroup
import com.cardio.physician.databinding.FragmentDashboardBinding

interface BaseGraph {
    fun addGraphToParent(viewGroup: ViewGroup, binding: FragmentDashboardBinding)
    fun invisibleWhileRefreshing()
}
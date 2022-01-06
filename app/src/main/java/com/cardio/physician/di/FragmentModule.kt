package com.cardio.physician.di

import com.cardio.physician.ui.views.dashboard.common.graph.*
import com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph
import com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph
import com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph
import com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    @FragmentScoped
    fun provideWeightGraph(weightMpChartGraphImp: WeightMpChartGraphImp)=weightMpChartGraphImp as WeightGraph

    @Provides
    @FragmentScoped
    fun provideHeartRateGraph(heartRateMpChartGraphImp: HeartRateMpChartGraphImp)=heartRateMpChartGraphImp as HeartRateGraph

    @Provides
    @FragmentScoped
    fun provideBpGraph(charImp: BpBarMpChartGraphImp)=charImp as BpGraph

    @Provides
    @FragmentScoped
    fun provideStepCountGraph(stepCountMpGraphImp: StepCountMpGraphImp)=stepCountMpGraphImp as StepCountGraph

}
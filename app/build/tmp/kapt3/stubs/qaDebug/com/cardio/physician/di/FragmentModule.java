package com.cardio.physician.di;

import com.cardio.physician.ui.views.dashboard.common.graph.*;
import com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.scopes.FragmentScoped;

@dagger.hilt.InstallIn(value = {dagger.hilt.android.components.FragmentComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/cardio/physician/di/FragmentModule;", "", "()V", "provideBpGraph", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BpGraph;", "charImp", "Lcom/cardio/physician/ui/views/dashboard/common/graph/BpBarMpChartGraphImp;", "provideHeartRateGraph", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/HeartRateGraph;", "heartRateMpChartGraphImp", "Lcom/cardio/physician/ui/views/dashboard/common/graph/HeartRateMpChartGraphImp;", "provideStepCountGraph", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/StepCountGraph;", "stepCountMpGraphImp", "Lcom/cardio/physician/ui/views/dashboard/common/graph/StepCountMpGraphImp;", "provideWeightGraph", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/WeightGraph;", "weightMpChartGraphImp", "Lcom/cardio/physician/ui/views/dashboard/common/graph/WeightMpChartGraphImp;", "app_qaDebug"})
@dagger.Module()
public final class FragmentModule {
    
    public FragmentModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.scopes.FragmentScoped()
    @dagger.Provides()
    public final com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph provideWeightGraph(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.common.graph.WeightMpChartGraphImp weightMpChartGraphImp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.scopes.FragmentScoped()
    @dagger.Provides()
    public final com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph provideHeartRateGraph(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.common.graph.HeartRateMpChartGraphImp heartRateMpChartGraphImp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.scopes.FragmentScoped()
    @dagger.Provides()
    public final com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph provideBpGraph(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.common.graph.BpBarMpChartGraphImp charImp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.scopes.FragmentScoped()
    @dagger.Provides()
    public final com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph provideStepCountGraph(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.common.graph.StepCountMpGraphImp stepCountMpGraphImp) {
        return null;
    }
}
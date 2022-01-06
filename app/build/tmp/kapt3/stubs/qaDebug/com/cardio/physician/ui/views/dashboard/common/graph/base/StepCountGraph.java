package com.cardio.physician.ui.views.dashboard.common.graph.base;

import com.cardio.physician.domain.fitness.model.FitnessModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/graph/base/StepCountGraph;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BaseGraph;", "showGraph", "", "listHealthLogs", "", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "app_qaDebug"})
public abstract interface StepCountGraph extends com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraph {
    
    public abstract void showGraph(@org.jetbrains.annotations.Nullable()
    java.util.List<com.cardio.physician.domain.fitness.model.FitnessModel> listHealthLogs);
}
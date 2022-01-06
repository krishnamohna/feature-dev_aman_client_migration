package com.cardio.physician.ui.views.dashboard.common.graph.base;

import android.view.ViewGroup;
import com.cardio.physician.databinding.FragmentDashboardBinding;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&\u00a8\u0006\t"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BaseGraph;", "", "addGraphToParent", "", "viewGroup", "Landroid/view/ViewGroup;", "binding", "Lcom/cardio/physician/databinding/FragmentDashboardBinding;", "invisibleWhileRefreshing", "app_qaDebug"})
public abstract interface BaseGraph {
    
    public abstract void addGraphToParent(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup viewGroup, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.databinding.FragmentDashboardBinding binding);
    
    public abstract void invisibleWhileRefreshing();
}
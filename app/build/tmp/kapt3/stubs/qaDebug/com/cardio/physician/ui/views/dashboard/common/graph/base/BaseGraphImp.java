package com.cardio.physician.ui.views.dashboard.common.graph.base;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentDashboardBinding;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0012H\u0016J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001c\u001a\u00020\u0012R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001d"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BaseGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BaseGraph;", "()V", "binding", "Lcom/cardio/physician/databinding/FragmentDashboardBinding;", "parentLayout", "Landroid/view/ViewGroup;", "getParentLayout", "()Landroid/view/ViewGroup;", "setParentLayout", "(Landroid/view/ViewGroup;)V", "typefaceBold", "Landroid/graphics/Typeface;", "getTypefaceBold$app_qaDebug", "()Landroid/graphics/Typeface;", "setTypefaceBold$app_qaDebug", "(Landroid/graphics/Typeface;)V", "addGraphToParent", "", "getGraphView", "Landroid/view/View;", "context", "Landroid/content/Context;", "invisibleWhileRefreshing", "setAverageValue", "avg", "", "setAverageValue2", "showGraphFilters", "app_qaDebug"})
public abstract class BaseGraphImp implements com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraph {
    private com.cardio.physician.databinding.FragmentDashboardBinding binding;
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Typeface typefaceBold;
    @org.jetbrains.annotations.Nullable()
    private android.view.ViewGroup parentLayout;
    
    public BaseGraphImp() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Typeface getTypefaceBold$app_qaDebug() {
        return null;
    }
    
    public final void setTypefaceBold$app_qaDebug(@org.jetbrains.annotations.Nullable()
    android.graphics.Typeface p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.view.ViewGroup getParentLayout() {
        return null;
    }
    
    public final void setParentLayout(@org.jetbrains.annotations.Nullable()
    android.view.ViewGroup p0) {
    }
    
    @java.lang.Override()
    public void addGraphToParent(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parentLayout, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.databinding.FragmentDashboardBinding binding) {
    }
    
    @java.lang.Override()
    public void invisibleWhileRefreshing() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.view.View getGraphView(@org.jetbrains.annotations.NotNull()
    android.content.Context context);
    
    public final void setAverageValue(@org.jetbrains.annotations.NotNull()
    java.lang.String avg) {
    }
    
    public final void setAverageValue2(@org.jetbrains.annotations.NotNull()
    java.lang.String avg) {
    }
    
    public final void showGraphFilters() {
    }
}
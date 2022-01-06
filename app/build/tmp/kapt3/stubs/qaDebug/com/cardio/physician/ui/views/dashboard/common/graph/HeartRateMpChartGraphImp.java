package com.cardio.physician.ui.views.dashboard.common.graph;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.view.View;
import com.cardio.physician.R;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.ui.common.utils.Timer;
import com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp;
import com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.customview.MyMarkerView;
import com.cardio.physician.ui.views.dashboard.common.graph.formatter.LineChartXAxisValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\bH\u0016J\u001c\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\b2\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/graph/HeartRateMpChartGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BaseGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/HeartRateGraph;", "Lcom/github/mikephil/charting/listener/OnChartValueSelectedListener;", "()V", "chart", "Lcom/github/mikephil/charting/charts/LineChart;", "addLimitLines", "", "yAxis", "Lcom/github/mikephil/charting/components/YAxis;", "getGraphView", "Landroid/view/View;", "context", "Landroid/content/Context;", "init", "onNothingSelected", "onValueSelected", "e", "Lcom/github/mikephil/charting/data/Entry;", "h", "Lcom/github/mikephil/charting/highlight/Highlight;", "showGraph", "listHealthLogs", "", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "app_qaDebug"})
public final class HeartRateMpChartGraphImp extends com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp implements com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph, com.github.mikephil.charting.listener.OnChartValueSelectedListener {
    private com.github.mikephil.charting.charts.LineChart chart;
    
    @javax.inject.Inject()
    public HeartRateMpChartGraphImp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getGraphView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @java.lang.Override()
    public void showGraph(@org.jetbrains.annotations.Nullable()
    java.util.List<com.cardio.physician.domain.fitness.model.FitnessModel> listHealthLogs) {
    }
    
    private final void init(android.content.Context context) {
    }
    
    private final void addLimitLines(com.github.mikephil.charting.components.YAxis yAxis) {
    }
    
    @java.lang.Override()
    public void onValueSelected(@org.jetbrains.annotations.Nullable()
    com.github.mikephil.charting.data.Entry e, @org.jetbrains.annotations.Nullable()
    com.github.mikephil.charting.highlight.Highlight h) {
    }
    
    @java.lang.Override()
    public void onNothingSelected() {
    }
}
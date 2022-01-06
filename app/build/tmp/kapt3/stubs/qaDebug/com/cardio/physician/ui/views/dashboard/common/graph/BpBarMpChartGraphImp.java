package com.cardio.physician.ui.views.dashboard.common.graph;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.ui.common.utils.Constants;
import com.cardio.physician.ui.common.utils.Timer;
import com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp;
import com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.formatter.LineChartXAxisValueFormatter;
import com.cardio.physician.ui.views.dashboard.common.graph.formatter.MyAxisValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u0005H\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0011\u001a\u00020\u00102\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/graph/BpBarMpChartGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BaseGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BpGraph;", "()V", "MATERIAL_COLORS", "", "getMATERIAL_COLORS", "()[I", "chart", "Lcom/github/mikephil/charting/charts/BarChart;", "getColors", "getGraphView", "Landroid/view/View;", "context", "Landroid/content/Context;", "init", "", "showGraph", "listHealthLogs", "", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "app_qaDebug"})
public final class BpBarMpChartGraphImp extends com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp implements com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph {
    private com.github.mikephil.charting.charts.BarChart chart;
    @org.jetbrains.annotations.NotNull()
    private final int[] MATERIAL_COLORS = null;
    
    @javax.inject.Inject()
    public BpBarMpChartGraphImp() {
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
    
    private final void init(com.github.mikephil.charting.charts.BarChart chart) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final int[] getMATERIAL_COLORS() {
        return null;
    }
    
    private final int[] getColors() {
        return null;
    }
}
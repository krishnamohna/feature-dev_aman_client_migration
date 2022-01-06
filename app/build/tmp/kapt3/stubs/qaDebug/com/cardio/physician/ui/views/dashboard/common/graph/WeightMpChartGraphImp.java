package com.cardio.physician.ui.views.dashboard.common.graph;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.view.View;
import com.cardio.physician.R;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.ui.common.utils.Constants;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.cardio.physician.ui.common.utils.QuestionTypes;
import com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp;
import com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.customview.MyMarkerView;
import com.cardio.physician.ui.views.dashboard.common.graph.formatter.LineChartXAxisValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\bH\u0016J\u001c\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\bH\u0002J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001e\u001a\u00020\b2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/graph/WeightMpChartGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BaseGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/WeightGraph;", "Lcom/github/mikephil/charting/listener/OnChartValueSelectedListener;", "()V", "chart", "Lcom/github/mikephil/charting/charts/LineChart;", "addLimitLines", "", "yAxis", "Lcom/github/mikephil/charting/components/YAxis;", "value", "", "getDryWeight", "", "diagnosisModel", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "getGraphView", "Landroid/view/View;", "context", "Landroid/content/Context;", "init", "onNothingSelected", "onValueSelected", "e", "Lcom/github/mikephil/charting/data/Entry;", "h", "Lcom/github/mikephil/charting/highlight/Highlight;", "removeAllLinesFirst", "setDryWeight", "showGraph", "listHealthLogs", "", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "app_qaDebug"})
public final class WeightMpChartGraphImp extends com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp implements com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph, com.github.mikephil.charting.listener.OnChartValueSelectedListener {
    private com.github.mikephil.charting.charts.LineChart chart;
    
    @javax.inject.Inject()
    public WeightMpChartGraphImp() {
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
    
    @java.lang.Override()
    public void onValueSelected(@org.jetbrains.annotations.Nullable()
    com.github.mikephil.charting.data.Entry e, @org.jetbrains.annotations.Nullable()
    com.github.mikephil.charting.highlight.Highlight h) {
    }
    
    @java.lang.Override()
    public void onNothingSelected() {
    }
    
    @java.lang.Override()
    public void setDryWeight(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
    }
    
    private final void removeAllLinesFirst() {
    }
    
    private final void addLimitLines(com.github.mikephil.charting.components.YAxis yAxis, float value) {
    }
    
    private final java.lang.String getDryWeight(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
}
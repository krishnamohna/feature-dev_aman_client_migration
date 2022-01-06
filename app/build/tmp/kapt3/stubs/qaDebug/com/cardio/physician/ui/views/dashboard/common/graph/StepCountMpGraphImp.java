package com.cardio.physician.ui.views.dashboard.common.graph;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.view.View;
import com.cardio.physician.R;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.ui.common.utils.Constants;
import com.cardio.physician.ui.common.utils.Timer;
import com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp;
import com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.customview.MyMarkerView;
import com.cardio.physician.ui.views.dashboard.common.graph.formatter.LineChartXAxisValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\f\u001a\u00020\u000b2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/graph/StepCountMpGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BaseGraphImp;", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/StepCountGraph;", "()V", "chart", "Lcom/github/mikephil/charting/charts/LineChart;", "getGraphView", "Landroid/view/View;", "context", "Landroid/content/Context;", "init", "", "showGraph", "listHealthLogs", "", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "app_qaDebug"})
public final class StepCountMpGraphImp extends com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp implements com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph {
    private com.github.mikephil.charting.charts.LineChart chart;
    
    @javax.inject.Inject()
    public StepCountMpGraphImp() {
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
}
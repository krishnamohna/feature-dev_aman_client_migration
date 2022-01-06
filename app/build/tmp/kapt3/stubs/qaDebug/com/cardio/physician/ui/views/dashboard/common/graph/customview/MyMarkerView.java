package com.cardio.physician.ui.views.dashboard.common.graph.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;
import com.cardio.physician.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@android.annotation.SuppressLint(value = {"ViewConstructor"})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/graph/customview/MyMarkerView;", "Lcom/github/mikephil/charting/components/MarkerView;", "context", "Landroid/content/Context;", "layoutResource", "", "(Landroid/content/Context;I)V", "tvContent", "Landroid/widget/TextView;", "getOffset", "Lcom/github/mikephil/charting/utils/MPPointF;", "refreshContent", "", "e", "Lcom/github/mikephil/charting/data/Entry;", "highlight", "Lcom/github/mikephil/charting/highlight/Highlight;", "app_qaDebug"})
public final class MyMarkerView extends com.github.mikephil.charting.components.MarkerView {
    private final android.widget.TextView tvContent = null;
    
    public MyMarkerView(@org.jetbrains.annotations.Nullable()
    android.content.Context context, int layoutResource) {
        super(null, 0);
    }
    
    @java.lang.Override()
    public void refreshContent(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.data.Entry e, @org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.highlight.Highlight highlight) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.mikephil.charting.utils.MPPointF getOffset() {
        return null;
    }
}
package com.cardio.physician.ui.common.utils;

import android.os.SystemClock;
import android.view.View;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/cardio/physician/ui/common/utils/DoubleClickListener;", "Landroid/view/View$OnClickListener;", "defaultInterval", "", "onSafeCLick", "Lkotlin/Function1;", "Landroid/view/View;", "", "(ILkotlin/jvm/functions/Function1;)V", "lastTimeClicked", "", "onClick", "v", "app_qaDebug"})
public final class DoubleClickListener implements android.view.View.OnClickListener {
    private int defaultInterval;
    private final kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit> onSafeCLick = null;
    private long lastTimeClicked = 0L;
    
    public DoubleClickListener(int defaultInterval, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.view.View, kotlin.Unit> onSafeCLick) {
        super();
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.NotNull()
    android.view.View v) {
    }
}
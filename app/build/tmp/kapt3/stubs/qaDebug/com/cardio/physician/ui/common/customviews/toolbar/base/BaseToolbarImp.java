package com.cardio.physician.ui.common.customviews.toolbar.base;

import android.content.Context;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.cardio.physician.R;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0012H&J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007J\b\u0010\u0014\u001a\u00020\bH\u0016R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0015"}, d2 = {"Lcom/cardio/physician/ui/common/customviews/toolbar/base/BaseToolbarImp;", "T", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "view", "Landroidx/constraintlayout/widget/ConstraintLayout;", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "onBackButtonClick", "Lkotlin/Function0;", "", "getOnBackButtonClick", "()Lkotlin/jvm/functions/Function0;", "setOnBackButtonClick", "(Lkotlin/jvm/functions/Function0;)V", "getView", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "getContext", "Landroid/content/Context;", "getTitle", "", "registerBackButtonListener", "setToolbar", "app_qaDebug"})
public abstract class BaseToolbarImp<T extends java.lang.Object> implements com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar {
    @org.jetbrains.annotations.NotNull()
    private final androidx.constraintlayout.widget.ConstraintLayout view = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onBackButtonClick;
    
    public BaseToolbarImp(@org.jetbrains.annotations.NotNull()
    androidx.constraintlayout.widget.ConstraintLayout view) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.constraintlayout.widget.ConstraintLayout getView() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnBackButtonClick() {
        return null;
    }
    
    public final void setOnBackButtonClick(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @java.lang.Override()
    public void setToolbar() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getTitle();
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.customviews.toolbar.base.BaseToolbarImp<T> registerBackButtonListener(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackButtonClick) {
        return null;
    }
}
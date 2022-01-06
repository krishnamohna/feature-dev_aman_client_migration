package com.cardio.physician.ui.common.base.activity;

import android.os.Bundle;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004\u00a8\u0006\u000b"}, d2 = {"Lcom/cardio/physician/ui/common/base/activity/BaseToolbarActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "()V", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setUpToolbar", "view", "app_qaDebug"})
public abstract class BaseToolbarActivity extends com.cardio.physician.ui.common.base.activity.BaseActivity {
    
    public BaseToolbarActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp();
    
    public final void setUpToolbar(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar view) {
    }
}
package com.cardio.physician.ui.common.utils.textwatcher;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.cardio.physician.R;
import com.cardio.physician.ui.common.base.activity.BaseActivity;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J(\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u0018H\u0002J(\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/cardio/physician/ui/common/utils/textwatcher/TextChangeWatcher;", "Landroid/text/TextWatcher;", "errorTxt", "Landroid/widget/TextView;", "label", "scrollView", "Landroid/widget/ScrollView;", "parentActivity", "Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "(Landroid/widget/TextView;Landroid/widget/TextView;Landroid/widget/ScrollView;Lcom/cardio/physician/ui/common/base/activity/BaseActivity;)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "getScrollRootView", "onTextChanged", "before", "app_qaDebug"})
public final class TextChangeWatcher implements android.text.TextWatcher {
    private final android.widget.TextView errorTxt = null;
    private final android.widget.TextView label = null;
    private final android.widget.ScrollView scrollView = null;
    private final com.cardio.physician.ui.common.base.activity.BaseActivity parentActivity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy handler$delegate = null;
    
    public TextChangeWatcher(@org.jetbrains.annotations.NotNull()
    android.widget.TextView errorTxt, @org.jetbrains.annotations.NotNull()
    android.widget.TextView label, @org.jetbrains.annotations.Nullable()
    android.widget.ScrollView scrollView, @org.jetbrains.annotations.Nullable()
    com.cardio.physician.ui.common.base.activity.BaseActivity parentActivity) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.os.Handler getHandler() {
        return null;
    }
    
    @java.lang.Override()
    public void beforeTextChanged(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence s, int start, int count, int after) {
    }
    
    @java.lang.Override()
    public void onTextChanged(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence s, int start, int before, int count) {
    }
    
    private final int getScrollRootView() {
        return 0;
    }
    
    @java.lang.Override()
    public void afterTextChanged(@org.jetbrains.annotations.Nullable()
    android.text.Editable p0) {
    }
}
package com.cardio.physician.ui.common.base.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import com.cardio.physician.ui.common.listeners.DialogHelper;
import com.cardio.physician.ui.common.listeners.DialogProvider;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0004J\u0014\u0010\u0019\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u001bJ\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0018H\u0016J\"\u0010 \u001a\u00020\u00182\b\u0010!\u001a\u0004\u0018\u00010\"2\u000e\u0010#\u001a\n\u0018\u00010$j\u0004\u0018\u0001`%H\u0004J\b\u0010&\u001a\u00020\fH\u0002J\b\u0010\'\u001a\u00020\u0018H\u0004J\u0010\u0010\'\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020(H\u0016R\u001c\u0010\u0005\u001a\u00028\u0000X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u00148F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006)"}, d2 = {"Lcom/cardio/physician/ui/common/base/fragment/BaseFragment;", "Binding", "Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "setBinding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "dialogHelper", "Lcom/cardio/physician/ui/common/listeners/DialogHelper;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "parentActivity", "Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "getParentActivity", "()Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "hideProgress", "", "launchWithMinDelay", "function", "Lkotlin/Function0;", "onAttach", "context", "Landroid/content/Context;", "onBackButtonClick", "onError", "msg", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "provideDialogHelper", "showProgress", "", "app_qaDebug"})
public abstract class BaseFragment<Binding extends androidx.viewbinding.ViewBinding> extends androidx.fragment.app.Fragment {
    public Binding binding;
    private com.cardio.physician.ui.common.listeners.DialogHelper dialogHelper;
    private final kotlin.Lazy handler$delegate = null;
    
    public BaseFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final Binding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    Binding p0) {
    }
    
    private final android.os.Handler getHandler() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.cardio.physician.ui.common.base.activity.BaseActivity getParentActivity() {
        return null;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    protected final void showProgress() {
    }
    
    public void showProgress(boolean showProgress) {
    }
    
    protected final void hideProgress() {
    }
    
    private final com.cardio.physician.ui.common.listeners.DialogHelper provideDialogHelper() {
        return null;
    }
    
    protected final void onError(@org.jetbrains.annotations.Nullable()
    java.lang.String msg, @org.jetbrains.annotations.Nullable()
    java.lang.Exception exception) {
    }
    
    public void onBackButtonClick() {
    }
    
    public final void launchWithMinDelay(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> function) {
    }
}
package com.cardio.physician.ui.common.utils.keyboard;

import android.view.ViewTreeObserver;
import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\bH\u0002J\b\u0010\u0011\u001a\u00020\fH\u0007J\b\u0010\u0012\u001a\u00020\fH\u0002J\b\u0010\u0013\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/cardio/physician/ui/common/utils/keyboard/KeyboardEventListener;", "Landroidx/lifecycle/LifecycleObserver;", "rootView", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isOpen", "", "(ILandroidx/appcompat/app/AppCompatActivity;Lkotlin/jvm/functions/Function1;)V", "listener", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "dispatchKeyboardEvent", "onLifecyclePause", "registerKeyboardListener", "unregisterKeyboardListener", "app_qaDebug"})
public final class KeyboardEventListener implements androidx.lifecycle.LifecycleObserver {
    private final int rootView = 0;
    private final androidx.appcompat.app.AppCompatActivity activity = null;
    private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> callback = null;
    private final android.view.ViewTreeObserver.OnGlobalLayoutListener listener = null;
    
    public KeyboardEventListener(int rootView, @org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> callback) {
        super();
    }
    
    private final void registerKeyboardListener() {
    }
    
    private final void dispatchKeyboardEvent(boolean isOpen) {
    }
    
    @androidx.annotation.CallSuper()
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_PAUSE)
    public final void onLifecyclePause() {
    }
    
    private final void unregisterKeyboardListener() {
    }
}
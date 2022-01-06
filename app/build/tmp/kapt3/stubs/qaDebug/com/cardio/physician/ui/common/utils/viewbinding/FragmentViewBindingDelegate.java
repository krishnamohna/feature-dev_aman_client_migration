package com.cardio.physician.ui.common.utils.viewbinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003B!\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\u0002\u0010\tJ\"\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00020\u00042\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0096\u0002\u00a2\u0006\u0002\u0010\u0014R\u0012\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0015"}, d2 = {"Lcom/cardio/physician/ui/common/utils/viewbinding/FragmentViewBindingDelegate;", "T", "Landroidx/databinding/ViewDataBinding;", "Lkotlin/properties/ReadOnlyProperty;", "Landroidx/fragment/app/Fragment;", "fragment", "viewBindingFactory", "Lkotlin/Function1;", "Landroid/view/View;", "(Landroidx/fragment/app/Fragment;Lkotlin/jvm/functions/Function1;)V", "binding", "Landroidx/databinding/ViewDataBinding;", "getFragment", "()Landroidx/fragment/app/Fragment;", "getViewBindingFactory", "()Lkotlin/jvm/functions/Function1;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Landroidx/fragment/app/Fragment;Lkotlin/reflect/KProperty;)Landroidx/databinding/ViewDataBinding;", "app_qaDebug"})
public final class FragmentViewBindingDelegate<T extends androidx.databinding.ViewDataBinding> implements kotlin.properties.ReadOnlyProperty<androidx.fragment.app.Fragment, T> {
    @org.jetbrains.annotations.NotNull()
    private final androidx.fragment.app.Fragment fragment = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<android.view.View, T> viewBindingFactory = null;
    private T binding;
    
    public FragmentViewBindingDelegate(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.view.View, ? extends T> viewBindingFactory) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.fragment.app.Fragment getFragment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<android.view.View, T> getViewBindingFactory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public T getValue(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment thisRef, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KProperty<?> property) {
        return null;
    }
}
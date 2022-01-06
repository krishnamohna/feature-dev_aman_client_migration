package com.google.mlkit.vision.demo.kotlin;

import com.google.android.gms.tasks.*;
import java.util.concurrent.Executor;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u001a>\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0005\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0001\u0012\u0004\u0012\u00020\u00070\t\u001a<\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\b\u0012\u00060\u000bj\u0002`\f\u0012\u0004\u0012\u00020\u00070\t\u001a8\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\t\u00a8\u0006\u000e"}, d2 = {"addOnCanceledListener", "Lcom/google/android/gms/tasks/Task;", "TResult", "executor", "Ljava/util/concurrent/Executor;", "listener", "Lkotlin/Function0;", "", "addOnCompleteListener", "Lkotlin/Function1;", "addOnFailureListener", "Ljava/lang/Exception;", "Lkotlin/Exception;", "addOnSuccessListener", "app_qaDebug"})
public final class TaskExtKt {
    
    /**
     * Quality-of-life helper to allow using trailing lambda syntax for adding a success listener to a
     * [Task].
     */
    @org.jetbrains.annotations.NotNull()
    public static final <TResult extends java.lang.Object>com.google.android.gms.tasks.Task<TResult> addOnSuccessListener(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.tasks.Task<TResult> $this$addOnSuccessListener, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.Executor executor, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super TResult, kotlin.Unit> listener) {
        return null;
    }
    
    /**
     * Quality-of-life helper to allow using trailing lambda syntax for adding a failure listener to a
     * [Task].
     */
    @org.jetbrains.annotations.NotNull()
    public static final <TResult extends java.lang.Object>com.google.android.gms.tasks.Task<TResult> addOnFailureListener(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.tasks.Task<TResult> $this$addOnFailureListener, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.Executor executor, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> listener) {
        return null;
    }
    
    /**
     * Quality-of-life helper to allow using trailing lambda syntax for adding a completion listener to
     * a [Task].
     */
    @org.jetbrains.annotations.NotNull()
    public static final <TResult extends java.lang.Object>com.google.android.gms.tasks.Task<TResult> addOnCompleteListener(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.tasks.Task<TResult> $this$addOnCompleteListener, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.Executor executor, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.google.android.gms.tasks.Task<TResult>, kotlin.Unit> listener) {
        return null;
    }
    
    /**
     * Quality-of-life helper to allow using trailing lambda syntax for adding a cancellation listener
     * to a [Task].
     */
    @org.jetbrains.annotations.NotNull()
    public static final <TResult extends java.lang.Object>com.google.android.gms.tasks.Task<TResult> addOnCanceledListener(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.tasks.Task<TResult> $this$addOnCanceledListener, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.Executor executor, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> listener) {
        return null;
    }
}
package com.cardio.physician.ui.common.utils;

import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.network.Resource;
import com.google.android.gms.tasks.Task;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u00008\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a}\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00012\u001e\b\u0004\u0010\u0003\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042$\b\u0004\u0010\u0007\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\b2\u0016\u0010\t\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\fj\u0002`\r0\u000b0\nH\u0086H\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u001ac\u0010\u000f\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00012\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00110\u00102\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00042\u0016\u0010\t\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\fj\u0002`\r0\u000b0\nH\u0086H\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012\u001a?\u0010\u0013\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00112\u0016\u0010\u0014\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\fj\u0002`\r0\u000b0\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"firebaseDocumentQuery", "R", "T", "operation", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "parse", "Lkotlin/Function2;", "errorLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firebaseQuery", "Lkotlin/Function0;", "Lcom/google/android/gms/tasks/Task;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firebaseTakeOff", "liveData", "(Lcom/google/android/gms/tasks/Task;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class FirebaseHelperKt {
    
    @org.jetbrains.annotations.Nullable()
    public static final <T extends java.lang.Object>java.lang.Object firebaseTakeOff(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.tasks.Task<T> operation, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> liveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final <T extends java.lang.Object, R extends java.lang.Object>java.lang.Object firebaseQuery(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends com.google.android.gms.tasks.Task<T>> operation, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, ? extends R> parse, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super R> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final <T extends java.lang.Object, R extends java.lang.Object>java.lang.Object firebaseDocumentQuery(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> operation, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> parse, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super R> p3) {
        return null;
    }
}
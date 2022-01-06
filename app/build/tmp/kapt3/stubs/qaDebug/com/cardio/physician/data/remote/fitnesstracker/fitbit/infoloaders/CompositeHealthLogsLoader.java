package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.cardio.physician.domain.fitness.model.*;
import java.lang.Exception;
import java.util.concurrent.ExecutorService;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\\\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0013J\u0006\u0010\u001d\u001a\u00020\u0007J\u001e\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00062\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\u001e\u0010#\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00062\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0!H\u0002J\u001e\u0010&\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00062\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0!H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R,\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006)"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/infoloaders/CompositeHealthLogsLoader;", "", "activity", "Landroid/content/Context;", "onSuccess", "Lkotlin/Function1;", "Lcom/cardio/physician/domain/fitness/model/SyncModel;", "", "onFailure", "Ljava/lang/Exception;", "Lkotlin/ParameterName;", "name", "msg", "periodDays", "", "executeService", "Ljava/util/concurrent/ExecutorService;", "handler", "Landroid/os/Handler;", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ILjava/util/concurrent/ExecutorService;Landroid/os/Handler;)V", "getActivity", "()Landroid/content/Context;", "getHandler", "()Landroid/os/Handler;", "getOnFailure", "()Lkotlin/jvm/functions/Function1;", "getOnSuccess", "getPeriodDays", "()I", "load", "updateHeartLogsAsPerDate", "syncModel", "heartLogs", "", "Lcom/cardio/physician/domain/fitness/model/HeartRateModel;", "updateStepCountLogsAsPerDate", "stepCounts", "Lcom/cardio/physician/domain/fitness/model/StepCountModel;", "updateWeightLogsAsPerDate", "weightLogs", "Lcom/cardio/physician/domain/fitness/model/WeightModel;", "app_qaDebug"})
public final class CompositeHealthLogsLoader {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context activity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.cardio.physician.domain.fitness.model.SyncModel, kotlin.Unit> onSuccess = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Exception, kotlin.Unit> onFailure = null;
    private final int periodDays = 0;
    private final java.util.concurrent.ExecutorService executeService = null;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler handler = null;
    
    public CompositeHealthLogsLoader(@org.jetbrains.annotations.NotNull()
    android.content.Context activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.SyncModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure, int periodDays, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.ExecutorService executeService, @org.jetbrains.annotations.NotNull()
    android.os.Handler handler) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getActivity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<com.cardio.physician.domain.fitness.model.SyncModel, kotlin.Unit> getOnSuccess() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Exception, kotlin.Unit> getOnFailure() {
        return null;
    }
    
    public final int getPeriodDays() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.os.Handler getHandler() {
        return null;
    }
    
    public final void load() {
    }
    
    private final void updateStepCountLogsAsPerDate(com.cardio.physician.domain.fitness.model.SyncModel syncModel, java.util.List<com.cardio.physician.domain.fitness.model.StepCountModel> stepCounts) {
    }
    
    private final void updateHeartLogsAsPerDate(com.cardio.physician.domain.fitness.model.SyncModel syncModel, java.util.List<com.cardio.physician.domain.fitness.model.HeartRateModel> heartLogs) {
    }
    
    private final void updateWeightLogsAsPerDate(com.cardio.physician.domain.fitness.model.SyncModel syncModel, java.util.List<com.cardio.physician.domain.fitness.model.WeightModel> weightLogs) {
    }
}
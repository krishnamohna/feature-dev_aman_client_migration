package com.cardio.physician.data.remote.fitnesstracker.fitbit;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.cardio.physician.data.remote.fitnesstracker.common.DataParser;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.CompositeHealthLogsLoader;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.TodayHeartRateLoader;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.fitness.model.HeartRateModel;
import com.cardio.physician.domain.fitness.model.SyncModel;
import com.cardio.physician.ui.common.utils.Constants;
import java.lang.Exception;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JM\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\f0\u00102!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0013\u00a2\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\f0\u00102\u0006\u0010\u0017\u001a\u00020\u0018JI\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u001a2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\f0\u00102#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001c\u00a2\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\f0\u0010H\u0002JE\u0010\u001d\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u001a2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\f0\u00102!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0013\u00a2\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\f0\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001f"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/FitbitManager;", "", "executerService", "Ljava/util/concurrent/ExecutorService;", "handler", "Landroid/os/Handler;", "(Ljava/util/concurrent/ExecutorService;Landroid/os/Handler;)V", "getExecuterService", "()Ljava/util/concurrent/ExecutorService;", "getHandler", "()Landroid/os/Handler;", "getFitnessLogs", "", "activity", "Landroid/content/Context;", "onSuccess", "Lkotlin/Function1;", "Lcom/cardio/physician/domain/fitness/model/SyncModel;", "onFailure", "Ljava/lang/Exception;", "Lkotlin/ParameterName;", "name", "msg", "periodDays", "", "getHeartRate", "Landroid/app/Activity;", "Lcom/cardio/physician/domain/fitness/model/HeartRateModel;", "", "getUserProfile", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "app_qaDebug"})
public final class FitbitManager {
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.ExecutorService executerService = null;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler handler = null;
    
    @javax.inject.Inject()
    public FitbitManager(@org.jetbrains.annotations.NotNull()
    java.util.concurrent.ExecutorService executerService, @org.jetbrains.annotations.NotNull()
    android.os.Handler handler) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.concurrent.ExecutorService getExecuterService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.os.Handler getHandler() {
        return null;
    }
    
    public final void getUserProfile(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.FitnessModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure) {
    }
    
    private final void getHeartRate(android.app.Activity activity, kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.HeartRateModel, kotlin.Unit> onSuccess, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onFailure) {
    }
    
    public final void getFitnessLogs(@org.jetbrains.annotations.NotNull()
    android.content.Context activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.SyncModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure, int periodDays) {
    }
}
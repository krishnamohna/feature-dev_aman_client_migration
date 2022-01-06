package com.cardio.physician.ui.service;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.lifecycle.LifecycleService;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0002\u0013\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\"\u0010\u000f\u001a\u00020\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0016R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/cardio/physician/ui/service/SyncHeathDataService;", "Landroidx/lifecycle/LifecycleService;", "()V", "binder", "Lcom/cardio/physician/ui/service/SyncHeathDataService$LocalBinder;", "facade", "Lcom/cardio/physician/ui/service/SyncHealthServiceFacade;", "getFacade", "()Lcom/cardio/physician/ui/service/SyncHealthServiceFacade;", "setFacade", "(Lcom/cardio/physician/ui/service/SyncHealthServiceFacade;)V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "Companion", "LocalBinder", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class SyncHeathDataService extends androidx.lifecycle.LifecycleService {
    private final com.cardio.physician.ui.service.SyncHeathDataService.LocalBinder binder = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.service.SyncHeathDataService.Companion Companion = null;
    @javax.inject.Inject()
    public com.cardio.physician.ui.service.SyncHealthServiceFacade facade;
    
    public SyncHeathDataService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.service.SyncHealthServiceFacade getFacade() {
        return null;
    }
    
    public final void setFacade(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.service.SyncHealthServiceFacade p0) {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/cardio/physician/ui/service/SyncHeathDataService$LocalBinder;", "Landroid/os/Binder;", "(Lcom/cardio/physician/ui/service/SyncHeathDataService;)V", "getService", "Lcom/cardio/physician/ui/service/SyncHeathDataService;", "app_qaDebug"})
    public final class LocalBinder extends android.os.Binder {
        
        public LocalBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.ui.service.SyncHeathDataService getService() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/cardio/physician/ui/service/SyncHeathDataService$Companion;", "", "()V", "start", "", "context", "Landroid/content/Context;", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
    }
}
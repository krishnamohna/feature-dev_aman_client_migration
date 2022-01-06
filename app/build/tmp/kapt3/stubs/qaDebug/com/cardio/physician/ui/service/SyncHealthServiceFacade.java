package com.cardio.physician.ui.service;

import android.app.Service;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.domain.fitness.FitnessRepositary;
import com.cardio.physician.domain.fitness.model.DateModel;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.fitness.model.SyncModel;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.network.NetworkError;
import com.cardio.physician.network.Resource;
import kotlinx.coroutines.GlobalScope;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\u0011\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0017J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\nH\u0002J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002J\u0018\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010 \u001a\u00020\u0015R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/cardio/physician/ui/service/SyncHealthServiceFacade;", "", "service", "Landroid/app/Service;", "fitnessRepositary", "Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "syncHealthRepositary", "Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;", "(Landroid/app/Service;Lcom/cardio/physician/domain/fitness/FitnessRepositary;Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;)V", "DEFALT_PREVIOUS_DAY_PERIOD", "", "_syncSingleLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "", "getService", "()Landroid/app/Service;", "calculateDayDiff", "fitnessModel", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "getHealthLogsFromWearable", "", "getSyncData", "Landroidx/lifecycle/LiveData;", "isTodayDataAlreadySynched", "isTodayDate", "dateModel", "Lcom/cardio/physician/domain/fitness/model/DateModel;", "loadLastCollection", "saveToCollection", "syncModel", "Lcom/cardio/physician/domain/fitness/model/SyncModel;", "syncData", "app_qaDebug"})
public final class SyncHealthServiceFacade {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Service service = null;
    private final com.cardio.physician.domain.fitness.FitnessRepositary fitnessRepositary = null;
    private final com.cardio.physician.domain.synchealth.SyncHealthRepositary syncHealthRepositary = null;
    private final int DEFALT_PREVIOUS_DAY_PERIOD = 30;
    private final androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Boolean>> _syncSingleLiveData = null;
    
    @javax.inject.Inject()
    public SyncHealthServiceFacade(@org.jetbrains.annotations.NotNull()
    android.app.Service service, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.FitnessRepositary fitnessRepositary, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.synchealth.SyncHealthRepositary syncHealthRepositary) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Service getService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.Boolean>> getSyncData() {
        return null;
    }
    
    public final void syncData() {
    }
    
    private final void loadLastCollection() {
    }
    
    private final int calculateDayDiff(com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel) {
        return 0;
    }
    
    private final void getHealthLogsFromWearable(com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel) {
    }
    
    private final boolean isTodayDataAlreadySynched(int calculateDayDiff) {
        return false;
    }
    
    private final void saveToCollection(com.cardio.physician.domain.fitness.model.SyncModel syncModel, com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel) {
    }
    
    private final boolean isTodayDate(com.cardio.physician.domain.fitness.model.DateModel dateModel) {
        return false;
    }
}
package com.cardio.physician.ui.views.sync_health_data;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.domain.fitness.FitnessRepositary;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.network.Resource;
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import javax.inject.Named;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u001d2\u0006\u0010\'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u001d2\u0006\u0010\'\u001a\u00020(J\u0006\u0010*\u001a\u00020+J\u0012\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0015J\u0012\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0015J\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020/J\u000e\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u00020+R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u00063"}, d2 = {"Lcom/cardio/physician/ui/views/sync_health_data/SyncHealthViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "fitbitRepositary", "Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "googlefitRepositary", "application", "Landroid/app/Application;", "(Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/domain/fitness/FitnessRepositary;Lcom/cardio/physician/domain/fitness/FitnessRepositary;Landroid/app/Application;)V", "_userFitbitSingleLiveData", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "_userGoogleSingleLiveData", "getApplication", "()Landroid/app/Application;", "getFitbitRepositary", "()Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "getGooglefitRepositary", "userFitbitLiveData", "Landroidx/lifecycle/LiveData;", "getUserFitbitLiveData", "()Landroidx/lifecycle/LiveData;", "userGoogleLiveData", "getUserGoogleLiveData", "getUserManager", "()Lcom/cardio/physician/data/local/UserManager;", "connectWithFitbit", "", "resultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "connectWithGooglefit", "fragment", "Landroidx/fragment/app/Fragment;", "getFitbitUserData", "activity", "Landroid/app/Activity;", "getGoogleUserData", "getSelectedHealthKit", "", "getUserFitbitData", "getUserGoogleFitLiveData", "isFitbitLoggedIn", "", "isGooglefitLoggedIn", "storeSyncSelectionInPreference", "name", "app_qaDebug"})
public final class SyncHealthViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.data.local.UserManager userManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.fitness.FitnessRepositary fitbitRepositary = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.fitness.FitnessRepositary googlefitRepositary = null;
    @org.jetbrains.annotations.NotNull()
    private final android.app.Application application = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> _userFitbitSingleLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> userFitbitLiveData = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> _userGoogleSingleLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> userGoogleLiveData = null;
    
    @javax.inject.Inject()
    public SyncHealthViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "FITBIT")
    com.cardio.physician.domain.fitness.FitnessRepositary fitbitRepositary, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GOOGLE")
    com.cardio.physician.domain.fitness.FitnessRepositary googlefitRepositary, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.data.local.UserManager getUserManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.FitnessRepositary getFitbitRepositary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.FitnessRepositary getGooglefitRepositary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Application getApplication() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> getUserFitbitLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> getUserGoogleLiveData() {
        return null;
    }
    
    public final void storeSyncSelectionInPreference(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedHealthKit() {
        return null;
    }
    
    public final void connectWithFitbit(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultLauncher, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void connectWithGooglefit(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    public final boolean isFitbitLoggedIn() {
        return false;
    }
    
    public final boolean isGooglefitLoggedIn() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> getUserFitbitData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> getUserGoogleFitLiveData() {
        return null;
    }
    
    public final void getFitbitUserData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void getGoogleUserData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
}
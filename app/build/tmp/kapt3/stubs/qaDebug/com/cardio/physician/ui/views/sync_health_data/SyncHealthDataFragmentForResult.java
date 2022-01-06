package com.cardio.physician.ui.views.sync_health_data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.ui.views.sync_health_data.activity.SyncHealthActivty;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0007H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\u001a\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\u0012\u001a\u00020\u0004H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0002\u00a8\u0006\u0014"}, d2 = {"Lcom/cardio/physician/ui/views/sync_health_data/SyncHealthDataFragmentForResult;", "Lcom/cardio/physician/ui/views/sync_health_data/SyncHealthDataFragment;", "()V", "onBackButtonClick", "", "onFitbitProfileDataRecieved", "fitnessModel", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "onFitbitSelection", "onGoogleProfileDataRecieved", "it", "onGoogleSelection", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "sendResultBack", "setListener", "setViews", "app_qaDebug"})
public final class SyncHealthDataFragmentForResult extends com.cardio.physician.ui.views.sync_health_data.SyncHealthDataFragment {
    
    public SyncHealthDataFragmentForResult() {
        super();
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setViews() {
    }
    
    @java.lang.Override()
    public void setListener() {
    }
    
    @java.lang.Override()
    public void onBackButtonClick() {
    }
    
    private final void sendResultBack(com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel) {
    }
    
    @java.lang.Override()
    public void onFitbitProfileDataRecieved(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel) {
    }
    
    @java.lang.Override()
    public void onGoogleProfileDataRecieved(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.FitnessModel it) {
    }
    
    @java.lang.Override()
    public void onFitbitSelection() {
    }
    
    @java.lang.Override()
    public void onGoogleSelection() {
    }
}
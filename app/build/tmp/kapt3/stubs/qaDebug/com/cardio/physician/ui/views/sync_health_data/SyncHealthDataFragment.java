package com.cardio.physician.ui.views.sync_health_data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.cardio.physician.R;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationHandler;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationResult;
import com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitManager;
import com.cardio.physician.databinding.FragmentSyncHealthDataBinding;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment;
import com.cardio.physician.ui.common.customviews.toolbar.SyncHealthToolbarImp;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.views.healthlogs.HealthLogsActivity;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000eH\u0002J\n\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\u000e\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+J\u0018\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0002J\"\u00100\u001a\u00020%2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\b\u00101\u001a\u0004\u0018\u00010\u0016H\u0016J\u0012\u00102\u001a\u00020%2\b\u00103\u001a\u0004\u0018\u000104H\u0016J&\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010=\u001a\u00020%H\u0002J\u0010\u0010>\u001a\u00020%2\u0006\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u00020%H\u0016J\b\u0010B\u001a\u00020%H\u0002J\u0010\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020@H\u0016J\b\u0010E\u001a\u00020%H\u0016J\u001a\u0010F\u001a\u00020%2\u0006\u0010G\u001a\u0002062\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010H\u001a\u00020%H\u0016J\b\u0010I\u001a\u00020%H\u0016J\b\u0010J\u001a\u00020%H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.\u00a2\u0006\u0004\n\u0002\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u000eX\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001b\u0010\u001e\u001a\u00020\u001f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!\u00a8\u0006K"}, d2 = {"Lcom/cardio/physician/ui/views/sync_health_data/SyncHealthDataFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseToolBarFragment;", "Lcom/cardio/physician/databinding/FragmentSyncHealthDataBinding;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/authentication/AuthenticationHandler;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "arrayOfImageView", "", "Landroid/widget/ImageView;", "[Landroid/widget/ImageView;", "isFitSelected", "", "()Z", "setFitSelected", "(Z)V", "isGoogleFitSelected", "setGoogleFitSelected", "resultHealthLogs", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "getResultHealthLogs", "()Landroidx/activity/result/ActivityResultLauncher;", "setResultHealthLogs", "(Landroidx/activity/result/ActivityResultLauncher;)V", "resultLauncher", "getResultLauncher", "setResultLauncher", "viewModel", "Lcom/cardio/physician/ui/views/sync_health_data/SyncHealthViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/sync_health_data/SyncHealthViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "enableContinueButton", "", "isEnable", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "logout", "activity", "Landroid/app/Activity;", "oAuthErrorMsg", "requestCode", "", "resultCode", "onActivityResult", "data", "onAuthFinished", "result", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/authentication/AuthenticationResult;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onFitBitAuthenticated", "onFitbitProfileDataRecieved", "fitnessModel", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "onFitbitSelection", "onGoogleAuthenticated", "onGoogleProfileDataRecieved", "it", "onGoogleSelection", "onViewCreated", "view", "setListener", "setObservers", "setViews", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public class SyncHealthDataFragment extends com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment<com.cardio.physician.databinding.FragmentSyncHealthDataBinding> implements com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationHandler {
    private boolean isFitSelected = false;
    private boolean isGoogleFitSelected = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "SyncHealthDataFragment";
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private android.widget.ImageView[] arrayOfImageView;
    @org.jetbrains.annotations.NotNull()
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultHealthLogs;
    @org.jetbrains.annotations.NotNull()
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultLauncher;
    
    public SyncHealthDataFragment() {
        super();
    }
    
    protected final boolean isFitSelected() {
        return false;
    }
    
    protected final void setFitSelected(boolean p0) {
    }
    
    protected final boolean isGoogleFitSelected() {
        return false;
    }
    
    protected final void setGoogleFitSelected(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTAG() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.sync_health_data.SyncHealthViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.activity.result.ActivityResultLauncher<android.content.Intent> getResultHealthLogs() {
        return null;
    }
    
    public final void setResultHealthLogs(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResultLauncher<android.content.Intent> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.activity.result.ActivityResultLauncher<android.content.Intent> getResultLauncher() {
        return null;
    }
    
    public final void setResultLauncher(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResultLauncher<android.content.Intent> p0) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setViews() {
    }
    
    private final void enableContinueButton(boolean isEnable) {
    }
    
    @java.lang.Override()
    public void onAuthFinished(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationResult result) {
    }
    
    public void setListener() {
    }
    
    public void setObservers() {
    }
    
    public void onGoogleProfileDataRecieved(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.FitnessModel it) {
    }
    
    public void onFitbitProfileDataRecieved(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel) {
    }
    
    private final void onGoogleAuthenticated() {
    }
    
    private final void onFitBitAuthenticated() {
    }
    
    public void onGoogleSelection() {
    }
    
    public void onFitbitSelection() {
    }
    
    private final void oAuthErrorMsg(int requestCode, int resultCode) {
    }
    
    public final void logout(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
}
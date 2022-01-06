package com.cardio.physician.ui.views.dashboard;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.cardio.physician.R;
import com.cardio.physician.data.remote.fcm.FcmManager;
import com.cardio.physician.databinding.ActivityDashboardBinding;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import com.cardio.physician.ui.common.utils.EXTRAS;
import com.cardio.physician.ui.service.SyncHeathDataService;
import com.cardio.physician.ui.views.add_patient.AddPatientActivity;
import com.cardio.physician.ui.views.dashboard.fragment.DashboardFragment;
import com.cardio.physician.ui.views.dashboard.fragment.PatientDashboardFragmentDirections;
import com.cardio.physician.ui.views.illness.IllnessActivity;
import com.cardio.physician.ui.views.notifications.NotificationsActivity;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u00012B\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u000fJ\u0006\u0010\"\u001a\u00020\u0019J\u0012\u0010#\u001a\u00020\u00192\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0012\u0010&\u001a\u00020\u00192\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020\u0019H\u0014J\u0016\u0010*\u001a\u00020\u00192\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018J\b\u0010+\u001a\u00020\u0019H\u0002J\u001a\u0010,\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00190\u001bJ\b\u0010-\u001a\u00020\u0019H\u0002J\u0010\u0010.\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010/\u001a\u00020\u0019H\u0002J\b\u00100\u001a\u00020\u0019H\u0002J\u0006\u00101\u001a\u00020\u0019R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\t\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R*\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u00a8\u00063"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/DashboardActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "binding", "Lcom/cardio/physician/databinding/ActivityDashboardBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityDashboardBinding;", "binding$delegate", "Lkotlin/Lazy;", "connection", "Landroid/content/ServiceConnection;", "currentTab", "Lcom/cardio/physician/ui/views/dashboard/DashboardActivity$TAB;", "mBound", "", "mService", "Lcom/cardio/physician/ui/service/SyncHeathDataService;", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "navController$delegate", "onDiagnosisClick", "Lkotlin/Function0;", "", "onHealthLogsSync", "Lkotlin/Function1;", "getOnHealthLogsSync", "()Lkotlin/jvm/functions/Function1;", "setOnHealthLogsSync", "(Lkotlin/jvm/functions/Function1;)V", "bottomBarVisibility", "visible", "init", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "registerDiagnosisClick", "registerListeners", "registerSyncUpdates", "setListener", "setMenuIconState", "setNavigationController", "startAndBindSyncService", "unregisterSyncUpdates", "TAB", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class DashboardActivity extends com.cardio.physician.ui.common.base.activity.BaseActivity implements android.view.View.OnClickListener {
    private kotlin.jvm.functions.Function0<kotlin.Unit> onDiagnosisClick;
    private com.cardio.physician.ui.views.dashboard.DashboardActivity.TAB currentTab = com.cardio.physician.ui.views.dashboard.DashboardActivity.TAB.DASHBOARD;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onHealthLogsSync;
    private com.cardio.physician.ui.service.SyncHeathDataService mService;
    private boolean mBound = false;
    private final kotlin.Lazy binding$delegate = null;
    private final kotlin.Lazy navController$delegate = null;
    
    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private final android.content.ServiceConnection connection = null;
    
    public DashboardActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnHealthLogsSync() {
        return null;
    }
    
    public final void setOnHealthLogsSync(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> p0) {
    }
    
    private final com.cardio.physician.databinding.ActivityDashboardBinding getBinding() {
        return null;
    }
    
    private final androidx.navigation.NavController getNavController() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void init() {
    }
    
    private final void setListener() {
    }
    
    private final void setMenuIconState(com.cardio.physician.ui.views.dashboard.DashboardActivity.TAB currentTab) {
    }
    
    public final void bottomBarVisibility(boolean visible) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View view) {
    }
    
    private final void startAndBindSyncService() {
    }
    
    private final void setNavigationController() {
    }
    
    private final void registerListeners() {
    }
    
    public final void registerSyncUpdates(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onHealthLogsSync) {
    }
    
    public final void registerDiagnosisClick(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDiagnosisClick) {
    }
    
    public final void unregisterSyncUpdates() {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/DashboardActivity$TAB;", "", "(Ljava/lang/String;I)V", "DASHBOARD", "PROFILE", "app_qaDebug"})
    public static enum TAB {
        /*public static final*/ DASHBOARD /* = new DASHBOARD() */,
        /*public static final*/ PROFILE /* = new PROFILE() */;
        
        TAB() {
        }
    }
}
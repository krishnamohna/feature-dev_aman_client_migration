package com.cardio.physician.ui.views.dashboard.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.databinding.FragmentDashboardBinding;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment;
import com.cardio.physician.ui.common.customviews.toolbar.DashBoardToolbarImp;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.views.dashboard.DashboardActivity;
import com.cardio.physician.ui.views.dashboard.common.clinicalview.AFibClinicalView;
import com.cardio.physician.ui.views.dashboard.common.clinicalview.CHFClinicalView;
import com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph;
import com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph;
import com.cardio.physician.ui.views.dashboard.common.medicineview.AFibMedicationView;
import com.cardio.physician.ui.views.dashboard.common.medicineview.CHFMedicationView;
import com.cardio.physician.ui.views.dashboard.common.medicineview.IMedicineView;
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity;
import com.cardio.physician.ui.views.diagnosis.EditDiagnosisActivity;
import com.cardio.physician.ui.views.healthlogs.HealthLogsActivity;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00d4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0081\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\n\u0010V\u001a\u0004\u0018\u00010(H\u0002J\n\u0010W\u001a\u0004\u0018\u00010XH\u0016J\u0006\u0010Y\u001a\u00020ZJ\b\u0010[\u001a\u00020ZH\u0002J\b\u0010\\\u001a\u00020ZH\u0002J&\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010_\u001a\u00020`2\b\u0010a\u001a\u0004\u0018\u00010b2\b\u0010c\u001a\u0004\u0018\u00010dH\u0016J\b\u0010e\u001a\u00020ZH\u0016J\b\u0010f\u001a\u00020ZH\u0002J\b\u0010g\u001a\u00020ZH\u0002J\b\u0010h\u001a\u00020ZH\u0016J\b\u0010i\u001a\u00020ZH\u0016J\b\u0010j\u001a\u00020ZH\u0002J\u001a\u0010k\u001a\u00020Z2\u0006\u0010l\u001a\u00020^2\b\u0010c\u001a\u0004\u0018\u00010dH\u0016J\u0010\u0010m\u001a\u00020Z2\u0006\u0010n\u001a\u00020(H\u0002J\u0012\u0010o\u001a\u00020Z2\b\u0010p\u001a\u0004\u0018\u00010qH\u0002J\u0012\u0010o\u001a\u00020Z2\b\u0010n\u001a\u0004\u0018\u00010(H\u0002J\u0010\u0010r\u001a\u00020Z2\u0006\u0010n\u001a\u00020(H\u0002J\u0010\u0010s\u001a\u00020Z2\u0006\u0010n\u001a\u00020(H\u0002J\u0012\u0010t\u001a\u00020Z2\b\u0010u\u001a\u0004\u0018\u00010^H\u0002J\b\u0010v\u001a\u00020ZH\u0002J\b\u0010w\u001a\u00020ZH\u0002J\b\u0010x\u001a\u00020ZH\u0002J\u0010\u0010y\u001a\u00020Z2\u0006\u0010z\u001a\u00020{H\u0002J\u0018\u0010|\u001a\u00020Z2\u000e\u0010}\u001a\n\u0012\u0004\u0012\u00020\u007f\u0018\u00010~H\u0002J\u0012\u0010\u0080\u0001\u001a\u00020Z2\u0007\u0010\u0080\u0001\u001a\u000201H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001c\u0010\u0007R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\"\u0010\t\u001a\u0004\b \u0010!R\u0016\u0010#\u001a\u0004\u0018\u00010$8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\u0010\u0010\'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010*\u001a\u00020+8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000201X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u00108\u001a\b\u0012\u0004\u0012\u00020706X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001e\u0010=\u001a\u00020>8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010E\u001a\u00020F8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001b\u0010K\u001a\u00020L8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bO\u0010\t\u001a\u0004\bM\u0010NR\u001e\u0010P\u001a\u00020Q8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010U\u00a8\u0006\u0082\u0001"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseToolBarFragment;", "Lcom/cardio/physician/databinding/FragmentDashboardBinding;", "()V", "afibMedicineView", "Lcom/cardio/physician/ui/views/dashboard/common/medicineview/IMedicineView;", "getAfibMedicineView", "()Lcom/cardio/physician/ui/views/dashboard/common/medicineview/IMedicineView;", "afibMedicineView$delegate", "Lkotlin/Lazy;", "afibView", "Lcom/cardio/physician/ui/views/dashboard/common/clinicalview/AFibClinicalView;", "getAfibView", "()Lcom/cardio/physician/ui/views/dashboard/common/clinicalview/AFibClinicalView;", "afibView$delegate", "args", "Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardFragmentArgs;", "getArgs", "()Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "bpGraph", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BpGraph;", "getBpGraph", "()Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BpGraph;", "setBpGraph", "(Lcom/cardio/physician/ui/views/dashboard/common/graph/base/BpGraph;)V", "chfMedicationView", "getChfMedicationView", "chfMedicationView$delegate", "chfView", "Lcom/cardio/physician/ui/views/dashboard/common/clinicalview/CHFClinicalView;", "getChfView", "()Lcom/cardio/physician/ui/views/dashboard/common/clinicalview/CHFClinicalView;", "chfView$delegate", "diagnosisActivity", "Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivity;", "getDiagnosisActivity", "()Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivity;", "diagnosisModelAfib", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "diagnosisModelChib", "heartRateGraph", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/HeartRateGraph;", "getHeartRateGraph", "()Lcom/cardio/physician/ui/views/dashboard/common/graph/base/HeartRateGraph;", "setHeartRateGraph", "(Lcom/cardio/physician/ui/views/dashboard/common/graph/base/HeartRateGraph;)V", "isAfibDiagnosisLoaded", "", "isChfDiagnosisLoaded", "notificationUpdateReciever", "Landroid/content/BroadcastReceiver;", "resultDiagnosisReportSubmition", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "resultHealthLogs", "getResultHealthLogs", "()Landroidx/activity/result/ActivityResultLauncher;", "setResultHealthLogs", "(Landroidx/activity/result/ActivityResultLauncher;)V", "stepCountGraph", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/StepCountGraph;", "getStepCountGraph", "()Lcom/cardio/physician/ui/views/dashboard/common/graph/base/StepCountGraph;", "setStepCountGraph", "(Lcom/cardio/physician/ui/views/dashboard/common/graph/base/StepCountGraph;)V", "toolbar", "Lcom/cardio/physician/ui/common/customviews/toolbar/DashBoardToolbarImp;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "getUserManager", "()Lcom/cardio/physician/data/local/UserManager;", "setUserManager", "(Lcom/cardio/physician/data/local/UserManager;)V", "viewModel", "Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardViewModel;", "viewModel$delegate", "weightGraph", "Lcom/cardio/physician/ui/views/dashboard/common/graph/base/WeightGraph;", "getWeightGraph", "()Lcom/cardio/physician/ui/views/dashboard/common/graph/base/WeightGraph;", "setWeightGraph", "(Lcom/cardio/physician/ui/views/dashboard/common/graph/base/WeightGraph;)V", "getLatestDiagnosis", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "init", "", "loadDiagnosis", "loadGraphData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onDiagnosisFailed", "onDiagnosisLoaded", "onPause", "onResume", "onUserDetailLoaded", "onViewCreated", "view", "setAfibData", "diagnosisModel", "setBasicInfo", "userModel", "Lcom/cardio/physician/domain/common/model/UserModel;", "setChbData", "setDiagnosisDataInView", "setFilterButtonState", "it", "setListener", "setObservers", "setViews", "setVisibilityOfGraphs", "visibility", "", "showGraphs", "listHealthLogs", "", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "showProgress", "Filter", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class DashboardFragment extends com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment<com.cardio.physician.databinding.FragmentDashboardBinding> {
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    private com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModelChib;
    private com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModelAfib;
    private com.cardio.physician.ui.common.customviews.toolbar.DashBoardToolbarImp toolbar;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private boolean isChfDiagnosisLoaded = false;
    private boolean isAfibDiagnosisLoaded = false;
    private final kotlin.Lazy chfView$delegate = null;
    private final kotlin.Lazy afibView$delegate = null;
    private final kotlin.Lazy chfMedicationView$delegate = null;
    private final kotlin.Lazy afibMedicineView$delegate = null;
    @javax.inject.Inject()
    public com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph weightGraph;
    @javax.inject.Inject()
    public com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph stepCountGraph;
    @javax.inject.Inject()
    public com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph heartRateGraph;
    @javax.inject.Inject()
    public com.cardio.physician.data.local.UserManager userManager;
    @javax.inject.Inject()
    public com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph bpGraph;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultDiagnosisReportSubmition;
    @org.jetbrains.annotations.NotNull()
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultHealthLogs;
    private final android.content.BroadcastReceiver notificationUpdateReciever = null;
    
    public DashboardFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.fragment.DashboardFragmentArgs getArgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.fragment.DashboardViewModel getViewModel() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.dashboard.common.clinicalview.CHFClinicalView getChfView() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.dashboard.common.clinicalview.AFibClinicalView getAfibView() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.dashboard.common.medicineview.IMedicineView getChfMedicationView() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.dashboard.common.medicineview.IMedicineView getAfibMedicineView() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph getWeightGraph() {
        return null;
    }
    
    public final void setWeightGraph(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph getStepCountGraph() {
        return null;
    }
    
    public final void setStepCountGraph(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph getHeartRateGraph() {
        return null;
    }
    
    public final void setHeartRateGraph(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.data.local.UserManager getUserManager() {
        return null;
    }
    
    public final void setUserManager(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph getBpGraph() {
        return null;
    }
    
    public final void setBpGraph(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph p0) {
    }
    
    private final com.cardio.physician.ui.views.diagnosis.DiagnosisActivity getDiagnosisActivity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.activity.result.ActivityResultLauncher<android.content.Intent> getResultHealthLogs() {
        return null;
    }
    
    public final void setResultHealthLogs(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResultLauncher<android.content.Intent> p0) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
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
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void showProgress(boolean showProgress) {
    }
    
    private final void setViews() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
    
    private final void setObservers() {
    }
    
    private final void onUserDetailLoaded() {
    }
    
    private final void onDiagnosisFailed() {
    }
    
    private final void onDiagnosisLoaded() {
    }
    
    public final void init() {
    }
    
    private final void loadDiagnosis() {
    }
    
    private final void setListener() {
    }
    
    private final void loadGraphData() {
    }
    
    private final void setFilterButtonState(android.view.View it) {
    }
    
    private final void setVisibilityOfGraphs(int visibility) {
    }
    
    private final void setDiagnosisDataInView(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
    }
    
    private final com.cardio.physician.domain.diagnosis.DiagnosisModel getLatestDiagnosis() {
        return null;
    }
    
    private final void showGraphs(java.util.List<com.cardio.physician.domain.fitness.model.FitnessModel> listHealthLogs) {
    }
    
    private final void setAfibData(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
    }
    
    private final void setChbData(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
    }
    
    private final void setBasicInfo(com.cardio.physician.domain.common.model.UserModel userModel) {
    }
    
    private final void setBasicInfo(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardFragment$Filter;", "", "value", "", "(Ljava/lang/String;IJ)V", "getValue", "()J", "THIRTY", "SIXTY", "NINETY", "app_qaDebug"})
    public static enum Filter {
        /*public static final*/ THIRTY /* = new THIRTY(0L) */,
        /*public static final*/ SIXTY /* = new SIXTY(0L) */,
        /*public static final*/ NINETY /* = new NINETY(0L) */;
        private final long value = 0L;
        
        Filter(long value) {
        }
        
        public final long getValue() {
            return 0L;
        }
    }
}
package com.cardio.physician.ui.views.profile.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentGetProfileBinding;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.customviews.toolbar.ProfileToolbarImp;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.cardio.physician.ui.views.dashboard.DashboardActivity;
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity;
import com.cardio.physician.ui.views.healthlogs.HealthLogsActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0016\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u0012\u0010\u001e\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J&\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010(\u001a\u00020\u001aH\u0016J\b\u0010)\u001a\u00020\u001aH\u0016J\u001a\u0010*\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010+\u001a\u00020\u001aH\u0002J\b\u0010,\u001a\u00020\u001aH\u0002J\u0012\u0010-\u001a\u00020\u001a2\b\u0010.\u001a\u0004\u0018\u00010/H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014\u00a8\u00060"}, d2 = {"Lcom/cardio/physician/ui/views/profile/profile/GetProfileFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseToolBarFragment;", "Lcom/cardio/physician/databinding/FragmentGetProfileBinding;", "Landroid/view/View$OnClickListener;", "()V", "resultDiagnosisReportSubmition", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "resultHealthLogs", "getResultHealthLogs", "()Landroidx/activity/result/ActivityResultLauncher;", "setResultHealthLogs", "(Landroidx/activity/result/ActivityResultLauncher;)V", "userModel", "Lcom/cardio/physician/domain/common/model/UserModel;", "userType", "", "viewModel", "Lcom/cardio/physician/ui/views/profile/profile/UserProfileViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/profile/profile/UserProfileViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "handleApiCallback", "", "apiResponse", "Lcom/cardio/physician/network/Resource;", "", "onClick", "view", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onViewCreated", "setListener", "setObservers", "showUserDetailOnUI", "documentReference", "Lcom/google/firebase/firestore/DocumentSnapshot;", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class GetProfileFragment extends com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment<com.cardio.physician.databinding.FragmentGetProfileBinding> implements android.view.View.OnClickListener {
    private java.lang.String userType;
    private com.cardio.physician.domain.common.model.UserModel userModel;
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultHealthLogs;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultDiagnosisReportSubmition;
    
    public GetProfileFragment() {
        super();
    }
    
    private final com.cardio.physician.ui.views.profile.profile.UserProfileViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.activity.result.ActivityResultLauncher<android.content.Intent> getResultHealthLogs() {
        return null;
    }
    
    public final void setResultHealthLogs(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResultLauncher<android.content.Intent> p0) {
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
    
    private final void setListener() {
    }
    
    private final void setObservers() {
    }
    
    private final void handleApiCallback(com.cardio.physician.network.Resource<? extends java.lang.Object> apiResponse) {
    }
    
    private final void showUserDetailOnUI(com.google.firebase.firestore.DocumentSnapshot documentReference) {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View view) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
}
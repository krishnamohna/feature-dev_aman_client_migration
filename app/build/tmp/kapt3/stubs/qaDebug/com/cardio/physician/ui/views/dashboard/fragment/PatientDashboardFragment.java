package com.cardio.physician.ui.views.dashboard.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.databinding.FragmentPatientDashboardBinding;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment;
import com.cardio.physician.ui.common.customviews.toolbar.DashBoardToolbarImp;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.views.add_patient.AddPatientActivity;
import com.cardio.physician.ui.views.dashboard.DashboardActivity;
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity;
import com.cardio.physician.ui.views.notifications.NotificationsActivity;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00018B\u0005\u00a2\u0006\u0002\u0010\u0003J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0006\u0010\u001e\u001a\u00020\u001fJ&\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010(\u001a\u00020\u001fH\u0016J\b\u0010)\u001a\u00020\u001fH\u0016J\u001a\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010,\u001a\u00020\u001fH\u0002J\u0012\u0010-\u001a\u00020\u001f2\b\u0010.\u001a\u0004\u0018\u00010/H\u0002J\b\u00100\u001a\u00020\u001fH\u0002J\b\u00101\u001a\u00020\u001fH\u0002J\b\u00102\u001a\u00020\u001fH\u0002J\u0012\u00103\u001a\u00020\u001f2\b\u0010.\u001a\u0004\u0018\u00010/H\u0002J\u0010\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020\u001f2\u0006\u00105\u001a\u000206H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0016\u001a\u00020\u00178FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019\u00a8\u00069"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/fragment/PatientDashboardFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseToolBarFragment;", "Lcom/cardio/physician/databinding/FragmentPatientDashboardBinding;", "()V", "adapter", "Lcom/cardio/physician/ui/views/dashboard/fragment/ConnectionsAdapter;", "getAdapter", "()Lcom/cardio/physician/ui/views/dashboard/fragment/ConnectionsAdapter;", "setAdapter", "(Lcom/cardio/physician/ui/views/dashboard/fragment/ConnectionsAdapter;)V", "endDate", "", "Ljava/lang/Long;", "startDate", "toolbar", "Lcom/cardio/physician/ui/common/customviews/toolbar/DashBoardToolbarImp;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "getUserManager", "()Lcom/cardio/physician/data/local/UserManager;", "setUserManager", "(Lcom/cardio/physician/data/local/UserManager;)V", "viewModel", "Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "init", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "resetFields", "saveInfoToPrefrence", "userModel", "Lcom/cardio/physician/domain/common/model/UserModel;", "setListener", "setObservers", "setRecyclerView", "setUserDataInView", "showHidePatientView", "showHide", "", "showHidePatientViewListEmpty", "TextChangeWatcher", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class PatientDashboardFragment extends com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment<com.cardio.physician.databinding.FragmentPatientDashboardBinding> {
    private com.cardio.physician.ui.common.customviews.toolbar.DashBoardToolbarImp toolbar;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    public com.cardio.physician.ui.views.dashboard.fragment.ConnectionsAdapter adapter;
    private java.lang.Long startDate = 0L;
    private java.lang.Long endDate = 0L;
    @javax.inject.Inject()
    public com.cardio.physician.data.local.UserManager userManager;
    
    public PatientDashboardFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.fragment.DashboardViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.fragment.ConnectionsAdapter getAdapter() {
        return null;
    }
    
    public final void setAdapter(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.fragment.ConnectionsAdapter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.data.local.UserManager getUserManager() {
        return null;
    }
    
    public final void setUserManager(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager p0) {
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
    
    private final void showHidePatientView(int showHide) {
    }
    
    private final void showHidePatientViewListEmpty(int showHide) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
    
    private final void setObservers() {
    }
    
    public final void init() {
    }
    
    private final void saveInfoToPrefrence(com.cardio.physician.domain.common.model.UserModel userModel) {
    }
    
    private final void resetFields() {
    }
    
    private final void setRecyclerView() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void setListener() {
    }
    
    private final void setUserDataInView(com.cardio.physician.domain.common.model.UserModel userModel) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J(\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/fragment/PatientDashboardFragment$TextChangeWatcher;", "Landroid/text/TextWatcher;", "view", "Landroid/view/View;", "(Lcom/cardio/physician/ui/views/dashboard/fragment/PatientDashboardFragment;Landroid/view/View;)V", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onTextChanged", "before", "app_qaDebug"})
    public final class TextChangeWatcher implements android.text.TextWatcher {
        private android.view.View view;
        
        public TextChangeWatcher(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super();
        }
        
        @java.lang.Override()
        public void beforeTextChanged(@org.jetbrains.annotations.NotNull()
        java.lang.CharSequence s, int start, int count, int after) {
        }
        
        @java.lang.Override()
        public void onTextChanged(@org.jetbrains.annotations.NotNull()
        java.lang.CharSequence s, int start, int before, int count) {
        }
        
        @java.lang.Override()
        public void afterTextChanged(@org.jetbrains.annotations.Nullable()
        android.text.Editable p0) {
        }
    }
}
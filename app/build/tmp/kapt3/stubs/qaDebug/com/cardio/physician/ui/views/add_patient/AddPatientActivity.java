package com.cardio.physician.ui.views.add_patient;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cardio.physician.R;
import com.cardio.physician.databinding.ActivityAddPatientBinding;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001e2\u00020\u00012\u00020\u0002:\u0002\u001e\u001fB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006 "}, d2 = {"Lcom/cardio/physician/ui/views/add_patient/AddPatientActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cardio/physician/ui/views/add_patient/PatientAdapter;", "binding", "Lcom/cardio/physician/databinding/ActivityAddPatientBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityAddPatientBinding;", "binding$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/cardio/physician/ui/views/add_patient/AddPatientViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/add_patient/AddPatientViewModel;", "viewModel$delegate", "onClick", "", "p0", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setListeners", "setObservers", "setRecyclerView", "showHidePatientView", "showHide", "", "Companion", "TextChangeWatcher", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class AddPatientActivity extends com.cardio.physician.ui.common.base.activity.BaseActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.add_patient.AddPatientActivity.Companion Companion = null;
    private com.cardio.physician.ui.views.add_patient.PatientAdapter adapter;
    private final kotlin.Lazy binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    
    public AddPatientActivity() {
        super();
    }
    
    private final com.cardio.physician.databinding.ActivityAddPatientBinding getBinding() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.add_patient.AddPatientViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setListeners() {
    }
    
    private final void setRecyclerView() {
    }
    
    private final void setObservers() {
    }
    
    private final void showHidePatientView(int showHide) {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View p0) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J(\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/cardio/physician/ui/views/add_patient/AddPatientActivity$TextChangeWatcher;", "Landroid/text/TextWatcher;", "view", "Landroid/view/View;", "(Lcom/cardio/physician/ui/views/add_patient/AddPatientActivity;Landroid/view/View;)V", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onTextChanged", "before", "app_qaDebug"})
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
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/cardio/physician/ui/views/add_patient/AddPatientActivity$Companion;", "", "()V", "start", "", "activity", "Landroid/app/Activity;", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity) {
        }
    }
}
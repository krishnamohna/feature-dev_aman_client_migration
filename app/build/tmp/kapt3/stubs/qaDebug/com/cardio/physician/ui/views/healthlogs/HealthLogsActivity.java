package com.cardio.physician.ui.views.healthlogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.cardio.physician.R;
import com.cardio.physician.databinding.ActivityHealthLogsBinding;
import com.cardio.physician.domain.common.model.validation.ValidationModelV2;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.network.Status;
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity;
import com.cardio.physician.ui.common.customviews.toolbar.HealthLogsToolbarImp;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.common.utils.inputfilter.DecimalDigitsInputFilter;
import com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper;
import com.cardio.physician.ui.common.utils.validation.FieldType;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 32\u00020\u0001:\u00013B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u0017H\u0002J\b\u0010!\u001a\u00020\u0017H\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u0017H\u0002J\b\u0010&\u001a\u00020\u0017H\u0002J\b\u0010\'\u001a\u00020\u0017H\u0002J(\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020\u0017H\u0002J\u0010\u00101\u001a\u00020\u00172\u0006\u0010#\u001a\u000202H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\b\u001a\u0004\b\u0013\u0010\u0014\u00a8\u00064"}, d2 = {"Lcom/cardio/physician/ui/views/healthlogs/HealthLogsActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseToolbarActivity;", "()V", "binding", "Lcom/cardio/physician/databinding/ActivityHealthLogsBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityHealthLogsBinding;", "binding$delegate", "Lkotlin/Lazy;", "labelVisiblityHelper", "Lcom/cardio/physician/ui/common/utils/textwatcher/LabelVisiblityHelper;", "getLabelVisiblityHelper", "()Lcom/cardio/physician/ui/common/utils/textwatcher/LabelVisiblityHelper;", "setLabelVisiblityHelper", "(Lcom/cardio/physician/ui/common/utils/textwatcher/LabelVisiblityHelper;)V", "selectedDate", "Ljava/util/Date;", "viewModel", "Lcom/cardio/physician/ui/views/healthlogs/HealthLogsViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/healthlogs/HealthLogsViewModel;", "viewModel$delegate", "clearAllFields", "", "enableButtonSave", "isEnable", "", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSubmitClick", "setAlphaVisibility", "setFitnessViews", "it", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "setInputFilters", "setListeners", "setObservers", "setValidationOnViews", "fieldType", "Lcom/cardio/physician/ui/common/utils/validation/FieldType;", "edtRoundedCorner", "", "visibility", "message", "", "setViews", "setViewsForValidations", "Lcom/cardio/physician/domain/common/model/validation/ValidationModelV2;", "Companion", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class HealthLogsActivity extends com.cardio.physician.ui.common.base.activity.BaseToolbarActivity {
    private java.util.Date selectedDate;
    private final kotlin.Lazy binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.healthlogs.HealthLogsActivity.Companion Companion = null;
    @javax.inject.Inject()
    public com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper labelVisiblityHelper;
    
    public HealthLogsActivity() {
        super();
    }
    
    private final com.cardio.physician.databinding.ActivityHealthLogsBinding getBinding() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.healthlogs.HealthLogsViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper getLabelVisiblityHelper() {
        return null;
    }
    
    public final void setLabelVisiblityHelper(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
    
    private final void setObservers() {
    }
    
    private final void clearAllFields() {
    }
    
    private final void setFitnessViews(com.cardio.physician.domain.fitness.model.FitnessModel it) {
    }
    
    private final void setViews() {
    }
    
    private final void setInputFilters() {
    }
    
    private final void setListeners() {
    }
    
    private final void setAlphaVisibility() {
    }
    
    private final void enableButtonSave(boolean isEnable) {
    }
    
    private final void onSubmitClick() {
    }
    
    private final void setViewsForValidations(com.cardio.physician.domain.common.model.validation.ValidationModelV2 it) {
    }
    
    private final void setValidationOnViews(com.cardio.physician.ui.common.utils.validation.FieldType fieldType, int edtRoundedCorner, int visibility, java.lang.String message) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a8\u0006\t"}, d2 = {"Lcom/cardio/physician/ui/views/healthlogs/HealthLogsActivity$Companion;", "", "()V", "getIntent", "Landroid/content/Intent;", "activity", "Landroid/app/Activity;", "userId", "", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getIntent(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity, @org.jetbrains.annotations.Nullable()
        java.lang.String userId) {
            return null;
        }
    }
}
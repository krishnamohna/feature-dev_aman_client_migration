package com.cardio.physician.ui.views.diagnosis.step1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentDiagnosisPart1Binding;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.common.model.validation.ValidationModelV2;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.fitness.model.HeartRateModel;
import com.cardio.physician.network.Status;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.common.utils.inputfilter.DecimalDigitsInputFilter;
import com.cardio.physician.ui.common.utils.keyboard.KeyboardEventListener;
import com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper;
import com.cardio.physician.ui.common.utils.validation.FieldType;
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment;
import com.cardio.physician.ui.views.diagnosis.step1.adapter.AilmentDropDownAdapter;
import com.cardio.physician.ui.views.sync_health_data.activity.SyncHealthActivty;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0011H\u0002J\b\u0010$\u001a\u00020\"H\u0002J?\u0010%\u001a\u00020\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0\'2\'\u0010(\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020+0*\u00a2\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\"0)H\u0002Jb\u0010/\u001a\u00020\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0\'2\'\u0010(\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020+0*\u00a2\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\"0)2!\u00100\u001a\u001d\u0012\u0013\u0012\u001101\u00a2\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\"0)H\u0002J\b\u00103\u001a\u00020\"H\u0016J$\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010<\u001a\u00020\"H\u0002J\b\u0010=\u001a\u00020\"H\u0016J\b\u0010>\u001a\u00020\"H\u0016J\u0006\u0010?\u001a\u00020\"J\u0016\u0010@\u001a\u00020\"2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002J\u001a\u0010B\u001a\u00020\"2\u0006\u0010C\u001a\u0002052\b\u0010:\u001a\u0004\u0018\u00010;H\u0016JP\u0010D\u001a\u00020\"2\u0006\u0010E\u001a\u0002012\u0006\u0010F\u001a\u0002012\u0006\u0010G\u001a\u0002012\u0006\u0010H\u001a\u0002012\u0006\u0010I\u001a\u0002012\u0006\u0010J\u001a\u0002012\u0006\u0010K\u001a\u0002012\u0006\u0010L\u001a\u0002012\u0006\u0010M\u001a\u000201H\u0002J\b\u0010N\u001a\u00020\"H\u0002J\b\u0010O\u001a\u00020\"H\u0002J\b\u0010P\u001a\u00020\"H\u0002J\b\u0010Q\u001a\u00020\"H\u0002J\b\u0010R\u001a\u00020\"H\u0016J\u0012\u0010S\u001a\u00020\"2\b\u0010T\u001a\u0004\u0018\u00010UH\u0002J\u0010\u0010S\u001a\u00020\"2\u0006\u0010V\u001a\u00020WH\u0002J\u0010\u0010S\u001a\u00020\"2\u0006\u0010A\u001a\u00020XH\u0002J(\u0010Y\u001a\u00020\"2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020]2\u0006\u0010_\u001a\u000201H\u0002J\b\u0010`\u001a\u00020\"H\u0002J\b\u0010a\u001a\u00020\"H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006b"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step1/DiagnosisFragmentStep1;", "Lcom/cardio/physician/ui/views/diagnosis/common/BaseDiagnosisFragment;", "Lcom/cardio/physician/databinding/FragmentDiagnosisPart1Binding;", "()V", "args", "Lcom/cardio/physician/ui/views/diagnosis/step1/DiagnosisFragmentStep1Args;", "getArgs", "()Lcom/cardio/physician/ui/views/diagnosis/step1/DiagnosisFragmentStep1Args;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "birthDate", "Ljava/util/Date;", "getBirthDate", "()Ljava/util/Date;", "setBirthDate", "(Ljava/util/Date;)V", "isKeyBoardOpen", "", "labelVisiblityHelper", "Lcom/cardio/physician/ui/common/utils/textwatcher/LabelVisiblityHelper;", "getLabelVisiblityHelper", "()Lcom/cardio/physician/ui/common/utils/textwatcher/LabelVisiblityHelper;", "setLabelVisiblityHelper", "(Lcom/cardio/physician/ui/common/utils/textwatcher/LabelVisiblityHelper;)V", "resultLauncherFitnessData", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "viewModel", "Lcom/cardio/physician/ui/views/diagnosis/step1/DiagnosisViewStep1ViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/diagnosis/step1/DiagnosisViewStep1ViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "enableButtonClick", "", "isEnable", "init", "isAllAlphaValidationSuccessFull", "success", "Lkotlin/Function0;", "failed", "Lkotlin/Function1;", "", "Lcom/cardio/physician/domain/common/model/validation/ValidationModelV2;", "Lkotlin/ParameterName;", "name", "validations", "isAllValidationSuccessFull", "failed2", "", "error", "loadUserProfile", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onFieldTextChange", "onNextClickAfterValidation", "onResume", "onSubmitClick", "onValidationsFailed", "it", "onViewCreated", "view", "saveStateToParent", "ailment", "firstName", "lastName", "age", "weight", "heartRate", "topBp", "bottomBp", "steps", "setAlphaVisibilityListener", "setInputFilters", "setLabelVisibilityListener", "setListeners", "setObservers", "setPatientDetail", "userModel", "Lcom/cardio/physician/domain/common/model/UserModel;", "fitnessModel", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "Lcom/cardio/physician/domain/fitness/model/HeartRateModel;", "setValidationOnViews", "fieldType", "Lcom/cardio/physician/ui/common/utils/validation/FieldType;", "edtRoundedCorner", "", "visibility", "message", "setViewListener", "setViews", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public class DiagnosisFragmentStep1 extends com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment<com.cardio.physician.databinding.FragmentDiagnosisPart1Binding> {
    @org.jetbrains.annotations.Nullable()
    private java.util.Date birthDate;
    private boolean isKeyBoardOpen = false;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @javax.inject.Inject()
    public com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper labelVisiblityHelper;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultLauncherFitnessData;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    
    public DiagnosisFragmentStep1() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date getBirthDate() {
        return null;
    }
    
    public final void setBirthDate(@org.jetbrains.annotations.Nullable()
    java.util.Date p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.diagnosis.step1.DiagnosisViewStep1ViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper getLabelVisiblityHelper() {
        return null;
    }
    
    public final void setLabelVisiblityHelper(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.textwatcher.LabelVisiblityHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
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
    
    private final void init() {
    }
    
    public void loadUserProfile() {
    }
    
    private final void setInputFilters() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    public void setObservers() {
    }
    
    private final void setPatientDetail(com.cardio.physician.domain.fitness.model.HeartRateModel it) {
    }
    
    private final void setPatientDetail(com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel) {
    }
    
    private final void setPatientDetail(com.cardio.physician.domain.common.model.UserModel userModel) {
    }
    
    public void setViews() {
    }
    
    private final void setListeners() {
    }
    
    private final void setViewListener() {
    }
    
    private final void setAlphaVisibilityListener() {
    }
    
    private final void onFieldTextChange() {
    }
    
    private final void setLabelVisibilityListener() {
    }
    
    public final void onSubmitClick() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.diagnosis.step1.DiagnosisFragmentStep1Args getArgs() {
        return null;
    }
    
    public void onNextClickAfterValidation() {
    }
    
    private final void isAllValidationSuccessFull(kotlin.jvm.functions.Function0<kotlin.Unit> success, kotlin.jvm.functions.Function1<? super java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2>, kotlin.Unit> failed, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> failed2) {
    }
    
    private final void isAllAlphaValidationSuccessFull(kotlin.jvm.functions.Function0<kotlin.Unit> success, kotlin.jvm.functions.Function1<? super java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2>, kotlin.Unit> failed) {
    }
    
    private final void saveStateToParent(java.lang.String ailment, java.lang.String firstName, java.lang.String lastName, java.lang.String age, java.lang.String weight, java.lang.String heartRate, java.lang.String topBp, java.lang.String bottomBp, java.lang.String steps) {
    }
    
    private final void onValidationsFailed(java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2> it) {
    }
    
    private final void setValidationOnViews(com.cardio.physician.ui.common.utils.validation.FieldType fieldType, int edtRoundedCorner, int visibility, java.lang.String message) {
    }
    
    private final void enableButtonClick(boolean isEnable) {
    }
}
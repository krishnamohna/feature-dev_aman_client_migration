package com.cardio.physician.ui.views.diagnosis.step4;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentDiagnosisPart4Binding;
import com.cardio.physician.databinding.ItemMedicationPreviewLayoutBinding;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.ui.common.customviews.questions.*;
import com.cardio.physician.ui.common.utils.QuestionTypes;
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity;
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J&\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\u001cH\u0002J\b\u0010 \u001a\u00020\u001cH\u0002J\b\u0010!\u001a\u00020\u001cH\u0002J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u0011H\u0002J\b\u0010$\u001a\u00020\u001cH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006%"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4;", "Lcom/cardio/physician/ui/views/diagnosis/common/BaseDiagnosisFragment;", "Lcom/cardio/physician/databinding/FragmentDiagnosisPart4Binding;", "()V", "args", "Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4Args;", "getArgs", "()Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4Args;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "viewModel", "Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4ViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4ViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getQuestionView", "Landroid/view/View;", "question", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setListeners", "setObserver", "setViews", "showMedicationData", "showQuestion", "questionView", "showQuestionare", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class DiagnosisFragmentStep4 extends com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment<com.cardio.physician.databinding.FragmentDiagnosisPart4Binding> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    
    public DiagnosisFragmentStep4() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4Args getArgs() {
        return null;
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
    
    private final void setObserver() {
    }
    
    private final void setViews() {
    }
    
    private final void showQuestionare() {
    }
    
    private final void showQuestion(android.view.View questionView) {
    }
    
    private final android.view.View getQuestionView(com.cardio.physician.domain.questionare.model.QuestionModel question) {
        return null;
    }
    
    private final void showMedicationData() {
    }
    
    private final void setListeners() {
    }
}
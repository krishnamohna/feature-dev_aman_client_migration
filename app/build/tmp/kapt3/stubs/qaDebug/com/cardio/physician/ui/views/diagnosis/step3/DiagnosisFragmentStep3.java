package com.cardio.physician.ui.views.diagnosis.step3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentDiagnosisPart3Binding;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.ui.common.customviews.questions.*;
import com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView;
import com.cardio.physician.ui.common.customviews.questions.base.QuestionView;
import com.cardio.physician.ui.common.utils.QuestionTypes;
import com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment;
import com.cardio.physician.ui.views.diagnosis.step1.DiagnosisFragmentStep1Args;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\fH\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0014H\u0002J\b\u0010!\u001a\u00020\u0016H\u0016J\b\u0010\"\u001a\u00020\u0016H\u0016J\b\u0010#\u001a\u00020\u0014H\u0002J\b\u0010$\u001a\u00020\u0014H\u0016J&\u0010%\u001a\u0004\u0018\u00010\u001a2\u0006\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u0014H\u0016J\u001a\u0010-\u001a\u00020\u00142\u0006\u0010.\u001a\u00020\u001a2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010/\u001a\u00020\u0014H\u0002J\b\u00100\u001a\u00020\u0014H\u0016J\u0018\u00101\u001a\u00020\u00142\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bH\u0002J\b\u00103\u001a\u00020\u0014H\u0016J\b\u00104\u001a\u00020\u0014H\u0002J\b\u00105\u001a\u00020\u0014H\u0002J\u0010\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u00020\u001aH\u0016J\f\u00108\u001a\u00020\u001a*\u000209H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006:"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step3/DiagnosisFragmentStep3;", "Lcom/cardio/physician/ui/views/diagnosis/common/BaseDiagnosisFragment;", "Lcom/cardio/physician/databinding/FragmentDiagnosisPart3Binding;", "()V", "args", "Lcom/cardio/physician/ui/views/diagnosis/step3/DiagnosisFragmentStep3Args;", "getArgs", "()Lcom/cardio/physician/ui/views/diagnosis/step3/DiagnosisFragmentStep3Args;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "questionList", "", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "viewModel", "Lcom/cardio/physician/ui/views/diagnosis/step3/DiagnosisFragmentStep3ViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/diagnosis/step3/DiagnosisFragmentStep3ViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "enableButtonClick", "", "isEnable", "", "enableLeftButton", "enableRightButton", "getQuestionView", "Landroid/view/View;", "question", "hasAnsweredCurrentQuestion", "hasNextQuestion", "hasPreviousQuestion", "hasUserGivenAllAnswers", "init", "isFirstQuestion", "isLastQuestion", "manageNextBackButtonBackground", "onBottomCancelButtonclick", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onNextButtonClick", "onViewCreated", "view", "setListeners", "setObservers", "setQuestionsInViews", "it", "setViews", "showNextQuestion", "showPreviousQuestion", "showQuestion", "questionView", "registerAnswerchange", "Lcom/cardio/physician/ui/common/customviews/questions/base/BaseQuestionView;", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public class DiagnosisFragmentStep3 extends com.cardio.physician.ui.views.diagnosis.common.BaseDiagnosisFragment<com.cardio.physician.databinding.FragmentDiagnosisPart3Binding> {
    private java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> questionList;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    
    public DiagnosisFragmentStep3() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.diagnosis.step3.DiagnosisFragmentStep3ViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.diagnosis.step3.DiagnosisFragmentStep3Args getArgs() {
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
    
    private final void init() {
    }
    
    public void setObservers() {
    }
    
    private final void setQuestionsInViews(java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> it) {
    }
    
    public void showQuestion(@org.jetbrains.annotations.NotNull()
    android.view.View questionView) {
    }
    
    private final void manageNextBackButtonBackground() {
    }
    
    private final void enableLeftButton(boolean isEnable) {
    }
    
    private final void enableRightButton(boolean isEnable) {
    }
    
    private final android.view.View getQuestionView(com.cardio.physician.domain.questionare.model.QuestionModel question) {
        return null;
    }
    
    public void setViews() {
    }
    
    private final void setListeners() {
    }
    
    public void onBottomCancelButtonclick() {
    }
    
    public void onNextButtonClick() {
    }
    
    private final boolean hasUserGivenAllAnswers() {
        return false;
    }
    
    private final void showNextQuestion() {
    }
    
    private final void showPreviousQuestion() {
    }
    
    private final boolean hasPreviousQuestion() {
        return false;
    }
    
    private final boolean hasNextQuestion() {
        return false;
    }
    
    private final boolean hasAnsweredCurrentQuestion() {
        return false;
    }
    
    private final android.view.View registerAnswerchange(com.cardio.physician.ui.common.customviews.questions.base.BaseQuestionView $this$registerAnswerchange) {
        return null;
    }
    
    private final void enableButtonClick(boolean isEnable) {
    }
    
    public boolean isLastQuestion() {
        return false;
    }
    
    public boolean isFirstQuestion() {
        return false;
    }
}
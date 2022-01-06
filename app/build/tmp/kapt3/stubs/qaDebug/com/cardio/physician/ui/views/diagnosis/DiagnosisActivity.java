package com.cardio.physician.ui.views.diagnosis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationHandler;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationResult;
import com.cardio.physician.databinding.ActivityDiagnosisBinding;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity;
import com.cardio.physician.ui.common.customviews.toolbar.DiagnosisToolbarImp;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.customviews.StepView;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.cardio.physician.ui.views.dashboard.fragment.DashboardFragment;
import dagger.hilt.android.AndroidEntryPoint;
import androidx.navigation.NavArgument;
import com.cardio.physician.R;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0017\u0018\u0000 W2\u00020\u00012\u00020\u0002:\u0001WB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010?\u001a\u00020@H\u0016J\"\u0010A\u001a\u00020+2\u0006\u0010B\u001a\u00020\u001d2\u0006\u0010C\u001a\u00020\u001d2\b\u0010D\u001a\u0004\u0018\u00010EH\u0014J\u0012\u0010F\u001a\u00020+2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\b\u0010I\u001a\u00020+H\u0016J\u0016\u00100\u001a\u00020+2\u000e\u0010J\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0*J\u0012\u0010K\u001a\u00020+2\b\u0010L\u001a\u0004\u0018\u00010MH\u0014J\u000e\u0010N\u001a\u00020+2\u0006\u0010O\u001a\u00020PJ\b\u0010Q\u001a\u00020+H\u0002J\b\u0010R\u001a\u00020+H\u0016J\u000e\u0010S\u001a\u00020+2\u0006\u0010T\u001a\u00020\u001dJ\u0010\u0010U\u001a\u00020+2\u0006\u00108\u001a\u000209H\u0016J\b\u0010V\u001a\u00020+H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0012\u0010\"\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010#R\u001b\u0010$\u001a\u00020%8@X\u0080\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010\t\u001a\u0004\b&\u0010\'R\"\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0018\u00100\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010+\u0018\u00010*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u00101\u001a\n\u0012\u0004\u0012\u000203\u0018\u000102X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010:\u001a\u00020;8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b>\u0010\t\u001a\u0004\b<\u0010=\u00a8\u0006X"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseToolbarActivity;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/authentication/AuthenticationHandler;", "()V", "binding", "Lcom/cardio/physician/databinding/ActivityDiagnosisBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityDiagnosisBinding;", "binding$delegate", "Lkotlin/Lazy;", "currentFilter", "Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardFragment$Filter;", "getCurrentFilter", "()Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardFragment$Filter;", "setCurrentFilter", "(Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardFragment$Filter;)V", "diagnosisModel", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "getDiagnosisModel$app_qaDebug", "()Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "setDiagnosisModel$app_qaDebug", "(Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;)V", "itoolbar", "Lcom/cardio/physician/ui/common/customviews/toolbar/DiagnosisToolbarImp;", "getItoolbar", "()Lcom/cardio/physician/ui/common/customviews/toolbar/DiagnosisToolbarImp;", "setItoolbar", "(Lcom/cardio/physician/ui/common/customviews/toolbar/DiagnosisToolbarImp;)V", "lastQuestionIndex", "", "getLastQuestionIndex", "()I", "setLastQuestionIndex", "(I)V", "lastStep", "Ljava/lang/Integer;", "navController", "Landroidx/navigation/NavController;", "getNavController$app_qaDebug", "()Landroidx/navigation/NavController;", "navController$delegate", "onBackClick", "Lkotlin/Function0;", "", "getOnBackClick", "()Lkotlin/jvm/functions/Function0;", "setOnBackClick", "(Lkotlin/jvm/functions/Function0;)V", "onConnectClick", "questionList", "", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "getQuestionList", "()Ljava/util/List;", "setQuestionList", "(Ljava/util/List;)V", "stepView", "Lcom/cardio/physician/ui/common/customviews/StepView;", "viewModel", "Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivityViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivityViewModel;", "viewModel$delegate", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAuthFinished", "authenticationResult", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/authentication/AuthenticationResult;", "onBackPressed", "function", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setConnectButtonVisibility", "visible", "", "setListeners", "setNavGraph", "setStepNo", "step", "setStepView", "setViews", "Companion", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public class DiagnosisActivity extends com.cardio.physician.ui.common.base.activity.BaseToolbarActivity implements com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationHandler {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.diagnosis.DiagnosisActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.ui.views.dashboard.fragment.DashboardFragment.Filter currentFilter = com.cardio.physician.ui.views.dashboard.fragment.DashboardFragment.Filter.THIRTY;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> questionList;
    private int lastQuestionIndex = 0;
    private kotlin.jvm.functions.Function0<kotlin.Unit> onConnectClick;
    private java.lang.Integer lastStep;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy navController$delegate = null;
    public com.cardio.physician.ui.common.customviews.toolbar.DiagnosisToolbarImp itoolbar;
    private final kotlin.Lazy binding$delegate = null;
    private com.cardio.physician.ui.common.customviews.StepView stepView;
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel;
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick;
    
    public DiagnosisActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.views.dashboard.fragment.DashboardFragment.Filter getCurrentFilter() {
        return null;
    }
    
    public final void setCurrentFilter(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.dashboard.fragment.DashboardFragment.Filter p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> getQuestionList() {
        return null;
    }
    
    public final void setQuestionList(@org.jetbrains.annotations.Nullable()
    java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> p0) {
    }
    
    public final int getLastQuestionIndex() {
        return 0;
    }
    
    public final void setLastQuestionIndex(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.navigation.NavController getNavController$app_qaDebug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.customviews.toolbar.DiagnosisToolbarImp getItoolbar() {
        return null;
    }
    
    public final void setItoolbar(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.customviews.toolbar.DiagnosisToolbarImp p0) {
    }
    
    private final com.cardio.physician.databinding.ActivityDiagnosisBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.diagnosis.DiagnosisModel getDiagnosisModel$app_qaDebug() {
        return null;
    }
    
    public final void setDiagnosisModel$app_qaDebug(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisModel p0) {
    }
    
    private final com.cardio.physician.ui.views.diagnosis.DiagnosisActivityViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setViews() {
    }
    
    public void setNavGraph() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void setListeners() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnBackClick() {
        return null;
    }
    
    public final void setOnBackClick(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
    
    public final void setConnectButtonVisibility(boolean visible) {
    }
    
    public final void setStepNo(int step) {
    }
    
    public void setStepView(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.customviews.StepView stepView) {
    }
    
    public final void onConnectClick(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> function) {
    }
    
    @java.lang.Override()
    public void onAuthFinished(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationResult authenticationResult) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivity$Companion;", "", "()V", "getActivityIntent", "Landroid/content/Intent;", "activity", "Landroid/app/Activity;", "start", "", "userId", "", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity, @org.jetbrains.annotations.Nullable()
        java.lang.String userId) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getActivityIntent(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity) {
            return null;
        }
    }
}
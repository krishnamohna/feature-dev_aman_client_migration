package com.cardio.physician.ui.views.diagnosis.common;

import androidx.viewbinding.ViewBinding;
import com.cardio.physician.ui.common.base.fragment.BaseFragment;
import com.cardio.physician.ui.common.customviews.StepView;
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000e"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/common/BaseDiagnosisFragment;", "Binding", "Landroidx/viewbinding/ViewBinding;", "Lcom/cardio/physician/ui/common/base/fragment/BaseFragment;", "()V", "diagnosisActivity", "Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivity;", "getDiagnosisActivity", "()Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivity;", "onCancelButtonClick", "", "setStepView", "stepView", "Lcom/cardio/physician/ui/common/customviews/StepView;", "app_qaDebug"})
public abstract class BaseDiagnosisFragment<Binding extends androidx.viewbinding.ViewBinding> extends com.cardio.physician.ui.common.base.fragment.BaseFragment<Binding> {
    
    public BaseDiagnosisFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.cardio.physician.ui.views.diagnosis.DiagnosisActivity getDiagnosisActivity() {
        return null;
    }
    
    public final void setStepView(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.customviews.StepView stepView) {
    }
    
    public final void onCancelButtonClick() {
    }
}
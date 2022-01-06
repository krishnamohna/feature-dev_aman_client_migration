package com.cardio.physician.ui.views.diagnosis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.cardio.physician.R;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.ui.common.customviews.StepView;
import com.cardio.physician.ui.common.utils.EXTRAS;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016\u00a8\u0006\f"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/EditDiagnosisActivity;", "Lcom/cardio/physician/ui/views/diagnosis/DiagnosisActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setNavGraph", "setStepView", "stepView", "Lcom/cardio/physician/ui/common/customviews/StepView;", "Companion", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class EditDiagnosisActivity extends com.cardio.physician.ui.views.diagnosis.DiagnosisActivity {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.diagnosis.EditDiagnosisActivity.Companion Companion = null;
    public static final int EDIT_BASIC_INFO = 1;
    public static final int EDIT_DIAGNOSIS_INFO = 2;
    public static final int EDIT_DIAGNOSIS_MEDICATION = 3;
    
    public EditDiagnosisActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void setStepView(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.customviews.StepView stepView) {
    }
    
    @java.lang.Override()
    public void setNavGraph() {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/EditDiagnosisActivity$Companion;", "", "()V", "EDIT_BASIC_INFO", "", "EDIT_DIAGNOSIS_INFO", "EDIT_DIAGNOSIS_MEDICATION", "getIntent", "Landroid/content/Intent;", "activity", "Landroid/app/Activity;", "diagnosisModel", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "editType", "userId", "", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getIntent(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity, @org.jetbrains.annotations.NotNull()
        com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel, int editType, @org.jetbrains.annotations.Nullable()
        java.lang.String userId) {
            return null;
        }
    }
}
package com.cardio.physician.ui.views.dashboard.common.clinicalview;

import android.view.View;
import android.widget.TextView;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentDashboardBinding;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.ui.common.utils.Constants;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.cardio.physician.ui.common.utils.QuestionTypes;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0016\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013\u00a8\u0006\u0017"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/clinicalview/CHFClinicalView;", "Lcom/cardio/physician/ui/views/dashboard/common/clinicalview/BaseClinicalView;", "()V", "getChfType", "", "diagnosisModel", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "getDefibrillatorLeads", "getDryWeight", "getEf", "getNyhaClass", "getPaceMakerLeads", "manageTextViewColor", "", "chfType", "tvChfType", "Landroid/widget/TextView;", "manageVisibilityOfViews", "binding", "Lcom/cardio/physician/databinding/FragmentDashboardBinding;", "setChFDiagnosisData", "showClinicalData", "Companion", "app_qaDebug"})
public final class CHFClinicalView extends com.cardio.physician.ui.views.dashboard.common.clinicalview.BaseClinicalView {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.dashboard.common.clinicalview.CHFClinicalView.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FAILURE_TYPE = "Heart Failure";
    
    public CHFClinicalView() {
        super();
    }
    
    public final void showClinicalData(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.databinding.FragmentDashboardBinding binding) {
    }
    
    private final void setChFDiagnosisData(com.cardio.physician.databinding.FragmentDashboardBinding binding, com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
    }
    
    private final void manageTextViewColor(java.lang.String chfType, android.widget.TextView tvChfType) {
    }
    
    @java.lang.Override()
    public void manageVisibilityOfViews(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.databinding.FragmentDashboardBinding binding) {
    }
    
    private final java.lang.String getPaceMakerLeads(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
    
    private final java.lang.String getDefibrillatorLeads(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
    
    private final java.lang.String getDryWeight(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
    
    private final java.lang.String getNyhaClass(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
    
    private final java.lang.String getEf(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
    
    private final java.lang.String getChfType(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/clinicalview/CHFClinicalView$Companion;", "", "()V", "FAILURE_TYPE", "", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
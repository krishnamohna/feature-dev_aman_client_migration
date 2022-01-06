package com.cardio.physician.ui.views.dashboard.common.medicineview;

import com.cardio.physician.databinding.FragmentDashboardBinding;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/medicineview/IMedicineView;", "", "showMedicationData", "", "diagnosisModel", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "binding", "Lcom/cardio/physician/databinding/FragmentDashboardBinding;", "app_qaDebug"})
public abstract interface IMedicineView {
    
    public abstract void showMedicationData(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.databinding.FragmentDashboardBinding binding);
}
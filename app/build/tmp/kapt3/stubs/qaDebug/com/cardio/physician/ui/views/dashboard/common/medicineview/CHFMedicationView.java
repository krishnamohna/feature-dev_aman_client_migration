package com.cardio.physician.ui.views.dashboard.common.medicineview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.res.ResourcesCompat;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentDashboardBinding;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.google.android.flexbox.FlexboxLayout;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J$\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0002J\u001a\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000bH\u0002\u00a8\u0006\u001d"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/common/medicineview/CHFMedicationView;", "Lcom/cardio/physician/ui/views/dashboard/common/medicineview/IMedicineView;", "()V", "addMedicineView", "", "flLayout", "Lcom/google/android/flexbox/FlexboxLayout;", "medicineView", "Landroid/view/View;", "addNoneMed", "binding", "Lcom/cardio/physician/databinding/FragmentDashboardBinding;", "getContext", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "getMedicineView", "context", "drugName", "", "style", "", "getNoneMedicineView", "removeAllMedicines", "setChefMedicatingData", "diagnosisModel", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "showMedicationData", "visibleOtherContainer", "visibile", "app_qaDebug"})
public final class CHFMedicationView implements com.cardio.physician.ui.views.dashboard.common.medicineview.IMedicineView {
    
    public CHFMedicationView() {
        super();
    }
    
    private final android.content.Context getContext(com.cardio.physician.databinding.FragmentDashboardBinding binding) {
        return null;
    }
    
    @java.lang.Override()
    public void showMedicationData(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.databinding.FragmentDashboardBinding binding) {
    }
    
    private final void setChefMedicatingData(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel, com.cardio.physician.databinding.FragmentDashboardBinding binding) {
    }
    
    private final void visibleOtherContainer(int visibile, com.cardio.physician.databinding.FragmentDashboardBinding binding) {
    }
    
    private final void removeAllMedicines(com.cardio.physician.databinding.FragmentDashboardBinding binding) {
    }
    
    private final void addNoneMed(com.cardio.physician.databinding.FragmentDashboardBinding binding) {
    }
    
    private final android.view.View getMedicineView(android.content.Context context, java.lang.String drugName, int style) {
        return null;
    }
    
    private final android.view.View getNoneMedicineView(android.content.Context context, java.lang.String drugName) {
        return null;
    }
    
    private final void addMedicineView(com.google.android.flexbox.FlexboxLayout flLayout, android.view.View medicineView) {
    }
}
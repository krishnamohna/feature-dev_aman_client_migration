package com.cardio.physician.ui.views.diagnosis.step2;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.connection.ConnectionRepo;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.diagnosis.MedicineModel;
import com.cardio.physician.domain.notifications.NotificationRepo;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.network.Resource;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eJ\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&J\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00100(2\u0006\u0010\u001f\u001a\u00020 R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R&\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006)"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step2/DiagnosisStep2ViewModel;", "Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4ViewModel;", "diagnosisRepo", "Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "connectionRepo", "Lcom/cardio/physician/domain/connection/ConnectionRepo;", "notificationRepo", "Lcom/cardio/physician/domain/notifications/NotificationRepo;", "(Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;Lcom/cardio/physician/domain/connection/ConnectionRepo;Lcom/cardio/physician/domain/notifications/NotificationRepo;)V", "getConnectionRepo", "()Lcom/cardio/physician/domain/connection/ConnectionRepo;", "getDiagnosisRepo", "()Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "liveMedicineData", "Landroidx/lifecycle/LiveData;", "Lcom/cardio/physician/network/Resource;", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "getLiveMedicineData", "()Landroidx/lifecycle/LiveData;", "setLiveMedicineData", "(Landroidx/lifecycle/LiveData;)V", "mutableMedicineData", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "getMutableMedicineData", "()Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "setMutableMedicineData", "(Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;)V", "getNotificationRepo", "()Lcom/cardio/physician/domain/notifications/NotificationRepo;", "fetchMed", "", "query", "", "isPreExistedMed", "", "getMedicineLiveData", "saveMediciensToFirestore", "context", "Landroid/content/Context;", "searchMedicine", "", "app_qaDebug"})
public final class DiagnosisStep2ViewModel extends com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.connection.ConnectionRepo connectionRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.notifications.NotificationRepo notificationRepo = null;
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.cardio.physician.domain.diagnosis.MedicineModel>> mutableMedicineData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.diagnosis.MedicineModel>> liveMedicineData;
    
    @javax.inject.Inject()
    public DiagnosisStep2ViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.connection.ConnectionRepo connectionRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationRepo notificationRepo) {
        super(null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.domain.diagnosis.DiagnosisRepo getDiagnosisRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.domain.connection.ConnectionRepo getConnectionRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.domain.notifications.NotificationRepo getNotificationRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.cardio.physician.domain.diagnosis.MedicineModel>> getMutableMedicineData() {
        return null;
    }
    
    public final void setMutableMedicineData(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.cardio.physician.domain.diagnosis.MedicineModel>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.diagnosis.MedicineModel>> getLiveMedicineData() {
        return null;
    }
    
    public final void setLiveMedicineData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.diagnosis.MedicineModel>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.diagnosis.MedicineModel>> getMedicineLiveData() {
        return null;
    }
    
    public final void fetchMed(@org.jetbrains.annotations.NotNull()
    java.lang.String query, boolean isPreExistedMed) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> searchMedicine(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    public final void saveMediciensToFirestore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}
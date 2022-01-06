package com.cardio.physician.ui.views.diagnosis.step4;

import androidx.lifecycle.LiveData;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.connection.ConnectionRepo;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.notifications.NotificationRepo;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.network.Resource;
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.GlobalScope;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001a\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0002J \u0010(\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\b\u0010&\u001a\u0004\u0018\u00010\'2\u0006\u0010$\u001a\u00020%R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R&\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006)"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4ViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseViewModel;", "diagnosisRepo", "Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "connectionRepo", "Lcom/cardio/physician/domain/connection/ConnectionRepo;", "notificationRepo", "Lcom/cardio/physician/domain/notifications/NotificationRepo;", "(Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;Lcom/cardio/physician/domain/connection/ConnectionRepo;Lcom/cardio/physician/domain/notifications/NotificationRepo;)V", "getConnectionRepo", "()Lcom/cardio/physician/domain/connection/ConnectionRepo;", "getDiagnosisRepo", "()Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "liveSubmitDiagnosis", "Landroidx/lifecycle/LiveData;", "Lcom/cardio/physician/network/Resource;", "", "getLiveSubmitDiagnosis", "()Landroidx/lifecycle/LiveData;", "setLiveSubmitDiagnosis", "(Landroidx/lifecycle/LiveData;)V", "mutableSubmitDiagnosis", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "getMutableSubmitDiagnosis", "()Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "setMutableSubmitDiagnosis", "(Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;)V", "getNotificationRepo", "()Lcom/cardio/physician/domain/notifications/NotificationRepo;", "getHealthModel", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "diagnosisModel", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "getUserModel", "Lcom/cardio/physician/domain/common/model/UserModel;", "notifyConnectionOnDiagnosisAddedOrEdited", "isEdit", "", "userId", "", "submitDiagnosisReport", "app_qaDebug"})
public class DiagnosisFragmentStep4ViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.connection.ConnectionRepo connectionRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.notifications.NotificationRepo notificationRepo = null;
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<kotlin.Unit>> mutableSubmitDiagnosis;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<kotlin.Unit>> liveSubmitDiagnosis;
    
    @javax.inject.Inject()
    public DiagnosisFragmentStep4ViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.connection.ConnectionRepo connectionRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationRepo notificationRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.cardio.physician.domain.diagnosis.DiagnosisRepo getDiagnosisRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.cardio.physician.domain.connection.ConnectionRepo getConnectionRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.cardio.physician.domain.notifications.NotificationRepo getNotificationRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<kotlin.Unit>> getMutableSubmitDiagnosis() {
        return null;
    }
    
    public final void setMutableSubmitDiagnosis(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<kotlin.Unit>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<kotlin.Unit>> getLiveSubmitDiagnosis() {
        return null;
    }
    
    public final void setLiveSubmitDiagnosis(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<kotlin.Unit>> p0) {
    }
    
    public final void submitDiagnosisReport(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, boolean isEdit) {
    }
    
    private final void notifyConnectionOnDiagnosisAddedOrEdited(boolean isEdit, java.lang.String userId) {
    }
    
    private final com.cardio.physician.domain.common.model.UserModel getUserModel(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
    
    private final com.cardio.physician.domain.fitness.model.FitnessModel getHealthModel(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
}
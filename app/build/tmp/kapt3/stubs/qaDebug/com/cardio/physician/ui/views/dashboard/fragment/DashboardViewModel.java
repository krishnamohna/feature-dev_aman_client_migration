package com.cardio.physician.ui.views.dashboard.fragment;

import androidx.lifecycle.LiveData;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.connection.ConnectionModel;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.network.Resource;
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ \u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010$J \u0010\'\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\b\u0010&\u001a\u0004\u0018\u00010$J\u0018\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020*2\b\u0010&\u001a\u0004\u0018\u00010$J\u000e\u0010+\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u0010\u0010,\u001a\u00020\"2\b\u0010&\u001a\u0004\u0018\u00010$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR#\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR#\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR#\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR \u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\u000b0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\u000b0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\f0\u000b0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000b0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/cardio/physician/ui/views/dashboard/fragment/DashboardViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseViewModel;", "diagnosisRepo", "Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "userProfileRepository", "Lcom/cardio/physician/data/remote/profile/UserProfileRepository;", "syncRepositary", "Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;", "(Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;Lcom/cardio/physician/data/remote/profile/UserProfileRepository;Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;)V", "liveDataConnections", "Landroidx/lifecycle/LiveData;", "Lcom/cardio/physician/network/Resource;", "", "Lcom/cardio/physician/domain/connection/ConnectionModel;", "getLiveDataConnections", "()Landroidx/lifecycle/LiveData;", "liveDataDiagnosisAfib", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "getLiveDataDiagnosisAfib", "liveDataDiagnosisChib", "getLiveDataDiagnosisChib", "liveDataHealthLogs", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "getLiveDataHealthLogs", "liveUserData", "Lcom/cardio/physician/domain/common/model/UserModel;", "getLiveUserData", "singleEventConnections", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "singleEventDiagnosisAfib", "singleEventDiagnosisChib", "singleEventHealthLogs", "userSingleLiveData", "getDiagnosisAfib", "", "currentDate", "", "ailment", "userId", "getDiagnosisChib", "getHealthLogs", "days", "", "getPatientList", "getUserDetail", "app_qaDebug"})
public final class DashboardViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseViewModel {
    private final com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo = null;
    private final com.cardio.physician.data.remote.profile.UserProfileRepository userProfileRepository = null;
    private final com.cardio.physician.domain.synchealth.SyncHealthRepositary syncRepositary = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.cardio.physician.domain.common.model.UserModel>> userSingleLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.common.model.UserModel>> liveUserData = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel>>> singleEventDiagnosisChib = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel>>> liveDataDiagnosisChib = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel>>> singleEventDiagnosisAfib = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel>>> liveDataDiagnosisAfib = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.fitness.model.FitnessModel>>> singleEventHealthLogs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.fitness.model.FitnessModel>>> liveDataHealthLogs = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.connection.ConnectionModel>>> singleEventConnections = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.connection.ConnectionModel>>> liveDataConnections = null;
    
    @javax.inject.Inject()
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.profile.UserProfileRepository userProfileRepository, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.synchealth.SyncHealthRepositary syncRepositary) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.common.model.UserModel>> getLiveUserData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel>>> getLiveDataDiagnosisChib() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel>>> getLiveDataDiagnosisAfib() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.fitness.model.FitnessModel>>> getLiveDataHealthLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.connection.ConnectionModel>>> getLiveDataConnections() {
        return null;
    }
    
    public final void getPatientList(@org.jetbrains.annotations.NotNull()
    java.lang.String currentDate) {
    }
    
    public final void getDiagnosisChib(@org.jetbrains.annotations.NotNull()
    java.lang.String currentDate, @org.jetbrains.annotations.NotNull()
    java.lang.String ailment, @org.jetbrains.annotations.Nullable()
    java.lang.String userId) {
    }
    
    public final void getDiagnosisAfib(@org.jetbrains.annotations.NotNull()
    java.lang.String currentDate, @org.jetbrains.annotations.NotNull()
    java.lang.String ailment, @org.jetbrains.annotations.Nullable()
    java.lang.String userId) {
    }
    
    public final void getUserDetail(@org.jetbrains.annotations.Nullable()
    java.lang.String userId) {
    }
    
    public final void getHealthLogs(long days, @org.jetbrains.annotations.Nullable()
    java.lang.String userId) {
    }
}
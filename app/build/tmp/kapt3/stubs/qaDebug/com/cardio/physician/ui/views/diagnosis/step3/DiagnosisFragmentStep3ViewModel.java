package com.cardio.physician.ui.views.diagnosis.step3;

import androidx.lifecycle.LiveData;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.connection.ConnectionRepo;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.notifications.NotificationRepo;
import com.cardio.physician.domain.questionare.QuestionnaireRepo;
import com.cardio.physician.domain.questionare.model.QuestionModel;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.network.Resource;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00110\u0010J\u0006\u0010\u001b\u001a\u00020\u001cR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00110\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step3/DiagnosisFragmentStep3ViewModel;", "Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4ViewModel;", "questionnaireRepo", "Lcom/cardio/physician/domain/questionare/QuestionnaireRepo;", "diagnosisRepo", "Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "connectionRepo", "Lcom/cardio/physician/domain/connection/ConnectionRepo;", "notificationRepo", "Lcom/cardio/physician/domain/notifications/NotificationRepo;", "(Lcom/cardio/physician/domain/questionare/QuestionnaireRepo;Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;Lcom/cardio/physician/domain/connection/ConnectionRepo;Lcom/cardio/physician/domain/notifications/NotificationRepo;)V", "getConnectionRepo", "()Lcom/cardio/physician/domain/connection/ConnectionRepo;", "getDiagnosisRepo", "()Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "liveDataQuestions", "Landroidx/lifecycle/LiveData;", "Lcom/cardio/physician/network/Resource;", "", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "getLiveDataQuestions", "()Landroidx/lifecycle/LiveData;", "getNotificationRepo", "()Lcom/cardio/physician/domain/notifications/NotificationRepo;", "singleLiveDataQuestions", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "getQuestionLiveData", "getQuestions", "", "app_qaDebug"})
public final class DiagnosisFragmentStep3ViewModel extends com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel {
    private final com.cardio.physician.domain.questionare.QuestionnaireRepo questionnaireRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.connection.ConnectionRepo connectionRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.notifications.NotificationRepo notificationRepo = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel>>> singleLiveDataQuestions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel>>> liveDataQuestions = null;
    
    @javax.inject.Inject()
    public DiagnosisFragmentStep3ViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.questionare.QuestionnaireRepo questionnaireRepo, @org.jetbrains.annotations.NotNull()
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
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel>>> getLiveDataQuestions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel>>> getQuestionLiveData() {
        return null;
    }
    
    public final void getQuestions() {
    }
}
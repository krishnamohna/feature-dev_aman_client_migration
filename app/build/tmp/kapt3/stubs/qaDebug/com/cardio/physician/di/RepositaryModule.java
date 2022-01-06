package com.cardio.physician.di;

import android.content.Context;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.questionnaire.QuestionnaireRepoImp;
import com.cardio.physician.data.remote.common.repositary.UserAuthRepositaryImp;
import com.cardio.physician.data.remote.connections.ConnectionRepoImp;
import com.cardio.physician.data.remote.diagnosis.repositary.DiagnosisRepoImp;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.FitbitRepositaryImp;
import com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitBitRepositaryImp;
import com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitManager;
import com.cardio.physician.data.remote.login.LoginRepositoryImp;
import com.cardio.physician.data.remote.notifications.NotificationsRepoImp;
import com.cardio.physician.data.remote.synchealth.SyncHealthRepositoryImp;
import com.cardio.physician.domain.common.repository.UserAuthRepositary;
import com.cardio.physician.domain.connection.ConnectionRepo;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.fitness.FitnessRepositary;
import com.cardio.physician.domain.login.LoginRepositary;
import com.cardio.physician.domain.notifications.NotificationRepo;
import com.cardio.physician.domain.questionare.QuestionnaireRepo;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.ui.common.utils.Preference;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Named;
import javax.inject.Singleton;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J.\u0010\u000f\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\f2\b\b\u0001\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u0007J\u0012\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u0014H\u0007J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0019H\u0007J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0007J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0007J\u0010\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)H\u0007J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0007\u00a8\u0006."}, d2 = {"Lcom/cardio/physician/di/RepositaryModule;", "", "()V", "provideConnectionsRepo", "Lcom/cardio/physician/domain/connection/ConnectionRepo;", "connectionRepoImp", "Lcom/cardio/physician/data/remote/connections/ConnectionRepoImp;", "provideDiagnosisRepo", "Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "diagnosisRepo", "Lcom/cardio/physician/data/remote/diagnosis/repositary/DiagnosisRepoImp;", "provideFitbitRepositary", "Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "fitBitRepo", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/FitbitRepositaryImp;", "provideFitnessRepositary", "googlefitBitRepo", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "context", "Landroid/content/Context;", "provideGoogleFitManager", "Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/GoogleFitManager;", "activity", "provideGoogleRepositary", "Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/GoogleFitBitRepositaryImp;", "provideLoginRepositary", "Lcom/cardio/physician/domain/login/LoginRepositary;", "loginRepositoryImp", "Lcom/cardio/physician/data/remote/login/LoginRepositoryImp;", "provideNotificationsRepo", "Lcom/cardio/physician/domain/notifications/NotificationRepo;", "notificationsRepoImp", "Lcom/cardio/physician/data/remote/notifications/NotificationsRepoImp;", "provideQuestionareRepo", "Lcom/cardio/physician/domain/questionare/QuestionnaireRepo;", "questionnaireRepoImp", "Lcom/cardio/physician/data/remote/questionnaire/QuestionnaireRepoImp;", "provideSyncHealthRepo", "Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;", "respositaryImp", "Lcom/cardio/physician/data/remote/synchealth/SyncHealthRepositoryImp;", "provideUserAuthRepo", "Lcom/cardio/physician/domain/common/repository/UserAuthRepositary;", "userAuthRepositaryImp", "Lcom/cardio/physician/data/remote/common/repositary/UserAuthRepositaryImp;", "app_qaDebug"})
@dagger.Module()
public final class RepositaryModule {
    
    public RepositaryModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.domain.login.LoginRepositary provideLoginRepositary(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.login.LoginRepositoryImp loginRepositoryImp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.domain.common.repository.UserAuthRepositary provideUserAuthRepo(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.common.repositary.UserAuthRepositaryImp userAuthRepositaryImp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.domain.diagnosis.DiagnosisRepo provideDiagnosisRepo(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.diagnosis.repositary.DiagnosisRepoImp diagnosisRepo) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "selectedFinessRepo")
    @dagger.Provides()
    public final com.cardio.physician.domain.fitness.FitnessRepositary provideFitnessRepositary(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "FITBIT")
    com.cardio.physician.domain.fitness.FitnessRepositary fitBitRepo, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GOOGLE")
    com.cardio.physician.domain.fitness.FitnessRepositary googlefitBitRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "FITBIT")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.domain.fitness.FitnessRepositary provideFitbitRepositary(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.fitnesstracker.fitbit.FitbitRepositaryImp fitBitRepo) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GOOGLE")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.domain.fitness.FitnessRepositary provideGoogleRepositary(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitBitRepositaryImp fitBitRepo) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitManager provideGoogleFitManager(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context activity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.cardio.physician.domain.synchealth.SyncHealthRepositary provideSyncHealthRepo(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.synchealth.SyncHealthRepositoryImp respositaryImp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.cardio.physician.domain.questionare.QuestionnaireRepo provideQuestionareRepo(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.questionnaire.QuestionnaireRepoImp questionnaireRepoImp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.cardio.physician.domain.notifications.NotificationRepo provideNotificationsRepo(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.notifications.NotificationsRepoImp notificationsRepoImp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.cardio.physician.domain.connection.ConnectionRepo provideConnectionsRepo(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.connections.ConnectionRepoImp connectionRepoImp) {
        return null;
    }
}
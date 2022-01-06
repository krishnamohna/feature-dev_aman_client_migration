package com.cardio.physician.di

import android.content.Context
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.data.remote.questionnaire.QuestionnaireRepoImp
import com.cardio.physician.data.remote.common.repositary.UserAuthRepositaryImp
import com.cardio.physician.data.remote.connections.ConnectionRepoImp
import com.cardio.physician.data.remote.diagnosis.repositary.DiagnosisRepoImp
import com.cardio.physician.data.remote.fitnesstracker.fitbit.FitbitRepositaryImp
import com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitBitRepositaryImp
import com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitManager
import com.cardio.physician.data.remote.login.LoginRepositoryImp
import com.cardio.physician.data.remote.notifications.NotificationsRepoImp
import com.cardio.physician.data.remote.synchealth.SyncHealthRepositoryImp
import com.cardio.physician.domain.common.repository.UserAuthRepositary
import com.cardio.physician.domain.connection.ConnectionRepo
import com.cardio.physician.domain.diagnosis.DiagnosisRepo
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.domain.login.LoginRepositary
import com.cardio.physician.domain.notifications.NotificationRepo
import com.cardio.physician.domain.questionare.QuestionnaireRepo
import com.cardio.physician.domain.synchealth.SyncHealthRepositary
import com.cardio.physician.ui.common.utils.Preference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositaryModule {

    @Provides
    @Singleton
    fun provideLoginRepositary(loginRepositoryImp: LoginRepositoryImp): LoginRepositary {
        return loginRepositoryImp
    }

    @Provides
    @Singleton
    fun provideUserAuthRepo(userAuthRepositaryImp: UserAuthRepositaryImp): UserAuthRepositary {
        return userAuthRepositaryImp
    }

    @Provides
    @Singleton
    fun provideDiagnosisRepo(diagnosisRepo: DiagnosisRepoImp) = diagnosisRepo as DiagnosisRepo

    @Provides
    @Named(REPO_FITNESS_SELECTED)
    fun provideFitnessRepositary(
        @Named(REPO_FITBIT)
        fitBitRepo: FitnessRepositary,
        @Named(REPO_GOOGLE)
        googlefitBitRepo: FitnessRepositary,
        userManager: UserManager,
        @ApplicationContext context: Context
    ): FitnessRepositary {
        return when (userManager.getString(Preference.SYNC_HEALTH,context.getString(R.string.fitbit))) {
            context.getString(R.string.fitbit) -> {
                fitBitRepo
            }
            context.getString(R.string.google_fit) -> {
                googlefitBitRepo
            }
            else -> {
                throw Exception("No other implementation !!")
            }
        }
    }

    @Provides
    @Singleton
    @Named(REPO_FITBIT)
    fun provideFitbitRepositary(fitBitRepo: FitbitRepositaryImp) = fitBitRepo as FitnessRepositary

    @Provides
    @Singleton
    @Named(REPO_GOOGLE)
    fun provideGoogleRepositary(fitBitRepo: GoogleFitBitRepositaryImp) = fitBitRepo as FitnessRepositary

    @Singleton
    @Provides
    fun provideGoogleFitManager(@ApplicationContext activity: Context)= GoogleFitManager(activity)

    @Singleton
    @Provides
    fun provideSyncHealthRepo(respositaryImp:SyncHealthRepositoryImp)=respositaryImp as SyncHealthRepositary

    @Singleton
    @Provides
    fun provideQuestionareRepo(questionnaireRepoImp: QuestionnaireRepoImp)=questionnaireRepoImp as QuestionnaireRepo

    @Singleton
    @Provides
    fun provideNotificationsRepo(notificationsRepoImp: NotificationsRepoImp)=notificationsRepoImp as NotificationRepo

    @Singleton
    @Provides
    fun provideConnectionsRepo(connectionRepoImp: ConnectionRepoImp)=connectionRepoImp as ConnectionRepo


}
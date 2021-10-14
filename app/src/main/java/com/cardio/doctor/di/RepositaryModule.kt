package com.cardio.doctor.di

import android.content.Context
import com.cardio.doctor.R
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.data.remote.common.repositary.UserAuthRepositaryImp
import com.cardio.doctor.data.remote.diagnosis.repositary.DiagnosisRepoImp
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.FitbitRepositaryImp
import com.cardio.doctor.data.remote.fitnesstracker.googlefit.GoogleFitBitRepositaryImp
import com.cardio.doctor.data.remote.login.LoginRepositoryImp
import com.cardio.doctor.domain.common.repository.UserAuthRepositary
import com.cardio.doctor.domain.diagnosis.DiagnosisRepo
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.login.LoginRepositary
import com.cardio.doctor.ui.common.utils.Preference
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
    @Singleton
    @Named("selectedFinessRepo")
    fun provideFitnessRepositary(
        @Named(REPO_FITBIT)
        fitBitRepo: FitnessRepositary,
        @Named(REPO_FITBIT)
        googlefitBitRepo: FitnessRepositary,
        userManager: UserManager,
        @ApplicationContext context: Context
    ): FitnessRepositary {
        return when (userManager.getString(Preference.SYNC_HEALTH,context.getString(R.string.fitbit))) {
            context.getString(R.string.fitbit) -> {
                fitBitRepo
            }
            context.getString(R.string.google_fit) -> {
                //returning fitbit for now
                fitBitRepo
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
    fun provideGoogleRepositary(fitBitRepo: GoogleFitBitRepositaryImp) =
        fitBitRepo as FitnessRepositary


}
package com.cardio.doctor.di

import com.cardio.doctor.data.remote.common.repositary.UserAuthRepositaryImp
import com.cardio.doctor.data.remote.diagnosis.repositary.DiagnosisRepoImp
import com.cardio.doctor.data.remote.login.LoginRepositoryImp
import com.cardio.doctor.domain.common.repository.UserAuthRepositary
import com.cardio.doctor.domain.diagnosis.DiagnosisRepo
import com.cardio.doctor.domain.login.LoginRepositary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
class RepositaryModule {

    @Provides
    @Singleton
    fun provideLoginRepositary(loginRepositoryImp: LoginRepositoryImp): LoginRepositary{
        return loginRepositoryImp
    }

    @Provides
    @Singleton
    fun provideUserAuthRepo(userAuthRepositaryImp: UserAuthRepositaryImp): UserAuthRepositary{
        return userAuthRepositaryImp
    }

    @Provides
    @Singleton
    fun provideDiagnosisRepo(diagnosisRepo: DiagnosisRepoImp)=diagnosisRepo as DiagnosisRepo

}
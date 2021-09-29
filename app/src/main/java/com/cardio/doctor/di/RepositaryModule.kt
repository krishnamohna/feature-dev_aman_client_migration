package com.cardio.doctor.di

import com.cardio.doctor.domain.login.LoginRepositary
import com.cardio.doctor.ui.views.fragment.login.LoginRepositoryImp
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

}
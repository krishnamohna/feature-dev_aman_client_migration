package com.cardio.doctor.di

import android.app.Service
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.synchealth.SyncHealthRepositary
import com.cardio.doctor.ui.service.SyncHealthServiceFacade
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Named


@Module
@InstallIn(ServiceComponent::class)
class ServiceModule {

    @ServiceScoped
    @Provides
    fun provideServiceFacade(
        service: Service,
        @Named(REPO_FITNESS_SELECTED) fitnessRepositary: FitnessRepositary,
        syncHealthRepositary: SyncHealthRepositary
    )=SyncHealthServiceFacade(service, fitnessRepositary, syncHealthRepositary)

}
package com.cardio.physician.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

/*
    @ActivityScoped
    @Provides
    fun provideGoogleFitManager(@ApplicationContext activity: Context)= GoogleFitManager(activity)
*/

}
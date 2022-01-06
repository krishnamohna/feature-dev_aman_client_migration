package com.cardio.physician.di;

import android.app.Service;
import com.cardio.physician.domain.fitness.FitnessRepositary;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.ui.service.SyncHealthServiceFacade;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.scopes.ServiceScoped;
import javax.inject.Named;

@dagger.hilt.InstallIn(value = {dagger.hilt.android.components.ServiceComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/cardio/physician/di/ServiceModule;", "", "()V", "provideServiceFacade", "Lcom/cardio/physician/ui/service/SyncHealthServiceFacade;", "service", "Landroid/app/Service;", "fitnessRepositary", "Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "syncHealthRepositary", "Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;", "app_qaDebug"})
@dagger.Module()
public final class ServiceModule {
    
    public ServiceModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.scopes.ServiceScoped()
    @dagger.Provides()
    public final com.cardio.physician.ui.service.SyncHealthServiceFacade provideServiceFacade(@org.jetbrains.annotations.NotNull()
    android.app.Service service, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "selectedFinessRepo")
    com.cardio.physician.domain.fitness.FitnessRepositary fitnessRepositary, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.synchealth.SyncHealthRepositary syncHealthRepositary) {
        return null;
    }
}
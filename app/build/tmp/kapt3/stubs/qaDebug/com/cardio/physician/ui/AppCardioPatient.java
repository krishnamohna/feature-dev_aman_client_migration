package com.cardio.physician.ui;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/cardio/physician/ui/AppCardioPatient;", "Landroid/app/Application;", "()V", "appCardioPatientFacade", "Lcom/cardio/physician/ui/AppCardioPatientFacade;", "getAppCardioPatientFacade", "()Lcom/cardio/physician/ui/AppCardioPatientFacade;", "setAppCardioPatientFacade", "(Lcom/cardio/physician/ui/AppCardioPatientFacade;)V", "onCreate", "", "app_qaDebug"})
@dagger.hilt.android.HiltAndroidApp()
public class AppCardioPatient extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.ui.AppCardioPatientFacade appCardioPatientFacade;
    
    public AppCardioPatient() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.AppCardioPatientFacade getAppCardioPatientFacade() {
        return null;
    }
    
    public final void setAppCardioPatientFacade(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.AppCardioPatientFacade p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
}
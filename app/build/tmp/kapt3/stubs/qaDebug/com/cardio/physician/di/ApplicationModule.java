package com.cardio.physician.di;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.cardio.physician.data.local.SharedPreferences;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.ui.AppCardioPatient;
import com.cardio.physician.ui.common.utils.validation.DefaultAlphaFieldValidation;
import com.cardio.physician.ui.common.utils.validation.DefaultFieldValidation;
import com.cardio.physician.ui.common.utils.validation.Validater;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import java.util.concurrent.Executors;
import javax.inject.Named;
import javax.inject.Singleton;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0007J\u0012\u0010\u0010\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0007J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016H\u0007J\u0012\u0010\u001a\u001a\u00020\b2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\u001b\u001a\u00020\u001cH\u0007\u00a8\u0006\u001d"}, d2 = {"Lcom/cardio/physician/di/ApplicationModule;", "", "()V", "getApplicationContext", "Lcom/cardio/physician/ui/AppCardioPatient;", "getUserManager", "Lcom/cardio/physician/data/local/UserManager;", "sharedPreferences", "Lcom/cardio/physician/data/local/SharedPreferences;", "provideAlphaFieldValidator", "Lcom/cardio/physician/ui/common/utils/validation/Validater;", "context", "Landroid/content/Context;", "provideExecuterService", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "provideFieldValidator", "provideFirebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "provideFirebaseFireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "provideFirebaseStorage", "Lcom/google/firebase/storage/FirebaseStorage;", "provideFirebaseStorageReference", "Lcom/google/firebase/storage/StorageReference;", "firebaseStorage", "providePreference", "provideUiHandler", "Landroid/os/Handler;", "app_qaDebug"})
@dagger.Module()
public final class ApplicationModule {
    
    public ApplicationModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.ui.AppCardioPatient getApplicationContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.data.local.UserManager getUserManager(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.SharedPreferences sharedPreferences) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.data.local.SharedPreferences providePreference(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.google.firebase.auth.FirebaseAuth provideFirebaseAuth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.google.firebase.firestore.FirebaseFirestore provideFirebaseFireStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.google.firebase.storage.FirebaseStorage provideFirebaseStorage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.google.firebase.storage.StorageReference provideFirebaseStorageReference(@org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.FirebaseStorage firebaseStorage) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "default_validator")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.ui.common.utils.validation.Validater provideFieldValidator(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "default_alpha_validator")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final com.cardio.physician.ui.common.utils.validation.Validater provideAlphaFieldValidator(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.concurrent.ExecutorService provideExecuterService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final android.os.Handler provideUiHandler() {
        return null;
    }
}
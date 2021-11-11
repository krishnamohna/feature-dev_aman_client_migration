package com.cardio.physician.di

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.cardio.physician.data.local.SharedPreferences
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.ui.AppCardioPatient
import com.cardio.physician.ui.common.utils.validation.DefaultFieldValidation
import com.cardio.physician.ui.common.utils.validation.Validater
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun getApplicationContext() = AppCardioPatient()

    @Provides
    @Singleton
    fun getUserManager(sharedPreferences: SharedPreferences): UserManager {
        return UserManager(sharedPreferences)
    }

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): SharedPreferences {
        return SharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorageReference(firebaseStorage: FirebaseStorage): StorageReference {
        return firebaseStorage.reference
    }

    @Provides
    @Singleton
    fun provideFieldValidator(@ApplicationContext context: Context): Validater {
        return Validater(DefaultFieldValidation(context))
    }

    @Provides
    @Singleton
    fun provideExecuterService() = Executors.newFixedThreadPool(2)

    @Provides
    @Singleton
    fun provideUiHandler() = Handler(Looper.getMainLooper())

}
package com.cardio.doctor.di

import android.content.Context
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.BuildConfig
import com.cardio.doctor.api.*
import com.cardio.doctor.api.ApiHeader.Companion.ACCEPT
import com.cardio.doctor.api.ApiHeader.Companion.APPLICATION_JSON
import com.cardio.doctor.api.ApiHeader.Companion.AUTHORIZATION
import com.cardio.doctor.api.ApiHeader.Companion.BEARER
import com.cardio.doctor.api.ApiHeader.Companion.CONTENT_TYPE
import com.cardio.doctor.api.ApiHeader.Companion.PLATFORM
import com.cardio.doctor.api.ApiHeader.Companion.PLATFORM_TYPE
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.storage.preference.SharedPreferences
import com.google.gson.GsonBuilder
import com.cardio.doctor.network.InternetInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent ::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun getApplicationContext() = AppCardioPatient()

    @Provides
    @Singleton
    fun getUserManager(sharedPreferences: SharedPreferences) : UserManager {
        return UserManager(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(userManager: UserManager, internetInterceptor: InternetInterceptor)
    : OkHttpClient{
       val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .connectionPool(ConnectionPool(0, 30, TimeUnit.MINUTES))
            .protocols(listOf(Protocol.HTTP_1_1))
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(internetInterceptor)
            .addInterceptor { chain ->
               val newRequest = chain.request().newBuilder()
                        .addHeader(ACCEPT, APPLICATION_JSON)
                        .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .addHeader(PLATFORM, PLATFORM_TYPE)
                        .addHeader(AUTHORIZATION, BEARER.plus(""))
                        .build()
                    chain.proceed(newRequest)
            }
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return builder.addInterceptor(loggingInterceptor)
                .build()
        } else
            return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithModifiers().create()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context : Context) : SharedPreferences {
        return SharedPreferences(context)
    }
}
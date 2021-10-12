package com.cardio.doctor.di

import com.cardio.doctor.BuildConfig
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.network.InternetInterceptor
import com.cardio.doctor.network.api.ApiHeader
import com.cardio.doctor.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(userManager: UserManager, internetInterceptor: InternetInterceptor)
            : OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(internetInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(ApiHeader.ACCEPT, ApiHeader.APPLICATION_JSON)
                    .addHeader(ApiHeader.CONTENT_TYPE, ApiHeader.APPLICATION_JSON)
                    .addHeader(ApiHeader.PLATFORM, ApiHeader.PLATFORM_TYPE)
                    .addHeader(ApiHeader.AUTHORIZATION, ApiHeader.BEARER.plus(""))
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
            .addConverterFactory(
                GsonConverterFactory
                    .create()
            )
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}
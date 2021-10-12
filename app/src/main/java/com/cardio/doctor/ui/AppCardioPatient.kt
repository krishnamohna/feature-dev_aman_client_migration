package com.cardio.doctor.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationConfiguration
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationConfigurationBuilder
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.ClientCredentials
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.Scope
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class AppCardioPatient : Application() {

    private val CLIENT_SECRET = "79a4baa0d161be1c4e286f61c7ab3edc"
    private val SECURE_KEY = "CVPdQNAT6fBI4rrPLEn9x0+UV84DoqLFiNHpKOPLRW0="

    open fun generateAuthenticationConfiguration(
        context: Context,
        mainActivityClass: Class<out Activity?>?
    ): AuthenticationConfiguration? {
        return try {
            val ai = context.packageManager.getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            )
            val bundle = ai.metaData

            // Load clientId and redirectUrl from application manifest
            val clientId = bundle.getString("com.fitbit.sampleandroidoauth2.CLIENT_ID")
            val redirectUrl = bundle.getString("com.fitbit.sampleandroidoauth2.REDIRECT_URL")
            val CLIENT_CREDENTIALS = ClientCredentials(clientId, CLIENT_SECRET, redirectUrl)
            AuthenticationConfigurationBuilder()
                .setClientCredentials(CLIENT_CREDENTIALS)
                .setEncryptionKey(SECURE_KEY)
                .setTokenExpiresIn(2592000L) // 30 days
                .setBeforeLoginActivity(Intent(context, mainActivityClass))
                .addRequiredScopes(Scope.profile, Scope.settings)
                .addOptionalScopes(Scope.activity, Scope.weight)
                .setLogoutOnAuthFailure(true)
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun onCreate() {
        super.onCreate()
       /* AuthenticationManager.configure(
            this,
            generateAuthenticationConfiguration(
                this,
                RootActivity::class.java
            )
        )*/
    }
}







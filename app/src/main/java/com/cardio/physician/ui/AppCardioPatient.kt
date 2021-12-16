package com.cardio.physician.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.*
import com.cardio.physician.ui.views.fitbit.RootActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class AppCardioPatient : Application() {

    private val CLIENT_SECRET = "aa660d599c83661dea59642f63cc56f2"
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
                .addOptionalScopes(Scope.activity, Scope.weight,Scope.heartrate)
                .setLogoutOnAuthFailure(true)
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun onCreate() {
        super.onCreate()
        AuthenticationManager.configure(
            this,
            generateAuthenticationConfiguration(
                this,
                RootActivity::class.java
            )
        )
    }
}







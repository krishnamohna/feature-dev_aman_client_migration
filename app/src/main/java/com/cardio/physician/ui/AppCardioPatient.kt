package com.cardio.physician.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class AppCardioPatient  : Application() {

    var appCardioPatientFacade = AppCardioPatientFacade()

    override fun onCreate() {
        super.onCreate()
        appCardioPatientFacade.onCreate(this)
    }


}







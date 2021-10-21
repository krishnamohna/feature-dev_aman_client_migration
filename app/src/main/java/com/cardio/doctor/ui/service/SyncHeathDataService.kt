package com.cardio.doctor.ui.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SyncHeathDataService : Service() {

    companion object{
        fun start(context: Context){
            Intent(context,SyncHeathDataService::class.java).run {
                context.startService(this)
            }
        }
    }

    @Inject
    lateinit var facade: SyncHealthServiceFacade

    override fun onCreate() {
        super.onCreate()
        facade.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        facade.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        facade.syncData()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
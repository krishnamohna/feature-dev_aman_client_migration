package com.cardio.doctor.ui.service

import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SyncHeathDataService : LifecycleService() {

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): SyncHeathDataService = this@SyncHeathDataService
    }

    companion object{
        fun start(context: Context){
            Intent(context,SyncHeathDataService::class.java).run {
                context.startService(this)
            }
        }
    }

    @Inject
    lateinit var facade: SyncHealthServiceFacade

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        facade.syncData()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }
}
package com.cardio.physician.ui.views.dashboard

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.navigation.fragment.NavHostFragment
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityDashboardBinding
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.service.SyncHeathDataService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    var onSynLoading: ((Boolean) -> Unit?)? =null
    private lateinit var mService: SyncHeathDataService
    private var mBound: Boolean = false
    private val binding by viewBinding(ActivityDashboardBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavigationController()
        startAndBindSyncService()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        mBound = false
    }

    fun startAndBindSyncService() {
        Intent(this, SyncHeathDataService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        SyncHeathDataService.start(this)
    }

    private fun setNavigationController() {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
                .navController
        baseViewModel.navDirectionLiveData.observe(this) {
            navController.navigate(it)
        }
    }

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as SyncHeathDataService.LocalBinder
            mService = binder.getService()
            mBound = true
            registerListeners()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    private fun registerListeners() {
        if (this::mService.isInitialized) {
            mService.facade.getSyncData().customObserver(
                this,
                onLoading = {
                    onSynLoading?.invoke(it)
                },
                {

                },
                {

                }
            )
        }
    }

    fun registerSyncUpdates(onSynLoading: (Boolean) -> Unit){
        this.onSynLoading=onSynLoading
    }

    fun unregisterSyncUpdates() {
        onSynLoading==null
    }
}
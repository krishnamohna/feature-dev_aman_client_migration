package com.cardio.doctor.ui.views.dashboard

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.navigation.fragment.NavHostFragment
import com.cardio.doctor.R
import com.cardio.doctor.databinding.ActivityDashboardBinding
import com.cardio.doctor.ui.common.base.activity.BaseActivity
import com.cardio.doctor.ui.common.utils.extentions.customObserver
import com.cardio.doctor.ui.service.SyncHeathDataService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    private  var onSynLoading: ((Boolean) -> Unit?)? =null
    private lateinit var mService: SyncHeathDataService
    private var mBound: Boolean = false
    private val binding by viewBinding(ActivityDashboardBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavigationController()
    }

    override fun onStart() {
        super.onStart()
        Intent(this, SyncHeathDataService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }


    fun startSynService(onSynLoading: (Boolean) -> Unit) {
        this.onSynLoading=onSynLoading
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
}
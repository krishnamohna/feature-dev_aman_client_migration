package com.cardio.physician.ui.views.dashboard

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityDashboardBinding
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
import com.cardio.physician.ui.common.utils.showPhysicianPickOption
import com.cardio.physician.ui.service.SyncHeathDataService
import com.cardio.physician.ui.views.add_patient.AddPatientActivity
import com.cardio.physician.ui.views.dashboard.fragment.DashboardFragmentDirections
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity(), View.OnClickListener {

    enum class TAB {
        DASHBOARD,
        PROFILE
    }

    private var currentTab = TAB.DASHBOARD
    var onSynLoading: ((Boolean) -> Unit?)? = null
    private lateinit var mService: SyncHeathDataService
    private var mBound: Boolean = false
    private val binding by viewBinding(ActivityDashboardBinding::inflate)
    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavigationController()
        startAndBindSyncService()
        setListener()
    }

    private fun setListener() {
        binding.btnDashboardOne.setOnClickListener(this)
        binding.btnDashboardTwo.setOnClickListener(this)
        binding.btnProfileMenu.setOnClickListener(this)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            destination.label?.let {
                when (it) {
                    getString(R.string.title_dashboard) -> {
                        currentTab = TAB.DASHBOARD
                        bottomBarVisibility(true)
                        setMenuIconState(currentTab)
                    }
                    getString(R.string.title_profile) -> {
                        currentTab = TAB.PROFILE
                        bottomBarVisibility(true)
                        setMenuIconState(currentTab)
                    }
                    else -> {
                        bottomBarVisibility(false)
                    }
                }
            }
        }
    }

    private fun setMenuIconState(currentTab: TAB) {
        when (currentTab) {
            TAB.DASHBOARD -> {
                binding.btnDashboardOne.setImageResource(R.drawable.ic_home_select)
                binding.btnProfileMenu.setImageResource(R.drawable.ic_dashboard_profile_unselect)
            }
            TAB.PROFILE -> {
                binding.btnDashboardOne.setImageResource(R.drawable.ic_home_unselect)
                binding.btnProfileMenu.setImageResource(R.drawable.ic_dashboard_profile_select)
            }
        }
    }

    fun bottomBarVisibility(visible: Boolean) {
        if (visible) {
            binding.cVBottomBar.visibility = View.VISIBLE
            binding.btnDashboardTwo.visibility = View.VISIBLE
        } else {
            binding.cVBottomBar.visibility = View.GONE
            binding.btnDashboardTwo.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        mBound = false
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnDashboardOne -> {
                if (currentTab == TAB.DASHBOARD) return
                onBackPressed()
            }
            binding.btnDashboardTwo -> {
                isConnectedOrThrowMsg {
                    showPhysicianPickOption(this, {
                        AddPatientActivity.start(this)
                    }, {
                        DiagnosisActivity.start(this, null)
                    })

                }
            }
            binding.btnProfileMenu -> {
                if (currentTab == TAB.PROFILE) return
                navController.navigate(DashboardFragmentDirections.dashboardToProfileFragment())
            }
        }
    }


    private fun startAndBindSyncService() {
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

    fun registerSyncUpdates(onSynLoading: (Boolean) -> Unit) {
        this.onSynLoading = onSynLoading
    }

    fun unregisterSyncUpdates() {
        onSynLoading == null
    }
}
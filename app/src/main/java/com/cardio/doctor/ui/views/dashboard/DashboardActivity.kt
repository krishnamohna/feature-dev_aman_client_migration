package com.cardio.doctor.ui.views.dashboard

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.cardio.doctor.R
import com.cardio.doctor.databinding.ActivityDashboardBinding
import com.cardio.doctor.ui.common.base.activity.BaseActivity
import com.cardio.doctor.ui.service.SyncHeathDataService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity(){

    private val binding by viewBinding(ActivityDashboardBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavigationController()
        startSynService()
    }

    private fun startSynService() {
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
}
package com.cardio.doctor.ui.activity.dashboard

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.cardio.doctor.R
import com.cardio.doctor.base.activity.BaseActivity
import com.cardio.doctor.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity(), View.OnClickListener {
    private val binding by viewBinding(ActivityDashboardBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavigationController()
        setListener()
    }

    private fun setNavigationController() {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
                .navController
        baseViewModel.navDirectionLiveData.observe(this) {
            navController.navigate(it)
        }
    }

    private fun setListener() {
        binding.btnDashboardOne.setOnClickListener(this)
        binding.btnDashboardTwo.setOnClickListener(this)
        binding.btnProfileMenu.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {

            binding.btnDashboardOne -> {
                setStartDestinationOfNavGraph(R.id.dashboardFragment)
            }
            binding.btnDashboardTwo -> {
                setStartDestinationOfNavGraph(R.id.dashboardFragment)
            }
            binding.btnProfileMenu -> {
                setStartDestinationOfNavGraph(R.id.graphProfile)
            }
        }
    }

    private fun setStartDestinationOfNavGraph(navId: Int) {
        val navHostFragmentView =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val graphInflater = navHostFragmentView.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.graph_dashboard)
        val navController = navHostFragmentView.navController
        navGraph.startDestination = navId
        navController.graph = navGraph
    }
}
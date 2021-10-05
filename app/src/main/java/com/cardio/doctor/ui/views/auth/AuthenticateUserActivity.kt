package com.cardio.doctor.ui.views.auth

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.cardio.doctor.R
import com.cardio.doctor.databinding.ActivityAuthenticateUserBinding
import com.cardio.doctor.ui.common.base.activity.BaseGoogleAuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticateUserActivity : BaseGoogleAuthActivity() {
    private val binding by viewBinding(ActivityAuthenticateUserBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavigationController()
    }


    private fun setNavigationController() {
        val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
            .navController
        baseViewModel.navDirectionLiveData.observe(this){
            navController.navigate(it)
        }
    }
}
package com.cardio.doctor.ui.views.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.cardio.doctor.ui.common.base.activity.BaseActivity
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.databinding.ActivitySplashBinding
import com.cardio.doctor.ui.views.auth.AuthenticateUserActivity
import com.cardio.doctor.ui.views.dashboard.DashboardActivity
import com.cardio.doctor.ui.views.tutorial.TutorialActivity
import com.cardio.doctor.ui.common.utils.Preference
import com.cardio.doctor.ui.common.utils.Timer.Companion.SPLASH_TIME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private val binding by viewBinding(ActivitySplashBinding::inflate)
    @Inject
    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(SPLASH_TIME)
            when {
                baseViewModel.auth.currentUser != null -> openActivity(DashboardActivity::class.java)
                !userManager.isTutorialRequired -> {
                    openActivity(TutorialActivity::class.java)
                    userManager.setBoolean(Preference.IS_TUTORIAL_SHOWN,true)
                }
                else -> openActivity(AuthenticateUserActivity::class.java)
            }
        }
    }

    private fun openActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
        finish()
    }
}
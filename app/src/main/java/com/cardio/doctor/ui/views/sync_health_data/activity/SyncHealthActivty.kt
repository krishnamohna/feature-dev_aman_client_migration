package com.cardio.doctor.ui.views.sync_health_data.activity

import android.os.Bundle
import com.cardio.doctor.R
import com.cardio.doctor.databinding.ActivitySyncHealthBinding
import com.cardio.doctor.ui.common.base.activity.BaseActivity
import com.cardio.doctor.ui.views.sync_health_data.SyncHealthDataFragmentForResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SyncHealthActivty : BaseActivity(){

    private val binding by viewBinding(ActivitySyncHealthBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, SyncHealthDataFragmentForResult())
            .commit()
    }
}
package com.cardio.physician.ui.views.sync_health_data.activity

import android.os.Bundle
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivitySyncHealthBinding
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.views.sync_health_data.SyncHealthDataFragmentForResult
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
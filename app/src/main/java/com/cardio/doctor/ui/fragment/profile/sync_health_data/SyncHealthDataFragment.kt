package com.cardio.doctor.ui.fragment.profile.sync_health_data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentSettingBinding
import com.cardio.doctor.databinding.FragmentSyncHealthDataBinding
import com.cardio.doctor.ui.fragment.profile.setting.SettingViewModel
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SyncHealthDataFragment : AppBaseFragment(R.layout.fragment_sync_health_data), View.OnClickListener {

    private val binding by viewBinding(FragmentSyncHealthDataBinding::bind)
    private val viewModel: SyncHealthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.sync_health_data), backBtnVisibility = true)
        setListener()
        setObservers()
    }

    private fun setListener() {

    }

    private fun setObservers() {


    }
    override fun onClick(view: View?) {

    }
}
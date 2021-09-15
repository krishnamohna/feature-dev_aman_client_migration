package com.cardio.doctor.ui.fragment.profile.change_password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentChangePasswordBinding
import com.cardio.doctor.databinding.FragmentSyncHealthDataBinding
import com.cardio.doctor.ui.fragment.profile.sync_health_data.SyncHealthViewModel
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : AppBaseFragment(R.layout.fragment_change_password), View.OnClickListener {
    private val binding by viewBinding(FragmentChangePasswordBinding::bind)
    private val viewModel: ChangePasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.change_password), backBtnVisibility = true)
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
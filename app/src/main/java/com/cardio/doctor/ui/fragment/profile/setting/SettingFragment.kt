package com.cardio.doctor.ui.fragment.profile.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentSettingBinding
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment :  AppBaseFragment(R.layout.fragment_setting), View.OnClickListener{
    private val binding by viewBinding(FragmentSettingBinding::bind)
    private val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.setting), backBtnVisibility = true)
        setListener()
        setObservers()
    }

    private fun setListener() {
        binding.changePasswordContainer.setOnClickListener(this)
        binding.aboutUsContainer.setOnClickListener(this)
        binding.faqContainer.setOnClickListener(this)
        binding.termsAndConditionContainer.setOnClickListener(this)
        binding.logoutContainer.setOnClickListener(this)

    }

    private fun setObservers() {


    }
    override fun onClick(view: View?) {
        when(view){
            binding.changePasswordContainer ->{
                baseViewModel.setDirection(SettingFragmentDirections.settingToChangePasswordFragment())
            }
            binding.aboutUsContainer ->{

            }

            binding.faqContainer ->{

            }

            binding.termsAndConditionContainer ->{

            }
            binding.logoutContainer ->{

            }

            binding.switchNotification ->{

            }
        }
    }
}
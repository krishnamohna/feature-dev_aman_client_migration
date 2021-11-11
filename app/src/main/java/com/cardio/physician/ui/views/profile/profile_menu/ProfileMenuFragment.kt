package com.cardio.physician.ui.views.profile.profile_menu

import android.os.Bundle
import android.view.View
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentProfileMenuBinding
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.physician.ui.common.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileMenuFragment :  BaseFragmentAuth(R.layout.fragment_profile_menu), View.OnClickListener{
    private val binding by viewBinding(FragmentProfileMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
    }

    private fun setListener() {

    }

    private fun setObservers() {
        binding.profileInfoContainer.setOnClickListener(this)
        binding.settingContainer.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        when(view){
            binding.profileInfoContainer ->{
                baseViewModel.setDirection(ProfileMenuFragmentDirections.profileMenuToGetProfile())
            }

            binding.settingContainer ->{
                //baseViewModel.setDirection(ProfileMenuFragmentDirections.profileMenuToSettingFragment())
            }
        }
    }

}
package com.cardio.physician.ui.views.profile.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentSettingBinding
import com.cardio.physician.domain.user.SignUpUserType
import com.cardio.physician.network.NetworkHelper
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.physician.ui.common.utils.WEBURL
import com.cardio.physician.ui.common.utils.customSnackBarFail
import com.cardio.physician.ui.common.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragmentAuth(R.layout.fragment_setting), View.OnClickListener {

    private val binding by viewBinding(FragmentSettingBinding::bind)
    private val viewModel: SettingViewModel by viewModels()
    private val navArgs by navArgs<SettingFragmentArgs>()

    @Inject
    lateinit var networkHelper: NetworkHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.setting), backBtnVisibility = true)
        setViews()
        setListener()
        setObservers()
    }

    private fun setViews() {
        navArgs.extrasUserType?.let {
           if(it== SignUpUserType.GOOGLE.name){
               binding.changePasswordContainer.visibility=View.GONE
           }
        }
    }

    private fun setListener() {
        binding.changePasswordContainer.setOnClickListener(this)
        binding.aboutUsContainer.setOnClickListener(this)
        binding.faqContainer.setOnClickListener(this)
        binding.termsAndConditionContainer.setOnClickListener(this)
        binding.logoutContainer.setOnClickListener(this)
        binding.switchNotification.setOnClickListener(this)
    }

    private fun setObservers() {

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.changePasswordContainer -> {
                baseViewModel.setDirection(SettingFragmentDirections.settingToChangePasswordFragment())
            }
            binding.aboutUsContainer -> {
                openWebUrl(getString(R.string.about_us), WEBURL.ABOUT_US)
            }

            binding.faqContainer -> {
                openWebUrl(getString(R.string.faq), WEBURL.FAQ)
            }

            binding.termsAndConditionContainer -> {
                openWebUrl(getString(R.string.terms_and_conditions), WEBURL.TERMS_AND_CONDITION)
            }
            binding.logoutContainer -> {
                showLogout(getString(R.string.logout), getString(R.string.logout_description)) {
                    (requireActivity() as BaseActivity).signOut()
                }
            }

            binding.switchNotification -> {
                binding.switchNotification.isChecked = !binding.switchNotification.isChecked
            }
        }
    }

    private fun openWebUrl(toolbarTitle: String, webUrl: String) {
        if (networkHelper.isNetworkConnected()) {
            baseViewModel.setDirection(SettingFragmentDirections.settingToWebView(
                toolbarTitle, webUrl
            ))
        } else customSnackBarFail(requireContext(),
            binding.root,
            getString(R.string.err_no_network_available))
    }
}
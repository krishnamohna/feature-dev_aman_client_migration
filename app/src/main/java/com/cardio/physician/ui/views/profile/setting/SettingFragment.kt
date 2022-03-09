package com.cardio.physician.ui.views.profile.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.data.remote.fcm.FcmManager
import com.cardio.physician.databinding.FragmentSettingBinding
import com.cardio.physician.domain.user.SignUpUserType
import com.cardio.physician.network.NetworkHelper
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.physician.ui.common.utils.Preference
import com.cardio.physician.ui.common.utils.Preference.Companion.HAS_MANUALLY_CHANGED_SUBSCRIPTION
import com.cardio.physician.ui.common.utils.WEBURL
import com.cardio.physician.ui.common.utils.customSnackBarFail
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.common.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragmentAuth(R.layout.fragment_setting), View.OnClickListener {

    private val binding by viewBinding(FragmentSettingBinding::bind)
    private val viewModel: SettingViewModel by viewModels()
    private val navArgs by navArgs<SettingFragmentArgs>()

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var networkHelper: NetworkHelper

    @Inject
    lateinit var fcmManager: FcmManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.setting), backBtnVisibility = true)
        setViews()
        setListener()
        setObservers()
    }

    private fun setViews() {
        navArgs.extrasUserType?.let {
            if (it == SignUpUserType.GOOGLE.name) {
                binding.changePasswordContainer.visibility = View.GONE
            }
        }
        binding.switchNotification.isChecked =
            userManager.getBoolean(Preference.IS_TOPIC_SUBSCRIBED)
    }



    private fun setListener() {
        binding.changePasswordContainer.setOnClickListener(this)
        binding.aboutUsContainer.setOnClickListener(this)
        binding.faqContainer.setOnClickListener(this)
        binding.termsAndConditionContainer.setOnClickListener(this)
        binding.logoutContainer.setOnClickListener(this)
        binding.switchNotification.setOnCheckedChangeListener { _, isSelected ->
            isConnectedOrThrowMsg {
                if (isSelected) {
                    userManager.setBoolean(HAS_MANUALLY_CHANGED_SUBSCRIPTION,true);
                    // binding.switchNotification.isEnabled = false
                    fcmManager.subscribeFcmTopic {
                        //   binding.switchNotification.isEnabled = true
                        if (!it) {
                            showToast(getString(R.string.error_subscribe_notifications))
                            binding.switchNotification.isChecked = false
                        }
                    }
                } else {
                    //  binding.switchNotification.isEnabled = false
                    fcmManager.unsubscribeFcmTopic {
                        //      binding.switchNotification.isEnabled = true
                    }
                }
            }
        }
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
package com.cardio.doctor.ui.fragment.profile.web_view

import android.os.Bundle
import android.view.View
import com.cardio.doctor.R
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentWebViewBinding
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : AppBaseFragment(R.layout.fragment_web_view), View.OnClickListener {
    private val binding by viewBinding(FragmentWebViewBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.setting), backBtnVisibility = true)
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
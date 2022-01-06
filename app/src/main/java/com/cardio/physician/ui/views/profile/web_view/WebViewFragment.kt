package com.cardio.physician.ui.views.profile.web_view

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentWebViewBinding
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.physician.ui.common.utils.WEBURL
import com.cardio.physician.ui.common.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BaseFragmentAuth(R.layout.fragment_web_view), View.OnClickListener {
    private val binding by viewBinding(FragmentWebViewBinding::bind)
    private val navArgs by navArgs<WebViewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, navArgs.toolBarTitle ?: "", backBtnVisibility = true)
        setListener()
        setObservers()
        binding.webView.loadUrl(navArgs.webUrl ?: WEBURL.DEFAULT_URL)
        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
    }

    private fun setListener() {

    }

    private fun setObservers() {


    }
    override fun onClick(view: View?) {

    }
}
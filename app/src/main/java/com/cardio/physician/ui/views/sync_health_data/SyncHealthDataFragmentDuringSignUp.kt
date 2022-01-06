package com.cardio.physician.ui.views.sync_health_data

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.customviews.toolbar.SyncHealthDuringSignupToolbarImp
import com.cardio.physician.ui.views.dashboard.DashboardActivity

class SyncHealthDataFragmentDuringSignUp : SyncHealthDataFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
    }

    override fun onResume() {
        super.onResume()
        (activity as? BaseActivity?)?.isBackButtonDisabled=true
    }

    override fun onPause() {
        super.onPause()
        (activity as? BaseActivity?)?.isBackButtonDisabled=false
    }

    override fun onFitbitSelection() {
        //do not call supper here because we do not want to do anything on fitbit and googlebit authenticated
    }

    override fun onGoogleSelection() {
        //do not call supper here because we do not want to do anything on fitbit and googlebit authenticated
    }

    private fun setViews() {
        binding.headerView.backBtn.visibility=View.INVISIBLE
        binding.btSkipWearables.visibility = View.VISIBLE
        binding.btNext.visibility = View.VISIBLE
        binding.healthLogsContainer.visibility=View.GONE
    }

    override fun setListener() {
        super.setListener()
        binding.btSkipWearables.setOnClickListener {
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
            requireActivity().finish()
        }
        binding.btNext.setOnClickListener {
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun getToolbarImp(): IToolbar? {
        return SyncHealthDuringSignupToolbarImp(binding.headerView.toolBarContainer)
    }

}
package com.cardio.doctor.ui.views.sync_health_data

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cardio.doctor.ui.views.dashboard.DashboardActivity

class SyncHealthDataFragmentDuringSignUp : SyncHealthDataFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
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

}
package com.cardio.doctor.ui.views.dashboard.fragment

import android.os.Bundle
import android.view.View
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDashboardBinding
import com.cardio.doctor.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.doctor.ui.common.utils.showToast
import com.cardio.doctor.ui.common.utils.viewbinding.viewBinding
import com.cardio.doctor.ui.views.dashboard.DashboardActivity
import com.cardio.doctor.ui.views.diagnosis.DiagnosisActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragmentAuth(R.layout.fragment_dashboard), View.OnClickListener {

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        binding.btnLogOut.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as DashboardActivity).unregisterSyncUpdates()
    }

    private fun setListener() {
        binding.btnDashboardOne.setOnClickListener(this)
        binding.btnDashboardTwo.setOnClickListener(this)
        binding.btnProfileMenu.setOnClickListener(this)
        binding.appButton.setOnClickListener { }
        (requireActivity() as DashboardActivity).registerSyncUpdates {
            binding.progressBarSync.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnDashboardOne -> {
                showToast(requireContext(), getString(R.string.coming_soon))
            }
            binding.btnDashboardTwo -> {
                activity?.let { DiagnosisActivity.start(it) }
            }
            binding.btnProfileMenu -> {
                baseViewModel.setDirection(DashboardFragmentDirections.dashboardToProfileFragment())
            }
        }
    }
}
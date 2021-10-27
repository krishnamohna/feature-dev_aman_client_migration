package com.cardio.doctor.ui.views.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDashboardBinding
import com.cardio.doctor.ui.common.base.fragment.BaseFragment
import com.cardio.doctor.ui.common.utils.showToast
import com.cardio.doctor.ui.views.dashboard.DashboardActivity
import com.cardio.doctor.ui.views.diagnosis.DiagnosisActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        binding.btnLogOut.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.let {
            (requireActivity() as? DashboardActivity)?.unregisterSyncUpdates()
        }
    }

    private fun setListener() {
        binding.btnDashboardOne.setOnClickListener(this)
        binding.btnDashboardTwo.setOnClickListener(this)
        binding.btnProfileMenu.setOnClickListener(this)
        binding.appButton.setOnClickListener { }
        activity?.let {
            (requireActivity() as? DashboardActivity)?.registerSyncUpdates {
                binding.progressBarSync.visibility = if (it) View.VISIBLE else View.GONE
            }
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
                findNavController().navigate(DashboardFragmentDirections.dashboardToProfileFragment())
            }
        }
    }
}
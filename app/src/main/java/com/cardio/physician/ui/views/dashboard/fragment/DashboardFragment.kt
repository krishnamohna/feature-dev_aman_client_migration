package com.cardio.physician.ui.views.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.ui.common.base.fragment.BaseFragment
import com.cardio.physician.ui.common.utils.getCurrentDate
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.dashboard.DashboardActivity
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>(), View.OnClickListener {

    val viewModel:DashboardViewModel by viewModels()

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
        init()
    }

    fun init(){
        viewModel.getDiagnosis(getCurrentDate())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.let {
            (requireActivity() as? DashboardActivity)?.unregisterSyncUpdates()
        }
    }

    private fun setListener() {
        binding.btnLogOut.setOnClickListener(this)
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
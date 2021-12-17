package com.cardio.physician.ui.views.dashboard.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment
import com.cardio.physician.ui.common.base.toolbar.DashBoardToolbarImp
import com.cardio.physician.ui.common.base.toolbar.IToolbar
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.getCurrentDate
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.dashboard.DashboardActivity
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseToolBarFragment<FragmentDashboardBinding>() {

    private var toolbar: DashBoardToolbarImp? = null
    val viewModel: DashboardViewModel by viewModels()
    lateinit var adapter: ConnectionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        setRecyclerView()
        init()
    }

    override fun getToolbarImp(): IToolbar? {
        toolbar = DashBoardToolbarImp(binding.headerView.toolBarContainer)
        return toolbar
    }

    private fun setObservers() {
        viewModel.liveUserData.customObserver(
            this,
            null,
            onSuccess = {
                setUserDataInView(it)
            },
            null
        )
        viewModel.liveDataConnections.customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = { connectionList ->
                connectionList?.let { adapter.updateConnectionList(it) }
            },
            onError = {
            }
        )
    }


    fun init() {
        viewModel.getPatientList(getCurrentDate())
        viewModel.getUserDetail()
        binding.tvDate.text = getCurrentDate()
    }

    private fun setRecyclerView(){
        val layoutManager = LinearLayoutManager(activity)
        adapter = ConnectionsAdapter {
            view, position -> DiagnosisActivity.start(requireActivity(), adapter.connectionsList[position].userId)
        };
        binding.rvConnections.layoutManager = layoutManager
        binding.rvConnections.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.let {
            (requireActivity() as? DashboardActivity)?.unregisterSyncUpdates()
        }
    }

    private fun setListener() {
        activity?.let {
            (requireActivity() as? DashboardActivity)?.registerSyncUpdates {
                binding.progressBarSync.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        binding.etSearch.addTextChangedListener(TextChangeWatcher(binding.etSearch))
        binding.ivFilter.setOnClickListener { showToast(requireContext(), "Under Development") }
    }

    private fun setUserDataInView(userModel: UserModel?) {
        toolbar?.setUserImage(userModel?.imagePath)
    }

    inner class TextChangeWatcher(private var view: View) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            view.setBackgroundResource(R.drawable.edt_rounded_corner)
            when (view) {
                binding.etSearch -> {
                    adapter.filter.filter(s)
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

}
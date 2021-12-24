package com.cardio.physician.ui.views.dashboard.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.views.dashboard.DashboardActivity
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DashboardFragment : BaseToolBarFragment<FragmentDashboardBinding>() {

    private var toolbar: DashBoardToolbarImp? = null
    val viewModel: DashboardViewModel by viewModels()
    lateinit var adapter: ConnectionsAdapter
    private var startDate: Long ?= 0L
    private var endDate: Long ?= 0L

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

    private fun showHidePatientView(showHide: Int){
        binding.tvNoPatientFound.visibility = showHide
        binding.ivNoPatientFound.visibility = showHide
        if(showHide == View.VISIBLE){
            binding.rvConnections.visibility = View.GONE
        }else{
            binding.rvConnections.visibility = View.VISIBLE
        }
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
                if(connectionList.isNullOrEmpty()){
                    showHidePatientView(View.VISIBLE)
                }else{
                    showHidePatientView(View.GONE)
                    connectionList?.let { adapter.updateConnectionList(it) }
                }
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
        adapter = ConnectionsAdapter( {
            view, position -> DiagnosisActivity.start(requireActivity(), adapter.connectionsList[position].userId)
        }, { showEmptyView -> if(showEmptyView) showHidePatientView(View.VISIBLE) else showHidePatientView(View.GONE) })
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
        binding.ivFilter.setOnClickListener { (activity as? DashboardActivity)?. let { it1 ->
            showDashboardFilter(
                it1, { view ->
                    val calendar = if(startDate!! > 0) {
                        val cal = Calendar.getInstance()
                        cal.timeInMillis = startDate!!
                        cal
                    }else Calendar.getInstance()
                    openCalendarDialog(requireContext(), calendar
                    ) { p0, p1, p2, p3 ->
                        val calendar = Calendar.getInstance()
                        calendar.set(p1, p2, p3)
                        startDate = calendar.timeInMillis
                        if(startDate!! > 0) view.text = getDateFromTimeMills(startDate!!)
                        else view.text = "From"
                    }
                }, { view ->
                    val calendar = if(endDate!! > 0) {
                        val cal = Calendar.getInstance()
                        cal.timeInMillis = endDate!!
                        cal
                    }else Calendar.getInstance()
                    openCalendarDialog(requireContext(), calendar
                    ) { p0, p1, p2, p3 ->
                        val calendar = Calendar.getInstance()
                        calendar.set(p1, p2, p3)
                        endDate = calendar.timeInMillis
                        if(endDate!! > 0) view.text = getDateFromTimeMills(endDate!!)
                        else view.text = "To"
                    }
                }, {
                    endDate?.let { it2 -> startDate?.let { it3 -> adapter.updateList(it3, it2) } }
                }, startDate, endDate)
        } }
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
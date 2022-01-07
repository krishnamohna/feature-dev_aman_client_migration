package com.cardio.physician.ui.views.dashboard.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.databinding.FragmentPatientDashboardBinding
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment
import com.cardio.physician.ui.common.customviews.toolbar.DashBoardToolbarImp
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.views.add_patient.AddPatientActivity
import com.cardio.physician.ui.views.dashboard.DashboardActivity
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import com.cardio.physician.ui.views.notifications.NotificationsActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PatientDashboardFragment : BaseToolBarFragment<FragmentPatientDashboardBinding>() {

    private var toolbar: DashBoardToolbarImp? = null
    val viewModel: DashboardViewModel by viewModels()
    lateinit var adapter: ConnectionsAdapter
    private var startDate: Long ?= 0L
    private var endDate: Long ?= 0L

    @Inject
    lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPatientDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        setRecyclerView()
    }

    override fun onResume() {
        super.onResume()
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
        toolbar = DashBoardToolbarImp(binding.headerView.toolBarContainer).registerNotificationClick {
            NotificationsActivity.start(requireActivity())
        }
        return toolbar


    }

    private fun setObservers() {
        viewModel.liveUserData.customObserver(
            this,
            null,
            onSuccess = {
                setUserDataInView(it)
                saveInfoToPrefrence(it)
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
            onError = { msg, exp ->
                showHidePatientView(View.VISIBLE)
            }
        )
    }


    fun init() {
        resetFields()
    }

    private fun saveInfoToPrefrence(userModel: UserModel?) {
        //save user info to preferences
        userManager.setString(Preference.PREF_DISPLAY_NAME, getDisplayName(userModel?.firstName,userModel?.lastName))
        userManager.setString(Preference.PREF_FIRST_NAME, userModel?.firstName)
        userManager.setString(Preference.PREF_LAST_NAME, userModel?.lastName)
        userManager.setString(Preference.PREF_EMAIL, userModel?.email)
        userManager.setString(Preference.PREF_PROFILE_IMAGE, userModel?.imagePath)
    }

    private fun resetFields(){
        viewModel.getPatientList(getCurrentDate())
        viewModel.getUserDetail(null)
        binding.tvDate.text = getCurrentDate()
        binding.etSearch.text?.clear()
        startDate = 0L
        endDate = 0L
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
        binding.tvNoPatientFound.setOnClickListener {
            AddPatientActivity.start(activity as DashboardActivity)
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
                    openCalendarDialog(requireContext(), calendar, startDate, endDate, true) { p0, p1, p2, p3 ->
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
                    openCalendarDialog(requireContext(), calendar, startDate, endDate, false) { p0, p1, p2, p3 ->
                        val calendar = Calendar.getInstance()
                        calendar.set(p1, p2, p3)
                        endDate = calendar.timeInMillis
                        if(endDate!! > 0) view.text = getDateFromTimeMills(endDate!!)
                        else view.text = "To"
                    }
                }, {
                    endDate?.let { it2 -> startDate?.let { it3 -> adapter.updateList(it3, it2) } }
                    binding.etSearch.text?.clear()
                }, {
                    startDate = 0
                    endDate = 0
                    adapter.updateList(0L, 0L)
                }, startDate, endDate)
        } }
    }

    private fun setUserDataInView(userModel: UserModel?) {
        toolbar?.view?.findViewById<ImageView>(R.id.imgProfilePicToolbar)?.visibility=View.VISIBLE
        toolbar?.view?.findViewById<ImageView>(R.id.imgEditProfile)?.visibility=View.VISIBLE
        toolbar?.view?.findViewById<ImageView>(R.id.imgEditProfile)?.setImageResource(R.drawable.ic_notification)

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
                    startDate = 0L
                    endDate = 0L
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

}
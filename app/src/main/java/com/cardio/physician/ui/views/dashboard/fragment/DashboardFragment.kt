package com.cardio.physician.ui.views.dashboard.fragment

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment
import com.cardio.physician.ui.common.customviews.toolbar.DashBoardToolbarImp
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.EXTRAS.EXTRAS_PUSH_NOTIFICATON_TITLE
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.ATRIAL_FABRILLATION
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.CARDIAC_HEART_FAILURE
import com.cardio.physician.ui.common.utils.NotificationTitle.NOTIFICATION_TITLE_DIAGNOSIS
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.extentions.isConnectedOrThrowMsg
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar
import com.cardio.physician.ui.views.dashboard.DashboardActivity
import com.cardio.physician.ui.views.dashboard.common.clinicalview.AFibClinicalView
import com.cardio.physician.ui.views.dashboard.common.clinicalview.CHFClinicalView
import com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph
import com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph
import com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph
import com.cardio.physician.ui.views.dashboard.common.graph.base.WeightGraph
import com.cardio.physician.ui.views.dashboard.common.medicineview.AFibMedicationView
import com.cardio.physician.ui.views.dashboard.common.medicineview.CHFMedicationView
import com.cardio.physician.ui.views.dashboard.common.medicineview.IMedicineView
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import com.cardio.physician.ui.views.diagnosis.EditDiagnosisActivity
import com.cardio.physician.ui.views.healthlogs.HealthLogsActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.cardio.doctor.ui.views.dashboard.common.considiration.Consideration
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.CHFConsideration

@AndroidEntryPoint
class DashboardFragment : BaseToolBarFragment<FragmentDashboardBinding>() {

    enum class Filter(val value: Long) {
        THIRTY(30L),
        SIXTY(60L),
        NINETY(90L)
    }

    val args : DashboardFragmentArgs by navArgs()
    private var diagnosisModelChib: DiagnosisModel? = null
    private var diagnosisModelAfib: DiagnosisModel? = null
    private var toolbar: DashBoardToolbarImp? = null
    val viewModel: DashboardViewModel by viewModels()
    private var isChfDiagnosisLoaded = false
    private var isAfibDiagnosisLoaded = false


    private val chfView: CHFClinicalView by lazy {
        CHFClinicalView()
    }
    private val afibView: AFibClinicalView by lazy {
        AFibClinicalView()
    }
    private val chfMedicationView: IMedicineView by lazy {
        CHFMedicationView()
    }
    private val afibMedicineView: IMedicineView by lazy {
        AFibMedicationView()
    }
    private val chfConsidirationView: Consideration by lazy {
        CHFConsideration()
    }

    @Inject
    lateinit var weightGraph: WeightGraph

    @Inject
    lateinit var stepCountGraph: StepCountGraph

    @Inject
    lateinit var heartRateGraph: HeartRateGraph

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var bpGraph: BpGraph
    private val diagnosisActivity: DiagnosisActivity?
        get() = activity as DiagnosisActivity

    private var resultDiagnosisReportSubmition: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                init()
            }
        }

    var resultHealthLogs: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // only if any diagnosis is filled then refresh scren
            if (diagnosisModelChib != null || diagnosisModelAfib != null)
                init()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.let {
            (requireActivity() as? DashboardActivity)?.unregisterSyncUpdates()
        }
        context?.unregisterReceiver(notificationUpdateReciever)
    }

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
        setViews()
        init()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as? DashboardActivity)?.registerDiagnosisClick {
            resultDiagnosisReportSubmition.launch(DiagnosisActivity.getActivityIntent(
                requireActivity()))
        }
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as? DashboardActivity)?.registerDiagnosisClick(null)
    }

    override fun showProgress(showProgress: Boolean) {
        binding.progressBarSync.visibility = if (showProgress) View.VISIBLE else View.GONE
        if (!showProgress) binding.progressBarForGraphs.visibility = View.GONE
    }

    private fun setViews() {
        binding.tvReportDateDash.text = getCurrentDate()
        heartRateGraph.addGraphToParent(binding.includeHeartVsDate.clHeartRateGraph, binding)
        bpGraph.addGraphToParent(binding.includeBpVsDate.clBpChart, binding)
        weightGraph.addGraphToParent(binding.includeWeightVsDate.clGraphContainer, binding)
        stepCountGraph.addGraphToParent(binding.includeStepstVsDate.clStepCount, binding)
        when (diagnosisActivity?.currentFilter) {
            Filter.THIRTY -> {
                setFilterButtonState(binding.includeFilterDash.btFilter30)
            }
            Filter.SIXTY -> {
                setFilterButtonState(binding.includeFilterDash.btFilter60)
            }
            Filter.NINETY -> {
                setFilterButtonState(binding.includeFilterDash.btFilter90)
            }
        }
    }

    override fun getToolbarImp(): IToolbar? {
//        toolbar = DashBoardToolbarImp(binding.headerView.toolBarContainer)
        return null
    }

    private fun setObservers() {
        //register broad cast reciver for push notification update
        val intentFilter = IntentFilter(BroadCastAction.ACTION_NOTIFICATION_UPDATE)
        context?.registerReceiver(notificationUpdateReciever, intentFilter)

        (requireActivity() as? DashboardActivity)?.registerSyncUpdates {
            /*new health logs are synced to server lets update this screen*/
            /*check if any user has any diagnosis then load health logs too*/
            if (diagnosisModelAfib != null || diagnosisModelChib != null)
                onDiagnosisLoaded()
        }
        viewModel.liveUserData.customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = {
                setBasicInfo(it)
                onUserDetailLoaded()
            },
            {msg,exp->
                onUserDetailLoaded()
            }
        )
        viewModel.liveDataDiagnosisChib.customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = { diagnosis ->
                isChfDiagnosisLoaded = true
                diagnosis?.isNotEmpty()?.let { isNonEmpty ->
                    onDiagnosisLoaded()
                    if (isNonEmpty)
                        setDiagnosisDataInView(diagnosis.get(0))
                    else{
                        isChfDiagnosisLoaded = true
                        onDiagnosisFailed()
                    }

                }
            },
            onError = {msg,exp->
                isChfDiagnosisLoaded = true
                onDiagnosisFailed()
            }
        )
        viewModel.liveDataDiagnosisAfib.customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = { diagnosis ->
                isAfibDiagnosisLoaded = true
                diagnosis?.isNotEmpty()?.let { isNonEmpty ->
                    onDiagnosisLoaded()
                    if (isNonEmpty)
                        setDiagnosisDataInView(diagnosis.get(0))
                    else{
                        isAfibDiagnosisLoaded = true
                        onDiagnosisFailed()
                    }
                }
            },
            onError = {msg,exp->
                isAfibDiagnosisLoaded = true
                onDiagnosisFailed()
            }
        )
        viewModel.liveDataHealthLogs.customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = { listHealthLogs ->
                listHealthLogs?.let {
                    showGraphs(it)
                }
            },
            onError = { _, _ ->
                setVisibilityOfGraphs(View.GONE)
            }
        )
        viewModel.liveDataHealthLogsConsiderations.customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = { listHealthLogs ->
                listHealthLogs?.let {
                    showConsidiration(it)
                }
            },
            onError = {msg,exp->
                setVisibilityOfGraphs(View.GONE)
            }
        )
    }

    private fun onUserDetailLoaded() {
        loadDiagnosis()
    }

    private fun onDiagnosisFailed() {
        if (diagnosisModelChib == null && diagnosisModelAfib == null && isChfDiagnosisLoaded && isAfibDiagnosisLoaded) {
            (activity as DiagnosisActivity).navController.navigate(DashboardFragmentDirections.dashboardToDiagnosisFragment(args.userId))
            /*binding.tvNoDiagnosis.visibility = View.VISIBLE
            binding.tvAddDiagnosis.visibility = View.VISIBLE
            binding.tvAddHealthLog.visibility = View.VISIBLE
            binding.tvMyReportlabel.visibility = View.GONE
            binding.tvReportDateDash.visibility = View.GONE*/
        }
    }


    private fun onDiagnosisLoaded() {
        binding.tvReportDateDash.visibility = View.VISIBLE
        binding.tvMyReportlabel.visibility = View.VISIBLE
        diagnosisActivity?.currentFilter?.value?.let { viewModel.getHealthLogs(it, arguments?.getString(FireStoreDocKey.USER_ID)) }

        viewModel.getHealthLogsForConsiderations(arguments?.getString(FireStoreDocKey.USER_ID))
    }


    fun init() {
        isConnectedOrThrowMsg {
            arguments?.getString(FireStoreDocKey.USER_ID)?.let { viewModel.getUserDetail(it) }
        }
    }

    private fun loadDiagnosis() {
        isChfDiagnosisLoaded = false
        isAfibDiagnosisLoaded = false
        binding.includeBasicInfo.tvDiagnosis.text = ""
        binding.tvReportDateDash.visibility = View.GONE
        binding.tvMyReportlabel.visibility = View.GONE
        binding.tvNoDiagnosis.visibility = View.GONE
        binding.tvAddDiagnosis.visibility = View.GONE
        binding.tvAddHealthLog.visibility = View.GONE
        viewModel.getDiagnosisChib(getCurrentDate(), FireStoreDocKey.CARDIAC_HEART_FAILURE, arguments?.getString(FireStoreDocKey.USER_ID))
        viewModel.getDiagnosisAfib(getCurrentDate(), FireStoreDocKey.ATRIAL_FABRILLATION, arguments?.getString(FireStoreDocKey.USER_ID))
    }


    private fun setListener() {
        binding.includeFilterDash.btFilter30.setOnClickListener {
            isConnectedOrThrowMsg {
                setFilterButtonState(it)
                loadGraphData()
            }
        }
        binding.includeFilterDash.btFilter60.setOnClickListener {
            isConnectedOrThrowMsg {
                setFilterButtonState(it)
                loadGraphData()
            }
        }
        binding.includeFilterDash.btFilter90.setOnClickListener {
            isConnectedOrThrowMsg {
                setFilterButtonState(it)
                loadGraphData()
            }
        }
        binding.includeChf.ivEditChbClinical.setOnClickListener {
            if (diagnosisModelChib != null) {
                resultDiagnosisReportSubmition.launch(EditDiagnosisActivity.getIntent(
                    requireActivity(),
                    diagnosisModelChib!!,
                    EditDiagnosisActivity.EDIT_DIAGNOSIS_INFO, args.userId))
            } else {
                resultDiagnosisReportSubmition.launch(DiagnosisActivity.getActivityIntent(
                    requireActivity()))
            }
        }
        binding.includeAfib.ivEditAfibClinical.setOnClickListener {
            showToast(requireActivity(), R.string.coming_soon)
        }
        binding.includeBasicInfo.ivEditBasicInfo.setOnClickListener {
            if (diagnosisModelChib != null) {
                resultDiagnosisReportSubmition.launch(EditDiagnosisActivity.getIntent(
                    requireActivity(),
                    diagnosisModelChib!!,
                    EditDiagnosisActivity.EDIT_BASIC_INFO, args.userId))
            } else {
                resultDiagnosisReportSubmition.launch(DiagnosisActivity.getActivityIntent(
                    requireActivity()))
            }
        }
        binding.includeChbMedications.ivEditChbMed.setOnClickListener {
            if (diagnosisModelChib != null) {
                resultDiagnosisReportSubmition.launch(EditDiagnosisActivity.getIntent(
                    requireActivity(),
                    diagnosisModelChib!!,
                    EditDiagnosisActivity.EDIT_DIAGNOSIS_MEDICATION, args.userId))
            } else {
                resultDiagnosisReportSubmition.launch(DiagnosisActivity.getActivityIntent(
                    requireActivity()))
            }
        }
        binding.includeAfibMedications.ivEditAfibMed.setOnClickListener {
            if (diagnosisModelAfib != null) {
                resultDiagnosisReportSubmition.launch(EditDiagnosisActivity.getIntent(
                    requireActivity(),
                    diagnosisModelAfib!!,
                    EditDiagnosisActivity.EDIT_DIAGNOSIS_MEDICATION, args.userId))
            } else {
                resultDiagnosisReportSubmition.launch(DiagnosisActivity.getActivityIntent(
                    requireActivity()))
            }
        }
        binding.tvAddDiagnosis.setOnClickListener {
            resultDiagnosisReportSubmition.launch(DiagnosisActivity.getActivityIntent(
                requireActivity()))
        }
        binding.tvAddHealthLog.setOnClickListener {
            resultHealthLogs.launch(HealthLogsActivity.getIntent(requireActivity(), args.userId))
        }
        binding.ivEditGraphHeathlogs.setOnClickListener {
            resultHealthLogs.launch(HealthLogsActivity.getIntent(requireActivity(), args.userId))
        }
    }

    private fun loadGraphData() {
        // hide charts and load graph data
        heartRateGraph.invisibleWhileRefreshing()
        bpGraph.invisibleWhileRefreshing()
        weightGraph.invisibleWhileRefreshing()
        stepCountGraph.invisibleWhileRefreshing()
        binding.progressBarForGraphs.visibility = View.VISIBLE
        diagnosisActivity?.currentFilter?.value?.let { it1 -> viewModel.getHealthLogs(it1, arguments?.getString(FireStoreDocKey.USER_ID)) }
    }

    private fun setFilterButtonState(it: View?) {
        when (it?.id) {
            R.id.btFilter30 -> {
                diagnosisActivity?.currentFilter = Filter.THIRTY
                binding.includeFilterDash.btFilter30.setTextColor(requireContext().resources.getColor(
                    R.color.white))
                binding.includeFilterDash.btFilter60.setTextColor(requireContext().resources.getColor(
                    R.color.color_filter))
                binding.includeFilterDash.btFilter90.setTextColor(requireContext().resources.getColor(
                    R.color.color_filter))
                binding.includeFilterDash.btFilter30.setBackgroundResource(R.drawable.btn_color_primary_left_rounded_background)
                binding.includeFilterDash.btFilter60.setBackgroundResource(R.drawable.btn_color_primary_border_rounded_background)
                binding.includeFilterDash.btFilter90.setBackgroundResource(R.drawable.btn_color_primary_border_right_rounded_background)
            }
            R.id.btFilter60 -> {
                diagnosisActivity?.currentFilter = Filter.SIXTY
                binding.includeFilterDash.btFilter30.setTextColor(requireContext().resources.getColor(
                    R.color.color_filter))
                binding.includeFilterDash.btFilter60.setTextColor(requireContext().resources.getColor(
                    R.color.white))
                binding.includeFilterDash.btFilter90.setTextColor(requireContext().resources.getColor(
                    R.color.color_filter))
                binding.includeFilterDash.btFilter30.setBackgroundResource(R.drawable.btn_color_primary_border_left_rounded_background)
                binding.includeFilterDash.btFilter60.setBackgroundResource(R.drawable.btn_color_primary_rounded_background)
                binding.includeFilterDash.btFilter90.setBackgroundResource(R.drawable.btn_color_primary_border_right_rounded_background)
            }
            R.id.btFilter90 -> {
                binding.includeFilterDash.btFilter30.setTextColor(requireContext().resources.getColor(
                    R.color.color_filter))
                binding.includeFilterDash.btFilter60.setTextColor(requireContext().resources.getColor(
                    R.color.color_filter))
                binding.includeFilterDash.btFilter90.setTextColor(requireContext().resources.getColor(
                    R.color.white))
                binding.includeFilterDash.btFilter30.setBackgroundResource(R.drawable.btn_color_primary_border_left_rounded_background)
                binding.includeFilterDash.btFilter60.setBackgroundResource(R.drawable.btn_color_primary_border_rounded_background)
                binding.includeFilterDash.btFilter90.setBackgroundResource(R.drawable.btn_color_primary_right_rounded_background)
                diagnosisActivity?.currentFilter = Filter.NINETY
            }
        }
    }

    private fun setVisibilityOfGraphs(visibility: Int) {
        binding.includeHeartVsDate.clHeartRateGraph.visibility = visibility
        binding.includeBpVsDate.clBpChart.visibility = visibility
        binding.includeWeightVsDate.clGraphContainer.visibility = visibility
        binding.includeStepstVsDate.clStepCount.visibility = visibility
    }

    private fun setDiagnosisDataInView(diagnosisModel: DiagnosisModel) {
        diagnosisModel.ailment?.let {
            if (it.equals(CARDIAC_HEART_FAILURE, true)) {
                this.diagnosisModelChib = diagnosisModel
                setChbData(diagnosisModel)
            } else if (it.equals(ATRIAL_FABRILLATION, true)) {
                this.diagnosisModelAfib = diagnosisModel
                setAfibData(diagnosisModel)
            }
        }
        //keep it below above method as obove method assign diagnosis to respective global variable
        setBasicInfo(getLatestDiagnosis())
    }

    private fun getLatestDiagnosis(): DiagnosisModel? {
        if (diagnosisModelAfib != null && diagnosisModelChib != null && diagnosisModelAfib!!.timeStamp != null && diagnosisModelChib!!.timeStamp != null) {
            return if (diagnosisModelAfib!!.timeStamp!! > diagnosisModelChib!!.timeStamp!!) diagnosisModelAfib else diagnosisModelChib
        }
        return if (diagnosisModelAfib != null) diagnosisModelAfib else diagnosisModelChib
    }

    private fun showConsidiration(it: List<FitnessModel>) {
        chfConsidirationView.showData(binding,it,diagnosisModelChib)
    }

    private fun showGraphs(listHealthLogs: List<FitnessModel>?) {
        //show all the graphs now
        heartRateGraph.showGraph(listHealthLogs)
        bpGraph.showGraph(listHealthLogs)
        weightGraph.showGraph(listHealthLogs)
        stepCountGraph.showGraph(listHealthLogs)
        diagnosisModelChib?.let { weightGraph.setDryWeight(it) }
    }

    private fun setAfibData(diagnosisModel: DiagnosisModel) {
        afibView.showClinicalData(diagnosisModel, binding)
        afibMedicineView.showMedicationData(diagnosisModel, binding)
    }

    private fun setChbData(diagnosisModel: DiagnosisModel) {
        chfView.showClinicalData(diagnosisModel, binding)
        chfMedicationView.showMedicationData(diagnosisModel, binding)
//        weightGraph.setDryWeight(diagnosisModel)
    }

    private fun setBasicInfo(userModel: UserModel?) {

        (activity as DiagnosisActivity)?.itoolbar.view.setUpToolbar(userModel?.firstName + " " + userModel?.lastName)

        if (userModel?.gender.isNullOrBlank()) binding.includeBasicInfo.tvGenderDash.text = "--"
        if (userModel?.dob.isNullOrBlank()) {
            binding.includeBasicInfo.tvDateDashboard.text="--"
            binding.includeBasicInfo.tvAgeDash.text = "--"
        }
        userModel?.gender?.let {
            if (it.isNotBlank() && !it.equals(getString(R.string.select_gender),
                    true)
            ) binding.includeBasicInfo.tvGenderDash.text = it
        }
        userModel?.dob?.let {
            if(it.isNotBlank()){
                binding.includeBasicInfo.tvDateDashboard.text = it
                binding.includeBasicInfo.tvAgeDash.text =
                    "${it.getNoYearsFromDate()} ${getString(R.string.years)}"
            }
        }
        toolbar?.setUserImage(userModel?.imagePath)
        userModel?.hasUnreadNotification?.let {
            toolbar?.notificationBadgeVisibility(it)
        }
    }

    private fun setBasicInfo(diagnosisModel: DiagnosisModel?) {
        diagnosisModel?.date?.let { binding.includeBasicInfo.tvAssesmentYearDash.text = it }
        diagnosisModel?.firstName?.let { binding.includeBasicInfo.tvNameDashboard.text = it }
        diagnosisModel?.lastName?.let { binding.includeBasicInfo.tvNameDashboard.append(" ${it}") }
    }

    private val notificationUpdateReciever: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.getStringExtra(EXTRAS_PUSH_NOTIFICATON_TITLE)?.let {title->
                if (title.equals(NOTIFICATION_TITLE_DIAGNOSIS,true)){
                    parentActivity?.runOnUiThread {
                        init()
                    }
                }
            }
        }
    }


}
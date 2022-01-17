package com.cardio.physician.ui.views.diagnosis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationHandler
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.AuthenticationResult
import com.cardio.physician.databinding.ActivityDiagnosisBinding
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity
import com.cardio.physician.ui.common.customviews.toolbar.DiagnosisToolbarImp
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.customviews.StepView
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar
import com.cardio.physician.ui.common.utils.showConfirmAlertDialog
import com.cardio.physician.ui.views.dashboard.fragment.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.NavArgument
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.EXTRAS
import com.cardio.physician.ui.views.notifications.NotificationsActivity


@AndroidEntryPoint
open class DiagnosisActivity : BaseToolbarActivity(), AuthenticationHandler {

    companion object {
        fun start(activity: Activity, userId: String?) {
            val intent = Intent(activity, DiagnosisActivity::class.java)
            intent.putExtra(FireStoreDocKey.USER_ID, userId)
            activity.startActivity(intent)
        }
        fun getActivityIntent(activity: Activity)=Intent(activity, DiagnosisActivity::class.java)
    }

    lateinit var currentFilter: DashboardFragment.Filter
    var questionList: List<QuestionModel>? = null
    var lastQuestionIndex = 0
    private var onConnectClick: (() -> Unit?)? = null
    private var lastStep: Int? = null
    internal val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragmentDiagnoises) as NavHostFragment).navController
    }
    lateinit var itoolbar: DiagnosisToolbarImp
    private val binding by viewBinding(ActivityDiagnosisBinding::inflate)
    private var stepView: StepView? = null
    internal var diagnosisModel = DiagnosisModel()
    private val viewModel: DiagnosisActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViews()
        setListeners()
        init()
    }

    private fun setViews() {
        setNavGraph()
    }

    fun init() {
        currentFilter=DashboardFragment.Filter.THIRTY
        //  fcmManager.getToken()
        fcmManager.subscribeFcmTopic()
        //  fcmManager.sendPushNotification("weather")
        intent?.let {
            if (it.getBooleanExtra(EXTRAS.EXTRAS_FROM_NOTIFICATION, false))
                NotificationsActivity.start(this)
        }
    }

    open fun setNavGraph() {
        val bundle = Bundle()
        bundle.putString(FireStoreDocKey.USER_ID, intent.getStringExtra(FireStoreDocKey.USER_ID))
        navController.setGraph(com.cardio.physician.R.navigation.graph_selection_item,bundle)

        /*navController.setGraph(com.cardio.physician.R.navigation.graph_selection_item)
        navController.graph.findNode(com.cardio.physician.R.id.graph_selection_item)
            ?.addArgument("userId", NavArgument.Builder()
                .setDefaultValue(intent.getStringExtra(FireStoreDocKey.USER_ID))
                .build())*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (!AuthenticationManager.onActivityResult(requestCode, resultCode, data, this)) {
            // Handle other activity results, if needed
        }
    }

    override fun onBackPressed() {
        finish()
        /*showConfirmAlertDialog(
            this!!,
            "",
            getString(R.string.confirm_dismiss_diagnosis)
        ) { btnText: String, dialog: DialogInterface ->
            when (btnText) {
                getString(R.string.yes) -> {
                    finish()
                }
            }
        }*/
    }

    private fun setListeners() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.label){
                "Dashboard" -> {
                    itoolbar.view.setUpToolbar("Dashboard")
                }
                else -> {
                    itoolbar.view.setUpToolbar("Diagnosis")
                    destination.label.toString().toInt().let {
                        lastStep = it
                        setStepNo(it)
                    }
                }
            }
        }
        binding.headerView.buttonConnect.setOnClickListener {
            onConnectClick?.invoke()
        }
    }

    var onBackClick: (() -> Unit)? = {
        onBackPressed()
    }

    override fun getToolbarImp(): IToolbar {
        itoolbar = DiagnosisToolbarImp(binding.headerView.toolBarContainer).apply {
            onBackClick = this@DiagnosisActivity.onBackClick
        }
        return itoolbar
    }

    fun setConnectButtonVisibility(visible: Boolean) {
        itoolbar.setConnectButtonVisibility(visible)
    }

    fun setStepNo(step: Int) {
        if (step == 4) stepView?.done(true)
        else
            stepView?.go(step, true);
        //set connect button visibility
        if (step == 0)
            setConnectButtonVisibility(false)
        else
            setConnectButtonVisibility(false)
    }

    open fun setStepView(stepView: StepView) {
        this.stepView = stepView
        lastStep?.let { setStepNo(it) }
    }


    fun onConnectClick(function: () -> Unit?) {
        this.onConnectClick = function
    }

    override fun onAuthFinished(authenticationResult: AuthenticationResult?) {
        if (authenticationResult != null && authenticationResult.isSuccessful()) {
            onConnectClick?.invoke()
        } /*else {
            displayAuthError(authenticationResult)
        }*/
    }

}
package com.cardio.doctor.ui.views.diagnosis

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cardio.doctor.R
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager
import com.cardio.doctor.databinding.ActivityDiagnosisBinding
import com.cardio.doctor.domain.diagnosis.DiagnosisModel
import com.cardio.doctor.ui.common.base.activity.BaseToolbarActivity
import com.cardio.doctor.ui.common.base.fragment.toolbar.DiagnosisToolbarImp
import com.cardio.doctor.ui.common.base.fragment.toolbar.IToolbar
import com.cardio.doctor.ui.common.customviews.StepView
import com.cardio.doctor.ui.common.utils.extentions.customObserver
import com.cardio.doctor.ui.common.utils.showConfirmAlertDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DiagnosisActivity : BaseToolbarActivity() {

    companion object {
        fun start(activity: Activity) {
            activity?.startActivity(Intent(activity, DiagnosisActivity::class.java))
        }
    }

    private var lastStep: Int? = null
    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragmentDiagnoises) as NavHostFragment).navController
    }
    private lateinit var itoolbar: DiagnosisToolbarImp
    private val binding by viewBinding(ActivityDiagnosisBinding::inflate)
    private var stepView: StepView? = null
    private val diagnosisModel = DiagnosisModel()
    private val viewModel: DiagnosisActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.getUserFitnessData()
            .customObserver(
                this,
                onLoading = ::showProgress,
                onSuccess = {

                },
                onError = ::onError
            )
    }


    override fun onBackPressed() {
        showConfirmAlertDialog(
            this!!,
            getString(R.string.confirm),
            getString(R.string.confirm_dismiss_diagnosis)
        ) { btnText: String, dialog: DialogInterface ->
            when (btnText) {
                getString(R.string.yes) -> {
                    finish()
                }
            }
        }
    }

    private fun setListeners() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            destination.label.toString().toInt().let {
                lastStep = it
                setStepNo(it)
            }
        }
        binding.headerView.buttonConnect.setOnClickListener {
            if (viewModel.isLoggedIn()) {
                viewModel.getUserData(this)
            } else {
                viewModel.login(this)
            }
        }
    }

    var onBackClick: (() -> Unit)? = {
        showConfirmAlertDialog(
            this!!,
            getString(R.string.confirm),
            getString(R.string.confirm_dismiss_diagnosis)
        ) { btnText: String, dialog: DialogInterface ->
            when (btnText) {
                getString(R.string.yes) -> {
                    finish()
                }
            }
        }
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
            setConnectButtonVisibility(true)
        else
            setConnectButtonVisibility(false)
    }

    fun setStepView(stepView: StepView) {
        this.stepView = stepView
        lastStep?.let { setStepNo(it) }
    }

    fun getDiagnosisModel(): DiagnosisModel {
        return diagnosisModel
    }

}
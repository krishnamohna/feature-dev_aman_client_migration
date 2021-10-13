package com.cardio.doctor.ui.views.diagnosis

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cardio.doctor.R
import com.cardio.doctor.databinding.ActivityDiagnosisBinding
import com.cardio.doctor.domain.diagnosis.DiagnosisModel
import com.cardio.doctor.ui.common.base.activity.BaseToolbarActivity
import com.cardio.doctor.ui.common.base.fragment.toolbar.DiagnosisToolbarImp
import com.cardio.doctor.ui.common.base.fragment.toolbar.IToolbar
import com.cardio.doctor.ui.common.customviews.StepView
import com.cardio.doctor.ui.common.utils.showConfirmAlertDialog
import com.cardio.doctor.ui.views.fitbit.RootActivity
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
    private val binding by viewBinding(ActivityDiagnosisBinding::inflate)
    private var stepView: StepView? = null
    private val diagnosisModel = DiagnosisModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        setListeners()
    }

    private fun initViews() {
        //setStepView(binding.stepView.stepView)
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
        binding.headerView.buttonConnect.setOnClickListener { RootActivity.start(DiagnosisActivity@ this) }
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
        return DiagnosisToolbarImp(binding.headerView.toolBarContainer).apply {
            onBackClick = this@DiagnosisActivity.onBackClick
        }
    }

    fun setStepNo(step: Int) {
        if (step == 4) stepView?.done(true)
        else
            stepView?.go(step, true);
    }

    fun setStepView(stepView: StepView) {
        this.stepView = stepView
        lastStep?.let { setStepNo(it) }
    }

    fun getDiagnosisModel(): DiagnosisModel {
        return diagnosisModel
    }

}
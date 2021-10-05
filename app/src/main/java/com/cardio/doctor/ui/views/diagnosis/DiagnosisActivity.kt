package com.cardio.doctor.ui.views.diagnosis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cardio.doctor.R
import com.cardio.doctor.databinding.ActivityDiagnosisBinding
import com.cardio.doctor.ui.common.base.activity.BaseToolbarActivity
import com.cardio.doctor.ui.common.base.fragment.toolbar.DiagnosisToolbarImp
import com.cardio.doctor.ui.common.base.fragment.toolbar.IToolbar


class DiagnosisActivity : BaseToolbarActivity() {

    companion object {
        fun start(activity: Activity) {
            activity?.startActivity(Intent(activity, DiagnosisActivity::class.java))
        }
    }

    private val navController:NavController by lazy {
            (supportFragmentManager.findFragmentById(R.id.navHostFragmentDiagnoises) as NavHostFragment).navController
    }
    private val binding by viewBinding(ActivityDiagnosisBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            destination.label.toString().toInt().let {
                setStepNo(it)
            }
        }
    }

    private fun moveToStep(it: Int) {

    }

    var onBackClick: (() -> Unit)? = {
        onBackPressed()
    }

    override fun getToolbarImp(): IToolbar {
        return DiagnosisToolbarImp(binding.headerView.toolBarContainer).apply {
            onBackClick = this@DiagnosisActivity.onBackClick
        }
    }

    fun setStepNo(step: Int) {
        if (step == 4) binding.stepView.done(true)
        else
            binding.stepView.go(step, true);
    }

}
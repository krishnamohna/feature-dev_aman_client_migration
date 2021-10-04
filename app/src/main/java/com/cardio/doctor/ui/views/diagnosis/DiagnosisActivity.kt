package com.cardio.doctor.ui.views.diagnosis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.cardio.doctor.databinding.ActivityDiagnosisBinding
import com.cardio.doctor.ui.common.base.activity.BaseActivity

class DiagnosisActivity : BaseActivity() {

    companion object{
        fun start(activity: Activity){
            activity?.startActivity(Intent(activity,DiagnosisActivity::class.java))
        }
    }

    private val binding by viewBinding(ActivityDiagnosisBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
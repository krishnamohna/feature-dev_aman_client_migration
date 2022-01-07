package com.cardio.physician.ui.views.illness

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityIllnessBinding
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.views.add_patient.AddPatientViewModel
import com.cardio.physician.ui.views.add_patient.PatientAdapter
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IllnessActivity : BaseActivity(), View.OnClickListener {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, IllnessActivity::class.java))
        }
    }

    private lateinit var adapter: PatientAdapter
    private val binding by viewBinding(ActivityIllnessBinding::inflate)
    private val viewModel: AddPatientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showHidePatientView(View.VISIBLE)
        setListeners()
        setRecyclerView()
        setObservers()
        viewModel.getAllPatientList()
    }

    private fun setListeners() {
        binding.etSearch.addTextChangedListener(TextChangeWatcher(binding.etSearch))
        binding.ivBack.setOnClickListener(this)
        binding.tvEmailUser.setOnClickListener(this)
        binding.ivEmailUser.setOnClickListener(this)
        binding.tvInviteUser.setOnClickListener(this)
    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        adapter = PatientAdapter({ view, position ->
            DiagnosisActivity.start(this, adapter.patientList[position].userId)
        }, isFromIllness = true, {
            if(it) {
                showHidePatientView(View.VISIBLE)
            }else{
                showHidePatientView(View.GONE)
            }
        })
        binding.rvPatientList.layoutManager = layoutManager
        binding.rvPatientList.adapter = adapter
    }


    private fun setObservers() {
        viewModel.liveUserData.customObserver(this, null, {
            if(it.isNullOrEmpty()){
                showHidePatientView(View.VISIBLE)
            }else{
                showHidePatientView(View.GONE)
                adapter.updateData(it)
            }
        }, { msg, exp ->
            showHidePatientView(View.VISIBLE)
            binding.ivEmailUser.visibility = View.GONE
            binding.tvEmailUser.visibility = View.GONE
            binding.tvInviteUser.visibility = View.GONE
        })
    }

    private fun showHidePatientView(showHide: Int) {
        binding.tvNoPatientFound.visibility = showHide
        binding.ivNoPatientFound.visibility = showHide
        /*if (showHide == View.VISIBLE) {
            binding.rvPatientList.visibility = View.GONE
        } else {
            binding.rvPatientList.visibility = View.VISIBLE
        }*/
    }

    inner class TextChangeWatcher(private var view: View) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            view.setBackgroundResource(R.drawable.edt_rounded_corner)
            when (view) {
                binding.etSearch -> {
                    adapter.filter.filter(binding.etSearch.text)
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.ivBack -> {
                onBackPressed()
            }
        }
    }
}
package com.cardio.physician.ui.views.add_patient

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cardio.physician.BuildConfig
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityAddPatientBinding
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.showAlertDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPatientActivity : BaseActivity(), View.OnClickListener {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, AddPatientActivity::class.java))
        }
    }

    private lateinit var adapter: PatientAdapter
    private val binding by viewBinding(ActivityAddPatientBinding::inflate)
    private val viewModel: AddPatientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showHidePatientView(View.VISIBLE)
        setListeners()
        setRecyclerView()
        setObservers()
    }

    private fun setListeners() {
        binding.etSearch.addTextChangedListener(TextChangeWatcher(binding.etSearch))
        binding.ivBack.setOnClickListener(this)
        binding.tvEmailUser.setOnClickListener(this)
        binding.ivEmailUser.setOnClickListener(this)
        binding.tvInviteUser.setOnClickListener(this)
    }

    private fun setRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        adapter = PatientAdapter ({ view, position ->
            if(view.id == R.id.tv_add){
                viewModel.updateDataToFirestore(adapter.patientList[position])
            }
        }, false, {

        })
        binding.rvPatientList.layoutManager = layoutManager
        binding.rvPatientList.adapter = adapter
    }


    private fun setObservers() {
        viewModel.liveUserData.customObserver(this, null, {
            if(!binding.etSearch.text.isNullOrEmpty()) {
                showHidePatientView(View.GONE)
                binding.rvPatientList.visibility = View.VISIBLE
                binding.ivEmailUser.visibility = View.GONE
                binding.tvEmailUser.visibility = View.GONE
                binding.tvInviteUser.visibility = View.GONE
                adapter.updateData(it)
            }else{
                binding.ivEmailUser.visibility = View.GONE
                binding.tvEmailUser.visibility = View.GONE
                binding.tvInviteUser.visibility = View.GONE
                binding.rvPatientList.visibility = View.GONE
                showHidePatientView(View.VISIBLE)
            }
        }, { msg, exp ->
            when(msg){
                "201" -> {
                    binding.ivEmailUser.visibility = View.VISIBLE
                    binding.tvEmailUser.visibility = View.VISIBLE
                    binding.tvInviteUser.visibility = View.VISIBLE
                    binding.rvPatientList.visibility = View.GONE
                    binding.tvEmailUser.text = binding.etSearch.text.toString()
                    showHidePatientView(View.GONE)
                }
                "203", "202" -> {
                    binding.ivEmailUser.visibility = View.GONE
                    binding.tvEmailUser.visibility = View.GONE
                    binding.tvInviteUser.visibility = View.GONE
                    binding.rvPatientList.visibility = View.GONE
                    showHidePatientView(View.VISIBLE)
                }
            }
        })
    }

    private fun showHidePatientView(showHide: Int){
        binding.tvNoPatientFound.visibility = showHide
        binding.ivNoPatientFound.visibility = showHide
    }

    inner class TextChangeWatcher(private var view: View) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            view.setBackgroundResource(R.drawable.edt_rounded_corner)
            when (view) {
                binding.etSearch -> {
                    if(s.isNotEmpty()) viewModel.searchUserInFirestore(s.toString().trim().lowercase())
                    else {
                        showHidePatientView(View.VISIBLE)
                        binding.rvPatientList.visibility = View.GONE
                    }
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.ivBack -> {
                onBackPressed()
            }
            binding.ivEmailUser, binding.tvEmailUser, binding.tvInviteUser -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey check out my patient app at: https://play.google.com/store/apps/details?id=com.cardio.doctor"
                )
                sendIntent.type = "text/plain"
                startActivity(sendIntent)

                /*showAlertDialog(this@AddPatientActivity,
                    "Invitation Sent", "You will be notified once the user\naccepts the invite.", getString(R.string.ok).uppercase(),
                    getString(R.string.cancel),
                    btnTwoVisibility = false
                ) { _: String, dialog: DialogInterface ->
                    dialog.dismiss()
                }*/
            }
        }
    }
}
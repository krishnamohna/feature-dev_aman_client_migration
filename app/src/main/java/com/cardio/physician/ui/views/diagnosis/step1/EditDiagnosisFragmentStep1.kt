package com.cardio.physician.ui.views.diagnosis.step1

import android.app.Activity
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.DateFormat_
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.datePickerStringToDate
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.showToast

class EditDiagnosisFragmentStep1 : DiagnosisFragmentStep1() {

    val arguments : EditDiagnosisFragmentStep1Args by navArgs()

    override fun setObservers() {
        super.setObservers()
        viewModel.liveSubmitDiagnosis.customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = {
                it?.let {
                    diagnosisActivity?.let { activity ->
                        showToast(activity, "Report submitted !!")
                        activity.setResult(Activity.RESULT_OK)
                        activity.finish()
                    }
                }
            },
            onError = ::onError
        )
    }

    override fun setViews() {
        super.setViews()
        binding.cvDiagnosisBottomContainer.btNext.setText(R.string.save)
        setDiagnosisData()
    }

    override fun loadUserProfile() {
        // keep this method open now as do not want to load user profile
    }

    private fun setDiagnosisData() {
        diagnosisActivity?.diagnosisModel?.let { diagnosisModel ->
            diagnosisModel.firstName?.let { binding.clPatientDetail.edtFirstName.setText(it) }
            diagnosisModel.lastName?.let { binding.clPatientDetail.edtLastName.setText(it) }
            diagnosisModel.age?.let {
                birthDate = it.datePickerStringToDate(DateFormat_.DATE_FORMAT_DD_MMM_YYYY)
                binding.clPatientDetail.edtAge.setText(it)
            }
            diagnosisModel.weight?.let { binding.clHealthDetail.edtWeight.setText(it) }
            diagnosisModel.heartRate?.let { binding.clHealthDetail.edtHeartRate.setText(it) }
            diagnosisModel.topBp?.let { binding.clHealthDetail.edtTopBp.setText(it) }
            diagnosisModel.bottomBp?.let { binding.clHealthDetail.edtBottomBp.setText(it) }
            diagnosisModel.stepCount?.let { binding.clHealthDetail.edtStepCount.setText(it) }
            diagnosisModel.ailment?.let {
                binding.spinnerCategory.isEnabled=false
                if(it.equals(FireStoreDocKey.CARDIAC_HEART_FAILURE)){
                    binding.spinnerCategory.setSelection(1)
                }else if(it.equals(FireStoreDocKey.ATRIAL_FABRILLATION)){
                    binding.spinnerCategory.setSelection(2)
                }
            }
        }
    }

    override fun onNextClickAfterValidation() {
        viewModel.submitDiagnosisReport(diagnosisActivity?.diagnosisModel!!, arguments.userId, true)
    }
}
package com.cardio.physician.ui.views.diagnosis.step2

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.showToast

class EditFragmentStep2 : DiagnosisFragmentStep2() {

    val arguments : EditFragmentStep2Args by navArgs()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvDiagnosisBottomContainer.btNext.text=getString(R.string.save)
        binding.cvDiagnosisBottomContainer.btCancel.text=getString(R.string.cancel)
        diagnosisActivity?.diagnosisModel?.medications?.forEach {
            addToList(it)
        }
    }

    override fun onNextButtonClick() {
        viewModel.submitDiagnosisReport(diagnosisActivity?.diagnosisModel!!, arguments.userId, true)
    }

    override fun onBottomBackButtonClick() {
        super.onCancelButtonClick()
    }
}
package com.cardio.physician.ui.views.diagnosis.step3

import android.app.Activity
import android.view.View
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.common.utils.showToast
import dagger.hilt.android.AndroidEntryPoint


class EditDiagnosisFragmentStep3 : DiagnosisFragmentStep3() {

    val argument: EditDiagnosisFragmentStep3Args by navArgs()

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
        binding.cvDiagnosisBottomContainer.btCancel.text = getString(R.string.cancel)
    }

    override fun onNextButtonClick() {
        viewModel.submitDiagnosisReport(diagnosisActivity?.diagnosisModel!!, argument.userId, true)
    }

    override fun onBottomCancelButtonclick() {
        super.onCancelButtonClick()
    }


    override fun showQuestion(questionView: View) {
        super.showQuestion(questionView)
        //check if last question then change next button text
        binding.cvDiagnosisBottomContainer.btNext.text =
            if (isLastQuestion()) getString(R.string.save) else getString(R.string.next)
        //check if first question then change back button text
        binding.cvDiagnosisBottomContainer.btCancel.text =
            if (isFirstQuestion()) getString(R.string.cancel) else getString(R.string.back)
    }
}
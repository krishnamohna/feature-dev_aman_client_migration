package com.cardio.doctor.ui.views.sync_health_data

import android.app.Activity
import android.content.Intent
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.cardio.doctor.network.api.EXTRAS
import com.cardio.doctor.ui.views.sync_health_data.activity.SyncHealthActivty


class SyncHealthDataFragmentForResult : SyncHealthDataFragment() {

    private var fitnessModel: FitnessModel? = null
    private var heartRateModel: HeartRateModel? = null

    override fun onFitbitSelection() {
        //do not call supper here
        // super.onFitbitSelection()
        viewModel.getUserData(requireActivity())
        viewModel.getHeartRate(requireActivity())
    }

    override fun onBackButtonClick() {
        requireActivity().onBackPressed()
    }

    override fun onFitbitHeartRateDataRecieved(it: HeartRateModel) {
        super.onFitbitHeartRateDataRecieved(it)
        this.heartRateModel = it
        if (isCompleteFitBitDataRecieved())
            sendResultBack()
    }

    private fun sendResultBack() {
        (requireActivity() as? SyncHealthActivty)?.apply {
            Intent().apply {
                putExtra(EXTRAS.USER_PROFILE,fitnessModel)
                putExtra(EXTRAS.HEAR_RATE,heartRateModel)
                setResult(Activity.RESULT_OK,this)
                finish()
            }
        }
    }

    override fun onFitbitProfileDataRecieved(fitnessModel: FitnessModel) {
        super.onFitbitProfileDataRecieved(fitnessModel)
        this.fitnessModel = fitnessModel
        if (isCompleteFitBitDataRecieved())
            sendResultBack()
    }


}
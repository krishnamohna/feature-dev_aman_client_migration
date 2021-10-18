package com.cardio.doctor.ui.views.sync_health_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.cardio.doctor.network.api.EXTRAS
import com.cardio.doctor.ui.views.sync_health_data.activity.SyncHealthActivty


class SyncHealthDataFragmentForResult : SyncHealthDataFragment() {

    private var fitnessModel: FitnessModel? = null
    private var heartRateModel: HeartRateModel? = null
    private var isFitSelected = false
    private var isGoogleFitSelected = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
    }

    private fun setViews() {
        binding.btNext.visibility = View.VISIBLE
    }

    override fun setListener() {
        super.setListener()
        binding.btNext.setOnClickListener {
            if (isFitSelected) {
                viewModel.getUserData(requireActivity())
                viewModel.getHeartRate(requireActivity())
            }else if(isGoogleFitSelected){
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onFitbitSelection() {
        //do not call supper here
        isFitSelected = true
    }

    override fun onGoogleSelection() {
        //do not call supper here
        isGoogleFitSelected = true
    }

    override fun onBackButtonClick() {
        requireActivity().onBackPressed()
    }

    private fun sendResultBack() {
        (requireActivity() as? SyncHealthActivty)?.apply {
            Intent().apply {
                putExtra(EXTRAS.USER_PROFILE, fitnessModel)
                putExtra(EXTRAS.HEAR_RATE, heartRateModel)
                setResult(Activity.RESULT_OK, this)
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

    override fun onFitbitHeartRateDataRecieved(it: HeartRateModel) {
        super.onFitbitHeartRateDataRecieved(it)
        this.heartRateModel = it
        if (isCompleteFitBitDataRecieved())
            sendResultBack()
    }


}
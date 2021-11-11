package com.cardio.physician.ui.views.sync_health_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.network.api.EXTRAS
import com.cardio.physician.ui.views.sync_health_data.activity.SyncHealthActivty


class SyncHealthDataFragmentForResult : SyncHealthDataFragment() {

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
                viewModel.getFitbitUserData(requireActivity())
            }else if(isGoogleFitSelected){
                viewModel.getGoogleUserData(requireActivity())
            }
        }
    }

    override fun onBackButtonClick() {
        requireActivity().onBackPressed()
    }

    private fun sendResultBack(fitnessModel: FitnessModel) {
        (requireActivity() as? SyncHealthActivty)?.apply {
            Intent().apply {
                putExtra(EXTRAS.USER_PROFILE, fitnessModel)
                setResult(Activity.RESULT_OK, this)
                finish()
            }
        }
    }

    override fun onFitbitProfileDataRecieved(fitnessModel: FitnessModel) {
        super.onFitbitProfileDataRecieved(fitnessModel)
        sendResultBack(fitnessModel)
    }

    override fun onGoogleProfileDataRecieved(it: FitnessModel) {
        super.onGoogleProfileDataRecieved(it)
        sendResultBack(it)
    }

    /*overide this method so that use won't navigate back on selelection when coming from step 1 */
    override fun onFitbitSelection() {
        //do not call supper here
    }
    /*overide this method so that use won't navigate back on selelection when coming from step 1 */
    override fun onGoogleSelection() {
        //do not call supper here
    }

}
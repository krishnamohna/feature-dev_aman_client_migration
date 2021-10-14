package com.cardio.doctor.data.remote.fitnesstracker.googlefit

import android.app.Activity
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import javax.inject.Inject

class GoogleFitBitRepositaryImp @Inject  constructor() :FitnessRepositary {

    override fun getProfileData(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getHeartRate(
        activity: Activity,
        onSuccess: (HeartRateModel) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun login(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun logout(activity: Activity) {
        TODO("Not yet implemented")
    }

}
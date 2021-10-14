package com.cardio.doctor.data.remote.fitnesstracker.fitbit

import android.app.Activity
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.loaders.HeartRateLoader
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.loaders.UserProfileLoader
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import javax.inject.Inject

class FitbitRepositaryImp @Inject constructor() : FitnessRepositary {

    override fun getProfileData(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg:String?) -> Unit
    ) {
        UserProfileLoader(activity,onSuccess,onFailure).load()
    }

    override fun getHeartRate(
        activity: Activity,
        onSuccess: (HeartRateModel) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        HeartRateLoader(activity,onSuccess,onFailure).load()
    }

    override fun isLoggedIn(): Boolean {
       return AuthenticationManager.isLoggedIn()
    }

    override fun login(activity: Activity) {
        AuthenticationManager.login(activity)
    }

    override fun logout(activity: Activity) {
        AuthenticationManager.logout(activity)
    }
}
package com.cardio.doctor.data.remote.fitnesstracker.fitbit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders.HeartRateLoader
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders.UserProfileLoader
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

    override fun login(activity: ActivityResultLauncher<Intent>,context: Context) {
        AuthenticationManager.login(activity,context)
    }

    override fun login(fragment: Fragment) {
        TODO("Not yet implemented")
    }

    override fun logout(activity: Activity) {
        AuthenticationManager.logout(activity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TODO("Not yet implemented")
    }
}
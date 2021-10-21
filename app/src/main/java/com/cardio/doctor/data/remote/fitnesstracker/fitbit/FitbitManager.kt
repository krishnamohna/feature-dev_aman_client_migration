package com.cardio.doctor.data.remote.fitnesstracker.fitbit

import android.app.Activity
import android.content.Context
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders.HeartRateLoader
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders.UserProfileLoader
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.cardio.doctor.domain.fitness.model.SyncModel
import javax.inject.Inject

class FitbitManager @Inject constructor() {
    /*load user profile first then load heart rate*/
    fun getUserProfile(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        UserProfileLoader(
            activity,
            { fitnessModel ->
                getHeartRate(activity, {
                    fitnessModel.heartRate = it.restHeartRate?.toFloat()
                    onSuccess.invoke(fitnessModel)
                }, {
                    onFailure.invoke(it)
                })
            },
            {
                getHeartRate(
                    activity,
                    {
                        var fitnesModel = FitnessModel()
                        fitnesModel.heartRate = it.restHeartRate?.toFloat()
                        onSuccess.invoke(fitnesModel)
                    },
                    { onFailure.invoke(it) }
                )
            }
        ).load()
    }

    fun getHeartRate(
        activity: Activity,
        onSuccess: (HeartRateModel) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        HeartRateLoader(activity, onSuccess, onFailure).load()
    }

    fun getFitnessLogs(
        activity: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: String?) -> Unit,
        periodDays: Int
    ) {
        TODO("Not yet implemented")
    }
}
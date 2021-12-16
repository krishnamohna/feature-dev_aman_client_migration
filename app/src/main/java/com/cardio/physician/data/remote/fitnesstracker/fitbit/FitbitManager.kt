package com.cardio.physician.data.remote.fitnesstracker.fitbit

import android.app.Activity
import android.content.Context
import android.os.Handler
import com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.CompositeHealthLogsLoader
import com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.TodayHeartRateLoader
import com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.UserProfileLoader
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.HeartRateModel
import com.cardio.physician.domain.fitness.model.SyncModel
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class FitbitManager @Inject constructor(val executerService: ExecutorService,val handler: Handler) {
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
                    fitnessModel.heartRate = it.restHeartRate?.toString()
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
                        fitnesModel.heartRate = it.restHeartRate?.toString()
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
        TodayHeartRateLoader(activity, onSuccess, onFailure).load()
    }

    fun getFitnessLogs(
        activity: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: String?) -> Unit,
        periodDays: Int
    ) {
        CompositeHealthLogsLoader(
            activity,
            onSuccess,
            onFailure,
            periodDays,
            executerService,
            handler
        ).load()
    }
}
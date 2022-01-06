package com.cardio.physician.data.remote.fitnesstracker.fitbit

import android.app.Activity
import android.content.Context
import android.os.Handler
import com.cardio.physician.data.remote.fitnesstracker.common.DataParser
import com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.CompositeHealthLogsLoader
import com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.TodayHeartRateLoader
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.HeartRateModel
import com.cardio.physician.domain.fitness.model.SyncModel
import com.cardio.physician.ui.common.utils.Constants
import java.lang.Exception
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class FitbitManager @Inject constructor(val executerService: ExecutorService,val handler: Handler) {
    /*load user profile first then load heart rate*/
    fun getUserProfile(
        activity: Activity,
        onSuccess: (FitnessModel) -> Unit,
        onFailure: (msg: Exception) -> Unit
    ) {
        /*UserProfileLoader(
            activity,
            { fitnessModel ->
                getHeartRate(activity, {
                    fitnessModel.heartRate = it.restHeartRate
                    onSuccess.invoke(fitnessModel)
                }, {
                    onFailure.invoke(it)
                })
            },
            {
                getHeartRate(
                    activity,
                    {
                        val fitnessModel = FitnessModel()
                        fitnessModel.heartRate = it.restHeartRate
                        onSuccess.invoke(fitnessModel)
                    },
                    { onFailure.invoke(it) }
                )
            }
        ).load()*/
        getFitnessLogs(activity,{
                     onSuccess.invoke(DataParser().parseSyncModelToFitness(it))
        },onFailure, Constants.HEALTH_LOG_PERIOD_FOR_CONNECT_FEATURE)
    }

    private fun getHeartRate(
        activity: Activity,
        onSuccess: (HeartRateModel) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        TodayHeartRateLoader(activity, onSuccess, onFailure).load()
    }
    /*get fitness log for given days to sync data to firestore to draw graph over dashboard */
    fun getFitnessLogs(
        activity: Context,
        onSuccess: (SyncModel) -> Unit,
        onFailure: (msg: Exception) -> Unit,
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
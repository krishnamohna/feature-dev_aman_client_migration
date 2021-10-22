package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import android.content.Context
import android.os.Handler
import android.util.Log
import com.cardio.doctor.domain.fitness.model.SyncModel
import java.util.concurrent.ExecutorService

class HealthLogsLoader(
    val activity: Context,
    val onSuccess: (SyncModel) -> Unit,
    val onFailure: (msg: String?) -> Unit,
    val periodDays: Int,
    val executerService: ExecutorService,
    val handler: Handler
) {

    fun load() {
        executerService.submit{
            WeightLoader({
                Log.i("",""+it)
            }, {
                Log.i("","")
            },periodDays).load()
            WeightLoader({
                Log.i("",""+it)
            }, {
                Log.i("","")
            },periodDays).load()
        }
    }

}
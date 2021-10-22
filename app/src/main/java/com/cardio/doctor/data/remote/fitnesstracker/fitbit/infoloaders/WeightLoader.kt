package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import android.app.Activity
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.base.SyncLoader
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.WeightLogs
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.services.WeightService
import com.cardio.doctor.domain.fitness.model.WeightModel
import java.util.*

class WeightLoader(
    private val activity: Activity,
    private val onSuccess: (List<WeightModel>) -> Unit,
    private val onFailure: (msg: String?) -> Unit
) : InfoLoaderSync<WeightLogs>(activity, onFailure),
    SyncLoader.LoaderCallbacks<ResourceLoaderResult<WeightLogs>> {

    override fun onLoadFinished(
        data: ResourceLoaderResult<WeightLogs>?
    ) {
        super.onLoadFinished( data)
        data?.result?.let {
           return onSuccess.invoke(it.toModel())
        }
    }

     override fun onCreateSyncLoader(): ResourceLoaderSync<WeightLogs>? {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        return WeightService.getWeightLogLoader(activity, calendar.time, Calendar.MONTH, 1)
    }

}
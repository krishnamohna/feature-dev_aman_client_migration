package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import android.app.Activity
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.WeightLogs
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.services.WeightService
import com.cardio.doctor.domain.fitness.model.WeightModel
import java.util.*

class WeightLoader(
    private val activity: Activity,
    private val onSuccess: (List<WeightModel>) -> Unit,
    private val onFailure: (msg: String?) -> Unit
) : InfoLoader<WeightLogs>(activity, onFailure),
    LoaderManager.LoaderCallbacks<ResourceLoaderResult<WeightLogs>> {

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<ResourceLoaderResult<WeightLogs>> {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        return WeightService.getWeightLogLoader(activity, calendar.time, Calendar.MONTH, 1)
    }

    override fun onLoadFinished(
        loader: Loader<ResourceLoaderResult<WeightLogs>>,
        data: ResourceLoaderResult<WeightLogs>?
    ) {
        super.onLoadFinished(loader, data)
        data?.result?.let {
           return onSuccess.invoke(it.toModel())
        }
    }

    override fun getLoaderId(): Int {
        return 7
    }

}
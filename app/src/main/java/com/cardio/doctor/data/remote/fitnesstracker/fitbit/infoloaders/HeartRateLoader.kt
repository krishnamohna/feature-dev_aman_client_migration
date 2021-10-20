package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import android.app.Activity
import android.app.LoaderManager
import android.content.Loader
import android.os.Bundle
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.heartrate.HeartRateEntity
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.services.HeartRateService
import com.cardio.doctor.domain.fitness.model.HeartRateModel

class HeartRateLoader(
    private val activity: Activity,
    private val onSuccess: (HeartRateModel) -> Unit,
    private val onFailure: (msg:String?) -> Unit
) : InfoLoader<HeartRateEntity>(activity, onFailure),
    LoaderManager.LoaderCallbacks<ResourceLoaderResult<HeartRateEntity>> {

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<ResourceLoaderResult<HeartRateEntity>> {
        return HeartRateService.getHeartRate(activity)
    }

    override fun onLoadFinished(
        p0: Loader<ResourceLoaderResult<HeartRateEntity>>?,
        data: ResourceLoaderResult<HeartRateEntity>?
    ) {
        super.onLoadFinished(p0, data)
        data?.result?.let {
           return onSuccess.invoke(it.toModel())
        }
    }

    override fun getLoaderId(): Int {
        return 6
    }

}
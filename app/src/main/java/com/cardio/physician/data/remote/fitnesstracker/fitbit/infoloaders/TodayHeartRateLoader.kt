package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders

import android.app.Activity
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate.HeartRateEntity
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services.HeartRateService
import com.cardio.physician.domain.fitness.model.HeartRateModel

class TodayHeartRateLoader(
    private val activity: Activity,
    private val onSuccess: (HeartRateModel) -> Unit,
    private val onFailure: (msg:String?) -> Unit
) : InfoLoader<HeartRateEntity>(activity, onFailure),
    LoaderManager.LoaderCallbacks<ResourceLoaderResult<HeartRateEntity>> {

    override fun onCreateLoader(
        id: Int,
        args: Bundle?
    ): Loader<ResourceLoaderResult<HeartRateEntity>> {
        return HeartRateService.getHeartRate(activity)
    }

    override fun getLoaderId(): Int {
        return 6
    }

    override fun onLoadFinished(
        loader: Loader<ResourceLoaderResult<HeartRateEntity>>,
        data: ResourceLoaderResult<HeartRateEntity>?
    ) {
        data?.result?.let {
            return onSuccess.invoke(it.toModel())
        }
    }

    override fun onLoaderReset(loader: Loader<ResourceLoaderResult<HeartRateEntity>>) {
        TODO("Not yet implemented")
    }

}
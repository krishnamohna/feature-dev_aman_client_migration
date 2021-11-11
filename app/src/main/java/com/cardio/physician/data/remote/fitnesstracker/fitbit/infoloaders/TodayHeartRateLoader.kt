package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders

import android.app.Activity
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.models.heartrate.HeartRateEntity
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services.HeartRateService
import com.cardio.physician.domain.fitness.model.HeartRateModel

class TodayHeartRateLoader(
    private val activity: Activity,
    private val onSuccess: (HeartRateModel) -> Unit,
    private val onFailure: (msg:String?) -> Unit
) : InfoLoader<HeartRateEntity>(activity, onFailure),
    LoaderManager.LoaderCallbacks<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<HeartRateEntity>> {

    override fun onCreateLoader(
        id: Int,
        args: Bundle?
    ): Loader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<HeartRateEntity>> {
        return com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services.HeartRateService.getHeartRate(activity)
    }

    override fun getLoaderId(): Int {
        return 6
    }

    override fun onLoadFinished(
        loader: Loader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<HeartRateEntity>>,
        data: com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<HeartRateEntity>?
    ) {
        data?.result?.let {
            return onSuccess.invoke(it.toModel())
        }
    }

    override fun onLoaderReset(loader: Loader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<HeartRateEntity>>) {
        TODO("Not yet implemented")
    }

}
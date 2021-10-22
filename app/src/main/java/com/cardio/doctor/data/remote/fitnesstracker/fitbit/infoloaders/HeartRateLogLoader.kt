package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.base.SyncLoader
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.heartrate.HeartRateEntity
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.services.HeartRateService
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import java.util.*

class HeartRateLogLoader(
    private val onSuccess: (List<HeartRateModel>) -> Unit,
    private val onFailure: (msg: String?) -> Unit,
    private val periodDays: Int
) : InfoLoaderSync<HeartRateEntity>( onFailure),
    SyncLoader.LoaderCallbacks<ResourceLoaderResult<HeartRateEntity>> {

    override fun onLoadFinished(
        data: ResourceLoaderResult<HeartRateEntity>?
    ) {
        super.onLoadFinished(data)
        data?.result?.let {
            return onSuccess.invoke(it.toModelList())
        }
    }

    override fun onCreateSyncLoader(): ResourceLoaderSync<HeartRateEntity>? {
        val calenderEnd = Calendar.getInstance()
        val calendarStart = Calendar.getInstance()
        calendarStart.add(Calendar.DAY_OF_MONTH, -periodDays)
        return HeartRateService.getHeartRateLogs(calendarStart.time, calenderEnd.time, 1)
    }

}
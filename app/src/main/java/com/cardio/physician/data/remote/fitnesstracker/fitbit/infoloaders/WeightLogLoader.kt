package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.WeightLogs
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SynTaskLoader
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services.WeightService
import com.cardio.physician.domain.fitness.model.WeightModel
import java.util.*

class WeightLogLoader(
    private val onSuccess: (List<WeightModel>) -> Unit,
    private val onFailure: (msg: String?) -> Unit,
    private val periodDays: Int
) : InfoLoaderSync<WeightLogs>( onFailure),
    SynTaskLoader.LoaderCallbacks<ResourceLoaderResult<WeightLogs>> {

    override fun onLoadFinished(
        data: ResourceLoaderResult<WeightLogs>?
    ) {
        super.onLoadFinished(data)
        data?.result?.let {
            return onSuccess.invoke(it.toModel())
        }
    }

    override fun onCreateSyncLoader(): ResourceLoaderSync<WeightLogs>? {
        val calenderEnd = Calendar.getInstance()
        val calendarStart = Calendar.getInstance()
        calendarStart.add(Calendar.DAY_OF_MONTH, -periodDays)
        return WeightService.getWeightLogLoader(calendarStart.time, calenderEnd.time, 1)
    }

}
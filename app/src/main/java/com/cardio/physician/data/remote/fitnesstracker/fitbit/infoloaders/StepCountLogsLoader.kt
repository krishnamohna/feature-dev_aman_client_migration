package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.StepCountEntity
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SynTaskLoader
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services.ActivityService
import com.cardio.physician.domain.fitness.model.StepCountModel
import java.util.*

class StepCountLogsLoader(
    private val onSuccess: (List<StepCountModel>) -> Unit,
    private val onFailure: (msg: String?) -> Unit,
    private val periodDays: Int
) : InfoLoaderSync<StepCountEntity>( onFailure),
    SynTaskLoader.LoaderCallbacks<ResourceLoaderResult<StepCountEntity>> {

    override fun onLoadFinished(
        data: ResourceLoaderResult<StepCountEntity>?
    ) {
        super.onLoadFinished(data)
        data?.result?.let {
            return onSuccess.invoke(it.toModelList())
        }
    }

    override fun onCreateSyncLoader(): ResourceLoaderSync<StepCountEntity>? {
        val calenderEnd = Calendar.getInstance()
        val calendarStart = Calendar.getInstance()
        calendarStart.add(Calendar.DAY_OF_MONTH, -periodDays)
        return ActivityService.getStepLogs(calendarStart.time, calenderEnd.time, 7)
    }

}
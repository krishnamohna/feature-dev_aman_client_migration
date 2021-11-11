package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SyncLoader

abstract class InfoLoaderSync<T>(
    private val onFailure: (msg: String?) -> Unit
) : com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SyncLoader.LoaderCallbacks<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<T>> {

    open fun load() {
        var loader = onCreateSyncLoader()
        loader?.initLoader(this)
    }

    abstract fun onCreateSyncLoader(): com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync<T>?

    override fun onLoadFinished(
        data: com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<T>?
    ) {
        when (data?.getResultType()) {
            com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult.ResultType.ERROR -> {
                onFailure.invoke(data?.errorMessage)
            }
            com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult.ResultType.EXCEPTION -> {
                onFailure.invoke(data?.errorMessage)
            }
        }
    }


}
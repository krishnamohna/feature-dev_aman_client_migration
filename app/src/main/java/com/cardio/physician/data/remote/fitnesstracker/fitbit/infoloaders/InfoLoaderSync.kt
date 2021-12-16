package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SynTaskLoader

abstract class InfoLoaderSync<T>(
    private val onFailure: (msg: String?) -> Unit
) : SynTaskLoader.LoaderCallbacks<ResourceLoaderResult<T>> {

    open fun load() {
        var loader = onCreateSyncLoader()
        loader?.initLoader(this)
    }

    abstract fun onCreateSyncLoader(): ResourceLoaderSync<T>?

    override fun onLoadFinished(
        data: ResourceLoaderResult<T>?
    ) {
        when (data?.getResultType()) {
            ResourceLoaderResult.ResultType.ERROR -> {
                onFailure.invoke(data?.errorMessage)
            }
            ResourceLoaderResult.ResultType.EXCEPTION -> {
                onFailure.invoke(data?.errorMessage)
            }
        }
    }


}
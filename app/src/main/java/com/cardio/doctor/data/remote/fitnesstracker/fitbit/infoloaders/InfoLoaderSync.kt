package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import android.app.Activity
import android.widget.Toast
import com.cardio.doctor.R
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.base.SyncLoader

abstract class InfoLoaderSync<T>(
    private val activity: Activity,
    private val onFailure: (msg: String?) -> Unit
) : SyncLoader.LoaderCallbacks<ResourceLoaderResult<T>> {

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
                Toast.makeText(activity, R.string.error_loading_data, Toast.LENGTH_LONG)
                    .show()
            }
            ResourceLoaderResult.ResultType.EXCEPTION -> {
                onFailure.invoke(data?.errorMessage)
                Toast.makeText(activity, R.string.error_loading_data, Toast.LENGTH_LONG).show()
            }
        }
    }


}
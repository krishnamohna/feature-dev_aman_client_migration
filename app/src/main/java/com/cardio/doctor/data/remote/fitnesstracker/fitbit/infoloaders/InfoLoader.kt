package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.cardio.doctor.R
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult

abstract class InfoLoader<T>(
    private val activity: Activity,
    private val onFailure: (msg: String?) -> Unit
) : LoaderManager.LoaderCallbacks<ResourceLoaderResult<T>> {

    fun load() {
        LoaderManager.getInstance(activity as AppCompatActivity).initLoader(getLoaderId(), null, this).forceLoad()
    }

    abstract fun getLoaderId(): Int

    override fun onLoadFinished(
        loader: Loader<ResourceLoaderResult<T>>,
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

    override fun onLoaderReset(loader: Loader<ResourceLoaderResult<T>>) {

    }


}
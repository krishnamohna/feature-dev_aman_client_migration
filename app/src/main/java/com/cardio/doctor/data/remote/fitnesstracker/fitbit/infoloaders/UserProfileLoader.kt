package com.cardio.doctor.data.remote.fitnesstracker.fitbit.infoloaders

import android.app.Activity
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.UserContainer
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.services.UserService
import com.cardio.doctor.domain.fitness.model.FitnessModel

class UserProfileLoader(
    private val activity: Activity,
    private val onSuccess: (FitnessModel) -> Unit,
    private val  onFailure: (msg:String?) -> Unit
) : InfoLoader<UserContainer>(activity, onFailure),
    LoaderManager.LoaderCallbacks<ResourceLoaderResult<UserContainer>> {

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<ResourceLoaderResult<UserContainer>> {
        return UserService.getLoggedInUserLoader(activity)
    }

    override fun onLoadFinished(
        loader: Loader<ResourceLoaderResult<UserContainer>>,
        data: ResourceLoaderResult<UserContainer>?
    ) {
        super.onLoadFinished(loader, data)
        data?.result?.user?.let {
           return onSuccess.invoke(it.toFinessModel())
        }
       // LoaderManager.getInstance(activity as ComponentActivity).destroyLoader(getLoaderId())
    }

    override fun getLoaderId(): Int {
        return 5
    }

}
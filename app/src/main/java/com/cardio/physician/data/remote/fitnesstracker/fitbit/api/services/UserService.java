package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services;

import android.app.Activity;

import androidx.loader.content.Loader;

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.exceptions.MissingScopesException;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.exceptions.TokenExpiredException;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderFactory;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.models.UserContainer;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.Scope;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderFactory;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.models.UserContainer;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.Scope;


/**
 * Created by jboggess on 9/14/16.
 */
public class UserService {

    private final static String USER_URL = "https://api.fitbit.com/1/user/-/profile.json";
    private static final ResourceLoaderFactory<UserContainer> USER_PROFILE_LOADER_FACTORY = new ResourceLoaderFactory<>(USER_URL, UserContainer.class);

    public static Loader<ResourceLoaderResult<UserContainer>> getLoggedInUserLoader(Activity activityContext) throws MissingScopesException, TokenExpiredException {
        return USER_PROFILE_LOADER_FACTORY.newResourceLoaderAsync(activityContext, new Scope[]{Scope.profile});
    }

}

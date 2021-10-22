package com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.services;

import android.app.Activity;

import androidx.loader.content.Loader;

import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.exceptions.MissingScopesException;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.exceptions.TokenExpiredException;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderFactory;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.heartrate.HeartRateEntity;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.Scope;


/**
 * Created by jboggess on 9/14/16.
 */
public class HeartRateService {

    private final static String HEART_RATE_URL = "https://api.fitbit.com/1/user/-/activities/heart/date/today/1d.json";
    private static final ResourceLoaderFactory<HeartRateEntity> HEART_RATE_RESOURCE_FACTORY = new ResourceLoaderFactory<>(HEART_RATE_URL, HeartRateEntity.class);

    public static Loader<ResourceLoaderResult<HeartRateEntity>> getHeartRate(Activity activityContext) throws MissingScopesException, TokenExpiredException {
        return HEART_RATE_RESOURCE_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.heartrate});
    }

}

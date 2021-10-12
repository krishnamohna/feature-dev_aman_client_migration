package com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.services;

import android.app.Activity;
import android.content.Loader;

import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.exceptions.MissingScopesException;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.exceptions.TokenExpiredException;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderFactory;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.Device;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.Scope;


/**
 * Created by jboggess on 9/14/16.
 */
public class DeviceService {

    private final static String DEVICE_URL = "https://api.fitbit.com/1/user/-/devices.json";
    private static final ResourceLoaderFactory<Device[]> USER_DEVICES_LOADER_FACTORY = new ResourceLoaderFactory<>(DEVICE_URL, Device[].class);

    public static Loader<ResourceLoaderResult<Device[]>> getUserDevicesLoader(Activity activityContext) throws MissingScopesException, TokenExpiredException {
        return USER_DEVICES_LOADER_FACTORY.newResourceLoader(activityContext, new Scope[]{Scope.settings});
    }

}

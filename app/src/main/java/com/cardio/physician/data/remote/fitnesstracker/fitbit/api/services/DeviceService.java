package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services;

import android.app.Activity;

import androidx.loader.content.Loader;

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.exceptions.MissingScopesException;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.exceptions.TokenExpiredException;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderFactory;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.Device;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.Scope;


/**
 * Created by jboggess on 9/14/16.
 */
public class DeviceService {

    private final static String DEVICE_URL = "https://api.fitbit.com/1/user/-/devices.json";
    private static final ResourceLoaderFactory<Device[]> USER_DEVICES_LOADER_FACTORY = new ResourceLoaderFactory<>(DEVICE_URL, Device[].class);

    public static Loader<ResourceLoaderResult<Device[]>> getUserDevicesLoader(Activity activityContext) throws MissingScopesException, TokenExpiredException {
        return USER_DEVICES_LOADER_FACTORY.newResourceLoaderAsync(activityContext, new Scope[]{Scope.settings});
    }

}

package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication.Scope;


/**
 * Created by jboggess on 9/19/16.
 */
public class ResourceLoaderFactory<T> {

    private String urlFormat;
    private Class<T> classType;

    public ResourceLoaderFactory(String urlFormat, Class<T> classType) {
        this.urlFormat = urlFormat;
        this.classType = classType;
    }

    public ResourceLoader<T> newResourceLoaderAsync(Activity contextActivity, Scope[] requiredScopes, String... pathParams) {
        String url = urlFormat;
        if (pathParams != null && pathParams.length > 0) {
            url = String.format(urlFormat, pathParams);
        }
        return new ResourceLoader<T>(contextActivity, url, requiredScopes, new Handler(), classType);
    }

    public ResourceLoaderSync<T> newResourceLoaderSync( Scope[] requiredScopes, String... pathParams) {
        String url = urlFormat;
        if (pathParams != null && pathParams.length > 0) {
            url = String.format(urlFormat, pathParams);
        }
        return new ResourceLoaderSync<T>( url, requiredScopes, new Handler(Looper.getMainLooper()), classType);
    }
}

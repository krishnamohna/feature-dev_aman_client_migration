package com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders;

import android.os.Handler;

import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.APIUtils;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.base.SyncLoader;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager;
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.Scope;
import com.cardio.doctor.network.basichttp.BasicHttpRequest;
import com.cardio.doctor.network.basichttp.BasicHttpResponse;
import com.google.gson.Gson;

import java.util.Locale;

/**
 * Created by jboggess on 9/19/16.
 */
public class ResourceLoaderSync<T> extends SyncLoader<ResourceLoaderResult<T>> {

    private final static String EOL = System.getProperty("line.separator");

    private final String url;
    private final Class<T> classType;
    private final Handler handler;
    private final Scope[] requiredScopes;

    public ResourceLoaderSync(String url, Scope[] requiredScopes, Handler handler, Class<T> classType) {
        super();
        this.url = url;
        this.classType = classType;
        this.handler = handler;
        this.requiredScopes = requiredScopes;
    }

    @Override
    public ResourceLoaderResult<T> loadInBackground() {
        try {
            APIUtils.validateToken(null, AuthenticationManager.getCurrentAccessToken(), requiredScopes);
            BasicHttpRequest request = AuthenticationManager
                    .createSignedRequest()
                    .setContentType("Application/json")
                    .setUrl(url)
                    .build();

            final BasicHttpResponse response = request.execute();
            int responseCode = response.getStatusCode();
            final String json = response.getBodyAsString();
            if (response.isSuccessful()) {
                final T resource = new Gson().fromJson(json, classType);
                return ResourceLoaderResult.onSuccess(resource);
            } else {
                if (responseCode == 401) {
                    if (AuthenticationManager.getAuthenticationConfiguration().isLogoutOnAuthFailure()) {
                        // Token may have been revoked or expired
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                AuthenticationManager.logout(null);
                            }
                        });
                    }
                    return ResourceLoaderResult.onLoggedOut();
                } else {
                    String errorMessage =
                            String.format(Locale.ENGLISH,
                                    "Error making request:%s\tHTTP Code:%d%s\tResponse Body:%s%s%s\n",
                                    EOL,
                                    responseCode,
                                    EOL,
                                    EOL,
                                    json,
                                    EOL);
                    return ResourceLoaderResult.onError(errorMessage);
                }
            }
        } catch (Exception e) {
            return ResourceLoaderResult.onException(e);
        }

    }

}

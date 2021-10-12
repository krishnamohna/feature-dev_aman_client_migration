package com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication;

/**
 * Created by jboggess on 9/14/16.
 */
public interface UrlChangeHandler {
    void onUrlChanged(String newUrl);
    void onLoadError(int errorCode, CharSequence description);
}

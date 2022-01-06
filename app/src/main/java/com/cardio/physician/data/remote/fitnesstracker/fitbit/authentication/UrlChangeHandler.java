package com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication;

/**
 * Created by jboggess on 9/14/16.
 */
public interface UrlChangeHandler {
    void onUrlChanged(String newUrl);
    void onLoadError(int errorCode, CharSequence description);
}

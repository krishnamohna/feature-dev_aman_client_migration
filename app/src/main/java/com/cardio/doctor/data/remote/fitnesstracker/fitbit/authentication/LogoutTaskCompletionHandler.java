package com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication;

/**
 * Created by jboggess on 9/19/16.
 */
public interface LogoutTaskCompletionHandler {
    void logoutSuccess();

    void logoutError(String message);
}
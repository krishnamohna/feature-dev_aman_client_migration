package com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.loaders.base;

import androidx.annotation.MainThread;

public abstract class SyncLoader<T> {

    private LoaderCallbacks<T> callbacks;

    public abstract T loadInBackground();

    public void initLoader(LoaderCallbacks<T> callbacks) {
        this.callbacks = callbacks;
        callbacks.onLoadFinished(loadInBackground());
    }

    public interface LoaderCallbacks<D> {
        @MainThread
        void onLoadFinished(D data);
    }
}

package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class SyncLoader<T> {

    private final ExecutorService executerService;
    private final Handler handlerUi;
    private final int POOL_SIZE = 2;
    private LoaderCallbacks<T> callbacks;

    public abstract T loadInBackground();

   protected SyncLoader() {
        executerService = Executors.newFixedThreadPool(POOL_SIZE);
        handlerUi=new Handler(Looper.getMainLooper());
    }

    public void initLoader(LoaderCallbacks<T> callbacks) {
        this.callbacks = callbacks;
        T response = loadInBackground();
        callbacks.onLoadFinished(response);
      /*  executerService.execute(new Runnable() {
            @Override
            public void run() {
                T response = loadInBackground();
                handlerUi.post(new Runnable() {
                    @Override
                    public void run() {
                        callbacks.onLoadFinished(response);
                    }
                });
            }
        });*/
    }

    public interface LoaderCallbacks<D> {
        @MainThread
        void onLoadFinished(D data);
    }
}

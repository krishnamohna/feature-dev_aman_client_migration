// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.data.remote.fitnesstracker.fitbit;

import android.os.Handler;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FitbitManager_Factory implements Factory<FitbitManager> {
  private final Provider<ExecutorService> executerServiceProvider;

  private final Provider<Handler> handlerProvider;

  public FitbitManager_Factory(Provider<ExecutorService> executerServiceProvider,
      Provider<Handler> handlerProvider) {
    this.executerServiceProvider = executerServiceProvider;
    this.handlerProvider = handlerProvider;
  }

  @Override
  public FitbitManager get() {
    return newInstance(executerServiceProvider.get(), handlerProvider.get());
  }

  public static FitbitManager_Factory create(Provider<ExecutorService> executerServiceProvider,
      Provider<Handler> handlerProvider) {
    return new FitbitManager_Factory(executerServiceProvider, handlerProvider);
  }

  public static FitbitManager newInstance(ExecutorService executerService, Handler handler) {
    return new FitbitManager(executerService, handler);
  }
}

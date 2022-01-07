// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_ProvideExecuterServiceFactory implements Factory<ExecutorService> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideExecuterServiceFactory(ApplicationModule module) {
    this.module = module;
  }

  @Override
  public ExecutorService get() {
    return provideExecuterService(module);
  }

  public static ApplicationModule_ProvideExecuterServiceFactory create(ApplicationModule module) {
    return new ApplicationModule_ProvideExecuterServiceFactory(module);
  }

  public static ExecutorService provideExecuterService(ApplicationModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideExecuterService());
  }
}
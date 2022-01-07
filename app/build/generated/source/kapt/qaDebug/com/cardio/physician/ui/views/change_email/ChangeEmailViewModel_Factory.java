// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.ui.views.change_email;

import android.app.Application;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.domain.common.repository.UserAuthRepositary;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ChangeEmailViewModel_Factory implements Factory<ChangeEmailViewModel> {
  private final Provider<UserManager> userManagerProvider;

  private final Provider<UserAuthRepositary> userAuthRepoProvider;

  private final Provider<Application> applicationProvider;

  public ChangeEmailViewModel_Factory(Provider<UserManager> userManagerProvider,
      Provider<UserAuthRepositary> userAuthRepoProvider,
      Provider<Application> applicationProvider) {
    this.userManagerProvider = userManagerProvider;
    this.userAuthRepoProvider = userAuthRepoProvider;
    this.applicationProvider = applicationProvider;
  }

  @Override
  public ChangeEmailViewModel get() {
    return newInstance(userManagerProvider.get(), userAuthRepoProvider.get(), applicationProvider.get());
  }

  public static ChangeEmailViewModel_Factory create(Provider<UserManager> userManagerProvider,
      Provider<UserAuthRepositary> userAuthRepoProvider,
      Provider<Application> applicationProvider) {
    return new ChangeEmailViewModel_Factory(userManagerProvider, userAuthRepoProvider, applicationProvider);
  }

  public static ChangeEmailViewModel newInstance(UserManager userManager,
      UserAuthRepositary userAuthRepo, Application application) {
    return new ChangeEmailViewModel(userManager, userAuthRepo, application);
  }
}
// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.ui.views.profile.editprofile;

import android.app.Application;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.network.NetworkHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class EditUserProfileViewModel_Factory implements Factory<EditUserProfileViewModel> {
  private final Provider<UserManager> userManagerProvider;

  private final Provider<UserProfileRepository> repositoryProvider;

  private final Provider<Application> applicationProvider;

  private final Provider<NetworkHelper> networkHelperProvider;

  public EditUserProfileViewModel_Factory(Provider<UserManager> userManagerProvider,
      Provider<UserProfileRepository> repositoryProvider, Provider<Application> applicationProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    this.userManagerProvider = userManagerProvider;
    this.repositoryProvider = repositoryProvider;
    this.applicationProvider = applicationProvider;
    this.networkHelperProvider = networkHelperProvider;
  }

  @Override
  public EditUserProfileViewModel get() {
    return newInstance(userManagerProvider.get(), repositoryProvider.get(), applicationProvider.get(), networkHelperProvider.get());
  }

  public static EditUserProfileViewModel_Factory create(Provider<UserManager> userManagerProvider,
      Provider<UserProfileRepository> repositoryProvider, Provider<Application> applicationProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    return new EditUserProfileViewModel_Factory(userManagerProvider, repositoryProvider, applicationProvider, networkHelperProvider);
  }

  public static EditUserProfileViewModel newInstance(UserManager userManager,
      UserProfileRepository repository, Application application, NetworkHelper networkHelper) {
    return new EditUserProfileViewModel(userManager, repository, application, networkHelper);
  }
}

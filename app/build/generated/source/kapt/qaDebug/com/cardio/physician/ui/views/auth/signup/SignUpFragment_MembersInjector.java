// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.ui.views.auth.signup;

import com.cardio.physician.network.NetworkHelper;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SignUpFragment_MembersInjector implements MembersInjector<SignUpFragment> {
  private final Provider<NetworkHelper> networkHelperProvider;

  public SignUpFragment_MembersInjector(Provider<NetworkHelper> networkHelperProvider) {
    this.networkHelperProvider = networkHelperProvider;
  }

  public static MembersInjector<SignUpFragment> create(
      Provider<NetworkHelper> networkHelperProvider) {
    return new SignUpFragment_MembersInjector(networkHelperProvider);
  }

  @Override
  public void injectMembers(SignUpFragment instance) {
    injectNetworkHelper(instance, networkHelperProvider.get());
  }

  @InjectedFieldSignature("com.cardio.physician.ui.views.auth.signup.SignUpFragment.networkHelper")
  public static void injectNetworkHelper(SignUpFragment instance, NetworkHelper networkHelper) {
    instance.networkHelper = networkHelper;
  }
}

// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.ui.common.base.activity;

import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.fcm.FcmManager;
import com.cardio.physician.domain.fitness.FitnessRepositary;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Named;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BaseActivity_MembersInjector implements MembersInjector<BaseActivity> {
  private final Provider<FcmManager> fcmManagerProvider;

  private final Provider<UserManager> userManagerProvider;

  private final Provider<FitnessRepositary> fitbitProvider;

  public BaseActivity_MembersInjector(Provider<FcmManager> fcmManagerProvider,
      Provider<UserManager> userManagerProvider, Provider<FitnessRepositary> fitbitProvider) {
    this.fcmManagerProvider = fcmManagerProvider;
    this.userManagerProvider = userManagerProvider;
    this.fitbitProvider = fitbitProvider;
  }

  public static MembersInjector<BaseActivity> create(Provider<FcmManager> fcmManagerProvider,
      Provider<UserManager> userManagerProvider, Provider<FitnessRepositary> fitbitProvider) {
    return new BaseActivity_MembersInjector(fcmManagerProvider, userManagerProvider, fitbitProvider);
  }

  @Override
  public void injectMembers(BaseActivity instance) {
    injectFcmManager(instance, fcmManagerProvider.get());
    injectUserManager(instance, userManagerProvider.get());
    injectFitbit(instance, fitbitProvider.get());
  }

  @InjectedFieldSignature("com.cardio.physician.ui.common.base.activity.BaseActivity.fcmManager")
  public static void injectFcmManager(BaseActivity instance, FcmManager fcmManager) {
    instance.fcmManager = fcmManager;
  }

  @InjectedFieldSignature("com.cardio.physician.ui.common.base.activity.BaseActivity.userManager")
  public static void injectUserManager(BaseActivity instance, UserManager userManager) {
    instance.userManager = userManager;
  }

  @InjectedFieldSignature("com.cardio.physician.ui.common.base.activity.BaseActivity.fitbit")
  @Named("FITBIT")
  public static void injectFitbit(BaseActivity instance, FitnessRepositary fitbit) {
    instance.fitbit = fitbit;
  }
}
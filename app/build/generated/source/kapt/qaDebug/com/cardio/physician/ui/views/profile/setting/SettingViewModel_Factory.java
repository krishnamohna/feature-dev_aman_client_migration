// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.ui.views.profile.setting;

import com.cardio.physician.domain.fitness.FitnessRepositary;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SettingViewModel_Factory implements Factory<SettingViewModel> {
  private final Provider<FitnessRepositary> fitbitProvider;

  public SettingViewModel_Factory(Provider<FitnessRepositary> fitbitProvider) {
    this.fitbitProvider = fitbitProvider;
  }

  @Override
  public SettingViewModel get() {
    return newInstance(fitbitProvider.get());
  }

  public static SettingViewModel_Factory create(Provider<FitnessRepositary> fitbitProvider) {
    return new SettingViewModel_Factory(fitbitProvider);
  }

  public static SettingViewModel newInstance(FitnessRepositary fitbit) {
    return new SettingViewModel(fitbit);
  }
}
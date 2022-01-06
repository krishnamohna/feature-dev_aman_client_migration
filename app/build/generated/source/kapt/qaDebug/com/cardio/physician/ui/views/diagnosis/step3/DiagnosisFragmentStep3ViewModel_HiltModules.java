package com.cardio.physician.ui.views.diagnosis.step3;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap;
import dagger.hilt.codegen.OriginatingElement;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import java.lang.String;

@OriginatingElement(
    topLevelClass = DiagnosisFragmentStep3ViewModel.class
)
public final class DiagnosisFragmentStep3ViewModel_HiltModules {
  private DiagnosisFragmentStep3ViewModel_HiltModules() {
  }

  @Module
  @InstallIn(ViewModelComponent.class)
  public abstract static class BindsModule {
    private BindsModule() {
    }

    @Binds
    @IntoMap
    @StringKey("com.cardio.physician.ui.views.diagnosis.step3.DiagnosisFragmentStep3ViewModel")
    @HiltViewModelMap
    public abstract ViewModel binds(DiagnosisFragmentStep3ViewModel vm);
  }

  @Module
  @InstallIn(ActivityRetainedComponent.class)
  public static final class KeyModule {
    private KeyModule() {
    }

    @Provides
    @IntoSet
    @HiltViewModelMap.KeySet
    public static String provide() {
      return "com.cardio.physician.ui.views.diagnosis.step3.DiagnosisFragmentStep3ViewModel";
    }
  }
}

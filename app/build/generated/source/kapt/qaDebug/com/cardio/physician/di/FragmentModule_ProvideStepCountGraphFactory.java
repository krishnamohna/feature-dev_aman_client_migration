// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.di;

import com.cardio.physician.ui.views.dashboard.common.graph.StepCountMpGraphImp;
import com.cardio.physician.ui.views.dashboard.common.graph.base.StepCountGraph;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FragmentModule_ProvideStepCountGraphFactory implements Factory<StepCountGraph> {
  private final FragmentModule module;

  private final Provider<StepCountMpGraphImp> stepCountMpGraphImpProvider;

  public FragmentModule_ProvideStepCountGraphFactory(FragmentModule module,
      Provider<StepCountMpGraphImp> stepCountMpGraphImpProvider) {
    this.module = module;
    this.stepCountMpGraphImpProvider = stepCountMpGraphImpProvider;
  }

  @Override
  public StepCountGraph get() {
    return provideStepCountGraph(module, stepCountMpGraphImpProvider.get());
  }

  public static FragmentModule_ProvideStepCountGraphFactory create(FragmentModule module,
      Provider<StepCountMpGraphImp> stepCountMpGraphImpProvider) {
    return new FragmentModule_ProvideStepCountGraphFactory(module, stepCountMpGraphImpProvider);
  }

  public static StepCountGraph provideStepCountGraph(FragmentModule instance,
      StepCountMpGraphImp stepCountMpGraphImp) {
    return Preconditions.checkNotNullFromProvides(instance.provideStepCountGraph(stepCountMpGraphImp));
  }
}

// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.di;

import com.cardio.physician.data.remote.synchealth.SyncHealthRepositoryImp;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RepositaryModule_ProvideSyncHealthRepoFactory implements Factory<SyncHealthRepositary> {
  private final RepositaryModule module;

  private final Provider<SyncHealthRepositoryImp> respositaryImpProvider;

  public RepositaryModule_ProvideSyncHealthRepoFactory(RepositaryModule module,
      Provider<SyncHealthRepositoryImp> respositaryImpProvider) {
    this.module = module;
    this.respositaryImpProvider = respositaryImpProvider;
  }

  @Override
  public SyncHealthRepositary get() {
    return provideSyncHealthRepo(module, respositaryImpProvider.get());
  }

  public static RepositaryModule_ProvideSyncHealthRepoFactory create(RepositaryModule module,
      Provider<SyncHealthRepositoryImp> respositaryImpProvider) {
    return new RepositaryModule_ProvideSyncHealthRepoFactory(module, respositaryImpProvider);
  }

  public static SyncHealthRepositary provideSyncHealthRepo(RepositaryModule instance,
      SyncHealthRepositoryImp respositaryImp) {
    return Preconditions.checkNotNullFromProvides(instance.provideSyncHealthRepo(respositaryImp));
  }
}

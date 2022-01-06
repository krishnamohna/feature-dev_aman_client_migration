// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.ui.views.dashboard.fragment;

import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<DiagnosisRepo> diagnosisRepoProvider;

  private final Provider<UserProfileRepository> userProfileRepositoryProvider;

  private final Provider<SyncHealthRepositary> syncRepositaryProvider;

  public DashboardViewModel_Factory(Provider<DiagnosisRepo> diagnosisRepoProvider,
      Provider<UserProfileRepository> userProfileRepositoryProvider,
      Provider<SyncHealthRepositary> syncRepositaryProvider) {
    this.diagnosisRepoProvider = diagnosisRepoProvider;
    this.userProfileRepositoryProvider = userProfileRepositoryProvider;
    this.syncRepositaryProvider = syncRepositaryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(diagnosisRepoProvider.get(), userProfileRepositoryProvider.get(), syncRepositaryProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<DiagnosisRepo> diagnosisRepoProvider,
      Provider<UserProfileRepository> userProfileRepositoryProvider,
      Provider<SyncHealthRepositary> syncRepositaryProvider) {
    return new DashboardViewModel_Factory(diagnosisRepoProvider, userProfileRepositoryProvider, syncRepositaryProvider);
  }

  public static DashboardViewModel newInstance(DiagnosisRepo diagnosisRepo,
      UserProfileRepository userProfileRepository, SyncHealthRepositary syncRepositary) {
    return new DashboardViewModel(diagnosisRepo, userProfileRepository, syncRepositary);
  }
}

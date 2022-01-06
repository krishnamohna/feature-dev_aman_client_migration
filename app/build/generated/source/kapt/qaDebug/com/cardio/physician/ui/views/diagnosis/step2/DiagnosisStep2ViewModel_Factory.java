// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.ui.views.diagnosis.step2;

import com.cardio.physician.domain.connection.ConnectionRepo;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.notifications.NotificationRepo;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DiagnosisStep2ViewModel_Factory implements Factory<DiagnosisStep2ViewModel> {
  private final Provider<DiagnosisRepo> diagnosisRepoProvider;

  private final Provider<ConnectionRepo> connectionRepoProvider;

  private final Provider<NotificationRepo> notificationRepoProvider;

  public DiagnosisStep2ViewModel_Factory(Provider<DiagnosisRepo> diagnosisRepoProvider,
      Provider<ConnectionRepo> connectionRepoProvider,
      Provider<NotificationRepo> notificationRepoProvider) {
    this.diagnosisRepoProvider = diagnosisRepoProvider;
    this.connectionRepoProvider = connectionRepoProvider;
    this.notificationRepoProvider = notificationRepoProvider;
  }

  @Override
  public DiagnosisStep2ViewModel get() {
    return newInstance(diagnosisRepoProvider.get(), connectionRepoProvider.get(), notificationRepoProvider.get());
  }

  public static DiagnosisStep2ViewModel_Factory create(
      Provider<DiagnosisRepo> diagnosisRepoProvider,
      Provider<ConnectionRepo> connectionRepoProvider,
      Provider<NotificationRepo> notificationRepoProvider) {
    return new DiagnosisStep2ViewModel_Factory(diagnosisRepoProvider, connectionRepoProvider, notificationRepoProvider);
  }

  public static DiagnosisStep2ViewModel newInstance(DiagnosisRepo diagnosisRepo,
      ConnectionRepo connectionRepo, NotificationRepo notificationRepo) {
    return new DiagnosisStep2ViewModel(diagnosisRepo, connectionRepo, notificationRepo);
  }
}

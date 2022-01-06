// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.data.remote.diagnosis.repositary;

import com.cardio.physician.data.remote.fcm.FcmManager;
import com.cardio.physician.network.api.ApiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DiagnosisRepoImp_Factory implements Factory<DiagnosisRepoImp> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<FirebaseFirestore> fireStoreDbProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  private final Provider<FcmManager> fcmManagerProvider;

  public DiagnosisRepoImp_Factory(Provider<ApiService> apiServiceProvider,
      Provider<FirebaseFirestore> fireStoreDbProvider, Provider<FirebaseAuth> firebaseAuthProvider,
      Provider<FcmManager> fcmManagerProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.fireStoreDbProvider = fireStoreDbProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
    this.fcmManagerProvider = fcmManagerProvider;
  }

  @Override
  public DiagnosisRepoImp get() {
    return newInstance(apiServiceProvider.get(), fireStoreDbProvider.get(), firebaseAuthProvider.get(), fcmManagerProvider.get());
  }

  public static DiagnosisRepoImp_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<FirebaseFirestore> fireStoreDbProvider, Provider<FirebaseAuth> firebaseAuthProvider,
      Provider<FcmManager> fcmManagerProvider) {
    return new DiagnosisRepoImp_Factory(apiServiceProvider, fireStoreDbProvider, firebaseAuthProvider, fcmManagerProvider);
  }

  public static DiagnosisRepoImp newInstance(ApiService apiService, FirebaseFirestore fireStoreDb,
      FirebaseAuth firebaseAuth, FcmManager fcmManager) {
    return new DiagnosisRepoImp(apiService, fireStoreDb, firebaseAuth, fcmManager);
  }
}

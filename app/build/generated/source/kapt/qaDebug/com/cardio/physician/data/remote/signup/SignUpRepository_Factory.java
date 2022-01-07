// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.data.remote.signup;

import com.cardio.physician.network.api.ApiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SignUpRepository_Factory implements Factory<SignUpRepository> {
  private final Provider<FirebaseAuth> firebaseAuthProvider;

  private final Provider<FirebaseFirestore> fireStoreProvider;

  private final Provider<StorageReference> storageReferenceProvider;

  private final Provider<ApiService> apiServiceProvider;

  public SignUpRepository_Factory(Provider<FirebaseAuth> firebaseAuthProvider,
      Provider<FirebaseFirestore> fireStoreProvider,
      Provider<StorageReference> storageReferenceProvider,
      Provider<ApiService> apiServiceProvider) {
    this.firebaseAuthProvider = firebaseAuthProvider;
    this.fireStoreProvider = fireStoreProvider;
    this.storageReferenceProvider = storageReferenceProvider;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public SignUpRepository get() {
    return newInstance(firebaseAuthProvider.get(), fireStoreProvider.get(), storageReferenceProvider.get(), apiServiceProvider.get());
  }

  public static SignUpRepository_Factory create(Provider<FirebaseAuth> firebaseAuthProvider,
      Provider<FirebaseFirestore> fireStoreProvider,
      Provider<StorageReference> storageReferenceProvider,
      Provider<ApiService> apiServiceProvider) {
    return new SignUpRepository_Factory(firebaseAuthProvider, fireStoreProvider, storageReferenceProvider, apiServiceProvider);
  }

  public static SignUpRepository newInstance(FirebaseAuth firebaseAuth, FirebaseFirestore fireStore,
      StorageReference storageReference, ApiService apiService) {
    return new SignUpRepository(firebaseAuth, fireStore, storageReference, apiService);
  }
}
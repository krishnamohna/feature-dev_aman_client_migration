// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.data.remote.profile;

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
public final class UserProfileRepository_Factory implements Factory<UserProfileRepository> {
  private final Provider<FirebaseAuth> firebaseAuthProvider;

  private final Provider<FirebaseFirestore> fireStoreProvider;

  private final Provider<StorageReference> storageReferenceProvider;

  private final Provider<ApiService> apiServiceProvider;

  public UserProfileRepository_Factory(Provider<FirebaseAuth> firebaseAuthProvider,
      Provider<FirebaseFirestore> fireStoreProvider,
      Provider<StorageReference> storageReferenceProvider,
      Provider<ApiService> apiServiceProvider) {
    this.firebaseAuthProvider = firebaseAuthProvider;
    this.fireStoreProvider = fireStoreProvider;
    this.storageReferenceProvider = storageReferenceProvider;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public UserProfileRepository get() {
    return newInstance(firebaseAuthProvider.get(), fireStoreProvider.get(), storageReferenceProvider.get(), apiServiceProvider.get());
  }

  public static UserProfileRepository_Factory create(Provider<FirebaseAuth> firebaseAuthProvider,
      Provider<FirebaseFirestore> fireStoreProvider,
      Provider<StorageReference> storageReferenceProvider,
      Provider<ApiService> apiServiceProvider) {
    return new UserProfileRepository_Factory(firebaseAuthProvider, fireStoreProvider, storageReferenceProvider, apiServiceProvider);
  }

  public static UserProfileRepository newInstance(FirebaseAuth firebaseAuth,
      FirebaseFirestore fireStore, StorageReference storageReference, ApiService apiService) {
    return new UserProfileRepository(firebaseAuth, fireStore, storageReference, apiService);
  }
}

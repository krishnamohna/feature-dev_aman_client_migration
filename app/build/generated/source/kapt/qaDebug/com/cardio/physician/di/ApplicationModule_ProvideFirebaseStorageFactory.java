// Generated by Dagger (https://dagger.dev).
package com.cardio.physician.di;

import com.google.firebase.storage.FirebaseStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_ProvideFirebaseStorageFactory implements Factory<FirebaseStorage> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideFirebaseStorageFactory(ApplicationModule module) {
    this.module = module;
  }

  @Override
  public FirebaseStorage get() {
    return provideFirebaseStorage(module);
  }

  public static ApplicationModule_ProvideFirebaseStorageFactory create(ApplicationModule module) {
    return new ApplicationModule_ProvideFirebaseStorageFactory(module);
  }

  public static FirebaseStorage provideFirebaseStorage(ApplicationModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideFirebaseStorage());
  }
}

package com.cardio.physician.ui.views.profile.setting;

import com.cardio.physician.domain.common.repository.BaseRepository;
import com.cardio.physician.network.api.ApiService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/cardio/physician/ui/views/profile/setting/SettingRepository;", "Lcom/cardio/physician/domain/common/repository/BaseRepository;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "storageReference", "Lcom/google/firebase/storage/StorageReference;", "apiService", "Lcom/cardio/physician/network/api/ApiService;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/storage/StorageReference;Lcom/cardio/physician/network/api/ApiService;)V", "app_qaDebug"})
public final class SettingRepository extends com.cardio.physician.domain.common.repository.BaseRepository {
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    private final com.google.firebase.storage.StorageReference storageReference = null;
    
    @javax.inject.Inject()
    public SettingRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.StorageReference storageReference, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.api.ApiService apiService) {
        super(null, null, null, null);
    }
}
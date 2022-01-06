package com.cardio.physician.data.remote.changepassword;

import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.domain.common.repository.BaseRepository;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.ApiService;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ;\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0016\u0010\u0011\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0014j\u0002`\u00150\u00130\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J;\u0010\u0017\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00102\u0016\u0010\u0011\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0014j\u0002`\u00150\u00130\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/cardio/physician/data/remote/changepassword/ChangePasswordRepository;", "Lcom/cardio/physician/domain/common/repository/BaseRepository;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "storageReference", "Lcom/google/firebase/storage/StorageReference;", "apiService", "Lcom/cardio/physician/network/api/ApiService;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/storage/StorageReference;Lcom/cardio/physician/network/api/ApiService;)V", "reAuthenticateUser", "", "firebaseUser", "Lcom/google/firebase/auth/FirebaseUser;", "oldPassword", "", "errorLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Lcom/google/firebase/auth/FirebaseUser;Ljava/lang/String;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassword", "newPassword", "app_qaDebug"})
public final class ChangePasswordRepository extends com.cardio.physician.domain.common.repository.BaseRepository {
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    private final com.google.firebase.storage.StorageReference storageReference = null;
    
    @javax.inject.Inject()
    public ChangePasswordRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.StorageReference storageReference, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.api.ApiService apiService) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object reAuthenticateUser(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseUser firebaseUser, @org.jetbrains.annotations.NotNull()
    java.lang.String oldPassword, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePassword(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseUser firebaseUser, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p3) {
        return null;
    }
}
package com.cardio.physician.data.remote.profile;

import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.common.repository.BaseRepository;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.ApiService;
import com.cardio.physician.network.api.ApiStatus;
import com.cardio.physician.ui.common.utils.FireStoreCollection;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J3\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00102\u0016\u0010\u0015\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0018j\u0002`\u00190\u00170\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010J\r\u0010\u001c\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\u001fJ+\u0010 \u001a\u0004\u0018\u00010!2\u0016\u0010\u0015\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0018j\u0002`\u00190\u00170\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J[\u0010#\u001a\u0004\u0018\u00010\u00132\u0006\u0010$\u001a\u00020%2&\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010(0\'j\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010(`)2\u0016\u0010\u0015\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0018j\u0002`\u00190\u00170\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J#\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006."}, d2 = {"Lcom/cardio/physician/data/remote/profile/UserProfileRepository;", "Lcom/cardio/physician/domain/common/repository/BaseRepository;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "storageReference", "Lcom/google/firebase/storage/StorageReference;", "apiService", "Lcom/cardio/physician/network/api/ApiService;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/storage/StorageReference;Lcom/cardio/physician/network/api/ApiService;)V", "getFirebaseAuth", "()Lcom/google/firebase/auth/FirebaseAuth;", "fetchUserDetailByModel", "Lcom/cardio/physician/domain/common/model/UserModel;", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isEmailAlreadyExist", "", "email", "errorLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isEmailEdited", "isEmailVerified", "()Ljava/lang/Boolean;", "reloadAuthIfEmailNotVerified", "", "sendVerificationEmail", "Ljava/lang/Void;", "(Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "storeUserDataInFireStore", "firebaseUser", "Lcom/google/firebase/auth/FirebaseUser;", "hashMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "(Lcom/google/firebase/auth/FirebaseUser;Ljava/util/HashMap;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProfile", "userModel", "(Lcom/cardio/physician/domain/common/model/UserModel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class UserProfileRepository extends com.cardio.physician.domain.common.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    private final com.google.firebase.storage.StorageReference storageReference = null;
    
    @javax.inject.Inject()
    public UserProfileRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.StorageReference storageReference, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.api.ApiService apiService) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.firebase.auth.FirebaseAuth getFirebaseAuth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchUserDetailByModel(@org.jetbrains.annotations.Nullable()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.common.model.UserModel> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object storeUserDataInFireStore(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseUser firebaseUser, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isEmailVerified() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendVerificationEmail(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Void> p1) {
        return null;
    }
    
    public final boolean isEmailEdited(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isEmailAlreadyExist(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
    
    public final void reloadAuthIfEmailNotVerified() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateProfile(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.common.model.UserModel userModel, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
}
package com.cardio.physician.data.remote.phoneverification;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.domain.common.repository.BaseRepository;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.ApiService;
import com.cardio.physician.ui.common.utils.FireStoreCollection;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ5\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J3\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J3\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J=\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001f2\"\u0010 \u001a\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\"0!j\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\"`#H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lcom/cardio/physician/data/remote/phoneverification/PhoneVerificationRepository;", "Lcom/cardio/physician/domain/common/repository/BaseRepository;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "storageReference", "Lcom/google/firebase/storage/StorageReference;", "apiService", "Lcom/cardio/physician/network/api/ApiService;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/storage/StorageReference;Lcom/cardio/physician/network/api/ApiService;)V", "enableAccountLinking", "", "credential", "Lcom/google/firebase/auth/AuthCredential;", "errorLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Lcom/google/firebase/auth/AuthCredential;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendVerificationEmail", "Ljava/lang/Void;", "firebaseUser", "Lcom/google/firebase/auth/FirebaseUser;", "(Lcom/google/firebase/auth/FirebaseUser;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signInWithCredential", "Lcom/google/firebase/auth/AuthResult;", "authCredential", "storeUserDataInFireStore", "childName", "", "hashMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "(Ljava/lang/String;Ljava/util/HashMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class PhoneVerificationRepository extends com.cardio.physician.domain.common.repository.BaseRepository {
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    private final com.google.firebase.storage.StorageReference storageReference = null;
    
    @javax.inject.Inject()
    public PhoneVerificationRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.StorageReference storageReference, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.api.ApiService apiService) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object storeUserDataInFireStore(@org.jetbrains.annotations.NotNull()
    java.lang.String childName, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendVerificationEmail(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseUser firebaseUser, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Void> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object signInWithCredential(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.AuthCredential authCredential, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.google.firebase.auth.AuthResult> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object enableAccountLinking(@org.jetbrains.annotations.Nullable()
    com.google.firebase.auth.AuthCredential credential, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
}
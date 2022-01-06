package com.cardio.physician.data.remote.login;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.domain.login.LoginRepositary;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.ApiService;
import com.cardio.physician.ui.common.utils.FireStoreCollection;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.google.firebase.auth.*;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ+\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J3\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J3\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00162\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ3\u0010\u001e\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001f\u001a\u00020\u00162\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ3\u0010 \u001a\u0004\u0018\u00010\u001b2\u0006\u0010!\u001a\u00020\u00162\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ5\u0010\"\u001a\u0004\u0018\u00010\u001b2\b\b\u0001\u0010#\u001a\u00020\u00182\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J/\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u00162\u0014\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010(0\'H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lcom/cardio/physician/data/remote/login/LoginRepositoryImp;", "Lcom/cardio/physician/domain/login/LoginRepositary;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "storageReference", "Lcom/google/firebase/storage/StorageReference;", "apiService", "Lcom/cardio/physician/network/api/ApiService;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/storage/StorageReference;Lcom/cardio/physician/network/api/ApiService;)V", "getFirebaseAuth", "()Lcom/google/firebase/auth/FirebaseAuth;", "fetchUserDetail", "Lcom/google/firebase/firestore/DocumentSnapshot;", "errorLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "googleSignInWithCredential", "", "authCredential", "Lcom/google/firebase/auth/AuthCredential;", "(Lcom/google/firebase/auth/AuthCredential;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCollectionExist", "", "uuid", "(Ljava/lang/String;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isEmailExistForNormalSignUp", "email", "isPhoneNumberExist", "phoneNumber", "linkGoogleCredentialWithExistingAcc", "credential", "storeUserDataInFireStore", "childName", "hashMap", "Ljava/util/HashMap;", "", "(Ljava/lang/String;Ljava/util/HashMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class LoginRepositoryImp implements com.cardio.physician.domain.login.LoginRepositary {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    private final com.google.firebase.storage.StorageReference storageReference = null;
    
    @javax.inject.Inject()
    public LoginRepositoryImp(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.StorageReference storageReference, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.api.ApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.auth.FirebaseAuth getFirebaseAuth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object googleSignInWithCredential(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.AuthCredential authCredential, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object linkGoogleCredentialWithExistingAcc(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    com.google.firebase.auth.AuthCredential credential, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object isCollectionExist(@org.jetbrains.annotations.NotNull()
    java.lang.String uuid, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object isEmailExistForNormalSignUp(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object isPhoneNumberExist(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object storeUserDataInFireStore(@org.jetbrains.annotations.NotNull()
    java.lang.String childName, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object fetchUserDetail(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.google.firebase.firestore.DocumentSnapshot> p1) {
        return null;
    }
}
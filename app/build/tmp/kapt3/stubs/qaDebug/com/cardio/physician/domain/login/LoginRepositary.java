package com.cardio.physician.domain.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.network.Resource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.firestore.DocumentSnapshot;
import java.util.*;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ3\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0016\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ3\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0016\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J3\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u000b2\u0016\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J3\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u000b2\u0016\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J5\u0010\u0017\u001a\u0004\u0018\u00010\u00102\b\b\u0001\u0010\u0018\u001a\u00020\r2\u0016\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ/\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000b2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001cH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/cardio/physician/domain/login/LoginRepositary;", "", "fetchUserDetail", "Lcom/google/firebase/firestore/DocumentSnapshot;", "errorLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "googleSignInWithCredential", "", "authCredential", "Lcom/google/firebase/auth/AuthCredential;", "(Lcom/google/firebase/auth/AuthCredential;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCollectionExist", "", "uuid", "(Ljava/lang/String;Landroidx/lifecycle/MutableLiveData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isEmailExistForNormalSignUp", "email", "isPhoneNumberExist", "phoneNumber", "linkGoogleCredentialWithExistingAcc", "credential", "storeUserDataInFireStore", "childName", "hashMap", "Ljava/util/HashMap;", "(Ljava/lang/String;Ljava/util/HashMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public abstract interface LoginRepositary {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isPhoneNumberExist(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object googleSignInWithCredential(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.AuthCredential authCredential, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> p2);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isEmailExistForNormalSignUp(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object storeUserDataInFireStore(@org.jetbrains.annotations.NotNull()
    java.lang.String childName, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object linkGoogleCredentialWithExistingAcc(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    com.google.firebase.auth.AuthCredential credential, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isCollectionExist(@org.jetbrains.annotations.NotNull()
    java.lang.String uuid, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p2);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchUserDetail(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Exception>> errorLiveData, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.google.firebase.firestore.DocumentSnapshot> p1);
}
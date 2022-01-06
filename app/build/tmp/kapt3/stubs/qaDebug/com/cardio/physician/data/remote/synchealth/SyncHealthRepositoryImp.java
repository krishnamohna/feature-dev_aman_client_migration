package com.cardio.physician.data.remote.synchealth;

import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.network.NetworkError;
import com.cardio.physician.ui.common.utils.FireStoreCollection;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ)\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/cardio/physician/data/remote/synchealth/SyncHealthRepositoryImp;", "Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/auth/FirebaseAuth;)V", "getHealthLogByDate", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHealthLogs", "", "days", "", "userId", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastSavedHealthLogCollectionDate", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveHealthData", "", "fitnessModel", "(Lcom/cardio/physician/domain/fitness/model/FitnessModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateHealthLogByDate", "(Lcom/cardio/physician/domain/fitness/model/FitnessModel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class SyncHealthRepositoryImp implements com.cardio.physician.domain.synchealth.SyncHealthRepositary {
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    
    @javax.inject.Inject()
    public SyncHealthRepositoryImp(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getLastSavedHealthLogCollectionDate(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.fitness.model.FitnessModel> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveHealthData(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getHealthLogByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.fitness.model.FitnessModel> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object updateHealthLogByDate(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getHealthLogs(long days, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.fitness.model.FitnessModel>> p2) {
        return null;
    }
}
package com.cardio.physician.data.remote.connections;

import com.cardio.physician.domain.connection.ConnectionModel;
import com.cardio.physician.domain.connection.ConnectionRepo;
import com.cardio.physician.network.NetworkError;
import com.cardio.physician.ui.common.utils.Constants;
import com.cardio.physician.ui.common.utils.FireStoreCollection;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/cardio/physician/data/remote/connections/ConnectionRepoImp;", "Lcom/cardio/physician/domain/connection/ConnectionRepo;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/auth/FirebaseAuth;)V", "getMyConnections", "", "Lcom/cardio/physician/domain/connection/ConnectionModel;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class ConnectionRepoImp implements com.cardio.physician.domain.connection.ConnectionRepo {
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    
    @javax.inject.Inject()
    public ConnectionRepoImp(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getMyConnections(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.connection.ConnectionModel>> p0) {
        return null;
    }
}
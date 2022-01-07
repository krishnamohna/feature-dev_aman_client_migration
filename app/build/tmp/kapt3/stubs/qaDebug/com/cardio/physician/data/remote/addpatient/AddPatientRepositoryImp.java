package com.cardio.physician.data.remote.addpatient;

import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.fcm.FcmManager;
import com.cardio.physician.domain.common.repository.BaseRepository;
import com.cardio.physician.network.api.ApiService;
import com.cardio.physician.domain.addpatient.PatientModel;
import com.cardio.physician.domain.connection.ConnectionModel;
import com.cardio.physician.ui.common.utils.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.StorageReference;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\n\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJc\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\"\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016j\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018`\u00192\"\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016j\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018`\u0019H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ#\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00172\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00140 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0002J\u001f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00140 2\u0006\u0010%\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u001f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00140 2\u0006\u0010(\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u001f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00140 2\u0006\u0010%\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lcom/cardio/physician/data/remote/addpatient/AddPatientRepositoryImp;", "Lcom/cardio/physician/domain/common/repository/BaseRepository;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "fireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "storageReference", "Lcom/google/firebase/storage/StorageReference;", "fcmManager", "Lcom/cardio/physician/data/remote/fcm/FcmManager;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "apiService", "Lcom/cardio/physician/network/api/ApiService;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/storage/StorageReference;Lcom/cardio/physician/data/remote/fcm/FcmManager;Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/network/api/ApiService;)V", "getFirebaseAuth", "()Lcom/google/firebase/auth/FirebaseAuth;", "addDataToFirestore", "", "patientId", "Lcom/cardio/physician/domain/addpatient/PatientModel;", "hashMap", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "hashMap1", "(Lcom/cardio/physician/domain/addpatient/PatientModel;Ljava/util/HashMap;Ljava/util/HashMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addNotificationsToConnections", "notificationType", "(Ljava/lang/String;Lcom/cardio/physician/domain/addpatient/PatientModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPatientList", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConnectedPatientList", "getNotificationMsg", "getPatientList", "searchString", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPatientListByDate", "date", "getPatientListWithEmail", "app_qaDebug"})
public final class AddPatientRepositoryImp extends com.cardio.physician.domain.common.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final com.google.firebase.firestore.FirebaseFirestore fireStore = null;
    private final com.google.firebase.storage.StorageReference storageReference = null;
    private final com.cardio.physician.data.remote.fcm.FcmManager fcmManager = null;
    private final com.cardio.physician.data.local.UserManager userManager = null;
    
    @javax.inject.Inject()
    public AddPatientRepositoryImp(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStore, @org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.StorageReference storageReference, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.fcm.FcmManager fcmManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.api.ApiService apiService) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.firebase.auth.FirebaseAuth getFirebaseAuth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPatientList(@org.jetbrains.annotations.NotNull()
    java.lang.String searchString, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.addpatient.PatientModel>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getConnectedPatientList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.addpatient.PatientModel>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllPatientList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.addpatient.PatientModel>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPatientListByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.addpatient.PatientModel>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPatientListWithEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String searchString, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.addpatient.PatientModel>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addDataToFirestore(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.domain.addpatient.PatientModel patientId, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.Object> hashMap1, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addNotificationsToConnections(@org.jetbrains.annotations.NotNull()
    java.lang.String notificationType, @org.jetbrains.annotations.Nullable()
    com.cardio.physician.domain.addpatient.PatientModel patientId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final java.lang.String getNotificationMsg(java.lang.String notificationType) {
        return null;
    }
}
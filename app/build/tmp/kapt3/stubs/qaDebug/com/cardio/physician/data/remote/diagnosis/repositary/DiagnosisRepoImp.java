package com.cardio.physician.data.remote.diagnosis.repositary;

import android.content.Context;
import com.cardio.physician.data.remote.diagnosis.entity.medicines.MedicineFireStoreEntity;
import com.cardio.physician.data.remote.fcm.FcmManager;
import com.cardio.physician.domain.common.model.BaseModel;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.connection.ConnectionModel;
import com.cardio.physician.domain.diagnosis.DiagnosisModel;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.diagnosis.MedicineModel;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.network.NetworkError;
import com.cardio.physician.network.api.ApiService;
import com.cardio.physician.ui.common.utils.FireStoreCollection;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;
import com.cardio.physician.ui.common.utils.UserType;
import com.cardio.physician.ui.common.utils.extentions.*;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\'\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J1\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u001e\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010#0\"2\u0006\u0010$\u001a\u00020\u001cH\u0002J\u001a\u0010%\u001a\u0004\u0018\u00010\u00162\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u0016H\u0002J\'\u0010)\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00162\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-J\u001e\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010#0\"2\u0006\u0010$\u001a\u00020\u001cH\u0002J\u0019\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J\u0019\u00102\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\'H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00103J\u0016\u00104\u001a\b\u0012\u0004\u0012\u00020\u00120\u001b2\u0006\u00105\u001a\u00020\u0016H\u0016J+\u00106\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00162\u0006\u00107\u001a\u00020\u0018H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J!\u00109\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010:R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006;"}, d2 = {"Lcom/cardio/physician/data/remote/diagnosis/repositary/DiagnosisRepoImp;", "Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "apiService", "Lcom/cardio/physician/network/api/ApiService;", "fireStoreDb", "Lcom/google/firebase/firestore/FirebaseFirestore;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "fcmManager", "Lcom/cardio/physician/data/remote/fcm/FcmManager;", "(Lcom/cardio/physician/network/api/ApiService;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/auth/FirebaseAuth;Lcom/cardio/physician/data/remote/fcm/FcmManager;)V", "getApiService", "()Lcom/cardio/physician/network/api/ApiService;", "addMedicineInfoToModel", "", "documentSnapshot", "Lcom/google/firebase/firestore/DocumentSnapshot;", "medicineModel", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "fetchMedicine", "Lcom/cardio/physician/domain/common/model/BaseModel;", "name", "", "isPreExistedMed", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDiagnosisByDate", "", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "date", "ailment", "userId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHealthLogMap", "", "", "diagnosisModel", "getJsonDataFromAsset", "context", "Landroid/content/Context;", "fileName", "getPatientListByDate", "listener", "Lcom/google/firebase/firestore/EventListener;", "Lcom/google/firebase/firestore/QuerySnapshot;", "(Ljava/lang/String;Lcom/google/firebase/firestore/EventListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserModelMap", "saveMedToCollection", "medName", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveMedicineToCollections", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchMedicine", "queryString", "submitReport", "isEdit", "(Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCollectionIfMedDoesNotExist", "(Ljava/lang/String;Lcom/cardio/physician/domain/diagnosis/MedicineModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class DiagnosisRepoImp implements com.cardio.physician.domain.diagnosis.DiagnosisRepo {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.network.api.ApiService apiService = null;
    private final com.google.firebase.firestore.FirebaseFirestore fireStoreDb = null;
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final com.cardio.physician.data.remote.fcm.FcmManager fcmManager = null;
    
    @javax.inject.Inject()
    public DiagnosisRepoImp(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.api.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStoreDb, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.fcm.FcmManager fcmManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.network.api.ApiService getApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object fetchMedicine(@org.jetbrains.annotations.NotNull()
    java.lang.String name, boolean isPreExistedMed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.common.model.BaseModel<com.cardio.physician.domain.diagnosis.MedicineModel>> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> searchMedicine(@org.jetbrains.annotations.NotNull()
    java.lang.String queryString) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getPatientListByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.EventListener<com.google.firebase.firestore.QuerySnapshot> listener, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final java.lang.Object updateCollectionIfMedDoesNotExist(java.lang.String name, com.cardio.physician.domain.diagnosis.MedicineModel medicineModel, kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final void addMedicineInfoToModel(com.google.firebase.firestore.DocumentSnapshot documentSnapshot, com.cardio.physician.domain.diagnosis.MedicineModel medicineModel) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object submitReport(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, boolean isEdit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getDiagnosisByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String ailment, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel>> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveMedicineToCollections(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    private final java.lang.String getJsonDataFromAsset(android.content.Context context, java.lang.String fileName) {
        return null;
    }
    
    private final java.lang.Object saveMedToCollection(java.lang.String medName, kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> getHealthLogMap(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> getUserModelMap(com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel) {
        return null;
    }
}
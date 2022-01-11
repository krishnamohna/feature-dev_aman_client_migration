package com.cardio.physician.domain.diagnosis;

import android.content.Context;
import com.cardio.physician.domain.common.model.BaseModel;
import com.cardio.physician.domain.connection.ConnectionModel;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\'\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ1\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\'\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0019\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H&J+\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2 = {"Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "", "fetchMedicine", "Lcom/cardio/physician/domain/common/model/BaseModel;", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "name", "", "isPreExistedMed", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDiagnosisByDate", "", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "date", "ailment", "userId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPatientListByDate", "", "listener", "Lcom/google/firebase/firestore/EventListener;", "Lcom/google/firebase/firestore/QuerySnapshot;", "(Ljava/lang/String;Lcom/google/firebase/firestore/EventListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveMedicineToCollections", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchMedicine", "submitReport", "diagnosisModel", "isEdit", "(Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public abstract interface DiagnosisRepo {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchMedicine(@org.jetbrains.annotations.NotNull()
    java.lang.String name, boolean isPreExistedMed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.common.model.BaseModel<com.cardio.physician.domain.diagnosis.MedicineModel>> p2);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> searchMedicine(@org.jetbrains.annotations.NotNull()
    java.lang.String name);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object submitReport(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisModel diagnosisModel, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, boolean isEdit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p3);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDiagnosisByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String ailment, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.diagnosis.DiagnosisModel>> p3);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveMedicineToCollections(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPatientListByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.EventListener<com.google.firebase.firestore.QuerySnapshot> listener, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2);
}
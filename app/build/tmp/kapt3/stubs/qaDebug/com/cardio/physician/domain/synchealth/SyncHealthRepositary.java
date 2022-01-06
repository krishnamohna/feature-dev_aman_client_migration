package com.cardio.physician.domain.synchealth;

import com.cardio.physician.domain.fitness.model.FitnessModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J)\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J#\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;", "", "getHealthLogByDate", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "date", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHealthLogs", "", "days", "", "userId", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastSavedHealthLogCollectionDate", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveHealthData", "", "fitnessModel", "(Lcom/cardio/physician/domain/fitness/model/FitnessModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateHealthLogByDate", "(Lcom/cardio/physician/domain/fitness/model/FitnessModel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public abstract interface SyncHealthRepositary {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLastSavedHealthLogCollectionDate(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.fitness.model.FitnessModel> p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveHealthData(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getHealthLogByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.fitness.model.FitnessModel> p1);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateHealthLogByDate(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.FitnessModel fitnessModel, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getHealthLogs(long days, @org.jetbrains.annotations.Nullable()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.fitness.model.FitnessModel>> p2);
}
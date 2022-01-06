package com.cardio.physician.network.api;

import com.cardio.physician.data.remote.diagnosis.entity.medicinesearch.MedicineEntity;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/cardio/physician/network/api/ApiService;", "", "searchMedicine", "Lretrofit2/Response;", "Lcom/cardio/physician/data/remote/diagnosis/entity/medicinesearch/MedicineEntity;", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "/REST/drugs.json?")
    public abstract java.lang.Object searchMedicine(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "name")
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.cardio.physician.data.remote.diagnosis.entity.medicinesearch.MedicineEntity>> p1);
}
package com.cardio.physician.ui.common.utils.extentions;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.domain.common.model.BaseModel;
import com.cardio.physician.domain.diagnosis.MedicineModel;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0085\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062#\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b2\u0014\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u00010\b2$\u0010\u000e\u001a \u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\f\u0012\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f\u001a,\u0010\u0013\u001a\u00020\u0001*\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00040\u0019\u001a(\u0010\u001a\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00192\n\u0010\u001b\u001a\u00060\u0011j\u0002`\u0012\u001a\u001c\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0019\u001a)\u0010\u001d\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00192\u0006\u0010\u001e\u001a\u0002H\u0002\u00a2\u0006\u0002\u0010\u001f\u00a8\u0006 "}, d2 = {"customObserver", "", "T", "Landroidx/lifecycle/LiveData;", "Lcom/cardio/physician/network/Resource;", "viewLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "onLoading", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "showProgress", "onSuccess", "onError", "Lkotlin/Function2;", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "observeApi", "Lkotlinx/coroutines/CoroutineScope;", "searchMedicine", "Lcom/cardio/physician/domain/common/model/BaseModel;", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "mutableMedicineData", "Landroidx/lifecycle/MutableLiveData;", "setError", "networkException", "setLoading", "setSuccess", "data", "(Landroidx/lifecycle/MutableLiveData;Ljava/lang/Object;)V", "app_qaDebug"})
public final class LiveDataExtenstionsKt {
    
    public static final <T extends java.lang.Object>void setLoading(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<T>> $this$setLoading) {
    }
    
    public static final <T extends java.lang.Object>void setSuccess(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<T>> $this$setSuccess, T data) {
    }
    
    public static final <T extends java.lang.Object>void setError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<T>> $this$setError, @org.jetbrains.annotations.NotNull()
    java.lang.Exception networkException) {
    }
    
    public static final void observeApi(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope $this$observeApi, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.common.model.BaseModel<com.cardio.physician.domain.diagnosis.MedicineModel> searchMedicine, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.diagnosis.MedicineModel>> mutableMedicineData) {
    }
    
    public static final <T extends java.lang.Object>void customObserver(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<T>> $this$customObserver, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner viewLifecycleOwner, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onLoading, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> onSuccess, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.Exception, kotlin.Unit> onError) {
    }
}
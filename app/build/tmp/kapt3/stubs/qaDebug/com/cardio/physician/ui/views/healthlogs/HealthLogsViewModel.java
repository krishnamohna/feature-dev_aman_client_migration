package com.cardio.physician.ui.views.healthlogs;

import android.widget.EditText;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.domain.common.model.validation.ValidationModelV2;
import com.cardio.physician.domain.common.repository.UserAuthRepositary;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel;
import com.cardio.physician.ui.common.utils.textwatcher.alphaVisibility.AlphaVisibilityHelper;
import com.cardio.physician.ui.common.utils.validation.FieldType;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.*;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001c0!Jt\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020$2\u0006\u0010(\u001a\u00020$2\b\u0010)\u001a\u0004\u0018\u00010*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001c0!2\u0018\u0010,\u001a\u0014\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u001c0-2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u001c00J\u000e\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020$J\u0012\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r04J\u0012\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\r04J\r\u00106\u001a\u0004\u0018\u000107\u00a2\u0006\u0002\u00108JO\u00109\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020$2\u0006\u0010)\u001a\u00020$2\b\u0010:\u001a\u0004\u0018\u0001072\u0006\u0010(\u001a\u00020$2\b\u0010;\u001a\u0004\u0018\u00010$\u00a2\u0006\u0002\u0010<R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R&\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\r0\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006="}, d2 = {"Lcom/cardio/physician/ui/views/healthlogs/HealthLogsViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseViewModel;", "syncHealthRepositary", "Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;", "userAuthRepositary", "Lcom/cardio/physician/domain/common/repository/UserAuthRepositary;", "(Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;Lcom/cardio/physician/domain/common/repository/UserAuthRepositary;)V", "alphaVisibilityHelper", "Lcom/cardio/physician/ui/common/utils/textwatcher/alphaVisibility/AlphaVisibilityHelper;", "getAlphaVisibilityHelper", "()Lcom/cardio/physician/ui/common/utils/textwatcher/alphaVisibility/AlphaVisibilityHelper;", "mutableLiveDataHealthLogs", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "getMutableLiveDataHealthLogs", "()Landroidx/lifecycle/MutableLiveData;", "setMutableLiveDataHealthLogs", "(Landroidx/lifecycle/MutableLiveData;)V", "getSyncHealthRepositary", "()Lcom/cardio/physician/domain/synchealth/SyncHealthRepositary;", "updateLogLiveData", "", "getUpdateLogLiveData", "setUpdateLogLiveData", "getUserAuthRepositary", "()Lcom/cardio/physician/domain/common/repository/UserAuthRepositary;", "addTextChangeListener", "", "list", "", "Landroid/widget/EditText;", "onTextChanged", "Lkotlin/Function0;", "checkValidation", "weight", "", "heartRate", "topBp", "bottomBp", "stepCount", "selectedDate", "Ljava/util/Date;", "onSuccess", "onFailure", "Lkotlin/Function2;", "Lcom/cardio/physician/domain/common/model/validation/ValidationModelV2;", "onFailure2", "Lkotlin/Function1;", "getHealthLogsByDate", "date", "getHealthlogsLiveData", "Landroidx/lifecycle/LiveData;", "getUpdateLiveData", "getUserSignedUpDate", "", "()Ljava/lang/Long;", "updateData", "timeStamp", "userId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "app_qaDebug"})
public final class HealthLogsViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.synchealth.SyncHealthRepositary syncHealthRepositary = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.common.repository.UserAuthRepositary userAuthRepositary = null;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Boolean>> updateLogLiveData;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.ui.common.utils.textwatcher.alphaVisibility.AlphaVisibilityHelper alphaVisibilityHelper = null;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> mutableLiveDataHealthLogs;
    
    @javax.inject.Inject()
    public HealthLogsViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.synchealth.SyncHealthRepositary syncHealthRepositary, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.common.repository.UserAuthRepositary userAuthRepositary) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.synchealth.SyncHealthRepositary getSyncHealthRepositary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.common.repository.UserAuthRepositary getUserAuthRepositary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Boolean>> getUpdateLogLiveData() {
        return null;
    }
    
    public final void setUpdateLogLiveData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.lang.Boolean>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.utils.textwatcher.alphaVisibility.AlphaVisibilityHelper getAlphaVisibilityHelper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.Boolean>> getUpdateLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> getMutableLiveDataHealthLogs() {
        return null;
    }
    
    public final void setMutableLiveDataHealthLogs(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.fitness.model.FitnessModel>> getHealthlogsLiveData() {
        return null;
    }
    
    public final void checkValidation(@org.jetbrains.annotations.NotNull()
    java.lang.String weight, @org.jetbrains.annotations.NotNull()
    java.lang.String heartRate, @org.jetbrains.annotations.NotNull()
    java.lang.String topBp, @org.jetbrains.annotations.NotNull()
    java.lang.String bottomBp, @org.jetbrains.annotations.NotNull()
    java.lang.String stepCount, @org.jetbrains.annotations.Nullable()
    java.util.Date selectedDate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super com.cardio.physician.domain.common.model.validation.ValidationModelV2, kotlin.Unit> onFailure, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.common.model.validation.ValidationModelV2, kotlin.Unit> onFailure2) {
    }
    
    public final void updateData(@org.jetbrains.annotations.NotNull()
    java.lang.String weight, @org.jetbrains.annotations.NotNull()
    java.lang.String heartRate, @org.jetbrains.annotations.NotNull()
    java.lang.String topBp, @org.jetbrains.annotations.NotNull()
    java.lang.String bottomBp, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedDate, @org.jetbrains.annotations.Nullable()
    java.lang.Long timeStamp, @org.jetbrains.annotations.NotNull()
    java.lang.String stepCount, @org.jetbrains.annotations.Nullable()
    java.lang.String userId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getUserSignedUpDate() {
        return null;
    }
    
    public final void addTextChangeListener(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.widget.EditText> list, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onTextChanged) {
    }
    
    public final void getHealthLogsByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
    }
}
package com.cardio.physician.ui.views.diagnosis.step1;

import android.widget.EditText;
import androidx.lifecycle.LiveData;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.common.model.validation.ValidationModelV2;
import com.cardio.physician.domain.connection.ConnectionRepo;
import com.cardio.physician.domain.diagnosis.DiagnosisRepo;
import com.cardio.physician.domain.notifications.NotificationRepo;
import com.cardio.physician.domain.synchealth.SyncHealthRepositary;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.cardio.physician.ui.common.utils.textwatcher.alphaVisibility.AlphaVisibilityHelper;
import com.cardio.physician.ui.common.utils.validation.Validater;
import com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import javax.inject.Named;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B;\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\"\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0\'J}\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020,2\u0006\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020,2\u0006\u00102\u001a\u00020,2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\"0\'2\'\u00104\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002060$\u00a2\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\"05J\u00a8\u0001\u0010:\u001a\u00020\"2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020,2\u0006\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020,2\u0006\u00102\u001a\u00020,2\u0006\u0010;\u001a\u00020,2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\"0\'2\'\u00104\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002060$\u00a2\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\"052!\u0010<\u001a\u001d\u0012\u0013\u0012\u00110,\u00a2\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020\"05J\u0010\u0010>\u001a\u00020\"2\b\u0010?\u001a\u0004\u0018\u00010,J0\u0010@\u001a\u00020A2\u0006\u0010/\u001a\u00020,2\u0006\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020,2\u0006\u00102\u001a\u00020,2\u0006\u0010;\u001a\u00020,H\u0002J\"\u0010B\u001a\u00020\"2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020%0$2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0\'R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001f\u00a8\u0006D"}, d2 = {"Lcom/cardio/physician/ui/views/diagnosis/step1/DiagnosisViewStep1ViewModel;", "Lcom/cardio/physician/ui/views/diagnosis/step4/DiagnosisFragmentStep4ViewModel;", "validater", "Lcom/cardio/physician/ui/common/utils/validation/Validater;", "validaterAlpha", "userProfileRepository", "Lcom/cardio/physician/data/remote/profile/UserProfileRepository;", "diagnosisRepo", "Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "connectionRepo", "Lcom/cardio/physician/domain/connection/ConnectionRepo;", "notificationRepo", "Lcom/cardio/physician/domain/notifications/NotificationRepo;", "(Lcom/cardio/physician/ui/common/utils/validation/Validater;Lcom/cardio/physician/ui/common/utils/validation/Validater;Lcom/cardio/physician/data/remote/profile/UserProfileRepository;Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;Lcom/cardio/physician/domain/connection/ConnectionRepo;Lcom/cardio/physician/domain/notifications/NotificationRepo;)V", "_userDetailDocument", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "Lcom/cardio/physician/domain/common/model/UserModel;", "alphaVisibilityHelper", "Lcom/cardio/physician/ui/common/utils/textwatcher/alphaVisibility/AlphaVisibilityHelper;", "getConnectionRepo", "()Lcom/cardio/physician/domain/connection/ConnectionRepo;", "getDiagnosisRepo", "()Lcom/cardio/physician/domain/diagnosis/DiagnosisRepo;", "getNotificationRepo", "()Lcom/cardio/physician/domain/notifications/NotificationRepo;", "userDetailDocument", "Landroidx/lifecycle/LiveData;", "getUserDetailDocument", "()Landroidx/lifecycle/LiveData;", "getValidater", "()Lcom/cardio/physician/ui/common/utils/validation/Validater;", "getValidaterAlpha", "addTextChangeListener", "", "list", "", "Landroid/widget/EditText;", "onTextChanged", "Lkotlin/Function0;", "checkAlphaValidation", "ailmentPosition", "", "firstName", "", "lastName", "age", "weight", "heartRate", "topBp", "bottomBp", "succcess", "failed", "Lkotlin/Function1;", "Lcom/cardio/physician/domain/common/model/validation/ValidationModelV2;", "Lkotlin/ParameterName;", "name", "validations", "checkValidation", "stepCount", "failed2", "msg", "getUserProfile", "userId", "isAnyHealthDataIsFilled", "", "setAlphaValidationListener", "listEdtFields", "app_qaDebug"})
public final class DiagnosisViewStep1ViewModel extends com.cardio.physician.ui.views.diagnosis.step4.DiagnosisFragmentStep4ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.ui.common.utils.validation.Validater validater = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.ui.common.utils.validation.Validater validaterAlpha = null;
    private final com.cardio.physician.data.remote.profile.UserProfileRepository userProfileRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.connection.ConnectionRepo connectionRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.notifications.NotificationRepo notificationRepo = null;
    private com.cardio.physician.ui.common.utils.textwatcher.alphaVisibility.AlphaVisibilityHelper alphaVisibilityHelper;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.cardio.physician.domain.common.model.UserModel>> _userDetailDocument = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.common.model.UserModel>> userDetailDocument = null;
    
    @javax.inject.Inject()
    public DiagnosisViewStep1ViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "default_validator")
    com.cardio.physician.ui.common.utils.validation.Validater validater, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "default_alpha_validator")
    com.cardio.physician.ui.common.utils.validation.Validater validaterAlpha, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.profile.UserProfileRepository userProfileRepository, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.diagnosis.DiagnosisRepo diagnosisRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.connection.ConnectionRepo connectionRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationRepo notificationRepo) {
        super(null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.utils.validation.Validater getValidater() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.ui.common.utils.validation.Validater getValidaterAlpha() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.domain.diagnosis.DiagnosisRepo getDiagnosisRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.domain.connection.ConnectionRepo getConnectionRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.domain.notifications.NotificationRepo getNotificationRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.common.model.UserModel>> getUserDetailDocument() {
        return null;
    }
    
    public final void checkValidation(int ailmentPosition, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    java.lang.String age, @org.jetbrains.annotations.NotNull()
    java.lang.String weight, @org.jetbrains.annotations.NotNull()
    java.lang.String heartRate, @org.jetbrains.annotations.NotNull()
    java.lang.String topBp, @org.jetbrains.annotations.NotNull()
    java.lang.String bottomBp, @org.jetbrains.annotations.NotNull()
    java.lang.String stepCount, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> succcess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2>, kotlin.Unit> failed, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> failed2) {
    }
    
    private final boolean isAnyHealthDataIsFilled(java.lang.String weight, java.lang.String heartRate, java.lang.String topBp, java.lang.String bottomBp, java.lang.String stepCount) {
        return false;
    }
    
    public final void checkAlphaValidation(int ailmentPosition, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    java.lang.String age, @org.jetbrains.annotations.NotNull()
    java.lang.String weight, @org.jetbrains.annotations.NotNull()
    java.lang.String heartRate, @org.jetbrains.annotations.NotNull()
    java.lang.String topBp, @org.jetbrains.annotations.NotNull()
    java.lang.String bottomBp, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> succcess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.cardio.physician.domain.common.model.validation.ValidationModelV2>, kotlin.Unit> failed) {
    }
    
    public final void getUserProfile(@org.jetbrains.annotations.Nullable()
    java.lang.String userId) {
    }
    
    public final void addTextChangeListener(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.widget.EditText> list, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onTextChanged) {
    }
    
    public final void setAlphaValidationListener(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.widget.EditText> listEdtFields, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onTextChanged) {
    }
}
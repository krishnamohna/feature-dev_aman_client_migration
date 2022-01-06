package com.cardio.physician.ui.views.profile.change_password;

import android.app.Application;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.changepassword.ChangePasswordRepository;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.AppCardioPatient;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.utils.ENUM;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J1\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020 H\u0002J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u0019H\u0002J\u001e\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0019J\u001e\u0010*\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0019R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006+"}, d2 = {"Lcom/cardio/physician/ui/views/profile/change_password/ChangePasswordViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "repository", "Lcom/cardio/physician/data/remote/changepassword/ChangePasswordRepository;", "application", "Landroid/app/Application;", "(Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/data/remote/changepassword/ChangePasswordRepository;Landroid/app/Application;)V", "_changePasswordResponse", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "", "changePasswordResponse", "Landroidx/lifecycle/LiveData;", "getChangePasswordResponse", "()Landroidx/lifecycle/LiveData;", "validationChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "getValidationChannel", "()Lkotlinx/coroutines/channels/Channel;", "changePassword", "", "oldPass", "", "newPass", "queueValidationRequest", "status", "Lcom/cardio/physician/network/Status;", "message", "edtResource", "", "tvResourceId", "(Lcom/cardio/physician/network/Status;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setObserverForAlpha", "resourceId", "showValidationMessage", "validateFieldsToSetAlpha", "oldPassword", "newPassword", "confirmPassword", "validatePassword", "app_qaDebug"})
public final class ChangePasswordViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel {
    private final com.cardio.physician.data.remote.changepassword.ChangePasswordRepository repository = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.Boolean>> _changePasswordResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.Boolean>> changePasswordResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> validationChannel = null;
    
    @javax.inject.Inject()
    public ChangePasswordViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.changepassword.ChangePasswordRepository repository, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.Boolean>> getChangePasswordResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> getValidationChannel() {
        return null;
    }
    
    public final void validateFieldsToSetAlpha(@org.jetbrains.annotations.NotNull()
    java.lang.String oldPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String confirmPassword) {
    }
    
    private final void setObserverForAlpha(int resourceId) {
    }
    
    public final void validatePassword(@org.jetbrains.annotations.NotNull()
    java.lang.String oldPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String confirmPassword) {
    }
    
    private final void changePassword(java.lang.String oldPass, java.lang.String newPass) {
    }
    
    private final void showValidationMessage(java.lang.String message) {
    }
    
    private final java.lang.Object queueValidationRequest(com.cardio.physician.network.Status status, java.lang.String message, int edtResource, int tvResourceId, kotlin.coroutines.Continuation<? super kotlin.Unit> p4) {
        return null;
    }
}
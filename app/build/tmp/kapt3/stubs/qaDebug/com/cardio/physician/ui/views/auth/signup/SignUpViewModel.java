package com.cardio.physician.ui.views.auth.signup;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.signup.SignUpRepository;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.network.NetworkHelper;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.AppCardioPatient;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010\'\u001a\u00020\u000eJ\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00170\u001bJ\u0006\u0010)\u001a\u00020*J\b\u0010+\u001a\u00020$H\u0014J1\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000201H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00103J\u0010\u00104\u001a\u00020$2\u0006\u00105\u001a\u000201H\u0002J\u0010\u00106\u001a\u00020$2\u0006\u0010/\u001a\u00020\u000eH\u0002J\u0096\u0001\u00107\u001a\u00020$2\u0006\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010\'\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e2K\u0010<\u001aG\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(%\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\'\u0012\u0004\u0012\u00020$0=H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010AJ>\u0010B\u001a\u00020$2\u0006\u0010C\u001a\u00020*2\u0006\u00108\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000e2\u0006\u0010\'\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000eR\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006E"}, d2 = {"Lcom/cardio/physician/ui/views/auth/signup/SignUpViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "signUpRepository", "Lcom/cardio/physician/data/remote/signup/SignUpRepository;", "application", "Landroid/app/Application;", "networkHelper", "Lcom/cardio/physician/network/NetworkHelper;", "(Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/data/remote/signup/SignUpRepository;Landroid/app/Application;Lcom/cardio/physician/network/NetworkHelper;)V", "_signUpApiResponse", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "", "deviceUri", "Landroid/net/Uri;", "getDeviceUri", "()Landroid/net/Uri;", "setDeviceUri", "(Landroid/net/Uri;)V", "mutableFragmentState", "Landroidx/lifecycle/MutableLiveData;", "Landroid/os/Bundle;", "getMutableFragmentState", "()Landroidx/lifecycle/MutableLiveData;", "signUpApiResponse", "Landroidx/lifecycle/LiveData;", "getSignUpApiResponse", "()Landroidx/lifecycle/LiveData;", "validationChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "getValidationChannel", "()Lkotlinx/coroutines/channels/Channel;", "checkIsUserExist", "", "countryCode", "phoneNumber", "email", "getLiveDataFragmentState", "isNetworkConnected", "", "onCleared", "queueValidationRequest", "status", "Lcom/cardio/physician/network/Status;", "message", "edtResource", "", "tvResourceId", "(Lcom/cardio/physician/network/Status;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setObserverForAlpha", "resourceId", "showValidationMessage", "validateFields", "firstName", "lastName", "password", "confirmPassword", "onValidationSuccess", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "phoneNo", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateFieldsToSetAlpha", "isChecked", "lastname", "app_qaDebug"})
public final class SignUpViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel {
    private final com.cardio.physician.data.remote.signup.SignUpRepository signUpRepository = null;
    private final com.cardio.physician.network.NetworkHelper networkHelper = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.String>> _signUpApiResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> signUpApiResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<android.os.Bundle> mutableFragmentState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> validationChannel = null;
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri deviceUri;
    
    @javax.inject.Inject()
    public SignUpViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.signup.SignUpRepository signUpRepository, @org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.NetworkHelper networkHelper) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> getSignUpApiResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<android.os.Bundle> getMutableFragmentState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> getValidationChannel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getDeviceUri() {
        return null;
    }
    
    public final void setDeviceUri(@org.jetbrains.annotations.Nullable()
    android.net.Uri p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<android.os.Bundle> getLiveDataFragmentState() {
        return null;
    }
    
    public final void validateFieldsToSetAlpha(boolean isChecked, @org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastname, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String confirmPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
    }
    
    private final void setObserverForAlpha(int resourceId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object validateFields(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String countryCode, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String confirmPassword, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onValidationSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p8) {
        return null;
    }
    
    public final void checkIsUserExist(@org.jetbrains.annotations.NotNull()
    java.lang.String countryCode, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    private final void showValidationMessage(java.lang.String message) {
    }
    
    private final java.lang.Object queueValidationRequest(com.cardio.physician.network.Status status, java.lang.String message, int edtResource, int tvResourceId, kotlin.coroutines.Continuation<? super kotlin.Unit> p4) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final boolean isNetworkConnected() {
        return false;
    }
}
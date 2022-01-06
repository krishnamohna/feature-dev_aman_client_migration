package com.cardio.physician.ui.views.auth.phone_verification;

import android.app.Application;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.phoneverification.PhoneVerificationRepository;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.login.request.PhoneVerificationDetails;
import com.cardio.physician.domain.user.SignUpUserType;
import com.cardio.physician.domain.user.UserType;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.AppCardioPatient;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.*;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0014\u0010\u001c\u001a\u00020\u00192\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001fH\u0002J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"H\u0002J\u001c\u0010#\u001a\u00020\u00192\b\u0010$\u001a\u0004\u0018\u00010\u00102\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\u001a\u0010\'\u001a\u00020\u00192\b\u0010$\u001a\u0004\u0018\u00010\u00102\b\u0010(\u001a\u0004\u0018\u00010&J\u000e\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u000eJ\"\u0010+\u001a\u00020\u00192\b\b\u0002\u0010,\u001a\u00020-2\u0010\b\u0002\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010/J\u0018\u00100\u001a\u00020\u00192\b\u00101\u001a\u0004\u0018\u00010\u000e2\u0006\u00102\u001a\u00020\u000eR\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015\u00a8\u00063"}, d2 = {"Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneVerificationViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "userProfileRepo", "Lcom/cardio/physician/data/remote/profile/UserProfileRepository;", "phoneVerificationRepository", "Lcom/cardio/physician/data/remote/phoneverification/PhoneVerificationRepository;", "application", "Landroid/app/Application;", "(Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/data/remote/profile/UserProfileRepository;Lcom/cardio/physician/data/remote/phoneverification/PhoneVerificationRepository;Landroid/app/Application;)V", "_loginApiResponse", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "", "_validatePhoneVerification", "Lcom/google/firebase/auth/FirebaseUser;", "imagePath", "loginApiResponse", "Landroidx/lifecycle/LiveData;", "getLoginApiResponse", "()Landroidx/lifecycle/LiveData;", "validateForgotPasswordResponse", "getValidateForgotPasswordResponse", "setObserverForAlpha", "", "resourceId", "", "showFailureException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "signInWithPhoneAuthCredential", "credential", "Lcom/google/firebase/auth/PhoneAuthCredential;", "storeUserDetailInFireStore", "user", "userDetail", "Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;", "updateEmailAndPassword", "phoneVerificationDetail", "validateFieldsToSetAlpha", "otp", "validateUserType", "isGoogleLogin", "", "onForceLogout", "Lkotlin/Function0;", "verifyPhoneNumberWithCode", "verificationId", "code", "app_qaDebug"})
public final class PhoneVerificationViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel {
    private final com.cardio.physician.data.remote.profile.UserProfileRepository userProfileRepo = null;
    private final com.cardio.physician.data.remote.phoneverification.PhoneVerificationRepository phoneVerificationRepository = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.google.firebase.auth.FirebaseUser>> _validatePhoneVerification = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.auth.FirebaseUser>> validateForgotPasswordResponse = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.String>> _loginApiResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> loginApiResponse = null;
    private java.lang.String imagePath = "";
    
    @javax.inject.Inject()
    public PhoneVerificationViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.profile.UserProfileRepository userProfileRepo, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.phoneverification.PhoneVerificationRepository phoneVerificationRepository, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.auth.FirebaseUser>> getValidateForgotPasswordResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> getLoginApiResponse() {
        return null;
    }
    
    public final void validateFieldsToSetAlpha(@org.jetbrains.annotations.NotNull()
    java.lang.String otp) {
    }
    
    private final void setObserverForAlpha(int resourceId) {
    }
    
    public final void verifyPhoneNumberWithCode(@org.jetbrains.annotations.Nullable()
    java.lang.String verificationId, @org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    private final void signInWithPhoneAuthCredential(com.google.firebase.auth.PhoneAuthCredential credential) {
    }
    
    public final void updateEmailAndPassword(@org.jetbrains.annotations.Nullable()
    com.google.firebase.auth.FirebaseUser user, @org.jetbrains.annotations.Nullable()
    com.cardio.physician.domain.login.request.PhoneVerificationDetails phoneVerificationDetail) {
    }
    
    private final void showFailureException(java.lang.Exception exception) {
    }
    
    private final void storeUserDetailInFireStore(com.google.firebase.auth.FirebaseUser user, com.cardio.physician.domain.login.request.PhoneVerificationDetails userDetail) {
    }
    
    public final void validateUserType(boolean isGoogleLogin, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onForceLogout) {
    }
}
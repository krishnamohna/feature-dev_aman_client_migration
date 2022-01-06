package com.cardio.physician.ui.views.auth.login;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.domain.login.LoginRepositary;
import com.cardio.physician.domain.user.UserType;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.AppCardioPatient;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.*;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ)\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000eH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J)\u0010#\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000eH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J\u0006\u0010&\u001a\u00020\u001dJ\u0018\u0010\'\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000eH\u0002J\b\u0010(\u001a\u00020\u001dH\u0014J&\u0010)\u001a\u00020\u001d2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010+2\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010.J1\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u000e2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000204H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00106J\u0010\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u000204H\u0002J\u0010\u00109\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u000eH\u0002J&\u0010:\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010.J\u0019\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010?J)\u0010@\u001a\u00020\u001d2\u0006\u0010A\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BJ\u0016\u0010C\u001a\u00020\u001d2\u0006\u0010A\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000eJ\u001a\u0010D\u001a\u00020\u001d2\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010.H\u0002R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006E"}, d2 = {"Lcom/cardio/physician/ui/views/auth/login/LoginViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "loginRepository", "Lcom/cardio/physician/domain/login/LoginRepositary;", "userProfileRepo", "Lcom/cardio/physician/data/remote/profile/UserProfileRepository;", "application", "Landroid/app/Application;", "(Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/domain/login/LoginRepositary;Lcom/cardio/physician/data/remote/profile/UserProfileRepository;Landroid/app/Application;)V", "_loginApiResponse", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "", "_userDetailDocument", "Lcom/google/firebase/firestore/DocumentSnapshot;", "loginApiResponse", "Landroidx/lifecycle/LiveData;", "getLoginApiResponse", "()Landroidx/lifecycle/LiveData;", "userDetailDocument", "getUserDetailDocument", "validationChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "getValidationChannel", "()Lkotlinx/coroutines/channels/Channel;", "checkValidationForEmailAndPassword", "", "context", "Lcom/cardio/physician/ui/AppCardioPatient;", "email", "password", "(Lcom/cardio/physician/ui/AppCardioPatient;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkValidationForPhoneNumber", "phoneNumber", "countryCode", "getUserDetail", "isUserExist", "onCleared", "onGoogleSignIn", "task", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "onForceLogout", "Lkotlin/Function0;", "queueValidationRequest", "status", "Lcom/cardio/physician/network/Status;", "message", "edtResource", "", "tvResourceId", "(Lcom/cardio/physician/network/Status;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setObserverForAlpha", "resourceId", "showValidationMessage", "signWithEmailAndPassword", "storeUserDetailInFireStore", "", "user", "Lcom/cardio/physician/domain/common/model/UserModel;", "(Lcom/cardio/physician/domain/common/model/UserModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateFields", "userId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateFieldsToSetAlpha", "validateUserType", "app_qaDebug"})
public final class LoginViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel {
    private final com.cardio.physician.domain.login.LoginRepositary loginRepository = null;
    private final com.cardio.physician.data.remote.profile.UserProfileRepository userProfileRepo = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.String>> _loginApiResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> loginApiResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> validationChannel = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.google.firebase.firestore.DocumentSnapshot>> _userDetailDocument = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.firestore.DocumentSnapshot>> userDetailDocument = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.login.LoginRepositary loginRepository, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.profile.UserProfileRepository userProfileRepo, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> getLoginApiResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> getValidationChannel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.firestore.DocumentSnapshot>> getUserDetailDocument() {
        return null;
    }
    
    public final void getUserDetail() {
    }
    
    public final void validateFieldsToSetAlpha(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    private final void setObserverForAlpha(int resourceId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object validateFields(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String countryCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
        return null;
    }
    
    private final java.lang.Object checkValidationForEmailAndPassword(com.cardio.physician.ui.AppCardioPatient context, java.lang.String email, java.lang.String password, kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
        return null;
    }
    
    private final java.lang.Object checkValidationForPhoneNumber(com.cardio.physician.ui.AppCardioPatient context, java.lang.String phoneNumber, java.lang.String countryCode, kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
        return null;
    }
    
    private final void isUserExist(java.lang.String countryCode, java.lang.String phoneNumber) {
    }
    
    private final void showValidationMessage(java.lang.String message) {
    }
    
    public final void signWithEmailAndPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onForceLogout) {
    }
    
    public final void onGoogleSignIn(@org.jetbrains.annotations.Nullable()
    com.google.android.gms.tasks.Task<com.google.android.gms.auth.api.signin.GoogleSignInAccount> task, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onForceLogout) {
    }
    
    private final void validateUserType(kotlin.jvm.functions.Function0<kotlin.Unit> onForceLogout) {
    }
    
    private final java.lang.Object queueValidationRequest(com.cardio.physician.network.Status status, java.lang.String message, int edtResource, int tvResourceId, kotlin.coroutines.Continuation<? super kotlin.Unit> p4) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final java.lang.Object storeUserDetailInFireStore(com.cardio.physician.domain.common.model.UserModel user, kotlin.coroutines.Continuation<? super java.lang.Boolean> p1) {
        return null;
    }
}
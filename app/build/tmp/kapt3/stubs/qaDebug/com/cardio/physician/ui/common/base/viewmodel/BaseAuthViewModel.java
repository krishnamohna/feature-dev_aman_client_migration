package com.cardio.physician.ui.common.base.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavDirections;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.domain.login.request.PhoneVerificationDetails;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.AppCardioPatient;
import com.cardio.physician.ui.common.utils.Preference;
import com.cardio.physician.ui.common.utils.Timer;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.*;
import com.google.firebase.ktx.Firebase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\b\u0017\u0018\u0000 W2\u00020\u0001:\u0001WB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010<\u001a\u00020=2\n\u0010>\u001a\u00060\nj\u0002`\u000bH\u0004J\u0006\u0010?\u001a\u00020=J>\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020C2\u0006\u0010E\u001a\u00020C2\u0006\u0010F\u001a\u00020C2\u0006\u0010G\u001a\u00020C2\u0006\u0010H\u001a\u00020C2\u0006\u0010I\u001a\u00020CJ\u0014\u0010J\u001a\u00020C2\n\u0010>\u001a\u00060\nj\u0002`\u000bH\u0004J\u0006\u0010K\u001a\u00020CJ\u0006\u0010L\u001a\u00020=J\u0006\u0010M\u001a\u000207J\u000e\u0010N\u001a\u00020=2\u0006\u0010>\u001a\u00020OJ\u000e\u0010P\u001a\u00020=2\u0006\u0010Q\u001a\u00020\u000fJ\u000e\u0010R\u001a\u00020=2\u0006\u0010S\u001a\u00020CJ\u0010\u0010T\u001a\u00020=2\u0006\u0010U\u001a\u00020CH\u0002J\u0010\u0010V\u001a\u00020=2\u0006\u0010U\u001a\u00020CH\u0002R$\u0010\u0007\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\t0\bX\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\t0\bX\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00058FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R!\u0010$\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\t0%\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000f0%\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\'R\u001d\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\t0%\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\'R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\t0%\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\'R\u001a\u0010.\u001a\u00020/X\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u001a\u00106\u001a\u000207X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;\u00a8\u0006X"}, d2 = {"Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "application", "Landroid/app/Application;", "(Lcom/cardio/physician/data/local/UserManager;Landroid/app/Application;)V", "_firebaseException", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "get_firebaseException", "()Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "_navDirectionLiveData", "Landroidx/navigation/NavDirections;", "_phoneAuthenticationResponse", "Lcom/google/firebase/auth/FirebaseUser;", "get_phoneAuthenticationResponse", "_phoneVerificationResponse", "Lcom/google/firebase/auth/PhoneAuthCredential;", "applicationContext", "getApplicationContext", "()Landroid/app/Application;", "applicationContext$delegate", "Lkotlin/Lazy;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "getAuth", "()Lcom/google/firebase/auth/FirebaseAuth;", "callbacks", "Lcom/google/firebase/auth/PhoneAuthProvider$OnVerificationStateChangedCallbacks;", "getCallbacks$app_qaDebug", "()Lcom/google/firebase/auth/PhoneAuthProvider$OnVerificationStateChangedCallbacks;", "setCallbacks$app_qaDebug", "(Lcom/google/firebase/auth/PhoneAuthProvider$OnVerificationStateChangedCallbacks;)V", "firebaseException", "Landroidx/lifecycle/LiveData;", "getFirebaseException", "()Landroidx/lifecycle/LiveData;", "navDirectionLiveData", "getNavDirectionLiveData", "phoneAuthenticationResponse", "getPhoneAuthenticationResponse", "phoneVerificationResponse", "getPhoneVerificationResponse", "resendToken", "Lcom/google/firebase/auth/PhoneAuthProvider$ForceResendingToken;", "getResendToken$app_qaDebug", "()Lcom/google/firebase/auth/PhoneAuthProvider$ForceResendingToken;", "setResendToken$app_qaDebug", "(Lcom/google/firebase/auth/PhoneAuthProvider$ForceResendingToken;)V", "getUserManager", "()Lcom/cardio/physician/data/local/UserManager;", "verificationInProgress", "", "getVerificationInProgress$app_qaDebug", "()Z", "setVerificationInProgress$app_qaDebug", "(Z)V", "checkForMultiFactorFailure", "", "e", "clearPreference", "createModelForPhoneVerification", "Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;", "firstName", "", "lastName", "countryCode", "phoneNumber", "email", "password", "imageUrl", "getExceptionMessage", "getStoreVerificationId", "initializePhoneAuthCallBack", "isLastOtpRequestStillValid", "phoneVerificationFailException", "Lcom/google/firebase/FirebaseException;", "setDirection", "navDirections", "setStoreVerificationId", "id", "showFirebaseException", "message", "showPhoneValidation", "Companion", "app_qaDebug"})
public class BaseAuthViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.data.local.UserManager userManager = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel.Companion Companion = null;
    private static long timeStampCodeSent = 0L;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String storedVerificationId = "";
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy applicationContext$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.google.firebase.auth.PhoneAuthCredential>> _phoneVerificationResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.auth.PhoneAuthCredential>> phoneVerificationResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.google.firebase.auth.FirebaseUser>> _phoneAuthenticationResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.auth.FirebaseUser>> phoneAuthenticationResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.Exception>> _firebaseException = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.Exception>> firebaseException = null;
    public com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private boolean verificationInProgress = false;
    public com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken resendToken;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<androidx.navigation.NavDirections> _navDirectionLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<androidx.navigation.NavDirections> navDirectionLiveData = null;
    
    @javax.inject.Inject()
    public BaseAuthViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.cardio.physician.data.local.UserManager getUserManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Application getApplicationContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.auth.FirebaseAuth getAuth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.auth.PhoneAuthCredential>> getPhoneVerificationResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.google.firebase.auth.FirebaseUser>> get_phoneAuthenticationResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.auth.FirebaseUser>> getPhoneAuthenticationResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.Exception>> get_firebaseException() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.Exception>> getFirebaseException() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks getCallbacks$app_qaDebug() {
        return null;
    }
    
    public final void setCallbacks$app_qaDebug(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks p0) {
    }
    
    public final boolean getVerificationInProgress$app_qaDebug() {
        return false;
    }
    
    public final void setVerificationInProgress$app_qaDebug(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken getResendToken$app_qaDebug() {
        return null;
    }
    
    public final void setResendToken$app_qaDebug(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<androidx.navigation.NavDirections> getNavDirectionLiveData() {
        return null;
    }
    
    public final void setDirection(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavDirections navDirections) {
    }
    
    public final void clearPreference() {
    }
    
    public final void initializePhoneAuthCallBack() {
    }
    
    public final void phoneVerificationFailException(@org.jetbrains.annotations.NotNull()
    com.google.firebase.FirebaseException e) {
    }
    
    private final void showPhoneValidation(java.lang.String message) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.login.request.PhoneVerificationDetails createModelForPhoneVerification(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    java.lang.String countryCode, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
        return null;
    }
    
    protected final void checkForMultiFactorFailure(@org.jetbrains.annotations.NotNull()
    java.lang.Exception e) {
    }
    
    private final void showFirebaseException(java.lang.String message) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final java.lang.String getExceptionMessage(@org.jetbrains.annotations.NotNull()
    java.lang.Exception e) {
        return null;
    }
    
    public final boolean isLastOtpRequestStillValid() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStoreVerificationId() {
        return null;
    }
    
    public final void setStoreVerificationId(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u000bR&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0011"}, d2 = {"Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel$Companion;", "", "()V", "value", "", "storedVerificationId", "getStoredVerificationId", "()Ljava/lang/String;", "setStoredVerificationId", "(Ljava/lang/String;)V", "timeStampCodeSent", "", "getTimeStampCodeSent", "()J", "setTimeStampCodeSent", "(J)V", "remainingTimeOut", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final long getTimeStampCodeSent() {
            return 0L;
        }
        
        public final void setTimeStampCodeSent(long p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getStoredVerificationId() {
            return null;
        }
        
        public final void setStoredVerificationId(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
        }
        
        public final long remainingTimeOut() {
            return 0L;
        }
    }
}
package com.cardio.physician.ui.views.auth.login;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.cardio.physician.R;
import com.cardio.physician.domain.login.request.PhoneVerificationDetails;
import java.io.Serializable;
import java.lang.UnsupportedOperationException;
import kotlin.Int;
import kotlin.Suppress;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lcom/cardio/physician/ui/views/auth/login/LoginFragmentDirections;", "", "()V", "Companion", "LoginToPhoneVerification", "app_qaDebug"})
public final class LoginFragmentDirections {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.auth.login.LoginFragmentDirections.Companion Companion = null;
    
    private LoginFragmentDirections() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003J\u001f\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\b\u0010\u0011\u001a\u00020\u0005H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/cardio/physician/ui/views/auth/login/LoginFragmentDirections$LoginToPhoneVerification;", "Landroidx/navigation/NavDirections;", "phoneVerificationDetail", "Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;", "isComingFrom", "", "(Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;I)V", "()I", "getPhoneVerificationDetail", "()Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;", "component1", "component2", "copy", "equals", "", "other", "", "getActionId", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "", "app_qaDebug"})
    static final class LoginToPhoneVerification implements androidx.navigation.NavDirections {
        @org.jetbrains.annotations.Nullable()
        private final com.cardio.physician.domain.login.request.PhoneVerificationDetails phoneVerificationDetail = null;
        private final int isComingFrom = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.ui.views.auth.login.LoginFragmentDirections.LoginToPhoneVerification copy(@org.jetbrains.annotations.Nullable()
        com.cardio.physician.domain.login.request.PhoneVerificationDetails phoneVerificationDetail, int isComingFrom) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public LoginToPhoneVerification(@org.jetbrains.annotations.Nullable()
        com.cardio.physician.domain.login.request.PhoneVerificationDetails phoneVerificationDetail, int isComingFrom) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.cardio.physician.domain.login.request.PhoneVerificationDetails component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.cardio.physician.domain.login.request.PhoneVerificationDetails getPhoneVerificationDetail() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int isComingFrom() {
            return 0;
        }
        
        @java.lang.Override()
        public int getActionId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.Suppress(names = {"CAST_NEVER_SUCCEEDS"})
        @java.lang.Override()
        public android.os.Bundle getArguments() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u0004\u00a8\u0006\u000b"}, d2 = {"Lcom/cardio/physician/ui/views/auth/login/LoginFragmentDirections$Companion;", "", "()V", "loginToForgotPassword", "Landroidx/navigation/NavDirections;", "loginToPhoneVerification", "phoneVerificationDetail", "Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;", "isComingFrom", "", "loginToSignUp", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections loginToSignUp() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections loginToPhoneVerification(@org.jetbrains.annotations.Nullable()
        com.cardio.physician.domain.login.request.PhoneVerificationDetails phoneVerificationDetail, int isComingFrom) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections loginToForgotPassword() {
            return null;
        }
    }
}
package com.cardio.physician.ui.views.auth.signup;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavDirections;
import com.cardio.physician.R;
import com.cardio.physician.domain.login.request.PhoneVerificationDetails;
import java.io.Serializable;
import java.lang.UnsupportedOperationException;
import kotlin.Int;
import kotlin.String;
import kotlin.Suppress;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00032\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0006"}, d2 = {"Lcom/cardio/physician/ui/views/auth/signup/SignUpFragmentDirections;", "", "()V", "Companion", "SignupToPhoneVerification", "SignupToWebView", "app_qaDebug"})
public final class SignUpFragmentDirections {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.auth.signup.SignUpFragmentDirections.Companion Companion = null;
    
    private SignUpFragmentDirections() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003J\u001f\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\b\u0010\u0011\u001a\u00020\u0005H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/cardio/physician/ui/views/auth/signup/SignUpFragmentDirections$SignupToPhoneVerification;", "Landroidx/navigation/NavDirections;", "phoneVerificationDetail", "Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;", "isComingFrom", "", "(Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;I)V", "()I", "getPhoneVerificationDetail", "()Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;", "component1", "component2", "copy", "equals", "", "other", "", "getActionId", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "", "app_qaDebug"})
    static final class SignupToPhoneVerification implements androidx.navigation.NavDirections {
        @org.jetbrains.annotations.Nullable()
        private final com.cardio.physician.domain.login.request.PhoneVerificationDetails phoneVerificationDetail = null;
        private final int isComingFrom = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.ui.views.auth.signup.SignUpFragmentDirections.SignupToPhoneVerification copy(@org.jetbrains.annotations.Nullable()
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
        
        public SignupToPhoneVerification(@org.jetbrains.annotations.Nullable()
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
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0016"}, d2 = {"Lcom/cardio/physician/ui/views/auth/signup/SignUpFragmentDirections$SignupToWebView;", "Landroidx/navigation/NavDirections;", "toolBarTitle", "", "webUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "getToolBarTitle", "()Ljava/lang/String;", "getWebUrl", "component1", "component2", "copy", "equals", "", "other", "", "getActionId", "", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "app_qaDebug"})
    static final class SignupToWebView implements androidx.navigation.NavDirections {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String toolBarTitle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String webUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.ui.views.auth.signup.SignUpFragmentDirections.SignupToWebView copy(@org.jetbrains.annotations.Nullable()
        java.lang.String toolBarTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String webUrl) {
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
        
        public SignupToWebView(@org.jetbrains.annotations.Nullable()
        java.lang.String toolBarTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String webUrl) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getToolBarTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getWebUrl() {
            return null;
        }
        
        @java.lang.Override()
        public int getActionId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public android.os.Bundle getArguments() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bJ\u001a\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a8\u0006\r"}, d2 = {"Lcom/cardio/physician/ui/views/auth/signup/SignUpFragmentDirections$Companion;", "", "()V", "signupToPhoneVerification", "Landroidx/navigation/NavDirections;", "phoneVerificationDetail", "Lcom/cardio/physician/domain/login/request/PhoneVerificationDetails;", "isComingFrom", "", "signupToWebView", "toolBarTitle", "", "webUrl", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections signupToPhoneVerification(@org.jetbrains.annotations.Nullable()
        com.cardio.physician.domain.login.request.PhoneVerificationDetails phoneVerificationDetail, int isComingFrom) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections signupToWebView(@org.jetbrains.annotations.Nullable()
        java.lang.String toolBarTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String webUrl) {
            return null;
        }
    }
}
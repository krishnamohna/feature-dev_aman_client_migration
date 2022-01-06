package com.cardio.physician.network.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/cardio/physician/network/api/Constants;", "", "Companion", "app_qaDebug"})
public abstract interface Constants {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.network.api.Constants.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "Login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOOGLE_SIGNUP = "google_sign_up";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SIGNUP = "Signup";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VALIDATION = "Validation";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FORGOT_PASSWORD = "ForgotPassword";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PHONE_VERIFICATION = "PhoneVerification";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PHONE_AUTHENTICATION = "PhoneAuthentication";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEND_OTP = "SendOtp";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UPDATE_EMAIL_AND_PASSWORD = "UpdateEmailAndPassword";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_DETAIL = "UserDetail";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_PROFILE_PIC = "UserProfilePic";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UPLOAD_PROFILE_PIC = "UploadProfilePic";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_GENDER = "UserGender";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EDIT_PROFILE = "EditProfile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANGE_PASSWORD = "ChangePassword";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANGE_EMAIL = "Change_email";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EMAIL_SEND_VERIFICATION = "email_send_verification";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ADD_PATIENT = "AddPatient";
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/cardio/physician/network/api/Constants$Companion;", "", "()V", "ADD_PATIENT", "", "CHANGE_EMAIL", "CHANGE_PASSWORD", "EDIT_PROFILE", "EMAIL_SEND_VERIFICATION", "FORGOT_PASSWORD", "GOOGLE_SIGNUP", "LOGIN", "PHONE_AUTHENTICATION", "PHONE_VERIFICATION", "SEND_OTP", "SIGNUP", "UPDATE_EMAIL_AND_PASSWORD", "UPLOAD_PROFILE_PIC", "USER_DETAIL", "USER_GENDER", "USER_PROFILE_PIC", "VALIDATION", "app_qaDebug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LOGIN = "Login";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GOOGLE_SIGNUP = "google_sign_up";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SIGNUP = "Signup";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String VALIDATION = "Validation";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String FORGOT_PASSWORD = "ForgotPassword";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PHONE_VERIFICATION = "PhoneVerification";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PHONE_AUTHENTICATION = "PhoneAuthentication";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SEND_OTP = "SendOtp";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String UPDATE_EMAIL_AND_PASSWORD = "UpdateEmailAndPassword";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String USER_DETAIL = "UserDetail";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String USER_PROFILE_PIC = "UserProfilePic";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String UPLOAD_PROFILE_PIC = "UploadProfilePic";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String USER_GENDER = "UserGender";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String EDIT_PROFILE = "EditProfile";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String CHANGE_PASSWORD = "ChangePassword";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String CHANGE_EMAIL = "Change_email";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String EMAIL_SEND_VERIFICATION = "email_send_verification";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String ADD_PATIENT = "AddPatient";
        
        private Companion() {
            super();
        }
    }
}
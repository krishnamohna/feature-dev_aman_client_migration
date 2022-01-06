package com.cardio.physician.ui.views.auth.phone_verification;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentPhoneNumberVerificationBinding;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth;
import com.cardio.physician.ui.common.utils.ENUM;
import com.cardio.physician.ui.common.utils.ERROR;
import com.cardio.physician.ui.views.dashboard.DashboardActivity;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import dagger.hilt.android.AndroidEntryPoint;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0002FGB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010&\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010\'\u001a\u00020(H\u0002J\u0016\u0010)\u001a\u00020 2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002J\b\u0010-\u001a\u00020 H\u0002J\u0018\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0002J\u0012\u00101\u001a\u00020 2\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020 H\u0016J\b\u00105\u001a\u00020 H\u0016J\u001a\u00106\u001a\u00020 2\u0006\u00107\u001a\u0002032\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010:\u001a\u00020 H\u0002J\b\u0010;\u001a\u00020 H\u0002J\b\u0010<\u001a\u00020 H\u0002J\b\u0010=\u001a\u00020 H\u0002J\b\u0010>\u001a\u00020 H\u0002J\b\u0010?\u001a\u00020 H\u0002J \u0010@\u001a\u00020 2\u0006\u0010A\u001a\u00020(2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020%H\u0002J\b\u0010E\u001a\u00020 H\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R#\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006H"}, d2 = {"Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneNumberVerificationFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseFragmentAuth;", "Landroid/view/View$OnClickListener;", "()V", "arrayOfEditText", "", "Landroidx/appcompat/widget/AppCompatEditText;", "[Landroidx/appcompat/widget/AppCompatEditText;", "binding", "Lcom/cardio/physician/databinding/FragmentPhoneNumberVerificationBinding;", "kotlin.jvm.PlatformType", "getBinding", "()Lcom/cardio/physician/databinding/FragmentPhoneNumberVerificationBinding;", "binding$delegate", "Lcom/cardio/physician/ui/common/utils/viewbinding/FragmentViewBindingDelegate;", "countDownTimer", "Landroid/os/CountDownTimer;", "navArgs", "Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneNumberVerificationFragmentArgs;", "getNavArgs", "()Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneNumberVerificationFragmentArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "otpTimeOut", "", "viewModel", "Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneVerificationViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneVerificationViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "clearOtpField", "", "enableButtonClick", "alpha", "", "clickable", "", "enableResendBtn", "getOtpFromView", "", "handleApiCallback", "apiResponse", "Lcom/cardio/physician/network/Resource;", "", "moveToNextScreen", "moveToNextView", "selectedEditText", "nextEditText", "onClick", "v", "Landroid/view/View;", "onDestroyView", "onResume", "onViewCreated", "view", "savedInstanceState", "Landroid/os/Bundle;", "removeInputTextOnResetOtp", "resendVerificationCode", "setListeners", "setNavArgsInFields", "setObserver", "setOtpTimer", "setTimerOnView", "text", "visibility", "", "enableBtn", "showVerificationEmail", "GenericKeyEvent", "TextChangeWatcher", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class PhoneNumberVerificationFragment extends com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth implements android.view.View.OnClickListener {
    private final com.cardio.physician.ui.common.utils.viewbinding.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private androidx.appcompat.widget.AppCompatEditText[] arrayOfEditText;
    private final androidx.navigation.NavArgsLazy navArgs$delegate = null;
    private android.os.CountDownTimer countDownTimer;
    private long otpTimeOut = 60000L;
    
    public PhoneNumberVerificationFragment() {
        super(0);
    }
    
    private final com.cardio.physician.databinding.FragmentPhoneNumberVerificationBinding getBinding() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.auth.phone_verification.PhoneVerificationViewModel getViewModel() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.auth.phone_verification.PhoneNumberVerificationFragmentArgs getNavArgs() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void setNavArgsInFields() {
    }
    
    private final void setObserver() {
    }
    
    private final void setListeners() {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    private final void moveToNextView(androidx.appcompat.widget.AppCompatEditText selectedEditText, androidx.appcompat.widget.AppCompatEditText nextEditText) {
    }
    
    private final java.lang.String getOtpFromView() {
        return null;
    }
    
    private final void handleApiCallback(com.cardio.physician.network.Resource<? extends java.lang.Object> apiResponse) {
    }
    
    private final void clearOtpField() {
    }
    
    private final void enableButtonClick(float alpha, boolean clickable) {
    }
    
    private final void showVerificationEmail() {
    }
    
    private final void resendVerificationCode() {
    }
    
    private final void moveToNextScreen() {
    }
    
    private final void setOtpTimer() {
    }
    
    private final void setTimerOnView(java.lang.String text, int visibility, boolean enableBtn) {
    }
    
    private final void removeInputTextOnResetOtp() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void enableResendBtn(float alpha, boolean clickable) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J(\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneNumberVerificationFragment$TextChangeWatcher;", "Landroid/text/TextWatcher;", "view", "Landroid/view/View;", "(Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneNumberVerificationFragment;Landroid/view/View;)V", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onTextChanged", "before", "app_qaDebug"})
    public final class TextChangeWatcher implements android.text.TextWatcher {
        private android.view.View view;
        
        public TextChangeWatcher(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super();
        }
        
        @java.lang.Override()
        public void beforeTextChanged(@org.jetbrains.annotations.NotNull()
        java.lang.CharSequence s, int start, int count, int after) {
        }
        
        @java.lang.Override()
        public void onTextChanged(@org.jetbrains.annotations.NotNull()
        java.lang.CharSequence s, int start, int before, int count) {
        }
        
        @java.lang.Override()
        public void afterTextChanged(@org.jetbrains.annotations.Nullable()
        android.text.Editable p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\"\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/cardio/physician/ui/views/auth/phone_verification/PhoneNumberVerificationFragment$GenericKeyEvent;", "Landroid/view/View$OnKeyListener;", "currentView", "Landroid/widget/EditText;", "previousView", "(Landroid/widget/EditText;Landroid/widget/EditText;)V", "onKey", "", "v", "Landroid/view/View;", "keyCode", "", "event", "Landroid/view/KeyEvent;", "app_qaDebug"})
    static final class GenericKeyEvent implements android.view.View.OnKeyListener {
        private final android.widget.EditText currentView = null;
        private final android.widget.EditText previousView = null;
        
        public GenericKeyEvent(@org.jetbrains.annotations.NotNull()
        android.widget.EditText currentView, @org.jetbrains.annotations.Nullable()
        android.widget.EditText previousView) {
            super();
        }
        
        @java.lang.Override()
        public boolean onKey(@org.jetbrains.annotations.Nullable()
        android.view.View v, int keyCode, @org.jetbrains.annotations.NotNull()
        android.view.KeyEvent event) {
            return false;
        }
    }
}
package com.cardio.physician.ui.views.auth.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentLoginBinding;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.network.NetworkHelper;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.views.dashboard.DashboardActivity;
import com.countrypicker.CountrySelectActivity;
import com.countrypicker.bean.CountryData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001IB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0018\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0010H\u0002J\u0016\u0010\'\u001a\u00020\"2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J\b\u0010+\u001a\u00020\"H\u0002J \u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020\u0005H\u0002J(\u00101\u001a\u00020\"2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020.2\u0006\u00105\u001a\u00020.2\u0006\u00106\u001a\u00020\u0005H\u0002J\u0010\u00107\u001a\u00020\"2\u0006\u00102\u001a\u000203H\u0002J\u0010\u00108\u001a\u00020\"2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0012\u00109\u001a\u00020\"2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010<\u001a\u00020\"H\u0016J\u001a\u0010=\u001a\u00020\"2\u0006\u0010:\u001a\u00020;2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\b\u0010@\u001a\u00020\"H\u0002J\b\u0010A\u001a\u00020\"H\u0002J\u0012\u0010B\u001a\u00020\"2\b\u0010C\u001a\u0004\u0018\u00010DH\u0002J\b\u0010E\u001a\u00020\"H\u0002J\b\u0010F\u001a\u00020\"H\u0002J\u0010\u0010G\u001a\u00020\"2\u0006\u0010H\u001a\u00020\u0005H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006J"}, d2 = {"Lcom/cardio/physician/ui/views/auth/login/LoginFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseFragmentAuth;", "Landroid/view/View$OnClickListener;", "()V", "apiConstant", "", "binding", "Lcom/cardio/physician/databinding/FragmentLoginBinding;", "kotlin.jvm.PlatformType", "getBinding", "()Lcom/cardio/physician/databinding/FragmentLoginBinding;", "binding$delegate", "Lcom/cardio/physician/ui/common/utils/viewbinding/FragmentViewBindingDelegate;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "isPasswordVisible", "", "networkHelper", "Lcom/cardio/physician/network/NetworkHelper;", "getNetworkHelper", "()Lcom/cardio/physician/network/NetworkHelper;", "setNetworkHelper", "(Lcom/cardio/physician/network/NetworkHelper;)V", "resultGoogleSignIn", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "resultLauncher", "viewModel", "Lcom/cardio/physician/ui/views/auth/login/LoginViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/auth/login/LoginViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkIsUserVerified", "", "enableButtonClick", "alpha", "", "clickable", "handleApiCallback", "apiResponse", "Lcom/cardio/physician/network/Resource;", "", "initializeGoogleSign", "manageCountryCodeVisibility", "countryCodeVisibility", "", "passwordVisibility", "loginBtnText", "manageViewVisibility", "validationModel", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "bgDrawable", "tvValidatorVisibility", "message", "manageViewsForValidation", "moveToNextScreen", "onClick", "view", "Landroid/view/View;", "onResume", "onViewCreated", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "setObservers", "showDialogEmailNotVerified", "user", "Lcom/google/firebase/auth/FirebaseUser;", "signWithGoogle", "startActivityForCountryCode", "startPhoneNumberVerification", "phoneNumber", "TextChangeWatcher", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class LoginFragment extends com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth implements android.view.View.OnClickListener {
    private final com.cardio.physician.ui.common.utils.viewbinding.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient;
    private boolean isPasswordVisible = false;
    private java.lang.String apiConstant;
    @javax.inject.Inject()
    public com.cardio.physician.network.NetworkHelper networkHelper;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultLauncher;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultGoogleSignIn;
    
    public LoginFragment() {
        super(0);
    }
    
    private final com.cardio.physician.databinding.FragmentLoginBinding getBinding() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.auth.login.LoginViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.network.NetworkHelper getNetworkHelper() {
        return null;
    }
    
    public final void setNetworkHelper(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.NetworkHelper p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void initializeGoogleSign() {
    }
    
    private final void setListener() {
    }
    
    private final void manageCountryCodeVisibility(int countryCodeVisibility, int passwordVisibility, java.lang.String loginBtnText) {
    }
    
    private final void setObservers() {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View view) {
    }
    
    private final void startActivityForCountryCode() {
    }
    
    private final void handleApiCallback(com.cardio.physician.network.Resource<? extends java.lang.Object> apiResponse) {
    }
    
    private final void signWithGoogle() {
    }
    
    private final void checkIsUserVerified(java.lang.String apiConstant) {
    }
    
    private final void showDialogEmailNotVerified(com.google.firebase.auth.FirebaseUser user) {
    }
    
    private final void startPhoneNumberVerification(java.lang.String phoneNumber) {
    }
    
    private final void moveToNextScreen(java.lang.String apiConstant) {
    }
    
    private final void enableButtonClick(float alpha, boolean clickable) {
    }
    
    private final void manageViewsForValidation(com.cardio.physician.domain.common.model.ValidationModel validationModel) {
    }
    
    private final void manageViewVisibility(com.cardio.physician.domain.common.model.ValidationModel validationModel, int bgDrawable, int tvValidatorVisibility, java.lang.String message) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J(\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J(\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/cardio/physician/ui/views/auth/login/LoginFragment$TextChangeWatcher;", "Landroid/text/TextWatcher;", "view", "Landroid/view/View;", "errorTxt", "Landroid/widget/TextView;", "(Lcom/cardio/physician/ui/views/auth/login/LoginFragment;Landroid/view/View;Landroid/widget/TextView;)V", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onTextChanged", "before", "app_qaDebug"})
    public final class TextChangeWatcher implements android.text.TextWatcher {
        private android.view.View view;
        private final android.widget.TextView errorTxt = null;
        
        public TextChangeWatcher(@org.jetbrains.annotations.NotNull()
        android.view.View view, @org.jetbrains.annotations.NotNull()
        android.widget.TextView errorTxt) {
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
}
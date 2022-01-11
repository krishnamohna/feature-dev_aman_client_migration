package com.cardio.physician.ui.common.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.fcm.FcmManager;
import com.cardio.physician.domain.fitness.FitnessRepositary;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.listeners.DialogHelper;
import com.cardio.physician.ui.common.listeners.DialogProvider;
import com.cardio.physician.ui.common.utils.DialogHelperImpl;
import com.cardio.physician.ui.common.utils.NotificationUtil;
import com.cardio.physician.ui.views.auth.AuthenticateUserActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.ktx.Firebase;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import javax.inject.Named;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020*J\b\u0010,\u001a\u00020*H\u0016J\u0012\u0010-\u001a\u00020*2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\"\u00100\u001a\u00020*2\b\u00101\u001a\u0004\u0018\u0001022\u0010\b\u0002\u00103\u001a\n\u0018\u000104j\u0004\u0018\u0001`5J\b\u00106\u001a\u00020*H\u0014J\b\u00107\u001a\u00020*H\u0002J\b\u00108\u001a\u00020\u000bH\u0016J,\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u0002022\b\b\u0002\u0010=\u001a\u00020\u001f2\b\b\u0002\u0010>\u001a\u00020\u001fH\u0004J\b\u0010?\u001a\u00020*H\u0004J\u0010\u0010?\u001a\u00020*2\u0006\u0010?\u001a\u00020\u001fH\u0004J\u0006\u0010@\u001a\u00020*J6\u0010A\u001a\b\u0012\u0004\u0012\u0002HC0B\"\b\b\u0000\u0010C*\u00020D*\u00020\u00012\u0014\b\u0004\u0010E\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u0002HC0FH\u0086\b\u00f8\u0001\u0000R\u001b\u0010\u0004\u001a\u00020\u00058DX\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\t\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u0003\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010 \"\u0004\b!\u0010\"R\u001e\u0010#\u001a\u00020$8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006H"}, d2 = {"Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/cardio/physician/ui/common/listeners/DialogProvider;", "()V", "baseViewModel", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "getBaseViewModel", "()Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "baseViewModel$delegate", "Lkotlin/Lazy;", "dialogHelper", "Lcom/cardio/physician/ui/common/listeners/DialogHelper;", "dialogHelperImps", "Lcom/cardio/physician/ui/common/utils/DialogHelperImpl;", "getDialogHelperImps", "()Lcom/cardio/physician/ui/common/utils/DialogHelperImpl;", "dialogHelperImps$delegate", "fcmManager", "Lcom/cardio/physician/data/remote/fcm/FcmManager;", "getFcmManager", "()Lcom/cardio/physician/data/remote/fcm/FcmManager;", "setFcmManager", "(Lcom/cardio/physician/data/remote/fcm/FcmManager;)V", "fitbit", "Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "getFitbit$annotations", "getFitbit", "()Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "setFitbit", "(Lcom/cardio/physician/domain/fitness/FitnessRepositary;)V", "isBackButtonDisabled", "", "()Z", "setBackButtonDisabled", "(Z)V", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "getUserManager", "()Lcom/cardio/physician/data/local/UserManager;", "setUserManager", "(Lcom/cardio/physician/data/local/UserManager;)V", "hideProgress", "", "logoutGoogleIfLoggedIn", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "msg", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onPause", "openLoginActivity", "provideDialogHelper", "setUpToolbar", "view", "Landroid/view/View;", "title", "backBtnVisibility", "editProfile", "showProgress", "signOut", "viewBinding", "Lkotlin/Lazy;", "T", "Landroidx/viewbinding/ViewBinding;", "bindingInflater", "Lkotlin/Function1;", "Landroid/view/LayoutInflater;", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity implements com.cardio.physician.ui.common.listeners.DialogProvider {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy baseViewModel$delegate = null;
    private com.cardio.physician.ui.common.listeners.DialogHelper dialogHelper;
    private boolean isBackButtonDisabled = false;
    @javax.inject.Inject()
    public com.cardio.physician.data.remote.fcm.FcmManager fcmManager;
    @javax.inject.Inject()
    public com.cardio.physician.data.local.UserManager userManager;
    @javax.inject.Inject()
    public com.cardio.physician.domain.fitness.FitnessRepositary fitbit;
    private final kotlin.Lazy dialogHelperImps$delegate = null;
    
    public BaseActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel getBaseViewModel() {
        return null;
    }
    
    public final boolean isBackButtonDisabled() {
        return false;
    }
    
    public final void setBackButtonDisabled(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.data.remote.fcm.FcmManager getFcmManager() {
        return null;
    }
    
    public final void setFcmManager(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.fcm.FcmManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.data.local.UserManager getUserManager() {
        return null;
    }
    
    public final void setUserManager(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.FitnessRepositary getFitbit() {
        return null;
    }
    
    @javax.inject.Named(value = "FITBIT")
    @java.lang.Deprecated()
    public static void getFitbit$annotations() {
    }
    
    public final void setFitbit(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.FitnessRepositary p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final com.cardio.physician.ui.common.utils.DialogHelperImpl getDialogHelperImps() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.common.listeners.DialogHelper provideDialogHelper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final <T extends androidx.viewbinding.ViewBinding>kotlin.Lazy<T> viewBinding(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity $this$viewBinding, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.view.LayoutInflater, ? extends T> bindingInflater) {
        return null;
    }
    
    public final void signOut() {
    }
    
    public final void logoutGoogleIfLoggedIn() {
    }
    
    private final void openLoginActivity() {
    }
    
    protected final void showProgress() {
    }
    
    protected final void showProgress(boolean showProgress) {
    }
    
    public final void hideProgress() {
    }
    
    public final void onError(@org.jetbrains.annotations.Nullable()
    java.lang.String msg, @org.jetbrains.annotations.Nullable()
    java.lang.Exception exception) {
    }
    
    protected final void setUpToolbar(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    java.lang.String title, boolean backBtnVisibility, boolean editProfile) {
    }
}
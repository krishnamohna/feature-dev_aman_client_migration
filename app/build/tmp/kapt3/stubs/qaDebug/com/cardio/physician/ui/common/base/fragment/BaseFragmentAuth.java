package com.cardio.physician.ui.common.base.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.cardio.physician.R;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.listeners.DialogHelper;
import com.cardio.physician.ui.common.listeners.DialogProvider;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0004J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\rH\u0016J,\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0004J$\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0 J\b\u0010!\u001a\u00020\u000fH\u0004R\u001b\u0010\u0006\u001a\u00020\u00078DX\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/cardio/physician/ui/common/base/fragment/BaseFragmentAuth;", "Landroidx/fragment/app/Fragment;", "Lcom/cardio/physician/ui/common/listeners/DialogProvider;", "layoutResId", "", "(I)V", "baseViewModel", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "getBaseViewModel", "()Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "baseViewModel$delegate", "Lkotlin/Lazy;", "dialogHelper", "Lcom/cardio/physician/ui/common/listeners/DialogHelper;", "hideProgress", "", "onAttach", "context", "Landroid/content/Context;", "onResume", "provideDialogHelper", "setUpToolbar", "view", "Landroid/view/View;", "title", "", "backBtnVisibility", "", "editProfile", "showLogout", "description", "onLogout", "Lkotlin/Function0;", "showProgress", "app_qaDebug"})
public abstract class BaseFragmentAuth extends androidx.fragment.app.Fragment implements com.cardio.physician.ui.common.listeners.DialogProvider {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy baseViewModel$delegate = null;
    private com.cardio.physician.ui.common.listeners.DialogHelper dialogHelper;
    
    public BaseFragmentAuth(@androidx.annotation.LayoutRes()
    int layoutResId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel getBaseViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    protected final void showProgress() {
    }
    
    protected final void hideProgress() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    protected final void setUpToolbar(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    java.lang.String title, boolean backBtnVisibility, boolean editProfile) {
    }
    
    public final void showLogout(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onLogout) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.common.listeners.DialogHelper provideDialogHelper() {
        return null;
    }
}
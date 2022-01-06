package com.cardio.physician.ui.views.splash;

import android.content.Intent;
import android.os.Bundle;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.databinding.ActivitySplashBinding;
import com.cardio.physician.ui.views.auth.AuthenticateUserActivity;
import com.cardio.physician.ui.views.dashboard.DashboardActivity;
import com.cardio.physician.ui.views.tutorial.TutorialActivity;
import com.cardio.physician.ui.common.utils.Preference;
import com.google.firebase.ktx.Firebase;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0014\u0010\r\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/cardio/physician/ui/views/splash/SplashActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "()V", "binding", "Lcom/cardio/physician/databinding/ActivitySplashBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivitySplashBinding;", "binding$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "openActivity", "activity", "Ljava/lang/Class;", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class SplashActivity extends com.cardio.physician.ui.common.base.activity.BaseActivity {
    private final kotlin.Lazy binding$delegate = null;
    
    public SplashActivity() {
        super();
    }
    
    private final com.cardio.physician.databinding.ActivitySplashBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void openActivity(java.lang.Class<?> activity) {
    }
}
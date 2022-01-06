package com.cardio.physician.data.remote.fitnesstracker.googlefit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.domain.fitness.FitnessRepositary;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.fitness.model.SyncModel;
import com.cardio.physician.ui.common.utils.Preference;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJK\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\f0\u00102%\u0010\u0012\u001a!\u0012\u0017\u0012\u00150\u0013j\u0002`\u0014\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\f0\u0010H\u0016JS\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f0\u00102%\u0010\u0012\u001a!\u0012\u0017\u0012\u00150\u0013j\u0002`\u0014\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\f0\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\u001e\u0010\u001f\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\fH\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/GoogleFitBitRepositaryImp;", "Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "googleFitManager", "Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/GoogleFitManager;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "context", "Landroid/content/Context;", "(Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/GoogleFitManager;Lcom/cardio/physician/data/local/UserManager;Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getProfileData", "", "activity", "Landroid/app/Activity;", "onSuccess", "Lkotlin/Function1;", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "onFailure", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lkotlin/ParameterName;", "name", "msg", "getSyncModel", "Lcom/cardio/physician/domain/fitness/model/SyncModel;", "periodDays", "", "isLoggedIn", "", "isSelected", "login", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "fragment", "Landroidx/fragment/app/Fragment;", "logout", "app_qaDebug"})
public final class GoogleFitBitRepositaryImp implements com.cardio.physician.domain.fitness.FitnessRepositary {
    private final com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitManager googleFitManager = null;
    private final com.cardio.physician.data.local.UserManager userManager = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public GoogleFitBitRepositaryImp(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.fitnesstracker.googlefit.GoogleFitManager googleFitManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @java.lang.Override()
    public void getProfileData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.FitnessModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure) {
    }
    
    @java.lang.Override()
    public void getSyncModel(@org.jetbrains.annotations.NotNull()
    android.content.Context activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.SyncModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure, int periodDays) {
    }
    
    @java.lang.Override()
    public boolean isLoggedIn() {
        return false;
    }
    
    @java.lang.Override()
    public boolean isSelected() {
        return false;
    }
    
    @java.lang.Override()
    public void login(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResultLauncher<android.content.Intent> activity, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void login(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    @java.lang.Override()
    public void logout() {
    }
}
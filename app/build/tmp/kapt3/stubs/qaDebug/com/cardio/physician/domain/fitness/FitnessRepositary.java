package com.cardio.physician.domain.fitness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import com.cardio.physician.domain.fitness.model.FitnessModel;
import com.cardio.physician.domain.fitness.model.SyncModel;
import java.lang.Exception;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JG\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\u0007H&JO\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000f2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00030\u00072!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0014H&J\u001e\u0010\u0016\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u000fH&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\b\u0010\u001c\u001a\u00020\u0003H&\u00a8\u0006\u001d"}, d2 = {"Lcom/cardio/physician/domain/fitness/FitnessRepositary;", "", "getProfileData", "", "activity", "Landroid/app/Activity;", "onSuccess", "Lkotlin/Function1;", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "onFailure", "Ljava/lang/Exception;", "Lkotlin/ParameterName;", "name", "msg", "getSyncModel", "Landroid/content/Context;", "Lcom/cardio/physician/domain/fitness/model/SyncModel;", "periodDays", "", "isLoggedIn", "", "isSelected", "login", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "context", "fragment", "Landroidx/fragment/app/Fragment;", "logout", "app_qaDebug"})
public abstract interface FitnessRepositary {
    
    public abstract void getProfileData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.FitnessModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure);
    
    public abstract void getSyncModel(@org.jetbrains.annotations.NotNull()
    android.content.Context activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.SyncModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure, int periodDays);
    
    public abstract boolean isLoggedIn();
    
    public abstract boolean isSelected();
    
    public abstract void login(@org.jetbrains.annotations.NotNull()
    androidx.activity.result.ActivityResultLauncher<android.content.Intent> activity, @org.jetbrains.annotations.NotNull()
    android.content.Context context);
    
    public abstract void login(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment);
    
    public abstract void logout();
}
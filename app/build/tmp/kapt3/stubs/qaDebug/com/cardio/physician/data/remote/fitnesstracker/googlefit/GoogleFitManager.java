package com.cardio.physician.data.remote.fitnesstracker.googlefit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.cardio.physician.domain.fitness.model.*;
import com.cardio.physician.network.NetworkError;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.HealthDataTypes;
import java.util.*;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JQ\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00150\u00172%\u0010\u0019\u001a!\u0012\u0017\u0012\u00150\u001aj\u0002`\u001b\u00a2\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00150\u00172\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020\"H\u0002JI\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00150\u00172%\u0010\u0019\u001a!\u0012\u0017\u0012\u00150\u001aj\u0002`\u001b\u00a2\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00150\u0017J\u0006\u0010\'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020\u0015J\b\u0010-\u001a\u00020(H\u0002J \u0010.\u001a\u00020\u00152\u0006\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020 2\b\u00101\u001a\u0004\u0018\u000102R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012\u00a8\u00064"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/GoogleFitManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getContext", "()Landroid/content/Context;", "dataParser", "Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/DataParserGoogleFit;", "getDataParser", "()Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/DataParserGoogleFit;", "dataParser$delegate", "Lkotlin/Lazy;", "fitnessOptions", "Lcom/google/android/gms/fitness/FitnessOptions;", "getFitnessOptions", "()Lcom/google/android/gms/fitness/FitnessOptions;", "fitnessOptions$delegate", "getFitnessLogs", "", "onSuccess", "Lkotlin/Function1;", "Lcom/cardio/physician/domain/fitness/model/SyncModel;", "onFailure", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lkotlin/ParameterName;", "name", "msg", "periodDays", "", "getGoogleAccount", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "getProfileData", "activity", "Landroid/app/Activity;", "Lcom/cardio/physician/domain/fitness/model/FitnessModel;", "isLoggedIn", "", "login", "fragment", "Landroidx/fragment/app/Fragment;", "logout", "oAuthPermissionsApproved", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "FitActionRequestCode", "app_qaDebug"})
public final class GoogleFitManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final kotlin.Lazy dataParser$delegate = null;
    private final java.lang.String TAG = "GoogleFitManager";
    private final kotlin.Lazy fitnessOptions$delegate = null;
    
    public GoogleFitManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    private final com.cardio.physician.data.remote.fitnesstracker.googlefit.DataParserGoogleFit getDataParser() {
        return null;
    }
    
    private final com.google.android.gms.fitness.FitnessOptions getFitnessOptions() {
        return null;
    }
    
    private final com.google.android.gms.auth.api.signin.GoogleSignInAccount getGoogleAccount() {
        return null;
    }
    
    private final boolean oAuthPermissionsApproved() {
        return false;
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    public final void login(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    public final void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public final void getProfileData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.FitnessModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure) {
    }
    
    public final void logout() {
    }
    
    public final void getFitnessLogs(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.SyncModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Exception, kotlin.Unit> onFailure, int periodDays) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/googlefit/GoogleFitManager$FitActionRequestCode;", "", "(Ljava/lang/String;I)V", "READ_DATA", "UPDATE_AND_READ_DATA", "DELETE_DATA", "app_qaDebug"})
    public static enum FitActionRequestCode {
        /*public static final*/ READ_DATA /* = new READ_DATA() */,
        /*public static final*/ UPDATE_AND_READ_DATA /* = new UPDATE_AND_READ_DATA() */,
        /*public static final*/ DELETE_DATA /* = new DELETE_DATA() */;
        
        FitActionRequestCode() {
        }
    }
}
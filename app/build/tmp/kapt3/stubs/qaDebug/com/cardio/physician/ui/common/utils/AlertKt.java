package com.cardio.physician.ui.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import com.cardio.physician.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.*;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a,\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0006\u0010\u0010\u001a\u00020\u0007\u001a?\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\u0002\u0010\u001b\u001aT\u0010\u001c\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\u00182\u001a\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0007\u0018\u00010$\u001a8\u0010&\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00070$\u001a\u0089\u0001\u0010\'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020)2!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110,\u00a2\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00070+2!\u0010/\u001a\u001d\u0012\u0013\u0012\u00110,\u00a2\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00070+2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u0007012\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u0007012\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0002\u00103\u001a*\u00104\u001a\u00020\u00072\u0006\u0010(\u001a\u00020)2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u0007012\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u000701\u001aB\u00107\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u001d2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u0007012\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u0007012\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u0007012\b\b\u0002\u00109\u001a\u00020\u0018\u001a:\u0010:\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u001a\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0007\u0018\u00010$\u001a*\u0010;\u001a\u00020\u00072\u0006\u0010(\u001a\u00020)2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u0007012\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000701\u001a\u0016\u0010>\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000f\u001a\u0018\u0010>\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\r\"\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\u00a8\u0006?"}, d2 = {"mBottomSheetDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "getMBottomSheetDialog", "()Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "setMBottomSheetDialog", "(Lcom/google/android/material/bottomsheet/BottomSheetDialog;)V", "customSnackBarFail", "", "context", "Landroid/content/Context;", "view", "Landroid/view/View;", "msg", "", "duration", "", "dismissBottomSheet", "openCalendarDialog", "calendar", "Ljava/util/Calendar;", "startDate", "", "endDate", "startDateCalendar", "", "listener", "Landroid/app/DatePickerDialog$OnDateSetListener;", "(Landroid/content/Context;Ljava/util/Calendar;Ljava/lang/Long;Ljava/lang/Long;ZLandroid/app/DatePickerDialog$OnDateSetListener;)V", "showAlertDialog", "Landroid/app/Activity;", "title", "description", "btnOneTitle", "btnTwoTitle", "btnTwoVisibility", "mCallBack", "Lkotlin/Function2;", "Landroid/content/DialogInterface;", "showConfirmAlertDialog", "showDashboardFilter", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "onStartDateClicked", "Lkotlin/Function1;", "Landroidx/appcompat/widget/AppCompatTextView;", "Lkotlin/ParameterName;", "name", "onEndDateClicked", "onApplyClicked", "Lkotlin/Function0;", "onClearClicked", "(Landroidx/appcompat/app/AppCompatActivity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Ljava/lang/Long;Ljava/lang/Long;)V", "showFilePickOptions", "onTakePicture", "onGalleryClick", "showFilePickOptionsForEditProfile", "onRemovePhotoClick", "showRemovePhotoOption", "showInfoAlertDialog", "showPhysicianPickOption", "onPatientClicked", "onIllnessClicked", "showToast", "app_qaDebug"})
public final class AlertKt {
    @org.jetbrains.annotations.Nullable()
    private static com.google.android.material.bottomsheet.BottomSheetDialog mBottomSheetDialog;
    
    @org.jetbrains.annotations.Nullable()
    public static final com.google.android.material.bottomsheet.BottomSheetDialog getMBottomSheetDialog() {
        return null;
    }
    
    public static final void setMBottomSheetDialog(@org.jetbrains.annotations.Nullable()
    com.google.android.material.bottomsheet.BottomSheetDialog p0) {
    }
    
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int msg) {
    }
    
    @android.annotation.SuppressLint(value = {"UseCompatLoadingForDrawables"})
    public static final void customSnackBarFail(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    java.lang.String msg, int duration) {
    }
    
    public static final void showInfoAlertDialog(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super android.content.DialogInterface, kotlin.Unit> mCallBack) {
    }
    
    public static final void showConfirmAlertDialog(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super android.content.DialogInterface, kotlin.Unit> mCallBack) {
    }
    
    public static final void showAlertDialog(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String btnOneTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String btnTwoTitle, boolean btnTwoVisibility, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super android.content.DialogInterface, kotlin.Unit> mCallBack) {
    }
    
    public static final void showFilePickOptions(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onTakePicture, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onGalleryClick) {
    }
    
    public static final void showPhysicianPickOption(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPatientClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onIllnessClicked) {
    }
    
    public static final void openCalendarDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.Calendar calendar, @org.jetbrains.annotations.Nullable()
    java.lang.Long startDate, @org.jetbrains.annotations.Nullable()
    java.lang.Long endDate, boolean startDateCalendar, @org.jetbrains.annotations.NotNull()
    android.app.DatePickerDialog.OnDateSetListener listener) {
    }
    
    public static final void showDashboardFilter(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.appcompat.widget.AppCompatTextView, kotlin.Unit> onStartDateClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.appcompat.widget.AppCompatTextView, kotlin.Unit> onEndDateClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onApplyClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClearClicked, @org.jetbrains.annotations.Nullable()
    java.lang.Long startDate, @org.jetbrains.annotations.Nullable()
    java.lang.Long endDate) {
    }
    
    public static final void showFilePickOptionsForEditProfile(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onTakePicture, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onGalleryClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRemovePhotoClick, boolean showRemovePhotoOption) {
    }
    
    public static final void dismissBottomSheet() {
    }
}
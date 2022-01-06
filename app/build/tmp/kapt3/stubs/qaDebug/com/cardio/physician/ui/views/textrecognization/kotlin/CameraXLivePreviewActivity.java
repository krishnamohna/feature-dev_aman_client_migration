package com.cardio.physician.ui.views.textrecognization.kotlin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import androidx.annotation.RequiresApi;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.cardio.physician.R;
import com.cardio.physician.databinding.ActivityVisionCameraxLivePreviewBinding;
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.customviews.toolbar.TextRecogToolbarImp;
import com.cardio.physician.ui.common.utils.EXTRAS;
import com.cardio.physician.ui.views.textrecognization.CameraXViewModel;
import com.cardio.physician.ui.views.textrecognization.VisionImageProcessor;
import com.cardio.physician.ui.views.textrecognization.preference.PreferenceUtils;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.material.chip.Chip;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.demo.kotlin.textdetector.TextRecognitionProcessor;
import java.util.*;

/**
 * Live preview demo app for ML Kit APIs using CameraX.
 */
@androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.LOLLIPOP)
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\b\b\u0007\u0018\u0000 O2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001OB\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010+\u001a\u00020\u0018H\u0002J\b\u0010,\u001a\u00020\u001bH\u0002J\b\u0010-\u001a\u00020\u001bH\u0002J\b\u0010.\u001a\u00020\u001bH\u0002J\b\u0010/\u001a\u000200H\u0016J\u0010\u00101\u001a\u00020\u001b2\b\u00102\u001a\u0004\u0018\u000103J\u0018\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0018H\u0016J\u0012\u00108\u001a\u00020\u001b2\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00109\u001a\u00020\u001bH\u0016J0\u0010:\u001a\u00020\u001b2\f\u0010;\u001a\b\u0012\u0002\b\u0003\u0018\u00010<2\b\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020\u00072\u0006\u0010@\u001a\u00020AH\u0016J\u0016\u0010B\u001a\u00020\u001b2\f\u0010;\u001a\b\u0012\u0002\b\u0003\u0018\u00010<H\u0016J\b\u0010C\u001a\u00020\u001bH\u0014J+\u0010D\u001a\u00020\u001b2\u0006\u0010E\u001a\u00020\u00072\f\u0010F\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010G\u001a\u00020HH\u0016\u00a2\u0006\u0002\u0010IJ\b\u0010J\u001a\u00020\u001bH\u0016J\u0010\u0010K\u001a\u00020\u001b2\u0006\u0010L\u001a\u000203H\u0014J\b\u0010M\u001a\u00020\u001bH\u0002J\b\u0010N\u001a\u00020\u001bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0#8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010\'\u001a\u00020\u001b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006P"}, d2 = {"Lcom/cardio/physician/ui/views/textrecognization/kotlin/CameraXLivePreviewActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseToolbarActivity;", "Landroidx/core/app/ActivityCompat$OnRequestPermissionsResultCallback;", "Landroid/widget/AdapterView$OnItemSelectedListener;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "()V", "DEFAULT_CHIP_COUNT", "", "analysisUseCase", "Landroidx/camera/core/ImageAnalysis;", "binding", "Lcom/cardio/physician/databinding/ActivityVisionCameraxLivePreviewBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityVisionCameraxLivePreviewBinding;", "binding$delegate", "Lkotlin/Lazy;", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "cameraSelector", "Landroidx/camera/core/CameraSelector;", "imageProcessor", "Lcom/cardio/physician/ui/views/textrecognization/VisionImageProcessor;", "lensFacing", "needUpdateGraphicOverlayImageSourceInfo", "", "onBackClick", "Lkotlin/Function0;", "", "getOnBackClick", "()Lkotlin/jvm/functions/Function0;", "setOnBackClick", "(Lkotlin/jvm/functions/Function0;)V", "previewUseCase", "Landroidx/camera/core/Preview;", "requiredPermissions", "", "", "getRequiredPermissions", "()[Ljava/lang/String;", "runtimePermissions", "getRuntimePermissions", "()Lkotlin/Unit;", "selectedModel", "allPermissionsGranted", "bindAllCameraUseCases", "bindAnalysisUseCase", "bindPreviewUseCase", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "init", "savedInstanceState", "Landroid/os/Bundle;", "onCheckedChanged", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "onCreate", "onDestroy", "onItemSelected", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "pos", "id", "", "onNothingSelected", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onSaveInstanceState", "bundle", "setListener", "setViews", "Companion", "app_qaDebug"})
@com.google.android.gms.common.annotation.KeepName()
public final class CameraXLivePreviewActivity extends com.cardio.physician.ui.common.base.activity.BaseToolbarActivity implements androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback, android.widget.AdapterView.OnItemSelectedListener, android.widget.CompoundButton.OnCheckedChangeListener {
    private final int DEFAULT_CHIP_COUNT = 20;
    private androidx.camera.lifecycle.ProcessCameraProvider cameraProvider;
    private androidx.camera.core.Preview previewUseCase;
    private androidx.camera.core.ImageAnalysis analysisUseCase;
    private com.cardio.physician.ui.views.textrecognization.VisionImageProcessor imageProcessor;
    private boolean needUpdateGraphicOverlayImageSourceInfo = false;
    private java.lang.String selectedModel = "Text Recognition";
    private int lensFacing = androidx.camera.core.CameraSelector.LENS_FACING_BACK;
    private androidx.camera.core.CameraSelector cameraSelector;
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.textrecognization.kotlin.CameraXLivePreviewActivity.Companion Companion = null;
    private static final java.lang.String TAG = "CameraXLivePreview";
    private static final int PERMISSION_REQUESTS = 1;
    private static final java.lang.String TEXT_RECOGNITION = "Text Recognition";
    private static final java.lang.String STATE_SELECTED_MODEL = "selected_model";
    
    public CameraXLivePreviewActivity() {
        super();
    }
    
    private final com.cardio.physician.databinding.ActivityVisionCameraxLivePreviewBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnBackClick() {
        return null;
    }
    
    public final void setOnBackClick(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setViews() {
    }
    
    public final void init(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setListener() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle bundle) {
    }
    
    @java.lang.Override()
    public synchronized void onItemSelected(@org.jetbrains.annotations.Nullable()
    android.widget.AdapterView<?> parent, @org.jetbrains.annotations.Nullable()
    android.view.View view, int pos, long id) {
    }
    
    @java.lang.Override()
    public void onNothingSelected(@org.jetbrains.annotations.Nullable()
    android.widget.AdapterView<?> parent) {
    }
    
    @java.lang.Override()
    public void onCheckedChanged(@org.jetbrains.annotations.NotNull()
    android.widget.CompoundButton buttonView, boolean isChecked) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final void bindAllCameraUseCases() {
    }
    
    private final void bindPreviewUseCase() {
    }
    
    private final void bindAnalysisUseCase() {
    }
    
    private final java.lang.String[] getRequiredPermissions() {
        return null;
    }
    
    private final boolean allPermissionsGranted() {
        return false;
    }
    
    private final kotlin.Unit getRuntimePermissions() {
        return null;
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/cardio/physician/ui/views/textrecognization/kotlin/CameraXLivePreviewActivity$Companion;", "", "()V", "PERMISSION_REQUESTS", "", "STATE_SELECTED_MODEL", "", "TAG", "TEXT_RECOGNITION", "getIntent", "Landroid/content/Intent;", "activity", "Landroid/app/Activity;", "CHOOSE_TYPE_CAMERA", "isPermissionGranted", "", "context", "Landroid/content/Context;", "permission", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final boolean isPermissionGranted(android.content.Context context, java.lang.String permission) {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getIntent(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity, @org.jetbrains.annotations.Nullable()
        java.lang.Object CHOOSE_TYPE_CAMERA) {
            return null;
        }
    }
}
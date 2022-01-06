package com.google.mlkit.vision.demo.kotlin;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import com.cardio.physician.R;
import com.cardio.physician.databinding.ActivityStillImageBinding;
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.customviews.toolbar.TextRecogToolbarImp;
import com.cardio.physician.ui.common.utils.EXTRAS;
import com.cardio.physician.ui.views.textrecognization.BitmapUtils;
import com.cardio.physician.ui.views.textrecognization.VisionImageProcessor;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.material.chip.Chip;
import com.google.mlkit.vision.demo.kotlin.textdetector.TextRecognitionProcessorForStillimage;
import java.io.IOException;
import java.util.*;

/**
 * Activity demonstrating different image detector features with a still image from camera.
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020\u0014H\u0002J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0014H\u0002J\"\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\u0012\u0010)\u001a\u00020\u00142\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020\u0014H\u0016J\b\u0010-\u001a\u00020\u0014H\u0016J\b\u0010.\u001a\u00020\u0014H\u0016J\u0010\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020+H\u0016J\b\u00101\u001a\u00020\u0014H\u0002J\b\u00102\u001a\u00020\u0014H\u0002J\b\u00103\u001a\u00020\u0014H\u0002J\b\u00104\u001a\u00020\u0014H\u0002J\b\u00105\u001a\u00020\u0014H\u0002J\b\u00106\u001a\u00020\u0014H\u0002J\b\u00107\u001a\u00020\u0014H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u001d8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\u00a8\u00069"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/StillImageActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseToolbarActivity;", "()V", "binding", "Lcom/cardio/physician/databinding/ActivityStillImageBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityStillImageBinding;", "binding$delegate", "Lkotlin/Lazy;", "imageMaxHeight", "", "imageMaxWidth", "imageProcessor", "Lcom/cardio/physician/ui/views/textrecognization/VisionImageProcessor;", "imageUri", "Landroid/net/Uri;", "isLandScape", "", "onBackClick", "Lkotlin/Function0;", "", "getOnBackClick", "()Lkotlin/jvm/functions/Function0;", "setOnBackClick", "(Lkotlin/jvm/functions/Function0;)V", "selectedMode", "", "selectedSize", "targetedWidthHeight", "Landroid/util/Pair;", "getTargetedWidthHeight", "()Landroid/util/Pair;", "createImageProcessor", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "init", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onSaveInstanceState", "outState", "populateFeatureSelector", "populateSizeSelector", "setListner", "startCameraIntentForResult", "startChooseImageIntentForResult", "tryReloadAndDetectInImage", "unCheckAllChips", "Companion", "app_qaDebug"})
@com.google.android.gms.common.annotation.KeepName()
public final class StillImageActivity extends com.cardio.physician.ui.common.base.activity.BaseToolbarActivity {
    private java.lang.String selectedMode = "Text Recognition";
    private java.lang.String selectedSize = "w:screen";
    private boolean isLandScape = false;
    private android.net.Uri imageUri;
    private int imageMaxWidth = 0;
    private int imageMaxHeight = 0;
    private com.cardio.physician.ui.views.textrecognization.VisionImageProcessor imageProcessor;
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.mlkit.vision.demo.kotlin.StillImageActivity.Companion Companion = null;
    private static final java.lang.String TAG = "StillImageActivity";
    private static final java.lang.String TEXT_RECOGNITION = "Text Recognition";
    public static final int CHOOSE_TYPE_CAMERA = 2;
    public static final int CHOOSE_TYPE_GALLERY = 3;
    private static final java.lang.String SIZE_SCREEN = "w:screen";
    private static final java.lang.String SIZE_1024_768 = "w:1024";
    private static final java.lang.String SIZE_640_480 = "w:640";
    private static final java.lang.String SIZE_ORIGINAL = "w:original";
    private static final java.lang.String KEY_IMAGE_URI = "com.google.mlkit.vision.demo.KEY_IMAGE_URI";
    private static final java.lang.String KEY_IMAGE_MAX_WIDTH = "com.google.mlkit.vision.demo.KEY_IMAGE_MAX_WIDTH";
    private static final java.lang.String KEY_IMAGE_MAX_HEIGHT = "com.google.mlkit.vision.demo.KEY_IMAGE_MAX_HEIGHT";
    private static final java.lang.String KEY_SELECTED_SIZE = "com.google.mlkit.vision.demo.KEY_SELECTED_SIZE";
    private static final int REQUEST_IMAGE_CAPTURE = 1001;
    private static final int REQUEST_CHOOSE_IMAGE = 1002;
    
    public StillImageActivity() {
        super();
    }
    
    private final com.cardio.physician.databinding.ActivityStillImageBinding getBinding() {
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
    
    private final void init() {
    }
    
    private final void setListner() {
    }
    
    private final void unCheckAllChips() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final void populateFeatureSelector() {
    }
    
    private final void populateSizeSelector() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void startCameraIntentForResult() {
    }
    
    private final void startChooseImageIntentForResult() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void tryReloadAndDetectInImage() {
    }
    
    private final android.util.Pair<java.lang.Integer, java.lang.Integer> getTargetedWidthHeight() {
        return null;
    }
    
    private final void createImageProcessor() {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/StillImageActivity$Companion;", "", "()V", "CHOOSE_TYPE_CAMERA", "", "CHOOSE_TYPE_GALLERY", "KEY_IMAGE_MAX_HEIGHT", "", "KEY_IMAGE_MAX_WIDTH", "KEY_IMAGE_URI", "KEY_SELECTED_SIZE", "REQUEST_CHOOSE_IMAGE", "REQUEST_IMAGE_CAPTURE", "SIZE_1024_768", "SIZE_640_480", "SIZE_ORIGINAL", "SIZE_SCREEN", "TAG", "TEXT_RECOGNITION", "getIntent", "Landroid/content/Intent;", "activity", "Landroid/app/Activity;", "chooseType", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getIntent(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity, int chooseType) {
            return null;
        }
    }
}
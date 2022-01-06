package com.google.mlkit.vision.demo.kotlin;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION_CODES;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.GuardedBy;
import androidx.annotation.RequiresApi;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageProxy;
import com.cardio.physician.ui.views.textrecognization.*;
import com.cardio.physician.ui.views.textrecognization.preference.PreferenceUtils;
import com.google.android.gms.tasks.*;
import com.google.android.material.chip.ChipGroup;
import com.google.android.odml.image.BitmapMlImageBuilder;
import com.google.android.odml.image.ByteBufferMlImageBuilder;
import com.google.android.odml.image.MediaMlImageBuilder;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * Abstract base class for ML Kit frame processors. Subclasses need to implement {@link
 * #onSuccess(T, FrameMetadata, GraphicOverlay)} to define what they want to with the detection
 * results and {@link #detectInImage(VisionImage)} to specify the detector object.
 *
 * @param <T> The type of the detected feature.
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 C*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001CB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020\"H\u0014J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020#H$J\u0012\u0010$\u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0014J\u0014\u0010%\u001a\u00020&2\n\u0010\'\u001a\u00060(j\u0002`)H$J\'\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00028\u00002\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H$\u00a2\u0006\u0002\u00100J$\u00101\u001a\u00020&2\b\u00102\u001a\u0004\u0018\u0001032\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J$\u00104\u001a\u00020&2\b\u00105\u001a\u0004\u0018\u00010\u00122\b\u00106\u001a\u0004\u0018\u00010\u00142\u0006\u0010,\u001a\u00020-H\u0016J \u00107\u001a\u00020&2\u0006\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u00142\u0006\u0010,\u001a\u00020-H\u0002J \u00108\u001a\u00020&2\u0006\u0010!\u001a\u0002092\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0017J\u0010\u0010:\u001a\u00020&2\u0006\u0010,\u001a\u00020-H\u0002JD\u0010;\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010,\u001a\u00020-2\b\u0010<\u001a\u0004\u0018\u0001032\u0006\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00162\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/H\u0002JD\u0010;\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010!\u001a\u00020#2\u0006\u0010,\u001a\u00020-2\b\u0010<\u001a\u0004\u0018\u0001032\u0006\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00162\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/H\u0002J\b\u0010?\u001a\u00020&H\u0002JJ\u0010@\u001a\b\u0012\u0004\u0012\u00028\u00000 2\f\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010,\u001a\u00020-2\b\u0010<\u001a\u0004\u0018\u0001032\u0006\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00162\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/H\u0002J\b\u0010B\u001a\u00020&H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006D"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/VisionProcessorBase;", "T", "Lcom/cardio/physician/ui/views/textrecognization/VisionImageProcessor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activityManager", "Landroid/app/ActivityManager;", "executor", "Lcom/cardio/physician/ui/views/textrecognization/ScopedExecutor;", "fpsTimer", "Ljava/util/Timer;", "frameProcessedInOneSecondInterval", "", "framesPerSecond", "isShutdown", "", "latestImage", "Ljava/nio/ByteBuffer;", "latestImageMetaData", "Lcom/cardio/physician/ui/views/textrecognization/FrameMetadata;", "maxDetectorMs", "", "maxFrameMs", "minDetectorMs", "minFrameMs", "numRuns", "processingImage", "processingMetaData", "totalDetectorMs", "totalFrameMs", "detectInImage", "Lcom/google/android/gms/tasks/Task;", "image", "Lcom/google/android/odml/image/MlImage;", "Lcom/google/mlkit/vision/common/InputImage;", "isMlImageEnabled", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "results", "graphicOverlay", "Lcom/cardio/physician/ui/views/textrecognization/GraphicOverlay;", "chipGroup", "Lcom/google/android/material/chip/ChipGroup;", "(Ljava/lang/Object;Lcom/cardio/physician/ui/views/textrecognization/GraphicOverlay;Lcom/google/android/material/chip/ChipGroup;)V", "processBitmap", "bitmap", "Landroid/graphics/Bitmap;", "processByteBuffer", "data", "frameMetadata", "processImage", "processImageProxy", "Landroidx/camera/core/ImageProxy;", "processLatestImage", "requestDetectInImage", "originalCameraImage", "shouldShowFps", "frameStartMs", "resetLatencyStats", "setUpListener", "task", "stop", "Companion", "app_qaDebug"})
public abstract class VisionProcessorBase<T extends java.lang.Object> implements com.cardio.physician.ui.views.textrecognization.VisionImageProcessor {
    @org.jetbrains.annotations.NotNull()
    public static final com.google.mlkit.vision.demo.kotlin.VisionProcessorBase.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MANUAL_TESTING_LOG = "LogTagForTest";
    private static final java.lang.String TAG = "VisionProcessorBase";
    private android.app.ActivityManager activityManager;
    private final java.util.Timer fpsTimer = null;
    private final com.cardio.physician.ui.views.textrecognization.ScopedExecutor executor = null;
    private boolean isShutdown = false;
    private int numRuns = 0;
    private long totalFrameMs = 0L;
    private long maxFrameMs = 0L;
    private long minFrameMs = 9223372036854775807L;
    private long totalDetectorMs = 0L;
    private long maxDetectorMs = 0L;
    private long minDetectorMs = 9223372036854775807L;
    private int frameProcessedInOneSecondInterval = 0;
    private int framesPerSecond = 0;
    @androidx.annotation.GuardedBy(value = "this")
    private java.nio.ByteBuffer latestImage;
    @androidx.annotation.GuardedBy(value = "this")
    private com.cardio.physician.ui.views.textrecognization.FrameMetadata latestImageMetaData;
    @androidx.annotation.GuardedBy(value = "this")
    private java.nio.ByteBuffer processingImage;
    @androidx.annotation.GuardedBy(value = "this")
    private com.cardio.physician.ui.views.textrecognization.FrameMetadata processingMetaData;
    
    public VisionProcessorBase(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    public void processBitmap(@org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay, @org.jetbrains.annotations.Nullable()
    com.google.android.material.chip.ChipGroup chipGroup) {
    }
    
    @java.lang.Override()
    public synchronized void processByteBuffer(@org.jetbrains.annotations.Nullable()
    java.nio.ByteBuffer data, @org.jetbrains.annotations.Nullable()
    com.cardio.physician.ui.views.textrecognization.FrameMetadata frameMetadata, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay) {
    }
    
    private final synchronized void processLatestImage(com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay) {
    }
    
    private final void processImage(java.nio.ByteBuffer data, com.cardio.physician.ui.views.textrecognization.FrameMetadata frameMetadata, com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay) {
    }
    
    @androidx.camera.core.ExperimentalGetImage()
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.KITKAT)
    @java.lang.Override()
    public void processImageProxy(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy image, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay, @org.jetbrains.annotations.NotNull()
    com.google.android.material.chip.ChipGroup chipGroup) {
    }
    
    private final com.google.android.gms.tasks.Task<T> requestDetectInImage(com.google.mlkit.vision.common.InputImage image, com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay, android.graphics.Bitmap originalCameraImage, boolean shouldShowFps, long frameStartMs, com.google.android.material.chip.ChipGroup chipGroup) {
        return null;
    }
    
    private final com.google.android.gms.tasks.Task<T> requestDetectInImage(com.google.android.odml.image.MlImage image, com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay, android.graphics.Bitmap originalCameraImage, boolean shouldShowFps, long frameStartMs, com.google.android.material.chip.ChipGroup chipGroup) {
        return null;
    }
    
    private final com.google.android.gms.tasks.Task<T> setUpListener(com.google.android.gms.tasks.Task<T> task, com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay, android.graphics.Bitmap originalCameraImage, boolean shouldShowFps, long frameStartMs, com.google.android.material.chip.ChipGroup chipGroup) {
        return null;
    }
    
    @java.lang.Override()
    public void stop() {
    }
    
    private final void resetLatencyStats() {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected abstract com.google.android.gms.tasks.Task<T> detectInImage(@org.jetbrains.annotations.NotNull()
    com.google.mlkit.vision.common.InputImage image);
    
    @org.jetbrains.annotations.NotNull()
    protected com.google.android.gms.tasks.Task<T> detectInImage(@org.jetbrains.annotations.NotNull()
    com.google.android.odml.image.MlImage image) {
        return null;
    }
    
    protected abstract void onSuccess(T results, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay, @org.jetbrains.annotations.Nullable()
    com.google.android.material.chip.ChipGroup chipGroup);
    
    protected abstract void onFailure(@org.jetbrains.annotations.NotNull()
    java.lang.Exception e);
    
    protected boolean isMlImageEnabled(@org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/VisionProcessorBase$Companion;", "", "()V", "MANUAL_TESTING_LOG", "", "TAG", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
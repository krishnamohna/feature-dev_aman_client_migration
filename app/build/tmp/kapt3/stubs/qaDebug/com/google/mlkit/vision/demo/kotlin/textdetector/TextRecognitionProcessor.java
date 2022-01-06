package com.google.mlkit.vision.demo.kotlin.textdetector;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.cardio.physician.ui.views.textrecognization.GraphicOverlay;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.demo.kotlin.VisionProcessorBase;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;

/**
 * Processor for the text detector demo.
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0002J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\u0014\u0010\u001b\u001a\u00020\u00132\n\u0010\u001c\u001a\u00060\u001dj\u0002`\u001eH\u0014J\"\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\"\u001a\u00020\u0013H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/textdetector/TextRecognitionProcessor;", "Lcom/google/mlkit/vision/demo/kotlin/VisionProcessorBase;", "Lcom/google/mlkit/vision/text/Text;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "states", "", "", "getStates", "()[[I", "setStates", "([[I)V", "[[I", "textRecognizer", "Lcom/google/mlkit/vision/text/TextRecognizer;", "addChipsToGroup", "", "chipGroup", "Lcom/google/android/material/chip/ChipGroup;", "text", "detectInImage", "Lcom/google/android/gms/tasks/Task;", "image", "Lcom/google/mlkit/vision/common/InputImage;", "onFailure", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "graphicOverlay", "Lcom/cardio/physician/ui/views/textrecognization/GraphicOverlay;", "stop", "Companion", "app_qaDebug"})
public final class TextRecognitionProcessor extends com.google.mlkit.vision.demo.kotlin.VisionProcessorBase<com.google.mlkit.vision.text.Text> {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final com.google.mlkit.vision.text.TextRecognizer textRecognizer = null;
    @org.jetbrains.annotations.NotNull()
    private int[][] states = {{16842910}, {-16842910}, {-16842912}, {16842919}};
    @org.jetbrains.annotations.NotNull()
    public static final com.google.mlkit.vision.demo.kotlin.textdetector.TextRecognitionProcessor.Companion Companion = null;
    private static final java.lang.String TAG = "TextRecProcessor";
    
    public TextRecognitionProcessor(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final int[][] getStates() {
        return null;
    }
    
    public final void setStates(@org.jetbrains.annotations.NotNull()
    int[][] p0) {
    }
    
    @java.lang.Override()
    public void stop() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected com.google.android.gms.tasks.Task<com.google.mlkit.vision.text.Text> detectInImage(@org.jetbrains.annotations.NotNull()
    com.google.mlkit.vision.common.InputImage image) {
        return null;
    }
    
    @java.lang.Override()
    protected void onSuccess(@org.jetbrains.annotations.NotNull()
    com.google.mlkit.vision.text.Text text, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.textrecognization.GraphicOverlay graphicOverlay, @org.jetbrains.annotations.Nullable()
    com.google.android.material.chip.ChipGroup chipGroup) {
    }
    
    private final void addChipsToGroup(com.google.android.material.chip.ChipGroup chipGroup, com.google.mlkit.vision.text.Text text) {
    }
    
    @java.lang.Override()
    protected void onFailure(@org.jetbrains.annotations.NotNull()
    java.lang.Exception e) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/textdetector/TextRecognitionProcessor$Companion;", "", "()V", "TAG", "", "logExtrasForTesting", "", "text", "Lcom/google/mlkit/vision/text/Text;", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final void logExtrasForTesting(com.google.mlkit.vision.text.Text text) {
        }
    }
}
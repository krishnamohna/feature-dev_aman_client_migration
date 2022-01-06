package com.google.mlkit.vision.demo.kotlin.textdetector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import com.cardio.physician.ui.views.textrecognization.GraphicOverlay;
import com.google.mlkit.vision.text.Text;
import java.util.Arrays;

/**
 * Graphic instance for rendering TextBlock position, size, and ID within an associated graphic
 * overlay view.
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/textdetector/TextGraphic;", "Lcom/cardio/physician/ui/views/textrecognization/GraphicOverlay$Graphic;", "overlay", "Lcom/cardio/physician/ui/views/textrecognization/GraphicOverlay;", "text", "Lcom/google/mlkit/vision/text/Text;", "(Lcom/cardio/physician/ui/views/textrecognization/GraphicOverlay;Lcom/google/mlkit/vision/text/Text;)V", "labelPaint", "Landroid/graphics/Paint;", "rectPaint", "textPaint", "draw", "", "canvas", "Landroid/graphics/Canvas;", "Companion", "app_qaDebug"})
public final class TextGraphic extends com.cardio.physician.ui.views.textrecognization.GraphicOverlay.Graphic {
    private final com.google.mlkit.vision.text.Text text = null;
    private final android.graphics.Paint rectPaint = null;
    private final android.graphics.Paint textPaint = null;
    private final android.graphics.Paint labelPaint = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.mlkit.vision.demo.kotlin.textdetector.TextGraphic.Companion Companion = null;
    private static final java.lang.String TAG = "TextGraphic";
    private static final int TEXT_COLOR = android.graphics.Color.BLACK;
    private static final int MARKER_COLOR = android.graphics.Color.WHITE;
    private static final float TEXT_SIZE = 54.0F;
    private static final float STROKE_WIDTH = 4.0F;
    
    public TextGraphic(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.ui.views.textrecognization.GraphicOverlay overlay, @org.jetbrains.annotations.NotNull()
    com.google.mlkit.vision.text.Text text) {
        super(null);
    }
    
    /**
     * Draws the text block annotations for position, size, and raw value on the supplied canvas.
     */
    @java.lang.Override()
    public void draw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/textdetector/TextGraphic$Companion;", "", "()V", "MARKER_COLOR", "", "STROKE_WIDTH", "", "TAG", "", "TEXT_COLOR", "TEXT_SIZE", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
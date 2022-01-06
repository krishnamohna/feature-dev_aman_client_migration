package com.cardio.physician.ui.common.utils;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import androidx.constraintlayout.motion.utils.Oscillator;
import androidx.core.content.FileProvider;
import com.cardio.physician.R;
import java.io.*;

@kotlin.Suppress(names = {"unused"})
@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000V\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a \u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\r\u001a\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002\u001a\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u001a\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015\u001a\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0004\u001a\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d\u001a\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u001d\u001a\u0019\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040!2\u0006\u0010\"\u001a\u00020\u0004\u00a2\u0006\u0002\u0010#\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"DEFAULT_BUFFER_SIZE", "", "EOF", "FILES_PATH", "", "copy", "input", "Ljava/io/InputStream;", "output", "Ljava/io/OutputStream;", "copyLarge", "", "buffer", "", "createImageFile", "Ljava/io/File;", "directory", "from", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "getFileName", "rename", "file", "newName", "saveImage", "", "bitmap", "Landroid/graphics/Bitmap;", "saveImageInCache", "image", "splitFileName", "", "fileName", "(Ljava/lang/String;)[Ljava/lang/String;", "app_qaDebug"})
public final class FileUtilsKt {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FILES_PATH = "Compressor";
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    
    private static final java.io.File createImageFile(java.lang.String directory) throws java.io.IOException {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final android.net.Uri saveImageInCache(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap image) {
        return null;
    }
    
    public static final void saveImage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.io.File from(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.net.Uri uri) throws java.io.IOException {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String[] splitFileName(@org.jetbrains.annotations.NotNull()
    java.lang.String fileName) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getFileName(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.io.File rename(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    java.lang.String newName) {
        return null;
    }
    
    public static final int copy(@org.jetbrains.annotations.NotNull()
    java.io.InputStream input, @org.jetbrains.annotations.NotNull()
    java.io.OutputStream output) throws java.io.IOException {
        return 0;
    }
    
    public static final long copyLarge(@org.jetbrains.annotations.NotNull()
    java.io.InputStream input, @org.jetbrains.annotations.NotNull()
    java.io.OutputStream output) throws java.io.IOException {
        return 0L;
    }
    
    public static final long copyLarge(@org.jetbrains.annotations.NotNull()
    java.io.InputStream input, @org.jetbrains.annotations.NotNull()
    java.io.OutputStream output, @org.jetbrains.annotations.Nullable()
    byte[] buffer) throws java.io.IOException {
        return 0L;
    }
}
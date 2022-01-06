package com.google.mlkit.vision.demo.kotlin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.cardio.physician.R;
import com.cardio.physician.ui.views.textrecognization.kotlin.CameraXLivePreviewActivity;
import java.util.*;

/**
 * Demo app chooser which takes care of runtime permission requesting and allow you pick from all
 * available testing Activities.
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002\u001d\u001eB\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0015\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0002\u00a2\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J.\u0010\u0014\u001a\u00020\f2\f\u0010\u0015\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016\u00a8\u0006\u001f"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/ChooserActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroidx/core/app/ActivityCompat$OnRequestPermissionsResultCallback;", "Landroid/widget/AdapterView$OnItemClickListener;", "()V", "allPermissionsGranted", "", "getRequiredPermissions", "", "", "()[Ljava/lang/String;", "getRuntimePermissions", "", "isPermissionGranted", "context", "Landroid/content/Context;", "permission", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "position", "", "id", "", "Companion", "MyArrayAdapter", "app_qaDebug"})
public final class ChooserActivity extends androidx.appcompat.app.AppCompatActivity implements androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback, android.widget.AdapterView.OnItemClickListener {
    @org.jetbrains.annotations.NotNull()
    public static final com.google.mlkit.vision.demo.kotlin.ChooserActivity.Companion Companion = null;
    private static final java.lang.String TAG = "ChooserActivity";
    private static final int PERMISSION_REQUESTS = 1;
    private static final java.lang.Class<?>[] CLASSES = null;
    private static final int[] DESCRIPTION_IDS = null;
    
    public ChooserActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onItemClick(@org.jetbrains.annotations.Nullable()
    android.widget.AdapterView<?> parent, @org.jetbrains.annotations.NotNull()
    android.view.View view, int position, long id) {
    }
    
    private final java.lang.String[] getRequiredPermissions() {
        return null;
    }
    
    private final boolean allPermissionsGranted() {
        return false;
    }
    
    private final void getRuntimePermissions() {
    }
    
    private final boolean isPermissionGranted(android.content.Context context, java.lang.String permission) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\b\u00a2\u0006\u0002\u0010\tJ\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\fR\u001a\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/ChooserActivity$MyArrayAdapter;", "Landroid/widget/ArrayAdapter;", "Ljava/lang/Class;", "ctx", "Landroid/content/Context;", "resource", "", "classes", "", "(Landroid/content/Context;I[Ljava/lang/Class;)V", "[Ljava/lang/Class;", "descriptionIds", "", "getView", "Landroid/view/View;", "position", "convertView", "parent", "Landroid/view/ViewGroup;", "setDescriptionIds", "", "app_qaDebug"})
    static final class MyArrayAdapter extends android.widget.ArrayAdapter<java.lang.Class<?>> {
        private final android.content.Context ctx = null;
        private final java.lang.Class<?>[] classes = null;
        private int[] descriptionIds;
        
        public MyArrayAdapter(@org.jetbrains.annotations.NotNull()
        android.content.Context ctx, int resource, @org.jetbrains.annotations.NotNull()
        java.lang.Class<?>[] classes) {
            super(null, 0);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
        android.view.View convertView, @org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            return null;
        }
        
        public final void setDescriptionIds(@org.jetbrains.annotations.NotNull()
        int[] descriptionIds) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/google/mlkit/vision/demo/kotlin/ChooserActivity$Companion;", "", "()V", "CLASSES", "", "Ljava/lang/Class;", "[Ljava/lang/Class;", "DESCRIPTION_IDS", "", "PERMISSION_REQUESTS", "", "TAG", "", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
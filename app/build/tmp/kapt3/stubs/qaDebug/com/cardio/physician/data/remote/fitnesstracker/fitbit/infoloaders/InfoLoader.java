package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders;

import android.app.Activity;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.cardio.physician.R;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B2\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012#\u0010\u0006\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\fH\u0016J,\u0010\u0011\u001a\u00020\f2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00132\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003H\u0016J\u001c\u0010\u0015\u001a\u00020\f2\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0006\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/infoloaders/InfoLoader;", "T", "Landroidx/loader/app/LoaderManager$LoaderCallbacks;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/loaders/ResourceLoaderResult;", "activity", "Landroid/app/Activity;", "onFailure", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "msg", "", "(Landroid/app/Activity;Lkotlin/jvm/functions/Function1;)V", "getLoaderId", "", "load", "onLoadFinished", "loader", "Landroidx/loader/content/Loader;", "data", "onLoaderReset", "app_qaDebug"})
public abstract class InfoLoader<T extends java.lang.Object> implements androidx.loader.app.LoaderManager.LoaderCallbacks<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<T>> {
    private final android.app.Activity activity = null;
    private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onFailure = null;
    
    public InfoLoader(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onFailure) {
        super();
    }
    
    public void load() {
    }
    
    public abstract int getLoaderId();
    
    @java.lang.Override()
    public void onLoadFinished(@org.jetbrains.annotations.NotNull()
    androidx.loader.content.Loader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<T>> loader, @org.jetbrains.annotations.Nullable()
    com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<T> data) {
    }
    
    @java.lang.Override()
    public void onLoaderReset(@org.jetbrains.annotations.NotNull()
    androidx.loader.content.Loader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<T>> loader) {
    }
}
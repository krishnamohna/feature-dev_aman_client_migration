package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders;

import android.app.Activity;
import android.os.Bundle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate.HeartRateEntity;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services.HeartRateService;
import com.cardio.physician.domain.fitness.model.HeartRateModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00040\u0003BF\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012#\u0010\u000b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\f\u00a2\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\n0\b\u00a2\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016J&\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00040\u00142\u0006\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\u0018\u001a\u00020\n2\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00040\u00142\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0004H\u0016J\u001c\u0010\u001b\u001a\u00020\n2\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00040\u0014H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\f\u00a2\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/infoloaders/TodayHeartRateLoader;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/infoloaders/InfoLoader;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/entities/heartrate/HeartRateEntity;", "Landroidx/loader/app/LoaderManager$LoaderCallbacks;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/loaders/ResourceLoaderResult;", "activity", "Landroid/app/Activity;", "onSuccess", "Lkotlin/Function1;", "Lcom/cardio/physician/domain/fitness/model/HeartRateModel;", "", "onFailure", "", "Lkotlin/ParameterName;", "name", "msg", "(Landroid/app/Activity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getLoaderId", "", "onCreateLoader", "Landroidx/loader/content/Loader;", "id", "args", "Landroid/os/Bundle;", "onLoadFinished", "loader", "data", "onLoaderReset", "app_qaDebug"})
public final class TodayHeartRateLoader extends com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.InfoLoader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate.HeartRateEntity> implements androidx.loader.app.LoaderManager.LoaderCallbacks<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate.HeartRateEntity>> {
    private final android.app.Activity activity = null;
    private final kotlin.jvm.functions.Function1<com.cardio.physician.domain.fitness.model.HeartRateModel, kotlin.Unit> onSuccess = null;
    private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onFailure = null;
    
    public TodayHeartRateLoader(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.cardio.physician.domain.fitness.model.HeartRateModel, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onFailure) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.loader.content.Loader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate.HeartRateEntity>> onCreateLoader(int id, @org.jetbrains.annotations.Nullable()
    android.os.Bundle args) {
        return null;
    }
    
    @java.lang.Override()
    public int getLoaderId() {
        return 0;
    }
    
    @java.lang.Override()
    public void onLoadFinished(@org.jetbrains.annotations.NotNull()
    androidx.loader.content.Loader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate.HeartRateEntity>> loader, @org.jetbrains.annotations.Nullable()
    com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate.HeartRateEntity> data) {
    }
    
    @java.lang.Override()
    public void onLoaderReset(@org.jetbrains.annotations.NotNull()
    androidx.loader.content.Loader<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate.HeartRateEntity>> loader) {
    }
}
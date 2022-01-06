package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders;

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SynTaskLoader;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B*\u0012#\u0010\u0004\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000eH&J\u0018\u0010\u000f\u001a\u00020\n2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003H\u0016R+\u0010\u0004\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/infoloaders/InfoLoaderSync;", "T", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/loaders/base/SynTaskLoader$LoaderCallbacks;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/loaders/ResourceLoaderResult;", "onFailure", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "msg", "", "(Lkotlin/jvm/functions/Function1;)V", "load", "onCreateSyncLoader", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/loaders/ResourceLoaderSync;", "onLoadFinished", "data", "app_qaDebug"})
public abstract class InfoLoaderSync<T extends java.lang.Object> implements com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SynTaskLoader.LoaderCallbacks<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<T>> {
    private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onFailure = null;
    
    public InfoLoaderSync(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onFailure) {
        super();
    }
    
    public void load() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync<T> onCreateSyncLoader();
    
    @java.lang.Override()
    public void onLoadFinished(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<T> data) {
    }
}
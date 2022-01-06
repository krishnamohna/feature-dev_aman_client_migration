package com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders;

import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.StepCountEntity;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SynTaskLoader;
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.services.ActivityService;
import com.cardio.physician.domain.fitness.model.StepCountModel;
import java.util.*;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00040\u0003BL\u0012\u0018\u0010\u0005\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\t0\u0006\u0012#\u0010\n\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\t2\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0004H\u0016R+\u0010\n\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/infoloaders/StepCountLogsLoader;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/infoloaders/InfoLoaderSync;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/entities/steps/StepCountEntity;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/loaders/base/SynTaskLoader$LoaderCallbacks;", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/loaders/ResourceLoaderResult;", "onSuccess", "Lkotlin/Function1;", "", "Lcom/cardio/physician/domain/fitness/model/StepCountModel;", "", "onFailure", "", "Lkotlin/ParameterName;", "name", "msg", "periodDays", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;I)V", "onCreateSyncLoader", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/loaders/ResourceLoaderSync;", "onLoadFinished", "data", "app_qaDebug"})
public final class StepCountLogsLoader extends com.cardio.physician.data.remote.fitnesstracker.fitbit.infoloaders.InfoLoaderSync<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.StepCountEntity> implements com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.base.SynTaskLoader.LoaderCallbacks<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.StepCountEntity>> {
    private final kotlin.jvm.functions.Function1<java.util.List<com.cardio.physician.domain.fitness.model.StepCountModel>, kotlin.Unit> onSuccess = null;
    private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onFailure = null;
    private final int periodDays = 0;
    
    public StepCountLogsLoader(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.cardio.physician.domain.fitness.model.StepCountModel>, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onFailure, int periodDays) {
        super(null);
    }
    
    @java.lang.Override()
    public void onLoadFinished(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderResult<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.StepCountEntity> data) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.cardio.physician.data.remote.fitnesstracker.fitbit.api.loaders.ResourceLoaderSync<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.StepCountEntity> onCreateSyncLoader() {
        return null;
    }
}
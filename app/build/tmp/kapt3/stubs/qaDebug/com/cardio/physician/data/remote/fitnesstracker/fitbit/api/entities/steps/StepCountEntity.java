package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps;

import com.cardio.physician.domain.fitness.model.StepCountModel;
import com.cardio.physician.ui.common.utils.DateFormat_;
import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/entities/steps/StepCountEntity;", "", "activities_steps", "", "Lcom/cardio/physician/data/remote/fitnesstracker/fitbit/api/entities/steps/ActivitiesStep;", "(Ljava/util/List;)V", "getActivities_steps", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toModelList", "Lcom/cardio/physician/domain/fitness/model/StepCountModel;", "toString", "", "app_qaDebug"})
public final class StepCountEntity {
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "activities-steps")
    private final java.util.List<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.ActivitiesStep> activities_steps = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.StepCountEntity copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.ActivitiesStep> activities_steps) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public StepCountEntity(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.ActivitiesStep> activities_steps) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.ActivitiesStep> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps.ActivitiesStep> getActivities_steps() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.fitness.model.StepCountModel> toModelList() {
        return null;
    }
}
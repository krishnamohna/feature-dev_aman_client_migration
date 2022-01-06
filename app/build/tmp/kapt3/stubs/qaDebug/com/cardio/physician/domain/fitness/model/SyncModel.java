package com.cardio.physician.domain.fitness.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010#J\u0016\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001eJf\u0010,\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u000202H\u00d6\u0001J\t\u00103\u001a\u000204H\u00d6\u0001R$\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0003X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0003X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\u00a8\u00065"}, d2 = {"Lcom/cardio/physician/domain/fitness/model/SyncModel;", "", "arrayHeartLogs", "", "Lcom/cardio/physician/domain/fitness/model/HeartRateModel;", "arrayWeightLogs", "Lcom/cardio/physician/domain/fitness/model/WeightModel;", "arrayBloodPresure", "Lcom/cardio/physician/domain/fitness/model/BloodPressureModel;", "arrayDates", "", "Lcom/cardio/physician/domain/fitness/model/DateModel;", "arrayStepCounts", "Lcom/cardio/physician/domain/fitness/model/StepCountModel;", "([Lcom/cardio/physician/domain/fitness/model/HeartRateModel;[Lcom/cardio/physician/domain/fitness/model/WeightModel;[Lcom/cardio/physician/domain/fitness/model/BloodPressureModel;Ljava/util/List;[Lcom/cardio/physician/domain/fitness/model/StepCountModel;)V", "getArrayBloodPresure", "()[Lcom/cardio/physician/domain/fitness/model/BloodPressureModel;", "setArrayBloodPresure", "([Lcom/cardio/physician/domain/fitness/model/BloodPressureModel;)V", "[Lcom/cardio/physician/domain/fitness/model/BloodPressureModel;", "getArrayDates", "()Ljava/util/List;", "setArrayDates", "(Ljava/util/List;)V", "getArrayHeartLogs", "()[Lcom/cardio/physician/domain/fitness/model/HeartRateModel;", "setArrayHeartLogs", "([Lcom/cardio/physician/domain/fitness/model/HeartRateModel;)V", "[Lcom/cardio/physician/domain/fitness/model/HeartRateModel;", "getArrayStepCounts", "()[Lcom/cardio/physician/domain/fitness/model/StepCountModel;", "setArrayStepCounts", "([Lcom/cardio/physician/domain/fitness/model/StepCountModel;)V", "[Lcom/cardio/physician/domain/fitness/model/StepCountModel;", "getArrayWeightLogs", "()[Lcom/cardio/physician/domain/fitness/model/WeightModel;", "setArrayWeightLogs", "([Lcom/cardio/physician/domain/fitness/model/WeightModel;)V", "[Lcom/cardio/physician/domain/fitness/model/WeightModel;", "component1", "component2", "component3", "component4", "component5", "copy", "([Lcom/cardio/physician/domain/fitness/model/HeartRateModel;[Lcom/cardio/physician/domain/fitness/model/WeightModel;[Lcom/cardio/physician/domain/fitness/model/BloodPressureModel;Ljava/util/List;[Lcom/cardio/physician/domain/fitness/model/StepCountModel;)Lcom/cardio/physician/domain/fitness/model/SyncModel;", "equals", "", "other", "hashCode", "", "toString", "", "app_qaDebug"})
public final class SyncModel {
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.domain.fitness.model.HeartRateModel[] arrayHeartLogs;
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.domain.fitness.model.WeightModel[] arrayWeightLogs;
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.domain.fitness.model.BloodPressureModel[] arrayBloodPresure;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.cardio.physician.domain.fitness.model.DateModel> arrayDates;
    @org.jetbrains.annotations.NotNull()
    private com.cardio.physician.domain.fitness.model.StepCountModel[] arrayStepCounts;
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.SyncModel copy(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.HeartRateModel[] arrayHeartLogs, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.WeightModel[] arrayWeightLogs, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.BloodPressureModel[] arrayBloodPresure, @org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.fitness.model.DateModel> arrayDates, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.StepCountModel[] arrayStepCounts) {
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
    
    public SyncModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.HeartRateModel[] arrayHeartLogs, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.WeightModel[] arrayWeightLogs, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.BloodPressureModel[] arrayBloodPresure, @org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.fitness.model.DateModel> arrayDates, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.StepCountModel[] arrayStepCounts) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.HeartRateModel[] component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.HeartRateModel[] getArrayHeartLogs() {
        return null;
    }
    
    public final void setArrayHeartLogs(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.HeartRateModel[] p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.WeightModel[] component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.WeightModel[] getArrayWeightLogs() {
        return null;
    }
    
    public final void setArrayWeightLogs(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.WeightModel[] p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.BloodPressureModel[] component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.BloodPressureModel[] getArrayBloodPresure() {
        return null;
    }
    
    public final void setArrayBloodPresure(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.BloodPressureModel[] p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.fitness.model.DateModel> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.fitness.model.DateModel> getArrayDates() {
        return null;
    }
    
    public final void setArrayDates(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.fitness.model.DateModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.StepCountModel[] component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.fitness.model.StepCountModel[] getArrayStepCounts() {
        return null;
    }
    
    public final void setArrayStepCounts(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.fitness.model.StepCountModel[] p0) {
    }
}
package com.cardio.physician.domain.fitness.model;

import android.os.Parcelable;
import kotlinx.android.parcel.Parcelize;

@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\nH\u00d6\u0001J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0010"}, d2 = {"Lcom/cardio/physician/domain/fitness/model/HeartRateModel;", "Landroid/os/Parcelable;", "restHeartRate", "", "date", "(Ljava/lang/String;Ljava/lang/String;)V", "getDate", "()Ljava/lang/String;", "getRestHeartRate", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_qaDebug"})
public final class HeartRateModel implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String restHeartRate = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String date = null;
    public static final android.os.Parcelable.Creator<com.cardio.physician.domain.fitness.model.HeartRateModel> CREATOR = null;
    
    public HeartRateModel(@org.jetbrains.annotations.Nullable()
    java.lang.String restHeartRate, @org.jetbrains.annotations.Nullable()
    java.lang.String date) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRestHeartRate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDate() {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<com.cardio.physician.domain.fitness.model.HeartRateModel> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.cardio.physician.domain.fitness.model.HeartRateModel createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.cardio.physician.domain.fitness.model.HeartRateModel[] newArray(int size) {
            return null;
        }
    }
}
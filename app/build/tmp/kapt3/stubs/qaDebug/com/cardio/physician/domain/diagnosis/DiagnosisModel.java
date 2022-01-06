package com.cardio.physician.domain.diagnosis;

import android.os.Parcel;
import android.os.Parcelable;
import com.cardio.physician.domain.questionare.model.QuestionModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 A2\u00020\u0001:\u0001AB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010<\u001a\u00020=H\u0016J\u0018\u0010>\u001a\u00020?2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010@\u001a\u00020=H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR\"\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\t\"\u0004\b\'\u0010\u000bR\"\u0010(\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\"\"\u0004\b+\u0010$R\u001c\u0010,\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\t\"\u0004\b.\u0010\u000bR\u001e\u0010/\u001a\u0004\u0018\u000100X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u00105\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001c\u00106\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\t\"\u0004\b8\u0010\u000bR\u001c\u00109\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\t\"\u0004\b;\u0010\u000b\u00a8\u0006B"}, d2 = {"Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "age", "", "getAge", "()Ljava/lang/String;", "setAge", "(Ljava/lang/String;)V", "ailment", "getAilment", "setAilment", "bottomBp", "getBottomBp", "setBottomBp", "date", "getDate", "setDate", "firstName", "getFirstName", "setFirstName", "heartRate", "getHeartRate", "setHeartRate", "lastName", "getLastName", "setLastName", "medications", "", "Lcom/cardio/physician/domain/diagnosis/MedicineModel;", "getMedications", "()Ljava/util/List;", "setMedications", "(Ljava/util/List;)V", "mediciene", "getMediciene", "setMediciene", "questionnaire", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "getQuestionnaire", "setQuestionnaire", "stepCount", "getStepCount", "setStepCount", "timeStamp", "", "getTimeStamp", "()Ljava/lang/Long;", "setTimeStamp", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "topBp", "getTopBp", "setTopBp", "weight", "getWeight", "setWeight", "describeContents", "", "writeToParcel", "", "flags", "CREATOR", "app_qaDebug"})
public final class DiagnosisModel implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String date;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long timeStamp;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> questionnaire;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String ailment;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String firstName;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastName;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String age;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String weight;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String heartRate;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String topBp;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String bottomBp;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String stepCount;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String mediciene;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> medications;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.domain.diagnosis.DiagnosisModel.CREATOR CREATOR = null;
    
    public DiagnosisModel() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDate() {
        return null;
    }
    
    public final void setDate(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getTimeStamp() {
        return null;
    }
    
    public final void setTimeStamp(@org.jetbrains.annotations.Nullable()
    java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> getQuestionnaire() {
        return null;
    }
    
    public final void setQuestionnaire(@org.jetbrains.annotations.Nullable()
    java.util.List<com.cardio.physician.domain.questionare.model.QuestionModel> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAilment() {
        return null;
    }
    
    public final void setAilment(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFirstName() {
        return null;
    }
    
    public final void setFirstName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastName() {
        return null;
    }
    
    public final void setLastName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAge() {
        return null;
    }
    
    public final void setAge(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getWeight() {
        return null;
    }
    
    public final void setWeight(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getHeartRate() {
        return null;
    }
    
    public final void setHeartRate(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTopBp() {
        return null;
    }
    
    public final void setTopBp(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBottomBp() {
        return null;
    }
    
    public final void setBottomBp(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getStepCount() {
        return null;
    }
    
    public final void setStepCount(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMediciene() {
        return null;
    }
    
    public final void setMediciene(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> getMedications() {
        return null;
    }
    
    public final void setMedications(@org.jetbrains.annotations.Nullable()
    java.util.List<com.cardio.physician.domain.diagnosis.MedicineModel> p0) {
    }
    
    public DiagnosisModel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel) {
        super();
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/cardio/physician/domain/diagnosis/DiagnosisModel$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/cardio/physician/domain/diagnosis/DiagnosisModel;", "app_qaDebug"})
    public static final class CREATOR implements android.os.Parcelable.Creator<com.cardio.physician.domain.diagnosis.DiagnosisModel> {
        
        private CREATOR() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.cardio.physician.domain.diagnosis.DiagnosisModel createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.cardio.physician.domain.diagnosis.DiagnosisModel[] newArray(int size) {
            return null;
        }
    }
}
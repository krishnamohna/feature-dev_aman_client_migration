package com.cardio.physician.domain.questionare.model;

import android.os.Parcel;
import android.os.Parcelable;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b/\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 B2\u00020\u0001:\u0001BB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u008b\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0014J\u000b\u0010(\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010!J\u000b\u0010.\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010!J\u000b\u00104\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u00aa\u0001\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u00106J\b\u00107\u001a\u000208H\u0016J\u0013\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<H\u00d6\u0003J\t\u0010=\u001a\u000208H\u00d6\u0001J\t\u0010>\u001a\u00020\u0006H\u00d6\u0001J\u0018\u0010?\u001a\u00020@2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010A\u001a\u000208H\u0016R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\"\u001a\u0004\b\'\u0010!\u00a8\u0006C"}, d2 = {"Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "id", "", "type", "", "question", "option_1", "option_2", "option_3", "option_4", "position", "secondary_option_1", "secondary_option_2", "secondary_option_3", "answer", "answerSecondary", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "setAnswer", "(Ljava/lang/String;)V", "getAnswerSecondary", "setAnswerSecondary", "getId", "getOption_1", "getOption_2", "getOption_3", "getOption_4", "getPosition", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getQuestion", "getSecondary_option_1", "getSecondary_option_2", "getSecondary_option_3", "getType", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "CREATOR", "app_qaDebug"})
public final class QuestionModel implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long type = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String question = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String option_1 = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String option_2 = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String option_3 = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String option_4 = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long position = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String secondary_option_1 = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String secondary_option_2 = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String secondary_option_3 = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String answer;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String answerSecondary;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.domain.questionare.model.QuestionModel.CREATOR CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.questionare.model.QuestionModel copy(@org.jetbrains.annotations.Nullable()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.Long type, @org.jetbrains.annotations.Nullable()
    java.lang.String question, @org.jetbrains.annotations.Nullable()
    java.lang.String option_1, @org.jetbrains.annotations.Nullable()
    java.lang.String option_2, @org.jetbrains.annotations.Nullable()
    java.lang.String option_3, @org.jetbrains.annotations.Nullable()
    java.lang.String option_4, @org.jetbrains.annotations.Nullable()
    java.lang.Long position, @org.jetbrains.annotations.Nullable()
    java.lang.String secondary_option_1, @org.jetbrains.annotations.Nullable()
    java.lang.String secondary_option_2, @org.jetbrains.annotations.Nullable()
    java.lang.String secondary_option_3, @org.jetbrains.annotations.Nullable()
    java.lang.String answer, @org.jetbrains.annotations.Nullable()
    java.lang.String answerSecondary) {
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
    
    public QuestionModel(@org.jetbrains.annotations.Nullable()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.Long type, @org.jetbrains.annotations.Nullable()
    java.lang.String question, @org.jetbrains.annotations.Nullable()
    java.lang.String option_1, @org.jetbrains.annotations.Nullable()
    java.lang.String option_2, @org.jetbrains.annotations.Nullable()
    java.lang.String option_3, @org.jetbrains.annotations.Nullable()
    java.lang.String option_4, @org.jetbrains.annotations.Nullable()
    java.lang.Long position, @org.jetbrains.annotations.Nullable()
    java.lang.String secondary_option_1, @org.jetbrains.annotations.Nullable()
    java.lang.String secondary_option_2, @org.jetbrains.annotations.Nullable()
    java.lang.String secondary_option_3, @org.jetbrains.annotations.Nullable()
    java.lang.String answer, @org.jetbrains.annotations.Nullable()
    java.lang.String answerSecondary) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getQuestion() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOption_1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOption_2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOption_3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOption_4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getPosition() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSecondary_option_1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSecondary_option_2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSecondary_option_3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAnswer() {
        return null;
    }
    
    public final void setAnswer(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAnswerSecondary() {
        return null;
    }
    
    public final void setAnswerSecondary(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public QuestionModel(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/cardio/physician/domain/questionare/model/QuestionModel$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/cardio/physician/domain/questionare/model/QuestionModel;", "app_qaDebug"})
    public static final class CREATOR implements android.os.Parcelable.Creator<com.cardio.physician.domain.questionare.model.QuestionModel> {
        
        private CREATOR() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.cardio.physician.domain.questionare.model.QuestionModel createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.cardio.physician.domain.questionare.model.QuestionModel[] newArray(int size) {
            return null;
        }
    }
}
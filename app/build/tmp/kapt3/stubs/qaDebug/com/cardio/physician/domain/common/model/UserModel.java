package com.cardio.physician.domain.common.model;

import android.os.Parcelable;
import com.cardio.physician.domain.user.SignUpUserType;
import com.cardio.physician.domain.user.UserType;
import kotlinx.android.parcel.Parcelize;

@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001@B\u008f\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0011H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00a3\u0001\u00102\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001J\t\u00103\u001a\u000204H\u00d6\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108H\u00d6\u0003J\t\u00109\u001a\u000204H\u00d6\u0001J\t\u0010:\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u000204H\u00d6\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010!R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014\u00a8\u0006A"}, d2 = {"Lcom/cardio/physician/domain/common/model/UserModel;", "Landroid/os/Parcelable;", "uid", "", "firstName", "lastName", "countryCode", "phoneNumber", "email", "imagePath", "signUpType", "Lcom/cardio/physician/domain/user/SignUpUserType;", "gender", "height", "weight", "dob", "userType", "Lcom/cardio/physician/domain/user/UserType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cardio/physician/domain/user/SignUpUserType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cardio/physician/domain/user/UserType;)V", "getCountryCode", "()Ljava/lang/String;", "getDob", "getEmail", "getFirstName", "getGender", "getHeight", "getImagePath", "getLastName", "getPhoneNumber", "getSignUpType", "()Lcom/cardio/physician/domain/user/SignUpUserType;", "getUid", "setUid", "(Ljava/lang/String;)V", "getUserType", "()Lcom/cardio/physician/domain/user/UserType;", "getWeight", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Builder", "app_qaDebug"})
public final class UserModel implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String uid;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String firstName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String countryCode = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String phoneNumber = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String email = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String imagePath = null;
    @org.jetbrains.annotations.Nullable()
    private final com.cardio.physician.domain.user.SignUpUserType signUpType = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String gender = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String height = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String weight = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String dob = null;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.domain.user.UserType userType = null;
    public static final android.os.Parcelable.Creator<com.cardio.physician.domain.common.model.UserModel> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.common.model.UserModel copy(@org.jetbrains.annotations.Nullable()
    java.lang.String uid, @org.jetbrains.annotations.Nullable()
    java.lang.String firstName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastName, @org.jetbrains.annotations.Nullable()
    java.lang.String countryCode, @org.jetbrains.annotations.Nullable()
    java.lang.String phoneNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String email, @org.jetbrains.annotations.Nullable()
    java.lang.String imagePath, @org.jetbrains.annotations.Nullable()
    com.cardio.physician.domain.user.SignUpUserType signUpType, @org.jetbrains.annotations.Nullable()
    java.lang.String gender, @org.jetbrains.annotations.Nullable()
    java.lang.String height, @org.jetbrains.annotations.Nullable()
    java.lang.String weight, @org.jetbrains.annotations.Nullable()
    java.lang.String dob, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.user.UserType userType) {
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
    
    public UserModel(@org.jetbrains.annotations.Nullable()
    java.lang.String uid, @org.jetbrains.annotations.Nullable()
    java.lang.String firstName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastName, @org.jetbrains.annotations.Nullable()
    java.lang.String countryCode, @org.jetbrains.annotations.Nullable()
    java.lang.String phoneNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String email, @org.jetbrains.annotations.Nullable()
    java.lang.String imagePath, @org.jetbrains.annotations.Nullable()
    com.cardio.physician.domain.user.SignUpUserType signUpType, @org.jetbrains.annotations.Nullable()
    java.lang.String gender, @org.jetbrains.annotations.Nullable()
    java.lang.String height, @org.jetbrains.annotations.Nullable()
    java.lang.String weight, @org.jetbrains.annotations.Nullable()
    java.lang.String dob, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.user.UserType userType) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUid() {
        return null;
    }
    
    public final void setUid(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFirstName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCountryCode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPhoneNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getImagePath() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.cardio.physician.domain.user.SignUpUserType component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.cardio.physician.domain.user.SignUpUserType getSignUpType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getGender() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getHeight() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getWeight() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDob() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.user.UserType component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.domain.user.UserType getUserType() {
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
    public static final class Creator implements android.os.Parcelable.Creator<com.cardio.physician.domain.common.model.UserModel> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.cardio.physician.domain.common.model.UserModel createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.cardio.physician.domain.common.model.UserModel[] newArray(int size) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lcom/cardio/physician/domain/common/model/UserModel$Builder;", "", "()V", "dob", "", "getDob", "()Ljava/lang/String;", "setDob", "(Ljava/lang/String;)V", "firstName", "getFirstName", "setFirstName", "lastName", "getLastName", "setLastName", "weight", "getWeight", "setWeight", "build", "Lcom/cardio/physician/domain/common/model/UserModel;", "app_qaDebug"})
    public static final class Builder {
        @org.jetbrains.annotations.NotNull()
        public static final com.cardio.physician.domain.common.model.UserModel.Builder INSTANCE = null;
        @org.jetbrains.annotations.Nullable()
        private static java.lang.String firstName;
        @org.jetbrains.annotations.Nullable()
        private static java.lang.String lastName;
        @org.jetbrains.annotations.Nullable()
        private static java.lang.String dob;
        @org.jetbrains.annotations.Nullable()
        private static java.lang.String weight;
        
        private Builder() {
            super();
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
        public final java.lang.String getDob() {
            return null;
        }
        
        public final void setDob(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getWeight() {
            return null;
        }
        
        public final void setWeight(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.domain.common.model.UserModel build() {
            return null;
        }
    }
}
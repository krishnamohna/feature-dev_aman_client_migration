package com.cardio.physician.network;

import com.google.firebase.FirebaseNetworkException;
import java.io.Serializable;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 0*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u00010BO\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010!\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00c6\u0003J\t\u0010#\u001a\u00020\bH\u00c6\u0003J\u0010\u0010$\u001a\u0004\u0018\u00018\u0000H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u000b\u0010%\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u0011\u0010\'\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000eH\u00c6\u0003Jj\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000eH\u00c6\u0001\u00a2\u0006\u0002\u0010)J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u00d6\u0003J\t\u0010.\u001a\u00020\u0006H\u00d6\u0001J\t\u0010/\u001a\u00020\u0004H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\t\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0013\"\u0004\b\u001a\u0010\u001bR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u00061"}, d2 = {"Lcom/cardio/physician/network/Resource;", "T", "Ljava/io/Serializable;", "apiConstant", "", "apiCode", "", "status", "Lcom/cardio/physician/network/Status;", "data", "message", "resourceId", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;ILcom/cardio/physician/network/Status;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Exception;)V", "getApiCode", "()I", "getApiConstant", "()Ljava/lang/String;", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getException", "()Ljava/lang/Exception;", "getMessage", "setMessage", "(Ljava/lang/String;)V", "getResourceId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStatus", "()Lcom/cardio/physician/network/Status;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;ILcom/cardio/physician/network/Status;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Exception;)Lcom/cardio/physician/network/Resource;", "equals", "", "other", "", "hashCode", "toString", "Companion", "app_qaDebug"})
public final class Resource<T extends java.lang.Object> implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String apiConstant = null;
    private final int apiCode = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.cardio.physician.network.Status status = null;
    @org.jetbrains.annotations.Nullable()
    private final T data = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String message;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer resourceId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Exception exception = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.network.Resource.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.network.Resource<T> copy(@org.jetbrains.annotations.Nullable()
    java.lang.String apiConstant, int apiCode, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.Status status, @org.jetbrains.annotations.Nullable()
    T data, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.Integer resourceId, @org.jetbrains.annotations.Nullable()
    java.lang.Exception exception) {
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
    
    public Resource(@org.jetbrains.annotations.Nullable()
    java.lang.String apiConstant, int apiCode, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.Status status, @org.jetbrains.annotations.Nullable()
    T data, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.Integer resourceId, @org.jetbrains.annotations.Nullable()
    java.lang.Exception exception) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getApiConstant() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getApiCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.network.Status component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.network.Status getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final T component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final T getData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getResourceId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Exception component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Exception getException() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tJK\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u0001H\u00052\u0010\b\u0002\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\n\u0010\u0011\u001a\u00060\rj\u0002`\u000eJ)\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u0001H\u0005\u00a2\u0006\u0002\u0010\u0013J\"\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0007J\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0007J\u0012\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u0005J)\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u0001H\u0005\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0019"}, d2 = {"Lcom/cardio/physician/network/Resource$Companion;", "", "()V", "error", "Lcom/cardio/physician/network/Resource;", "T", "errorCode", "", "msg", "", "apiConstant", "data", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Object;Ljava/lang/Exception;)Lcom/cardio/physician/network/Resource;", "firebaseException", "e", "loading", "(Ljava/lang/String;Ljava/lang/Object;)Lcom/cardio/physician/network/Resource;", "requiredResource", "resourceId", "setAlpha", "setLoading", "success", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.cardio.physician.network.Resource<T> success(@org.jetbrains.annotations.NotNull()
        java.lang.String apiConstant, @org.jetbrains.annotations.Nullable()
        T data) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.cardio.physician.network.Resource<T> error(int errorCode, @org.jetbrains.annotations.Nullable()
        java.lang.String msg) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.cardio.physician.network.Resource<T> error(@org.jetbrains.annotations.NotNull()
        java.lang.String apiConstant, int errorCode, @org.jetbrains.annotations.NotNull()
        java.lang.String msg, @org.jetbrains.annotations.Nullable()
        T data, @org.jetbrains.annotations.Nullable()
        java.lang.Exception exception) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.cardio.physician.network.Resource<T> requiredResource(@org.jetbrains.annotations.NotNull()
        java.lang.String apiConstant, int resourceId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.cardio.physician.network.Resource<T> setAlpha(@org.jetbrains.annotations.NotNull()
        java.lang.String apiConstant, int resourceId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.cardio.physician.network.Resource<T> loading(@org.jetbrains.annotations.NotNull()
        java.lang.String apiConstant, @org.jetbrains.annotations.Nullable()
        T data) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.cardio.physician.network.Resource<T> setLoading() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.cardio.physician.network.Resource<T> firebaseException(@org.jetbrains.annotations.NotNull()
        java.lang.Exception e) {
            return null;
        }
    }
}
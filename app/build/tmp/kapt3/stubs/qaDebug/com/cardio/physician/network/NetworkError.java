package com.cardio.physician.network;

import com.cardio.physician.network.api.ApiStatus;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00152\u00060\u0001j\u0002`\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0004H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/cardio/physician/network/NetworkError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorCode", "", "msg", "", "(ILjava/lang/String;)V", "getErrorCode", "()I", "getMsg", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "Companion", "app_qaDebug"})
public final class NetworkError extends java.lang.Exception {
    private final int errorCode = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String msg = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.network.NetworkError.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.cardio.physician.network.NetworkError copy(int errorCode, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
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
    
    public NetworkError(int errorCode, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getErrorCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMsg() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0012\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/cardio/physician/network/NetworkError$Companion;", "", "()V", "noMoreRecordFound", "Lcom/cardio/physician/network/NetworkError;", "noRecordFound", "msg", "", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.network.NetworkError noRecordFound(@org.jetbrains.annotations.Nullable()
        java.lang.String msg) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.network.NetworkError noMoreRecordFound() {
            return null;
        }
    }
}
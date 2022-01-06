package com.cardio.physician.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fJ\u0016\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0006J\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\fR\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/cardio/physician/data/local/UserManager;", "", "sharedPreferences", "Lcom/cardio/physician/data/local/SharedPreferences;", "(Lcom/cardio/physician/data/local/SharedPreferences;)V", "isTutorialRequired", "", "()Z", "clearAllPreference", "", "getBoolean", "key", "", "getString", "default", "setBoolean", "value", "setString", "app_qaDebug"})
@javax.inject.Singleton()
public final class UserManager {
    private final com.cardio.physician.data.local.SharedPreferences sharedPreferences = null;
    
    @javax.inject.Inject()
    public UserManager(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.SharedPreferences sharedPreferences) {
        super();
    }
    
    public final boolean isTutorialRequired() {
        return false;
    }
    
    public final void setBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean value) {
    }
    
    public final boolean getBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return false;
    }
    
    public final void setString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String p1_772401952) {
        return null;
    }
    
    public final void clearAllPreference() {
    }
}
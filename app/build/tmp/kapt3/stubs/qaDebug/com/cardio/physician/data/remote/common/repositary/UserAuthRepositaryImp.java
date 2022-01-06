package com.cardio.physician.data.remote.common.repositary;

import com.cardio.physician.domain.common.repository.UserAuthRepositary;
import com.cardio.physician.network.Resource;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016\u00a2\u0006\u0002\u0010\tJ9\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0016\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0012j\u0002`\u00130\u00110\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/cardio/physician/data/remote/common/repositary/UserAuthRepositaryImp;", "Lcom/cardio/physician/domain/common/repository/UserAuthRepositary;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/google/firebase/auth/FirebaseAuth;)V", "getFirebaseAuth", "()Lcom/google/firebase/auth/FirebaseAuth;", "getUserCreatedTime", "", "()Ljava/lang/Long;", "updateEmailAddress", "", "newEmail", "", "oldPass", "_firebaseException", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;Ljava/lang/String;Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class UserAuthRepositaryImp implements com.cardio.physician.domain.common.repository.UserAuthRepositary {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    
    @javax.inject.Inject()
    public UserAuthRepositaryImp(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.auth.FirebaseAuth getFirebaseAuth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object updateEmailAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String newEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String oldPass, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.Exception>> _firebaseException, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Long getUserCreatedTime() {
        return null;
    }
}
package com.cardio.physician.domain.common.repository;

import com.cardio.physician.network.Resource;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&\u00a2\u0006\u0002\u0010\u0004J9\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0016\u0010\n\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\f0\u000bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/cardio/physician/domain/common/repository/UserAuthRepositary;", "", "getUserCreatedTime", "", "()Ljava/lang/Long;", "updateEmailAddress", "", "newEmail", "", "oldPass", "_firebaseException", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;Ljava/lang/String;Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public abstract interface UserAuthRepositary {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateEmailAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String newEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String oldPass, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.Exception>> _firebaseException, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p3);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getUserCreatedTime();
}
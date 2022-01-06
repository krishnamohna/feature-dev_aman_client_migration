package com.cardio.physician.data.remote.fcm;

import android.content.Context;
import android.util.Log;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.domain.connection.ConnectionRepo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.GlobalScope;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0016\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0015J\u0006\u0010\u0019\u001a\u00020\u0010J\u0018\u0010\u001a\u001a\u00020\u00102\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001cR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/cardio/physician/data/remote/fcm/FcmManager;", "", "context", "Landroid/content/Context;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "okHttpClient", "Lokhttp3/OkHttpClient;", "executeService", "Ljava/util/concurrent/ExecutorService;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "connectionRepo", "Lcom/cardio/physician/domain/connection/ConnectionRepo;", "(Landroid/content/Context;Lcom/google/firebase/auth/FirebaseAuth;Lokhttp3/OkHttpClient;Ljava/util/concurrent/ExecutorService;Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/domain/connection/ConnectionRepo;)V", "getToken", "", "notifyUsersForDiagnosis", "isEdit", "", "userId", "", "sendPushNotification", "senderId", "message", "subscribeFcmTopic", "unsubscribeFcmTopic", "onUnsubscribed", "Lkotlin/Function0;", "app_qaDebug"})
public final class FcmManager {
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final okhttp3.OkHttpClient okHttpClient = null;
    private final java.util.concurrent.ExecutorService executeService = null;
    private final com.cardio.physician.data.local.UserManager userManager = null;
    private final com.cardio.physician.domain.connection.ConnectionRepo connectionRepo = null;
    
    @javax.inject.Inject()
    public FcmManager(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.ExecutorService executeService, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.connection.ConnectionRepo connectionRepo) {
        super();
    }
    
    public final void getToken() {
    }
    
    public final void subscribeFcmTopic() {
    }
    
    public final void unsubscribeFcmTopic(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onUnsubscribed) {
    }
    
    public final void notifyUsersForDiagnosis(boolean isEdit, @org.jetbrains.annotations.Nullable()
    java.lang.String userId) {
    }
    
    public final void sendPushNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String senderId, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
}
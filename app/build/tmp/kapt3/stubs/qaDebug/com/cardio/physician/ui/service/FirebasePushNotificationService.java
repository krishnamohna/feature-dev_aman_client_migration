package com.cardio.physician.ui.service;

import android.content.Intent;
import com.cardio.physician.ui.common.utils.BroadCastAction;
import com.cardio.physician.ui.common.utils.EXTRAS;
import com.cardio.physician.ui.common.utils.NotificationUtil;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016\u00a8\u0006\f"}, d2 = {"Lcom/cardio/physician/ui/service/FirebasePushNotificationService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "getTitle", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onMessageReceived", "", "onNewToken", "p0", "", "app_qaDebug"})
public final class FirebasePushNotificationService extends com.google.firebase.messaging.FirebaseMessagingService {
    
    public FirebasePushNotificationService() {
        super();
    }
    
    @java.lang.Override()
    public void onMessageReceived(@org.jetbrains.annotations.NotNull()
    com.google.firebase.messaging.RemoteMessage remoteMessage) {
    }
    
    private final java.lang.CharSequence getTitle(com.google.firebase.messaging.RemoteMessage remoteMessage) {
        return null;
    }
    
    @java.lang.Override()
    public void onNewToken(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
}
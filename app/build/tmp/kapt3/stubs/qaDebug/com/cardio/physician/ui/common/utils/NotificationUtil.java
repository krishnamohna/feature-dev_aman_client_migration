package com.cardio.physician.ui.common.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.cardio.physician.R;
import com.cardio.physician.ui.views.dashboard.DashboardActivity;
import com.google.firebase.messaging.RemoteMessage;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/cardio/physician/ui/common/utils/NotificationUtil;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "clearAllNotifications", "", "createNotificationChannel", "getMessage", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "getPendingIntent", "Landroid/app/PendingIntent;", "getTitle", "showNotification", "Companion", "app_qaDebug"})
public final class NotificationUtil {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.common.utils.NotificationUtil.Companion Companion = null;
    private static final java.lang.String CHANNEL_ID = "Default Notifications";
    
    public NotificationUtil(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void showNotification(@org.jetbrains.annotations.NotNull()
    com.google.firebase.messaging.RemoteMessage remoteMessage) {
    }
    
    private final android.app.PendingIntent getPendingIntent() {
        return null;
    }
    
    private final void createNotificationChannel() {
    }
    
    private final java.lang.CharSequence getMessage(com.google.firebase.messaging.RemoteMessage remoteMessage) {
        return null;
    }
    
    private final java.lang.CharSequence getTitle(com.google.firebase.messaging.RemoteMessage remoteMessage) {
        return null;
    }
    
    public final void clearAllNotifications() {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/cardio/physician/ui/common/utils/NotificationUtil$Companion;", "", "()V", "CHANNEL_ID", "", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
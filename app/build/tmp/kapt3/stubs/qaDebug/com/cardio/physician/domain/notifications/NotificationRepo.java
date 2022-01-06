package com.cardio.physician.domain.notifications;

import com.cardio.physician.domain.connection.ConnectionModel;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\'\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J)\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/cardio/physician/domain/notifications/NotificationRepo;", "", "acceptRequest", "Lcom/cardio/physician/domain/notifications/NotificationModel;", "senderId", "", "documentId", "notificationModel", "(Ljava/lang/String;Ljava/lang/String;Lcom/cardio/physician/domain/notifications/NotificationModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addNotificationsToConnections", "", "listConnections", "", "Lcom/cardio/physician/domain/connection/ConnectionModel;", "notificationType", "(Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotifications", "lastNotificationModel", "(Lcom/cardio/physician/domain/notifications/NotificationModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rejectRequest", "app_qaDebug"})
public abstract interface NotificationRepo {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNotifications(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.domain.notifications.NotificationModel lastNotificationModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.notifications.NotificationModel>> p1);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object acceptRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String senderId, @org.jetbrains.annotations.NotNull()
    java.lang.String documentId, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationModel notificationModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.notifications.NotificationModel> p3);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object rejectRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String senderId, @org.jetbrains.annotations.NotNull()
    java.lang.String documentId, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationModel notificationModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.notifications.NotificationModel> p3);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addNotificationsToConnections(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.connection.ConnectionModel> listConnections, @org.jetbrains.annotations.NotNull()
    java.lang.String notificationType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2);
}
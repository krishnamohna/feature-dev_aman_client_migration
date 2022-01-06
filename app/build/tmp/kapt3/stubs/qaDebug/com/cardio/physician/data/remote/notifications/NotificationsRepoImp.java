package com.cardio.physician.data.remote.notifications;

import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.fcm.FcmManager;
import com.cardio.physician.domain.connection.ConnectionModel;
import com.cardio.physician.domain.notifications.NotificationModel;
import com.cardio.physician.domain.notifications.NotificationRepo;
import com.cardio.physician.network.NetworkError;
import com.cardio.physician.ui.common.utils.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ)\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\'\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J!\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000eH\u0002J!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J)\u0010!\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/cardio/physician/data/remote/notifications/NotificationsRepoImp;", "Lcom/cardio/physician/domain/notifications/NotificationRepo;", "fireStoreDb", "Lcom/google/firebase/firestore/FirebaseFirestore;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "fcmManager", "Lcom/cardio/physician/data/remote/fcm/FcmManager;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/auth/FirebaseAuth;Lcom/cardio/physician/data/remote/fcm/FcmManager;Lcom/cardio/physician/data/local/UserManager;)V", "acceptRequest", "Lcom/cardio/physician/domain/notifications/NotificationModel;", "senderId", "", "documentId", "notificationModel", "(Ljava/lang/String;Ljava/lang/String;Lcom/cardio/physician/domain/notifications/NotificationModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addNotificationsToConnections", "", "listConnections", "", "Lcom/cardio/physician/domain/connection/ConnectionModel;", "notificationType", "(Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "changeRequestStatus", "accepted", "", "(ZLcom/cardio/physician/domain/notifications/NotificationModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotificationMsg", "getNotifications", "lastNotificationModel", "(Lcom/cardio/physician/domain/notifications/NotificationModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rejectRequest", "app_qaDebug"})
public final class NotificationsRepoImp implements com.cardio.physician.domain.notifications.NotificationRepo {
    private final com.google.firebase.firestore.FirebaseFirestore fireStoreDb = null;
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final com.cardio.physician.data.remote.fcm.FcmManager fcmManager = null;
    private final com.cardio.physician.data.local.UserManager userManager = null;
    
    @javax.inject.Inject()
    public NotificationsRepoImp(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fireStoreDb, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.fcm.FcmManager fcmManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getNotifications(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.domain.notifications.NotificationModel lastNotificationModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.cardio.physician.domain.notifications.NotificationModel>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object acceptRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String senderId, @org.jetbrains.annotations.NotNull()
    java.lang.String documentId, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationModel notificationModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.notifications.NotificationModel> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object rejectRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String senderId, @org.jetbrains.annotations.NotNull()
    java.lang.String documentId, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationModel notificationModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cardio.physician.domain.notifications.NotificationModel> p3) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object addNotificationsToConnections(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.connection.ConnectionModel> listConnections, @org.jetbrains.annotations.NotNull()
    java.lang.String notificationType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final java.lang.String getNotificationMsg(java.lang.String notificationType) {
        return null;
    }
    
    private final java.lang.Object changeRequestStatus(boolean accepted, com.cardio.physician.domain.notifications.NotificationModel notificationModel, kotlin.coroutines.Continuation<? super com.cardio.physician.domain.notifications.NotificationModel> p2) {
        return null;
    }
}
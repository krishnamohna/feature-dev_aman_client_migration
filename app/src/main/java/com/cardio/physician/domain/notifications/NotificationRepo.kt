package com.cardio.physician.domain.notifications

import com.cardio.physician.domain.connection.ConnectionModel

interface NotificationRepo {
    suspend fun getNotifications(lastNotificationModel: NotificationModel?): List<NotificationModel>
    suspend fun acceptRequest(
        senderId: String,
        documentId: String,
        notificationModel: NotificationModel,
    ): NotificationModel
    suspend fun rejectRequest(senderId: String, documentId: String, notificationModel: NotificationModel): NotificationModel
    suspend fun addNotificationsToConnections(listConnections:List<ConnectionModel>,notificationType:String)
}
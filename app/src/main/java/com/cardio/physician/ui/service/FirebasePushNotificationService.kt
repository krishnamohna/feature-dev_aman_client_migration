package com.cardio.physician.ui.service

import com.cardio.physician.ui.common.utils.NotificationUtil
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        NotificationUtil(this).showNotification(p0)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}
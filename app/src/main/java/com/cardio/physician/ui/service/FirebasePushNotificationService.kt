package com.cardio.physician.ui.service

import android.content.Intent
import com.cardio.physician.ui.common.utils.BroadCastAction
import com.cardio.physician.ui.common.utils.EXTRAS
import com.cardio.physician.ui.common.utils.NotificationUtil
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        NotificationUtil(this).showNotification(remoteMessage)
        //send broadcast to notify notification screen if its opened
        sendBroadcast(Intent(BroadCastAction.ACTION_NOTIFICATION_UPDATE).apply {
            putExtra(EXTRAS.EXTRAS_PUSH_NOTIFICATON_TITLE,getTitle(remoteMessage))
        })
    }

    private fun getTitle(remoteMessage: RemoteMessage): CharSequence? =
        remoteMessage.notification?.title ?: "Pocket Cardio"

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}
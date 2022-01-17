package com.cardio.physician.ui.common.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.EXTRAS.EXTRAS_FROM_NOTIFICATION
import com.cardio.physician.ui.views.dashboard.DashboardActivity
import com.google.firebase.messaging.RemoteMessage

class NotificationUtil constructor(private val context: Context) {

    companion object {
        private const val CHANNEL_ID = "Default Notifications"
    }

    fun showNotification(remoteMessage: RemoteMessage) {
        createNotificationChannel()
        var builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getTitle(remoteMessage))
            .setContentText(getMessage(remoteMessage))
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(getMessage(remoteMessage)))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(getPendingIntent())
//            .setAutoCancel(true)
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(System.currentTimeMillis().hashCode(), builder.build())
    }

    private fun getPendingIntent(): PendingIntent? {
        return PendingIntent.getActivity(context, 0, Intent(context, DashboardActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(EXTRAS_FROM_NOTIFICATION,true)
        }, 0)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.default_notification_channel_id)
            val descriptionText =
                context.getString(R.string.default_notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getMessage(remoteMessage: RemoteMessage): CharSequence? =
        remoteMessage.notification?.body ?: "Incompatible notification.Report to Support"

    private fun getTitle(remoteMessage: RemoteMessage): CharSequence? =
        remoteMessage.notification?.title ?: "Pocket Cardio"


    fun clearAllNotifications(){
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }
}
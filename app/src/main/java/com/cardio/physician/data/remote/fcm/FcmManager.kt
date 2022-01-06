package com.cardio.physician.data.remote.fcm

import android.content.Context
import android.util.Log
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.domain.connection.ConnectionRepo
import com.cardio.physician.ui.common.utils.Constants.FCM_SERVER_KEY
import com.cardio.physician.ui.common.utils.Preference.Companion.IS_TOPIC_SUBSCRIBED
import com.cardio.physician.ui.common.utils.Preference.Companion.PREF_DISPLAY_NAME
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class FcmManager @Inject constructor(
    @ApplicationContext context: Context,
    private val firebaseAuth: FirebaseAuth,
    private val okHttpClient: OkHttpClient,
    private val executeService: ExecutorService,
    private val userManager: UserManager,
    private val connectionRepo: ConnectionRepo,
) {

    fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Dashboard activity",
                    "Fetching FCM registration token failed",
                    task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            // Log and toast
            Log.d("Dashboard activity", token)
        })
    }

    fun subscribeFcmTopic() {
        if (!userManager.getBoolean(IS_TOPIC_SUBSCRIBED)) {
            firebaseAuth.currentUser?.let {
                FirebaseMessaging.getInstance().subscribeToTopic(it.uid)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            userManager.setBoolean(IS_TOPIC_SUBSCRIBED, true)
                        }
                    }
            }
        }
    }

    fun unsubscribeFcmTopic(onUnsubscribed: (() -> Unit)? = null) {
        firebaseAuth.currentUser?.let {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(it.uid)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        userManager.setBoolean(IS_TOPIC_SUBSCRIBED, false)
                    }
                    onUnsubscribed?.invoke()
                }
        }
    }

    fun notifyUsersForDiagnosis(isEdit: Boolean, userId: String?) {
        GlobalScope.launch {
            try {
                val msg =
                    if (isEdit) "${userManager.getString(PREF_DISPLAY_NAME)} has added a diagnosis." else
                        "${userManager.getString(PREF_DISPLAY_NAME)} has edited a diagnosis."
                userId?.let { it1 -> sendPushNotification(it1, msg) }

                /*connectionRepo.getMyConnections().forEach {

                }*/
            } catch (exp: Exception) {
                exp.printStackTrace()
            }
        }
    }

    fun sendPushNotification(senderId: String, message: String) {
        executeService.execute(Runnable {
            val mediaType: MediaType? = "application/json".toMediaTypeOrNull()
            val body =
                "{\r\n\t\"to\": \"/topics/$senderId\",\r\n\t\"notification\": {\r\n\t\t\"body\": \"$message\",\r\n\t\t\"title\": \"Pocket Cardio\"\r\n\t}\r\n}".toRequestBody(
                    mediaType)
            val request: Request = Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization",
                    "key=$FCM_SERVER_KEY")
                .build()
            val response: Response = okHttpClient.newCall(request).execute()
        })
    }
}
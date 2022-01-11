package com.cardio.physician.data.remote.notifications

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.data.remote.fcm.FcmManager
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.domain.notifications.NotificationModel
import com.cardio.physician.domain.notifications.NotificationRepo
import com.cardio.physician.network.NetworkError
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.Constants.USER_TYPE_PATIENT
import com.cardio.physician.ui.common.utils.Constants.USER_TYPE_PHYSICIAN
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.NOTIFICATION_TYPE_ADD_DIAGNOSIS
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.NOTIFICATION_TYPE_EDIT_DIAGNOSIS
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.NOTIFICATION_TYPE_REQUEST
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.NOTIFICATION_TYPE_REQUEST_ACCEPTED
import com.cardio.physician.ui.common.utils.Preference.Companion.PREF_DISPLAY_NAME
import com.cardio.physician.ui.common.utils.extentions.toNotificationsList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NotificationsRepoImp @Inject constructor(
    private val fireStoreDb: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val fcmManager: FcmManager,
    private val userManager: UserManager,
) : NotificationRepo {

    override suspend fun getNotifications(lastNotificationModel: NotificationModel?): List<NotificationModel> {
        var query: Query = fireStoreDb.collection(FireStoreCollection.NOTIFICATIONS)
            .document(UserType.USER_TYPE_PHYSICIAN)
            .collection(getPatientUid())
            .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
            .limit(PAGE_SIZE.toLong())
        lastNotificationModel?.let {
            query = fireStoreDb.collection(FireStoreCollection.NOTIFICATIONS)
                .document(UserType.USER_TYPE_PHYSICIAN)
                .collection(getPatientUid())
                .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
                .startAfter(it.timeStamp)
                .limit(PAGE_SIZE.toLong())
        }
        val querySnapshot = query.get().await()
        if (querySnapshot.isEmpty) {
            if (lastNotificationModel == null)
                throw NetworkError.noRecordFound()
            else
                throw  NetworkError.noMoreRecordFound()
        } else {
            return querySnapshot.toNotificationsList()
        }
    }

    override suspend fun acceptRequest(
        senderId: String,
        documentId: String,
        notificationModel: NotificationModel
    ): NotificationModel {
        return changeRequestStatus(true, notificationModel)
    }

    override suspend fun rejectRequest(
        senderId: String,
        documentId: String,
        notificationModel: NotificationModel
    ): NotificationModel {
        return changeRequestStatus(false, notificationModel)
    }

    override suspend fun addNotificationsToConnections(
        listConnections: List<ConnectionModel>,
        notificationType: String,
    ) {
        val mapNotification = mutableMapOf<String, Any?>()
        mapNotification[FireStoreDocKey.USER_ID] = firebaseAuth.currentUser?.uid!!
        mapNotification[FireStoreDocKey.NOTIFICATION_TYPE] = notificationType.toInt()
        mapNotification[FireStoreDocKey.EMAIL] =
            userManager.getString(Preference.PREF_EMAIL)
        mapNotification[FireStoreDocKey.IMAGE_URL] =
            userManager.getString(Preference.PREF_PROFILE_IMAGE)
        mapNotification[FireStoreDocKey.FIRST_NAME] =
            userManager.getString(Preference.PREF_FIRST_NAME)
        mapNotification[FireStoreDocKey.LAST_NAME] =
            userManager.getString(Preference.PREF_LAST_NAME)
        mapNotification[FireStoreDocKey.REQUEST_STATUS] = true
        mapNotification[FireStoreDocKey.TIME_STAMP_CAMEL] = System.currentTimeMillis()
        listConnections.forEach {connection->
            fireStoreDb.collection(FireStoreCollection.NOTIFICATIONS)
                .document(USER_TYPE_PATIENT)
                .collection(connection.userId!!)
                .document().set(mapNotification).await()
            //send push
            val msg = getNotificationMsg(notificationType)
            fcmManager.sendPushNotification(connection.userId,msg, "Diagnosis")
        }
    }

    private fun getNotificationMsg(notificationType: String): String {
        return  when(notificationType){
            NOTIFICATION_TYPE_ADD_DIAGNOSIS->{
                "${userManager.getString(Preference.PREF_DISPLAY_NAME)} has added your new diagnosis."
            }
            NOTIFICATION_TYPE_EDIT_DIAGNOSIS->{
                "${userManager.getString(Preference.PREF_DISPLAY_NAME)} has edited your diagnosis."
            }
            else -> {
                "Incompatible notification type.Talk to support"
            }
        }
    }

    private suspend fun changeRequestStatus(
        accepted: Boolean,
        notificationModel: NotificationModel
    ): NotificationModel {
        val mapConnection = mutableMapOf<String, Any?>()
        mapConnection[FireStoreDocKey.REQUEST_STATUS] = accepted
        val refConnectionPatient = fireStoreDb.collection(FireStoreCollection.CONNECTIONS)
            .document(USER_TYPE_PATIENT)
            .collection(getPatientUid()).document(notificationModel.userId)
        val refConnectionDoctor = fireStoreDb.collection(FireStoreCollection.CONNECTIONS)
            .document(Constants.USER_TYPE_PHYSICIAN)
            .collection(notificationModel.userId)
            .document(getPatientUid())
        val refPatientNotification = fireStoreDb.collection(FireStoreCollection.NOTIFICATIONS)
            .document(USER_TYPE_PATIENT)
            .collection(getPatientUid())
            .document(notificationModel.documentId)
        val refDoctorNotifications = fireStoreDb.collection(FireStoreCollection.NOTIFICATIONS)
            .document(USER_TYPE_PHYSICIAN)
            .collection(notificationModel.userId)
            .document()
        fireStoreDb.runBatch {
            it.update(refConnectionPatient, mapConnection)
            it.update(refConnectionDoctor, mapConnection)
            it.update(refPatientNotification, mapConnection)
            if (accepted) {
                val mapNotification = mutableMapOf<String, Any?>()
                mapNotification[FireStoreDocKey.USER_ID] = firebaseAuth.currentUser?.uid!!
                mapNotification[FireStoreDocKey.NOTIFICATION_TYPE] = NOTIFICATION_TYPE_REQUEST.toInt()
                mapNotification[FireStoreDocKey.EMAIL] =
                    userManager.getString(Preference.PREF_EMAIL)
                mapNotification[FireStoreDocKey.IMAGE_URL] =
                    userManager.getString(Preference.PREF_PROFILE_IMAGE)
                mapNotification[FireStoreDocKey.FIRST_NAME] =
                    userManager.getString(Preference.PREF_FIRST_NAME)
                mapNotification[FireStoreDocKey.LAST_NAME] =
                    userManager.getString(Preference.PREF_LAST_NAME)
                mapNotification[FireStoreDocKey.REQUEST_STATUS] = true
                mapNotification[FireStoreDocKey.TIME_STAMP_CAMEL] = System.currentTimeMillis()
                it.set(refDoctorNotifications, mapNotification)
            }
        }.await()
        if (accepted) {
            notificationModel.requestStatus=true
            //send push notification to use to notify him
            fcmManager.sendPushNotification(notificationModel.userId,
                "${userManager.getString(PREF_DISPLAY_NAME)} has accepted your request.", "Add Request")
        }else{
            notificationModel.requestStatus=false
        }
        return notificationModel
    }

}
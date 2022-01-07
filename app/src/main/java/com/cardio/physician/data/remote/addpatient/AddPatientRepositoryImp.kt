package com.cardio.physician.data.remote.addpatient

import com.cardio.physician.data.local.UserManager
import com.cardio.physician.data.remote.fcm.FcmManager
import com.cardio.physician.domain.common.repository.BaseRepository
import com.cardio.physician.network.api.ApiService
import com.cardio.physician.domain.addpatient.PatientModel
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.NOTIFICATION_TYPE_REQUEST
import com.cardio.physician.ui.common.utils.extentions.toCPatientModel
import com.cardio.physician.ui.common.utils.extentions.toPatientModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AddPatientRepositoryImp @Inject constructor(override val firebaseAuth: FirebaseAuth,
                                                  private val fireStore: FirebaseFirestore,
                                                  private val storageReference: StorageReference,
                                                  private val fcmManager: FcmManager,
                                                  private val userManager: UserManager,
                                                  apiService: ApiService) : BaseRepository(firebaseAuth, fireStore, storageReference, apiService) {
    suspend fun getPatientList(searchString : String): List<PatientModel> {
        return fireStore.collection(FireStoreCollection.USERS).
        orderBy(FireStoreDocKey.SEARCH_NAME, Query.Direction.ASCENDING).
        startAt(searchString.lowercase()).endAt(searchString + "\uf8ff").
        whereEqualTo(FireStoreDocKey.USER_TYPE, UserType.USER_TYPE_PATIENT)
            .limit(25)
            .get().await().toPatientModel()
    }

    suspend fun getConnectedPatientList(): List<PatientModel> {
        return firebaseAuth.currentUser?.uid?.let {
            fireStore.collection(FireStoreCollection.CONNECTIONS)
                .document(UserType.USER_TYPE_PHYSICIAN)
                .collection(it)
                .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
        }!!.get().await().toCPatientModel()
    }

    suspend fun getAllPatientList(): List<PatientModel> {
        return fireStore.collection(FireStoreCollection.USERS).
        whereEqualTo(FireStoreDocKey.USER_TYPE, UserType.USER_TYPE_PATIENT)
            .limit(25)
            .get().await().toPatientModel()
    }

    suspend fun getPatientListByDate(date: String): List<PatientModel> {
        val query = firebaseAuth.currentUser?.uid?.let {
            fireStore.collection(FireStoreCollection.CONNECTIONS)
                .document(UserType.USER_TYPE_PHYSICIAN)
                .collection(it)
                .orderBy(FireStoreDocKey.TIME_STAMP_CAMEL, Query.Direction.DESCENDING)
        }
        val querySnapshot = query?.get()?.await()
        return querySnapshot?.toCPatientModel()!!
    }


    suspend fun getPatientListWithEmail(searchString : String): List<PatientModel> {
        return fireStore.collection(FireStoreCollection.USERS).
        orderBy(FireStoreDocKey.EMAIL, Query.Direction.ASCENDING).
        startAt(searchString.lowercase()).endAt(searchString + "\uf8ff").
        whereEqualTo(FireStoreDocKey.USER_TYPE, UserType.USER_TYPE_PATIENT)
            .limit(25)
            .get().await().toPatientModel()
    }

    suspend fun addDataToFirestore(patientId: PatientModel?, hashMap: HashMap<String, Any>) {
        val userId= firebaseAuth.currentUser?.uid
        fireStore.collection(FireStoreCollection.CONNECTIONS).document(UserType.USER_TYPE_PHYSICIAN).collection(userId?:"").document(patientId?.userId?:"").set(hashMap)
        fireStore.collection(FireStoreCollection.CONNECTIONS).document(UserType.USER_TYPE_PATIENT).collection(patientId?.userId?:"").document(userId?:"").set(hashMap)
        addNotificationsToConnections(NOTIFICATION_TYPE_REQUEST, patientId)
    }

    suspend fun addNotificationsToConnections(
        notificationType: String,
        patientId: PatientModel?
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
//        mapNotification[FireStoreDocKey.REQUEST_STATUS] = false
        mapNotification[FireStoreDocKey.TIME_STAMP_CAMEL] = System.currentTimeMillis()

        fireStore.collection(FireStoreCollection.NOTIFICATIONS)
            .document(com.cardio.physician.ui.common.utils.Constants.USER_TYPE_PATIENT)
            .collection(patientId?.userId!!)
            .document().set(mapNotification).await()

        val msg = getNotificationMsg(notificationType)
        fcmManager.sendPushNotification(patientId.userId,msg)

    /*listConnections.forEach {connection->

            //send push

        }*/
    }

    private fun getNotificationMsg(notificationType: String): String {
        return  when(notificationType){
            FireStoreDocKey.NOTIFICATION_TYPE_ADD_DIAGNOSIS ->{
                "${userManager.getString(Preference.PREF_DISPLAY_NAME)} has added a diagnosis for you."
            }
            FireStoreDocKey.NOTIFICATION_TYPE_EDIT_DIAGNOSIS ->{
                "${userManager.getString(Preference.PREF_DISPLAY_NAME)} has edited a diagnosis for you."
            }
            FireStoreDocKey.NOTIFICATION_TYPE_REQUEST ->{
                "${userManager.getString(Preference.PREF_DISPLAY_NAME)} has sent a request to add you as patient."
            }
            else -> {
                "Incompatible notification type.Talk to support"
            }
        }
    }
}
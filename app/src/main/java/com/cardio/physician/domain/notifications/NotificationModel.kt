package com.cardio.physician.domain.notifications

import com.google.firebase.firestore.QueryDocumentSnapshot

data class NotificationModel(
    var documentId:String,
    val type: String,
    val userId: String,
    val timeStamp: Long,
    val lastName: String,
    val imageUrl: String,
    val firstName: String,
    val email: String,
    var requestStatus : Boolean?,
    var documentSnapShot: QueryDocumentSnapshot
){
    fun getFullName()= "$firstName $lastName"
}

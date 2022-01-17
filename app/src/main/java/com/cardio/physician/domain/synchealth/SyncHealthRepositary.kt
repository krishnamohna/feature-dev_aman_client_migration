package com.cardio.physician.domain.synchealth

import com.cardio.physician.domain.fitness.model.FitnessModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

interface SyncHealthRepositary {
    suspend fun getLastSavedHealthLogCollectionDate(): FitnessModel?
    suspend fun saveHealthData(fitnessModel: FitnessModel)
    suspend fun getHealthLogByDate(date:String, userId: String?):FitnessModel
    suspend fun updateHealthLogByDate(fitnessModel: FitnessModel, userId:String?)
    suspend fun getHealthLogs(days:Long, userId: String?, listener: EventListener<QuerySnapshot>)
}
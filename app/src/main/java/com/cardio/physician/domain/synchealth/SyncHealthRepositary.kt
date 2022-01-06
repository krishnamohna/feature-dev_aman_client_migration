package com.cardio.physician.domain.synchealth

import com.cardio.physician.domain.fitness.model.FitnessModel

interface SyncHealthRepositary {
    suspend fun getLastSavedHealthLogCollectionDate(): FitnessModel?
    suspend fun saveHealthData(fitnessModel: FitnessModel)
    suspend fun getHealthLogByDate(date:String):FitnessModel
    suspend fun updateHealthLogByDate(fitnessModel: FitnessModel, userId:String?)
    suspend fun getHealthLogs(days:Long, userId: String?):List<FitnessModel>
}
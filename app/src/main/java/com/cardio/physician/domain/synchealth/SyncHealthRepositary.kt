package com.cardio.physician.domain.synchealth

import com.cardio.physician.domain.fitness.model.FitnessModel

interface SyncHealthRepositary {
    suspend fun getLastSavedCollectionDate(): FitnessModel?
    suspend fun saveHealthData(fitnessModel: FitnessModel)
    suspend fun updateHealthLogByDate(fitnessModel: FitnessModel)
}
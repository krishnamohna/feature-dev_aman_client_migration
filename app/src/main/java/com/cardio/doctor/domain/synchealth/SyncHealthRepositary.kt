package com.cardio.doctor.domain.synchealth

import com.cardio.doctor.domain.fitness.model.FitnessModel

interface SyncHealthRepositary {
    suspend fun getLastSavedCollectionDate(): FitnessModel?
    suspend fun saveHealthData(
        fitnessModel: FitnessModel
    )
}
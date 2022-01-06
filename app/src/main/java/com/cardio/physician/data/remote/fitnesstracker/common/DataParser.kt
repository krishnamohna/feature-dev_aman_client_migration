package com.cardio.physician.data.remote.fitnesstracker.common

import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.fitness.model.SyncModel

open class DataParser {

    fun parseSyncModelToFitness(syncModel: SyncModel): FitnessModel {
        val fitnessModel = FitnessModel()
        fitnessModel.weight = (syncModel.arrayWeightLogs.findLast {
            it != null
        })?.weight
        fitnessModel.heartRate = (syncModel.arrayHeartLogs.findLast {
            it?.restHeartRate != null && it.restHeartRate!="0"
        })?.restHeartRate
        fitnessModel.bloodPressureTopBp = (syncModel.arrayBloodPresure.findLast {
            it != null
        })?.topBp
        fitnessModel.bloodPressureBottomBp = (syncModel.arrayBloodPresure.findLast {
            it != null
        })?.bottomBp
        fitnessModel.stepCount = (syncModel.arrayStepCounts.findLast {
            it != null
        })?.stepCount
        return fitnessModel
    }
}
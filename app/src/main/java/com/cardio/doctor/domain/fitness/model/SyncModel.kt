package com.cardio.doctor.domain.fitness.model

data class SyncModel(
    var listHeartLogs: Array<HeartRateModel?>,
    var listWeightLogs: Array<WeightModel?>,
    var listBloodPresure: Array<BloodPressureModel?>
)

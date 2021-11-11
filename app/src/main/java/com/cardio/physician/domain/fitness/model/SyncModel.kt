package com.cardio.physician.domain.fitness.model

data class SyncModel(
    var arrayHeartLogs: Array<HeartRateModel?>,
    var arrayWeightLogs: Array<WeightModel?>,
    var arrayBloodPresure: Array<BloodPressureModel?>,
    var arrayDates: MutableList<DateModel>
)

package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.models.heartrate

data class Value(
    val customHeartRateZones: List<Any>,
    val heartRateZones: List<HeartRateZone>,
    val restingHeartRate: Int
)
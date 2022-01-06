package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate

data class HeartRateZone(
    val caloriesOut: Double,
    val max: Int,
    val min: Int,
    val minutes: Int,
    val name: String
)
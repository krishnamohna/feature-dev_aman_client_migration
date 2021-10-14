package com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.heartrate

data class HeartRateZone(
    val caloriesOut: Double,
    val max: Int,
    val min: Int,
    val minutes: Int,
    val name: String
)
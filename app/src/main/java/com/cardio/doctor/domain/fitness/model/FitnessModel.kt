package com.cardio.doctor.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FitnessModel(
    val weight: Double?,
    val height: Double?,
    val heartRate: Int?,
    val weightUnit: String
) : Parcelable

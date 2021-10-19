package com.cardio.doctor.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FitnessModel(
    var weight: Double?=null,
    val height: Double?=null,
    var heartRate: Float?=null,
    val weightUnit: String?=null
) : Parcelable

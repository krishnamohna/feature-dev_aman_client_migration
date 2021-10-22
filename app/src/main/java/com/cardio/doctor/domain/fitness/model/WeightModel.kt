package com.cardio.doctor.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeightModel(val weight: Double?, val date: String?) : Parcelable
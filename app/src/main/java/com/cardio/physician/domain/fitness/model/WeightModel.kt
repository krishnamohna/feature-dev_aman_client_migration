package com.cardio.physician.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeightModel(val weight: Double?, val date: String?) : Parcelable
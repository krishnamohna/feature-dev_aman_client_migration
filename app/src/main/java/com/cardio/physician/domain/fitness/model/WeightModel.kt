package com.cardio.physician.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeightModel(val weight: String?, val date: String?) : Parcelable
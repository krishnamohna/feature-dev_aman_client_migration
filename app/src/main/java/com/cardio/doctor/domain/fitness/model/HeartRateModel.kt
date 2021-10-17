package com.cardio.doctor.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeartRateModel(val restHeartRate:Int?) : Parcelable
package com.cardio.physician.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FitnessModel(
    var weight: Double?=null,
    val height: Double?=null,
    var heartRate: Float?=null,
    val weightUnit: String?=null,
    var bloodPressureTopBp: Double?=null,
    var date:String?=null,
    var timeStamp:Long?=null,
    var bloodPressureBottomBp: Double?=null,
) : Parcelable

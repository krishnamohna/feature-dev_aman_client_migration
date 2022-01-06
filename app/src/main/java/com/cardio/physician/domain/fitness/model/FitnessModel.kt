package com.cardio.physician.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FitnessModel(
    var weight: String?=null,
    val height: String?=null,
    var heartRate: String?=null,
    val weightUnit: String?=null,
    var bloodPressureTopBp: String?=null,
    var date:String?=null,
    var timeStamp:Long?=null,
    var bloodPressureBottomBp: String?=null,
    var stepCount: String?=null,
) : Parcelable

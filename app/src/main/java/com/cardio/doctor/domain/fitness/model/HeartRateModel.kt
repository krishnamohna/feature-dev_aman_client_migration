package com.cardio.doctor.domain.fitness.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class HeartRateModel(val restHeartRate: Int?,val date:String?=null) : Parcelable
package com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.heartrate

import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.cardio.doctor.ui.common.utils.DateFormat_
import com.cardio.doctor.ui.common.utils.formatDate
import com.google.gson.annotations.SerializedName

data class HeartRateEntity(@SerializedName("activities-heart")val activities_heart: List<ActivitiesHeart>) {

    fun toModel(): HeartRateModel {
        return HeartRateModel(activities_heart?.first()?.value?.restingHeartRate)
    }

    fun toModelList(): MutableList<HeartRateModel> {
        var list= mutableListOf<HeartRateModel>()
        activities_heart?.forEach {
            list.add(HeartRateModel(it.value?.restingHeartRate, formatDate(DateFormat_.DATE_FORMAT_YYYY_MMM_DD,it.dateTime)))
        }
        return list
    }
}
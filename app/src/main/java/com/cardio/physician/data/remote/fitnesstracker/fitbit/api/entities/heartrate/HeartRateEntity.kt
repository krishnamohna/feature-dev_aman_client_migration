package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.heartrate

import com.cardio.physician.domain.fitness.model.HeartRateModel
import com.cardio.physician.ui.common.utils.DateFormat_
import com.cardio.physician.ui.common.utils.formatDate
import com.google.gson.annotations.SerializedName

data class HeartRateEntity(@SerializedName("activities-heart")val activities_heart: List<ActivitiesHeart>) {

    fun toModel(): HeartRateModel {
        return HeartRateModel(activities_heart?.first()?.value?.restingHeartRate.toString())
    }

    fun toModelList(): MutableList<HeartRateModel> {
        var list= mutableListOf<HeartRateModel>()
        activities_heart?.forEach {
            list.add(HeartRateModel(it.value?.restingHeartRate.toString(), formatDate(DateFormat_.DATE_FORMAT_YYYY_MMM_DD,it.dateTime)))
        }
        return list
    }
}
package com.cardio.doctor.data.remote.fitnesstracker.fitbit.api.models.heartrate

import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.google.gson.annotations.SerializedName

data class HeartRateEntity(@SerializedName("activities-heart")val activities_heart: List<ActivitiesHeart>) {

    fun toModel(): HeartRateModel {
        return HeartRateModel(activities_heart?.first()?.value?.restingHeartRate)
    }

    fun toModelList(): MutableList<HeartRateModel> {
        var list= mutableListOf<HeartRateModel>()
        activities_heart?.forEach {
            list.add(HeartRateModel(it.value?.restingHeartRate))
        }
        return list
    }
}
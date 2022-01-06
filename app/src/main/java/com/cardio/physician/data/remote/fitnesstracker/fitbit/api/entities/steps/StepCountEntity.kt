package com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.steps

import com.cardio.physician.domain.fitness.model.StepCountModel
import com.cardio.physician.ui.common.utils.DateFormat_
import com.cardio.physician.ui.common.utils.formatDate
import com.google.gson.annotations.SerializedName

data class StepCountEntity(
    @SerializedName("activities-steps") val activities_steps: List<ActivitiesStep>,
) {
    fun toModelList(): List<StepCountModel> {
        val listStepsModel = mutableListOf<StepCountModel>()
        activities_steps.forEach {
            listStepsModel.add(StepCountModel(it.value,formatDate(DateFormat_.DATE_FORMAT_YYYY_MMM_DD,it.dateTime)))
        }
        return listStepsModel
    }
}
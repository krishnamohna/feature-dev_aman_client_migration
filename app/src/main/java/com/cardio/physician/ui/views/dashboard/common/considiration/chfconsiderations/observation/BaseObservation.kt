package com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.observation

import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.CHFConsiderationUtil

open class BaseObservation {

    protected var OBSERVATION_READING_COUNT: Float = 0f
    protected var TWENTY_PERCENT_OF_READING_COUNT: Float = 0f

    private val util: CHFConsiderationUtil by lazy {
        CHFConsiderationUtil()
        //hello
    }

    fun hasLowHeartRate(healthLogs: List<FitnessModel>): Boolean {
        return util.countLowHeartRate(healthLogs,OBSERVATION_READING_COUNT) > TWENTY_PERCENT_OF_READING_COUNT
    }

    fun hasHighHeartRate(healthLogs: List<FitnessModel>): Boolean {
        return util.countHighHeartRate(healthLogs,OBSERVATION_READING_COUNT) > TWENTY_PERCENT_OF_READING_COUNT
    }

    fun hasHighBp(healthLogs: List<FitnessModel>): Boolean {
        return util.countHighBp(healthLogs,OBSERVATION_READING_COUNT) > TWENTY_PERCENT_OF_READING_COUNT
    }

    fun hasLowBp(healthLogs: List<FitnessModel>): Boolean {
        return util.countLowBp(healthLogs,OBSERVATION_READING_COUNT) > TWENTY_PERCENT_OF_READING_COUNT
    }

    fun hasLessStepsBelowAvg(stepsAvg: Float, healthLogs: List<FitnessModel>): Boolean {
        return util.countStepsBeloAvg(stepsAvg,healthLogs,OBSERVATION_READING_COUNT) > TWENTY_PERCENT_OF_READING_COUNT
    }

    fun checkWeightIncrease(
        weight: Float,
        healthLogs: List<FitnessModel>,
        WEIGHT_INCREASE_REFRENCE_FOR_72_HOUR: Int
    ): Boolean {
        return util.checkWeightIncreaseInLastEntries(
            weight, healthLogs,
            WEIGHT_INCREASE_REFRENCE_FOR_72_HOUR,
            OBSERVATION_READING_COUNT
        )
    }


}
package com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations

import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.ui.common.utils.extentions.isValidHealthLog
import com.cardio.physician.ui.views.dashboard.common.considiration.BaseCosideration
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.CHFConsideration.RESULTS.ERROR_MIN_ENTRIES_BP
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.CHFConsideration.RESULTS.ERROR_MIN_ENTRIES_HEART_RATE
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.CHFConsideration.RESULTS.ERROR_MIN_ENTRIES_STEPS
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.CHFConsideration.RESULTS.ERROR_MIN_ENTRIES_WEIGHT
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.observation.Observation10Days.Observation10.OBSERVATION_READING_COUNT

class PreconditionsCHF constructor(private val baseCosideration: BaseCosideration) {

    fun checkHeartPreCondition(logs: List<FitnessModel>) {
        if (logs.count {
                it.heartRate.isValidHealthLog()
            } < OBSERVATION_READING_COUNT) {
            baseCosideration.addErrorMsgToConsideration(ERROR_MIN_ENTRIES_HEART_RATE)
        }
    }

    fun checkStepsPreCondition(logs: List<FitnessModel>) {
        if (logs.count {
                it.stepCount.isValidHealthLog()
            } < OBSERVATION_READING_COUNT) {
            baseCosideration.addErrorMsgToConsideration(ERROR_MIN_ENTRIES_STEPS)
        }
    }

    fun checkBpPreCondition(logs: List<FitnessModel>) {
        if (logs.count {
                it.bloodPressureBottomBp.isValidHealthLog() && it.bloodPressureTopBp.isValidHealthLog()
            } < OBSERVATION_READING_COUNT) {
            baseCosideration.addErrorMsgToConsideration(ERROR_MIN_ENTRIES_BP)
        }
    }

    fun checkWeightPreCondition(logs: List<FitnessModel>) {
        if (logs.count {
                it.weight.isValidHealthLog()
            } ==0) {
            baseCosideration.addErrorMsgToConsideration(ERROR_MIN_ENTRIES_WEIGHT)
        }
    }


}
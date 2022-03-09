package com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations

import android.content.Context
import com.cardio.physician.R
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.ui.common.utils.extentions.inValidHealthLog
import com.cardio.physician.ui.common.utils.extentions.isValidHealthLog
import com.cardio.physician.ui.common.utils.extentions.removeSpace

class CHFConsiderationUtil {

    fun checkWeightIncrease(
        dryWeight: Float,
        healthLogs: List<FitnessModel>,
        listPastDates: MutableList<String>,
        WEIGHT_REFERENCE: Int,
    ): Boolean {
        var isIncreased = false
        healthLogs.filter {
            it.weight.isValidHealthLog() && listPastDates.contains(it.date)
        }.forEach {
            if (it.weight!!.toFloat() - dryWeight > WEIGHT_REFERENCE) {
                isIncreased = true
                return isIncreased
            }
        }
        return isIncreased
    }

    fun checkWeightIncreaseInLastEntries(
        dryWeight: Float,
        logs: List<FitnessModel>,
        WEIGHT_REFERENCE: Int,
        observationReadingCount: Float
    ): Boolean {
        var isIncreased = false
        val wightLogCount = logs.count {
            it.weight.isValidHealthLog()
        }
        var startIndexSublist = -1
        if (wightLogCount > 0) {
            startIndexSublist = wightLogCount
            if (wightLogCount > observationReadingCount) {
                startIndexSublist = observationReadingCount.toInt()
            }
        }
        if (startIndexSublist != -1) {
            logs.filter {
                it.weight.isValidHealthLog()
            }.let {
                it.subList(it.size - startIndexSublist, it.size)
            }.forEach { log ->
                log.weight?.let {
                    if (it.toFloat() - dryWeight > WEIGHT_REFERENCE) {
                        isIncreased = true
                        return isIncreased
                    }
                }
            }
        }
        return isIncreased
    }

    fun countLowHeartRate(logs: List<FitnessModel>,observationReadingCount:Float): Int {
        var count = 0
        val countLogs = logs.count {
            it.heartRate.isValidHealthLog()
        }
        if (countLogs >= observationReadingCount) {
            logs.filter {
                it.heartRate.isValidHealthLog()
            }.let {
                it.subList(it.size - observationReadingCount.toInt(), it.size)
            }.forEach { log ->
                log.heartRate?.let {
                    if (it.toFloat() < CHFConsideration.LOW_HEART_RATE)
                        count++
                }
            }
        }
        return count
    }

    fun countLowBp(logs: List<FitnessModel>,observationReadingCount: Float): Int {
        var count = 0
        val countLogs = logs.count { it.bloodPressureBottomBp.isValidHealthLog() }
        if (countLogs >= observationReadingCount) {
            logs.filter {
                it.bloodPressureBottomBp.isValidHealthLog()
            }.let {
                it.subList(it.size - observationReadingCount.toInt(), it.size)
            }.forEach { log ->
                log.bloodPressureBottomBp?.let {
                    if (it.toFloat() > CHFConsideration.LOW_BP)
                        count++
                }
            }
        }
        return count
    }

    fun countHighBp(logs: List<FitnessModel>,observationReadingCount: Float): Int {
        var count = 0
        val countLogs = logs.count {
            !it.bloodPressureTopBp.inValidHealthLog()
        }
        if (countLogs >= observationReadingCount) {
            logs.filter {
                it.bloodPressureTopBp.isValidHealthLog()
            }.let {
                it.subList(it.size - observationReadingCount.toInt(), it.size)
            }.forEach { log ->
                log.bloodPressureTopBp?.let {
                    if (it.toFloat() > CHFConsideration.HIGH_BP)
                        count++
                }
            }
        }
        return count
    }

    fun countHighHeartRate(logs: List<FitnessModel>,observationReadingCount: Float): Int {
        var count = 0
        val countLogs = logs.count {
            !it.heartRate.inValidHealthLog()
        }
        if (countLogs >= observationReadingCount) {
            logs.filter {
                it.heartRate.isValidHealthLog()
            }.let {
                it.subList(it.size - observationReadingCount.toInt(), it.size)
            }.forEach { log ->
                log.heartRate?.let {
                    if (it.toFloat() > CHFConsideration.HIGH_HEART_RATE)
                        count++
                }
            }
        }
        return count
    }


    fun getStepsAvg(healthLogs: List<FitnessModel>): Float {
        var totalValue = 0f
        var count = 0
        healthLogs.forEach { log ->
            log.stepCount?.let {
                if (it.inValidHealthLog()) return@let
                totalValue += it.toFloat()
                count++
            }
        }
        return totalValue / count
    }

    fun countStepsBeloAvg(stepsAvg: Float, logs: List<FitnessModel>,observationReadingCount: Float): Int {
        var count = 0
        val countLogs = logs.count {
            !it.stepCount.isNullOrBlank() && it.stepCount != "0"
        }
        if (countLogs >= observationReadingCount) {
            logs.filter {
                it.stepCount.isValidHealthLog()
            }.let {
                it.subList(it.size - observationReadingCount.toInt(), it.size)
            }.forEach { log ->
                log.stepCount?.let {
                    if (it.toFloat() < stepsAvg)
                        count++
                }
            }
        }
        return count
    }

    fun hasNoneCategoryMedicine(diagnosisModelChib: DiagnosisModel?, context: Context): Boolean {
        val betaBlocker =
            context.resources.getString(R.string.beta_blocker).removeSpace()
        val arni = context.resources.getString(R.string.arni).removeSpace()
        val sglt2_inhibitor =
            context.resources.getString(R.string.sglt2_inhibitor).removeSpace()
        val ace_arb = context.resources.getString(R.string.ace_arb).removeSpace()
        val mra = context.resources.getString(R.string.mra).removeSpace()
        var hasNoneTypeMed = false
        if (diagnosisModelChib?.medications == null)
            hasNoneTypeMed = true
        diagnosisModelChib?.medications?.let { list ->
            list.find {
                it.category?.removeSpace().equals(betaBlocker.removeSpace(), true)
            }.let {
                if (it == null)
                    hasNoneTypeMed = true
            }
            list.find {
                it.category?.removeSpace().equals(arni.removeSpace(), true)
            }.let {
                if (it == null)
                    hasNoneTypeMed = true
            }
            list.find {
                it.category?.removeSpace().equals(sglt2_inhibitor.removeSpace(), true)
            }.let {
                if (it == null)
                    hasNoneTypeMed = true
            }
            list.find {
                it.category?.removeSpace().equals(ace_arb.removeSpace(), true)
            }.let {
                if (it == null)
                    hasNoneTypeMed = true
            }
            list.find {
                it.category?.removeSpace().equals(mra.removeSpace(), true)
            }.let {
                if (it == null)
                    hasNoneTypeMed = true
            }
        }

        return hasNoneTypeMed
    }
}
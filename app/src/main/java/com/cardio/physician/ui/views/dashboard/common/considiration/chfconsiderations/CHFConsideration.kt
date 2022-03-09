package com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations

import android.content.Context
import android.view.View
import com.cardio.physician.databinding.FragmentDashboardBinding
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.ui.common.utils.Constants.ANSWER_2_OR_LESS
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.UNKNOWN
import com.cardio.physician.ui.common.utils.FireStoreDocKey.Companion.YES
import com.cardio.physician.ui.common.utils.getDatesOfLastDays
import com.cardio.physician.ui.views.dashboard.common.considiration.BaseCosideration
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.observation.Observation10Days
import com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.observation.Observation30Days
import com.cardio.physician.ui.views.dashboard.common.extenstions.getDefibrillatorQuestion
import com.cardio.physician.ui.views.dashboard.common.extenstions.getDryWeight
import com.cardio.physician.ui.views.dashboard.common.extenstions.getEjectionFractionQuestion
import com.cardio.physician.ui.views.dashboard.common.extenstions.getPacemakerQuestion

class CHFConsideration : BaseCosideration() {

    companion object RESULTS {
        const val EJECTION_FRACTION_REFRENCE_35 = 35
        const val EJECTION_FRACTION_REFRENCE_45 = 45
        const val WEIGHT_INCREASE_REFRENCE_FOR_48_HOUR = 3
        const val WEIGHT_INCREASE_REFRENCE_FOR_72_HOUR = 5
        const val LOW_HEART_RATE = 50
        const val HIGH_HEART_RATE = 70
        const val HIGH_BP = 130
        const val LOW_BP = 80
        const val CONSIDERATION_1 =
            "Consider cardiac resynchronization therapy depending upon QRS duration, morphology and NYHA class"
        const val CONSIDERATION_2 = "Physician review of device for impedance/activity reporting"
        const val CONSIDERATION_3 =
            "Pulse rate is low and potential medication adjustment may be needed"
        const val CONSIDERATION_4 =
            "Consider advancing rate control agents with priority to advancing beta blocker to guideline recommended target doses, towards target heart rate <70bpm >80% of the time"
        const val CONSIDERATION_5 =
            "Based on systolic blood pressure, Consider advancing blood pressure medications towards BP <130/80 greater than 80% of the time"
        const val CONSIDERATION_6 =
            "Based on diastolic bloop pressure, Consider advancing blood pressure medications towards BP <130/80 greater than 80% of the time"
        const val CONSIDERATION_7 =
            "Increasing diuretic with your provider, based on your last weight recorded"
        const val CONSIDERATION_8 =
            "Starting diuretic with your provider, based on your last weight recorded."
        const val CONSIDERATION_9 =
            "Your step count is lower than your average for the past few days, get moving and increase your step count"
        const val CONSIDERATION_10 =
            "Guideline directed medical therapy for medications should be reviewed as per type and prioritization in the CV medications above."

        //error messages
        const val ERROR_MIN_ENTRIES_HEART_RATE =
            "Please add a minimum 10 days data entry for heart rate recommendation."
        const val ERROR_MIN_ENTRIES_BP =
            "Please add a minimum 10 days data entry for blood pressure recommendation."
        const val ERROR_MIN_ENTRIES_WEIGHT =
            "Please add a minimum 3 days data entry for weight recommendation."
        const val ERROR_MIN_ENTRIES_STEPS =
            "Please add a minimum 10 days data entry for step count recommendation."
    }

    private val preconditionsCHF = PreconditionsCHF(this)
    private val util: CHFConsiderationUtil by lazy {
        CHFConsiderationUtil()
    }
    private val observation10Days: Observation10Days by lazy {
        Observation10Days()
    }
    private val observation30Days: Observation30Days by lazy {
        Observation30Days()
    }

    override fun showData(
        binding: FragmentDashboardBinding,
        healthLogs: List<FitnessModel>,
        diagnosisModelChib: DiagnosisModel?,
    ) {
        binding.tvConsidirationLabel.visibility = View.GONE
        binding.includeDashConsidiratiodn.cvChfConsidiration.visibility = View.GONE
        binding.includeDashConsidiratiodn.llChfConsidiration.removeAllViews()
        listConsideration.clear()
        //do all considerations here
        //keep ninthh consideration on first
        ninthConsideration(diagnosisModelChib, binding.root.context)
        firstConsideration(diagnosisModelChib)
        thirdConsideration(healthLogs, diagnosisModelChib)
        fourthConsideration(healthLogs)
        fifthConsideration(healthLogs)
        sixthConsideration(healthLogs)
        seventhConsideration(healthLogs, diagnosisModelChib)
        eighthConsideration(healthLogs)
        if (listConsideration.isNotEmpty())
            viewVisibility(true, binding)
        else
            viewVisibility(false, binding)
        listConsideration.forEach {
            addConsidirationView(
                binding.includeDashConsidiratiodn.llChfConsidiration,
                it
            )
        }
    }

    private fun viewVisibility(visible: Boolean, binding: FragmentDashboardBinding) {
        binding.tvConsidirationLabel.visibility = if (visible) View.VISIBLE else View.GONE
        binding.includeDashConsidiratiodn.cvChfConsidiration.visibility =
            if (visible) View.VISIBLE else View.GONE
    }

    private fun firstConsideration(diagnosisModelChib: DiagnosisModel?) {
        diagnosisModelChib?.run {
            getDefibrillatorQuestion()?.let { it ->
                if (it.answer.equals(FireStoreDocKey.YES, true)) {
                    if (it.answerSecondary == ANSWER_2_OR_LESS || it.answerSecondary == UNKNOWN) {
                        getEjectionFractionQuestion()?.let { ef ->
                            if (!ef.answer.equals(FireStoreDocKey.UNKNOWN) && ef.answer!!.toInt() <= EJECTION_FRACTION_REFRENCE_35) {
                                listConsideration.add(CONSIDERATION_1)
                                return
                                //must return here as we only need to check one between defibrillator and  pacemaker
                            }
                        }
                    }
                }
            }
            getPacemakerQuestion()?.let { it ->
                if (it.answer.equals(FireStoreDocKey.YES, true)) {
                    if (it.answerSecondary == ANSWER_2_OR_LESS || it.answerSecondary == UNKNOWN) {
                        getEjectionFractionQuestion()?.let { ef ->
                            if (!ef.answer.equals(FireStoreDocKey.UNKNOWN) && ef.answer!!.toInt() <= EJECTION_FRACTION_REFRENCE_35) {
                                listConsideration.add(CONSIDERATION_1)
                                return
                            }
                        }
                    }
                }
            }
            //we need to check if both defibrillator or pacemaker is no or unknown
            getDefibrillatorQuestion()?.let { it ->
                if (it.answer.equals(
                        FireStoreDocKey.NO,
                        true
                    ) || it.answer.equals(FireStoreDocKey.UNKNOWN, true)
                ) {
                    getPacemakerQuestion()?.let { it ->
                        if (it.answer.equals(FireStoreDocKey.NO, true) || it.answer.equals(
                                FireStoreDocKey.UNKNOWN,
                                true
                            )
                        ) {
                            getEjectionFractionQuestion()?.let { ef ->
                                if (!ef.answer.equals(FireStoreDocKey.UNKNOWN) && ef.answer!!.toInt() <= EJECTION_FRACTION_REFRENCE_35) {
                                    listConsideration.add(CONSIDERATION_1)
                                    return
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //no need of this consideration now
/*
    private fun secondConsideration(diagnosisModelChib: DiagnosisModel?) {
        diagnosisModelChib?.run {
            getDefibrillatorQuestion()?.let { question ->
                if (question.answer.equals(
                        FireStoreDocKey.YES,
                        true
                    )
                ) {
                    listConsideration.add(CONSIDERATION_2)
                }
            }
        }
    }
*/

    private fun thirdConsideration(
        healthLogs: List<FitnessModel>,
        diagnosisModelChib: DiagnosisModel?,
    ) {
        preconditionsCHF.checkHeartPreCondition(healthLogs)
        diagnosisModelChib?.medications?.find {
            it.rateControlAgent.equals(YES, true)
        }?.let {
            if (observation30Days.hasLowHeartRate(healthLogs) || observation10Days.hasLowHeartRate(
                    healthLogs
                )
            ) {
                listConsideration.add(CONSIDERATION_3)
            }
            /*if (util.countLowHeartRate(healthLogs) > TWENTY_PERCENT_OF_READING_COUNT) {
                listConsideration.add(CONSIDERATION_3)
            }*/
        }
    }

    private fun fourthConsideration(healthLogs: List<FitnessModel>) {
        preconditionsCHF.checkHeartPreCondition(healthLogs)
        if (observation30Days.hasHighHeartRate(healthLogs) || observation10Days.hasHighHeartRate(
                healthLogs
            )
        ) {
            listConsideration.add(CONSIDERATION_4)
        }
        /*if (util.countHighHeartRate(healthLogs) > TWENTY_PERCENT_OF_READING_COUNT) {
            listConsideration.add(CONSIDERATION_4)
        }*/
    }

    private fun fifthConsideration(healthLogs: List<FitnessModel>) {
        preconditionsCHF.checkBpPreCondition(healthLogs)
        /*if (util.countHighBp(healthLogs) > TWENTY_PERCENT_OF_READING_COUNT) {
            listConsideration.add(CONSIDERATION_5)
        }*/
        if (observation30Days.hasHighBp(healthLogs) || observation10Days.hasHighBp(healthLogs)) {
            listConsideration.add(CONSIDERATION_5)
        }
    }

    private fun sixthConsideration(healthLogs: List<FitnessModel>) {
        preconditionsCHF.checkBpPreCondition(healthLogs)
        /*if (util.countLowBp(healthLogs) > TWENTY_PERCENT_OF_READING_COUNT) {
            listConsideration.add(CONSIDERATION_6)
        }*/
        if (observation30Days.hasLowBp(healthLogs) || observation10Days.hasLowBp(healthLogs)) {
            listConsideration.add(CONSIDERATION_6)
        }
    }

    private fun seventhConsideration(
        healthLogs: List<FitnessModel>,
        diagnosisModelChib: DiagnosisModel?,
    ) {
        preconditionsCHF.checkWeightPreCondition(healthLogs)
        diagnosisModelChib?.getDryWeight()?.let {
            if (!it.answer.equals(FireStoreDocKey.UNKNOWN, true)) {
                val arrayDatesLastTwoDays = mutableListOf<String>()
                val arrayDatesLastThreeDays = mutableListOf<String>()
                val arrayDatesLastTenDays = mutableListOf<String>()
                getDatesOfLastDays(2, arrayDatesLastTwoDays)
                getDatesOfLastDays(3, arrayDatesLastThreeDays)
                getDatesOfLastDays(10, arrayDatesLastTenDays)
                if (util.checkWeightIncrease(
                        it.answer!!.toFloat(),
                        healthLogs, arrayDatesLastTwoDays, WEIGHT_INCREASE_REFRENCE_FOR_48_HOUR
                    ) || util.checkWeightIncrease(
                        it.answer!!.toFloat(), healthLogs, arrayDatesLastThreeDays,
                        WEIGHT_INCREASE_REFRENCE_FOR_72_HOUR
                    )
                    || observation30Days.checkWeightIncrease(
                        it.answer!!.toFloat(),
                        healthLogs,
                        WEIGHT_INCREASE_REFRENCE_FOR_72_HOUR
                    )
                    || observation10Days.checkWeightIncrease(
                        it.answer!!.toFloat(),
                        healthLogs,
                        WEIGHT_INCREASE_REFRENCE_FOR_72_HOUR
                    )/*util.checkWeightIncreaseInLast10Entries(
                                it.answer!!.toFloat(), healthLogs,
                                WEIGHT_INCREASE_REFRENCE_FOR_72_HOUR
                        )*/
                ) {
                    diagnosisModelChib.medications?.let {
                        if (it.find { it.diuretics.equals(YES, true) } != null) {
                            listConsideration.add(CONSIDERATION_7)
                        } else {
                            listConsideration.add(CONSIDERATION_8)
                        }
                    }
                }
            }
        }
    }

    private fun eighthConsideration(healthLogs: List<FitnessModel>) {
        preconditionsCHF.checkStepsPreCondition(healthLogs)
        val stepsAvg = util.getStepsAvg(healthLogs)
        if (stepsAvg > 0) {
            /*if (util.countStepsBeloAvg(stepsAvg, healthLogs) > TWENTY_PERCENT_OF_READING_COUNT) {
                listConsideration.add(CONSIDERATION_9)
            }*/
            if (observation30Days.hasLessStepsBelowAvg(
                    stepsAvg,
                    healthLogs
                ) || observation10Days.hasLessStepsBelowAvg(stepsAvg, healthLogs)
            ) {
                listConsideration.add(CONSIDERATION_9)
            }
        }
    }

    private fun ninthConsideration(diagnosisModelChib: DiagnosisModel?, context: Context) {
        if (util.hasNoneCategoryMedicine(diagnosisModelChib, context))
            listConsideration.add(CONSIDERATION_10)
    }


}
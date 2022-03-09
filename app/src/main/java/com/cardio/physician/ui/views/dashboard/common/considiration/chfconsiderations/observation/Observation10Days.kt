package com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.observation

class Observation10Days : BaseObservation() {
    object Observation10 {
        const val OBSERVATION_READING_COUNT = 10F
        const val TWENTY_PERCENT_OF_READING_COUNT = OBSERVATION_READING_COUNT * 20 / 100
    }
    init {
        super.OBSERVATION_READING_COUNT= Observation10.OBSERVATION_READING_COUNT
        super.TWENTY_PERCENT_OF_READING_COUNT= Observation10.TWENTY_PERCENT_OF_READING_COUNT
    }
}
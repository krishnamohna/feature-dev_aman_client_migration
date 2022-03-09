package com.cardio.physician.ui.views.dashboard.common.considiration.chfconsiderations.observation

class Observation30Days : BaseObservation(){
    object Observation30 {
        const val OBSERVATION_READING_COUNT = 30F
        const val TWENTY_PERCENT_OF_READING_COUNT = OBSERVATION_READING_COUNT * 20 / 100
    }
    init {
        super.OBSERVATION_READING_COUNT= Observation30.OBSERVATION_READING_COUNT
        super.TWENTY_PERCENT_OF_READING_COUNT= Observation30.TWENTY_PERCENT_OF_READING_COUNT
    }
}
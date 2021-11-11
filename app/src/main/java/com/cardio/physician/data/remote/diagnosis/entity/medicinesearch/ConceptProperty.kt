package com.cardio.physician.data.remote.diagnosis.entity.medicinesearch

data class ConceptProperty(
    val language: String,
    val name: String,
    val rxcui: String,
    val suppress: String,
    val synonym: String,
    val tty: String,
    val umlscui: String
)
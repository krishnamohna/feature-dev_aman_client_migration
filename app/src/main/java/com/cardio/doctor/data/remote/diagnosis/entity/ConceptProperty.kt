package com.cardio.doctor.data.remote.diagnosis.entity

data class ConceptProperty(
    val language: String,
    val name: String,
    val rxcui: String,
    val suppress: String,
    val synonym: String,
    val tty: String,
    val umlscui: String
)
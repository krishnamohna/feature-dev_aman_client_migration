package com.cardio.physician.data.remote.diagnosis.entity.medicinesearch

data class ConceptGroup(
    val conceptProperties: List<ConceptProperty>,
    val tty: String
)
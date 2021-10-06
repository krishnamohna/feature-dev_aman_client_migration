package com.cardio.doctor.domain.diagnosis

data class DiagnosisModel(
    val ailment: String,
    val firstName: String,
    val lastName:String,
    val age:String,
    val weight:Int,
    val heartRate:Int,
    val topBp:Int,
    val bottomBp:Int,
    val mediciene:String
)

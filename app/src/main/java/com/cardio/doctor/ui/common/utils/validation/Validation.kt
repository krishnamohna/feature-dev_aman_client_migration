package com.cardio.doctor.ui.common.utils.validation

import com.cardio.doctor.domain.common.model.validation.ValidationModelV2

interface Validation {
    fun validateFirstName(firstName: String)
    fun build(): List<ValidationModelV2>
    fun validateLastname(lastName: String)
    fun validateAge(age: String)
    fun validateWeight(weight: String)
    fun validateHeartRate(heartRate: String)
    fun validateTopBp(heartRate: String)
    fun validateBottomBp(bottomBp: String)
    fun init()
}
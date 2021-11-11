package com.cardio.physician.ui.common.utils.validation

import com.cardio.physician.domain.common.model.validation.ValidationModelV2

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
    fun validateAilment(ailmentPosition: Int)
}
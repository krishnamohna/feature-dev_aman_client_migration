package com.cardio.physician.ui.common.utils.validation

import com.cardio.physician.domain.common.model.validation.ValidationModelV2
import com.cardio.physician.network.Status

class Validater constructor(private val validation: Validation) {

    fun validateDiagnosisFirstStep(
        ailmentPosition: Int,
        firstName: String,
        lastName: String,
        age: String
    ): List<ValidationModelV2> {
        validation.init()
        validation.validateAilment(ailmentPosition)
        validation.validateFirstName(firstName)
        validation.validateLastname(lastName)
        validation.validateAge(age)
        return validation.build()
    }

    fun validateAlphaDiagnosisFirstStep(
        ailmentPosition: Int,
        firstName: String,
        lastName: String,
        age: String
    ): List<ValidationModelV2> {
        validation.init()
        validation.validateAilment(ailmentPosition)
        validation.validateFirstName(firstName)
        validation.validateLastname(lastName)
        validation.validateAge(age)
        return validation.build()
    }

    fun areAllFieldValidated(validations: List<ValidationModelV2>): Boolean {
        var validation = validations.find {
            it.status == Status.ERROR
        }
        return validation == null
    }

}
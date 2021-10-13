package com.cardio.doctor.ui.common.utils.validation

import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.network.Status

class Validater constructor(val validation: Validation){

    fun validateDiagnosisFirstStep(
        ailmentPosition:Int,
        firstName: String,
        lastName: String,
        age: String,
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String
    ) : List<ValidationModelV2>{
        validation.init()
        validation.validateAilment(ailmentPosition)
        validation.validateFirstName(firstName)
        validation.validateLastname(lastName)
        validation.validateAge(age)
        validation.validateWeight(weight)
        validation.validateHeartRate(heartRate)
        validation.validateTopBp(topBp)
        validation.validateBottomBp(bottomBp)
        return validation.build()
    }


    fun areAllFieldValidated(validations:List<ValidationModelV2>): Boolean {
        var validation=validations.find {
            it.status==Status.ERROR
        }
        return validation==null
    }

}
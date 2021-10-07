package com.cardio.doctor.ui.views.diagnosis.step1

import com.cardio.doctor.domain.common.model.validation.ValidationModelV2
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.validation.Validater
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiagnosisViewStep1ViewModel @Inject constructor(val validater: Validater): BaseViewModel(){

    fun checkValidation(
        firstName: String,
        lastName: String,
        age: String,
        weight: String,
        heartRate: String,
        topBp: String,
        bottomBp: String,
        succcess: () -> Unit,
        failed: (validations:List<ValidationModelV2>) -> Unit
    ) {
        var validations = validater.validateDiagnosisFirstStep(
            firstName,
            lastName,
            age,
            weight,
            heartRate,
            topBp,
            bottomBp
        )
        if(validater.areAllFieldValidated(validations))
            succcess.invoke()
        else
            failed.invoke(validations)
    }

}
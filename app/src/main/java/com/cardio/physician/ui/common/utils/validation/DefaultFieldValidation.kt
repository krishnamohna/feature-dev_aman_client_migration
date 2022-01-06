package com.cardio.physician.ui.common.utils.validation

import android.content.Context
import com.cardio.physician.R
import com.cardio.physician.domain.common.model.validation.ValidationModelV2
import com.cardio.physician.network.Status

class DefaultFieldValidation constructor(val context: Context):Validation {

    var validations = mutableListOf<ValidationModelV2>()

    private fun queueValidationRequest(
        error: Status,
        msg: String,
        fieldType:FieldType
    ) {
        validations.add(ValidationModelV2(error,msg,fieldType))
    }

    override fun init() {
        validations.clear()
    }

    override fun build(): List<ValidationModelV2> {
        return validations
    }

    override fun validateAilment(ailmentPosition: Int) {
        if(ailmentPosition==0){
            queueValidationRequest(
                Status.ERROR,
                context.getString(R.string.select_ailment),
                FieldType.AILMENT
            )
        }else{
            queueValidationRequest(
                Status.SUCCESS, "",
                FieldType.AILMENT
            )
            true
        }
    }

    override fun validateFirstName(firstName: String) {
        validations.add(CommonValidations.minThreeCharValidation(firstName,FieldType.FIRST_NAME,R.string.enter_valid_first_name,context))
    }

    override fun validateLastname(lastName: String) {
        validations.add(CommonValidations.minThreeCharValidation(lastName,FieldType.LAST_NAME,R.string.enter_valid_last_name,context))
    }

    override fun validateAge(age: String){
        validations.add(CommonValidations.emptyValidation(age,FieldType.AGE,R.string.enter_age,context))
    }

    override fun validateWeight(weight: String) {
        validations.add(CommonValidations.emptyValidation(weight,FieldType.WEIGHT,R.string.enter_weight,context))
    }

    override fun validateHeartRate(heartRate: String) {
        validations.add(CommonValidations.emptyValidation(heartRate,FieldType.HEART_RATE,R.string.enter_heart_rate,context))
    }

    override fun validateTopBp(value: String) {
        validations.add(CommonValidations.emptyValidation(value,FieldType.TOP_BP,R.string.enter_top_bp,context))
    }

    override fun validateBottomBp(bottomBp: String) {
        validations.add(CommonValidations.emptyValidation(bottomBp,FieldType.BOTTOM_BP,R.string.enter_bottom_bp,context))
    }

}
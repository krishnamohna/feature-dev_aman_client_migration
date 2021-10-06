package com.cardio.doctor.domain.common.model.validation

import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.common.utils.validation.FieldType

data class ValidationModelV2(
    var status:Status,
    var message:String,
    var field_type:FieldType
)
package com.cardio.physician.domain.common.model.validation

import com.cardio.physician.network.Status
import com.cardio.physician.ui.common.utils.validation.FieldType

data class ValidationModelV2(
    var status:Status,
    var message:String,
    var field_type:FieldType
)
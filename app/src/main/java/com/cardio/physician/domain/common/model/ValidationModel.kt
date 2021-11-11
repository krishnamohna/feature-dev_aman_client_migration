package com.cardio.physician.domain.common.model

import com.cardio.physician.network.Status

data class ValidationModel(
    var edtResourceId: Int,
    var tvResourceId: Int,
    var status:Status,
    var message:String,
)
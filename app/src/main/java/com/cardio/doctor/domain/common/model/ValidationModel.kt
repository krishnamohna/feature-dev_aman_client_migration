package com.cardio.doctor.domain.common.model

import com.cardio.doctor.network.Status

data class ValidationModel(
    var edtResourceId: Int,
    var tvResourceId: Int,
    var status:Status,
    var message:String,
)
package com.cardio.doctor.ui.common.utils.extentions

import com.cardio.doctor.ui.common.utils.isValidEmail

fun String.isEmailVerified(): Boolean {
    return isValidEmail(this)
}
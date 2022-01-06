package com.cardio.physician.domain.user

import com.cardio.physician.ui.common.utils.Constants.USER_TYPE_NOT_DEFINED
import com.cardio.physician.ui.common.utils.Constants.USER_TYPE_PATIENT
import com.cardio.physician.ui.common.utils.Constants.USER_TYPE_PHYSICIAN

enum class UserType(val value: String) {
    NOT_DEFINED(USER_TYPE_NOT_DEFINED),
    PATIENT(USER_TYPE_PATIENT),
    PHYSICIAN(USER_TYPE_PHYSICIAN);

    companion object {
        fun fromName(value: String?): UserType {
            val type: UserType? = values().firstOrNull {
                if (value.isNullOrBlank())
                    NOT_DEFINED
                it.value.equals(value,true)
            }
            return type ?: NOT_DEFINED
        }
    }
}


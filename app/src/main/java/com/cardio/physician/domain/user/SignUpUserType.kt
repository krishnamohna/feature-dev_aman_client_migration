package com.cardio.physician.domain.user

enum class SignUpUserType(val value: String) {
    NORMAL("NORMAL"),
    GOOGLE("GOOGLE"),
    APPLE("GOOGLE");

    companion object {
        fun fromName(value: String?) = values().first {
            if (value.isNullOrBlank())
                return NORMAL
            it.value.equals(value,true)
        }
    }
}
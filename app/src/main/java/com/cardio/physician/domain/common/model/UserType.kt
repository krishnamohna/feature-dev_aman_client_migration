package com.cardio.physician.domain.common.model

enum class UserType(value: String) {
    NORMAL("NORMAL"),
    GOOGLE("GOOGLE"),
    APPLE("GOOGLE");

    companion object {
        fun fromName(value: String?) = UserType.values().first {
            if (value == null)
                return NORMAL
            it.name == value ?: ""
        }
    }
}
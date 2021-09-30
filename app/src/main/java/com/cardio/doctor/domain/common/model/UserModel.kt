package com.cardio.doctor.domain.common.model

data class UserModel(var uid: String,
                     val firstName: String,
                     val lastName: String?,
                     val countryCode: String?,
                     val phoneNumber: String?,
                     val email: String,
                     val imagePath: String?,
                     val userType: UserType
)

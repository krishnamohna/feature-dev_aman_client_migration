package com.cardio.doctor.domain.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(var uid: String?,
                     val firstName: String?,
                     val lastName: String?,
                     val countryCode: String?,
                     val phoneNumber: String?,
                     val email: String?,
                     val imagePath: String?,
                     val userType: UserType
): Parcelable

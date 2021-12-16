package com.cardio.physician.domain.common.model

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
                     val userType: UserType,
                     val gender:String?=null,
                     val height:String?=null,
                     val weight:String?=null,
                     val dob:String?=null

): Parcelable

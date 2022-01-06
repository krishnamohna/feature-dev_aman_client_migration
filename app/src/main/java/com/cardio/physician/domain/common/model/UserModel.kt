package com.cardio.physician.domain.common.model

import android.os.Parcelable
import com.cardio.physician.domain.user.SignUpUserType
import com.cardio.physician.domain.user.UserType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(var uid: String?,
                     val firstName: String?,
                     val lastName: String?,
                     val countryCode: String?,
                     val phoneNumber: String?,
                     val email: String?,
                     val imagePath: String?,
                     val signUpType: SignUpUserType?,
                     val gender:String?=null,
                     val height:String?=null,
                     val weight:String?=null,
                     val dob:String?=null,
                     val userType: UserType=UserType.PHYSICIAN

): Parcelable{

    object Builder{
        var firstName: String?=null
        var lastName: String?=null
        var dob: String?=null
        var weight: String?=null
        fun build(): UserModel {
            return UserModel(null, firstName, lastName,null,null,null,null,null,null,null, weight,
                dob)
        }
    }
}


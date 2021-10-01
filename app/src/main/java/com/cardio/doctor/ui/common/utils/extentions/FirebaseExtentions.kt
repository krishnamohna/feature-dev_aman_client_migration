package com.cardio.doctor.ui.common.utils.extentions

import com.cardio.doctor.domain.common.model.UserModel
import com.cardio.doctor.domain.common.model.UserType
import com.cardio.doctor.ui.common.utils.FireStoreDocKey
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.firestore.DocumentSnapshot


fun GoogleSignInAccount.toUserModel(): UserModel {
    return UserModel("","",null,null,null,email,null,UserType.GOOGLE)
}

fun DocumentSnapshot.toUserModel():UserModel{
    var uid=data?.get(FireStoreDocKey.USER_ID) as? String?
    val firstName = data?.get(FireStoreDocKey.FIRST_NAME) as? String?
    val lastName = data?.get(FireStoreDocKey.LAST_NAME) as? String?
    val email = data?.get(FireStoreDocKey.EMAIL) as? String?
    val countryCode = data?.get(FireStoreDocKey.COUNTRY_CODE) as? String?
    val phoneNumber = data?.get(FireStoreDocKey.PHONE_NUMBER) as? String?
    val gender = data?.get(FireStoreDocKey.GENDER) as? String?
    val dob = data?.get(FireStoreDocKey.DOB) as? String?
    val height = data?.get(FireStoreDocKey.HEIGHT) as? String?
    val heartRate = data?.get(FireStoreDocKey.WEIGHT) as? String?
    val imageUrl = data?.get(FireStoreDocKey.IMAGE_URL) as? String?
    val userType= data?.get(FireStoreDocKey.SIGN_UP_TYPE) as? String?
    return UserModel(uid,firstName,lastName,countryCode,phoneNumber,email,imageUrl, UserType.fromName(userType))
}

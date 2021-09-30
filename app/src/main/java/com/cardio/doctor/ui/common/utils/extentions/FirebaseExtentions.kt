package com.cardio.doctor.ui.common.utils.extentions

import com.cardio.doctor.domain.common.model.UserModel
import com.cardio.doctor.domain.common.model.UserType
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


fun GoogleSignInAccount.toUserModel(): UserModel {
    return UserModel("","",null,null,null,email,null,UserType.GOOGLE)
}


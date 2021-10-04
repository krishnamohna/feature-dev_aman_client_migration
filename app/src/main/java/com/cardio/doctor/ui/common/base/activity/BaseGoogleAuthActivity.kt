package com.cardio.doctor.ui.common.base.activity

import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient

open class BaseGoogleAuthActivity:BaseActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        googleSignIn()
    }

}
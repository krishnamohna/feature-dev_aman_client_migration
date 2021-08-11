package com.cardio.doctor.ui.activity.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.cardio.doctor.R
import com.cardio.doctor.base.activity.BaseActivity
import com.cardio.doctor.databinding.ActivityAuthenticateUserBinding
import com.cardio.doctor.databinding.ActivityDashboardBinding
import com.cardio.doctor.ui.activity.AuthenticateUserActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardActivity : BaseActivity() {
    private val binding by viewBinding(ActivityDashboardBinding::inflate)
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Initialize Firebase Auth
        googleSignIn()
        binding.btnSignOut.setOnClickListener {
            signOut()
        }
    }

    private fun googleSignIn() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Initialize Firebase Auth
        auth = Firebase.auth
    }


    private fun signOut() {
        // Firebase sign out
        auth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            //updateUI(null)
        }
        openLoginActivity()
    }
    private fun openLoginActivity(){
        startActivity(Intent(this,AuthenticateUserActivity::class.java))
        finish()
    }
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }*/
}
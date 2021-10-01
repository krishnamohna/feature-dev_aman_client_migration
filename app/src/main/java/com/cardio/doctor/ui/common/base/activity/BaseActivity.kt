package com.cardio.doctor.ui.common.base.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.cardio.doctor.R
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.listeners.DialogHelper
import com.cardio.doctor.ui.common.listeners.DialogProvider
import com.cardio.doctor.ui.common.utils.DialogHelperImpl
import com.cardio.doctor.ui.views.activity.AuthenticateUserActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

abstract class BaseActivity : AppCompatActivity() , DialogProvider {

    protected val baseViewModel : BaseViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var dialogHelper : DialogHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogHelper = provideDialogHelper()
        googleSignIn()
    }
    private val dialogHelperImps by lazy {
        DialogHelperImpl(this)
    }

    override fun provideDialogHelper(): DialogHelper {
        return dialogHelperImps
    }

    inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }

    private fun googleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    fun signOut() {
        baseViewModel.clearPreference()
        baseViewModel.auth.signOut()
        googleSignInClient.signOut().addOnCompleteListener(this) {
        }
        openLoginActivity()
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, AuthenticateUserActivity::class.java))
        finish()
    }

    protected fun showProgress() {
        dialogHelper.showProgress()
    }

    protected fun hideProgress() {
        dialogHelper.hideProgress()
    }

    protected fun setUpToolbar(view: View, title: String, backBtnVisibility: Boolean = false
                               , editProfile : Boolean = false) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val backBtn = view.findViewById<ImageView>(R.id.backBtn)
        val toolbarTitle = view.findViewById<TextView>(R.id.toolbarTitle)
        val imgEdtProfile = view.findViewById<ImageView>(R.id.imgEditProfile)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        backBtn.visibility = if(title.isNotEmpty()) View.VISIBLE else View.GONE
        backBtn.visibility = if(backBtnVisibility) View.VISIBLE else View.INVISIBLE
        imgEdtProfile.visibility = if(editProfile) View.VISIBLE else View.GONE
        if(!TextUtils.isEmpty(title)){
            toolbarTitle.text = title
        }
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }

}
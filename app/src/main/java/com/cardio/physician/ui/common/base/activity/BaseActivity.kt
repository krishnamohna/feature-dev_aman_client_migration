package com.cardio.physician.ui.common.base.activity

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
import com.cardio.physician.R
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.data.remote.fcm.FcmManager
import com.cardio.physician.di.REPO_FITBIT
import com.cardio.physician.domain.fitness.FitnessRepositary
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.physician.ui.common.listeners.DialogHelper
import com.cardio.physician.ui.common.listeners.DialogProvider
import com.cardio.physician.ui.common.utils.DialogHelperImpl
import com.cardio.physician.ui.common.utils.NotificationUtil
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.auth.AuthenticateUserActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(), DialogProvider {

    protected val baseViewModel: BaseAuthViewModel by viewModels()
    private lateinit var dialogHelper: DialogHelper
    var isBackButtonDisabled = false

    @Inject
    lateinit var fcmManager: FcmManager

    @Inject
    lateinit var userManager: UserManager

    @Inject
    @Named(REPO_FITBIT)
    lateinit var fitbit: FitnessRepositary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogHelper = provideDialogHelper()
    }

    override fun onPause() {
        super.onPause()
        isBackButtonDisabled = false
    }

    override fun onBackPressed() {
        if (!isBackButtonDisabled)
            super.onBackPressed()
    }

    private val dialogHelperImps by lazy {
        DialogHelperImpl(this)
    }

    override fun provideDialogHelper(): DialogHelper {
        return dialogHelperImps
    }

    inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T,
    ) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }

    fun signOut() {
        //unsubscribe topic first as it need preference then clear preference
        //  fcmManager.unsubscribeFcmTopic()
        Firebase.auth.signOut()
        logoutGoogleIfLoggedIn()
        fitbit.logout()
        NotificationUtil(this).clearAllNotifications()
        //clear preferences in the end
        //  userManager.clearAllPreference()
        openLoginActivity()
    }

    fun logoutGoogleIfLoggedIn() {
        try {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.signOut()
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, AuthenticateUserActivity::class.java))
        finish()
    }

    protected fun showProgress() {
        dialogHelper.showProgress()
    }

    protected fun showProgress(showProgress: Boolean) {
        if (showProgress)
            dialogHelper.showProgress()
        else
            dialogHelper.hideProgress()
    }

    fun hideProgress() {
        dialogHelper.hideProgress()
    }

    fun onError(msg: String?, exception: Exception? = null) {
        showToast(this, msg ?: "Something went wrong!!!")
    }

    protected fun setUpToolbar(
        view: View, title: String, backBtnVisibility: Boolean = false, editProfile: Boolean = false,
    ) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val backBtn = view.findViewById<ImageView>(R.id.backBtn)
        val toolbarTitle = view.findViewById<TextView>(R.id.toolbarTitle)
        val imgEdtProfile = view.findViewById<ImageView>(R.id.imgEditProfile)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        backBtn.visibility = if (title.isNotEmpty()) View.VISIBLE else View.GONE
        backBtn.visibility = if (backBtnVisibility) View.VISIBLE else View.INVISIBLE
        imgEdtProfile.visibility = if (editProfile) View.VISIBLE else View.GONE
        if (!TextUtils.isEmpty(title)) {
            toolbarTitle.text = title
        }
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }

}
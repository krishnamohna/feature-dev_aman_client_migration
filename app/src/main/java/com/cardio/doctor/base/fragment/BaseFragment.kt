package com.cardio.doctor.base.fragment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.base.activity.BaseActivity
import com.cardio.doctor.listeners.DialogHelper
import com.cardio.doctor.listeners.DialogProvider
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.activity.AuthenticateUserActivity
import com.cardio.doctor.utils.showAlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId), DialogProvider {
    protected val baseViewModel : BaseViewModel by activityViewModels()
    private lateinit var dialogHelper : DialogHelper

    override fun onAttach(context: Context) {
        dialogHelper = provideDialogHelper()
        super.onAttach(context)
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

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        backBtn.visibility = if(title.isNotEmpty()) View.VISIBLE else View.GONE
        backBtn.visibility = if(backBtnVisibility) View.VISIBLE else View.INVISIBLE
        imgEdtProfile.visibility = if(editProfile) View.VISIBLE else View.GONE


        if(!TextUtils.isEmpty(title)){
            toolbarTitle.text = title
        }

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun showLogout(title :String,description : String,cancelVisibility :Boolean=true)
    {
        showAlertDialog(requireActivity() as AppCompatActivity,
            title, description,
            getString(R.string.ok),
            getString(R.string.cancel),btnTwoVisibility =cancelVisibility){ btnText: String, dialog: DialogInterface ->
            if(btnText.equals(getString(R.string.ok),true)){
                (requireActivity() as BaseActivity).signOut()
            }else dialog.dismiss()
        }
    }


}
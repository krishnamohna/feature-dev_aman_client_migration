package com.cardio.physician.ui.common.base.fragment

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cardio.physician.R
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.physician.ui.common.listeners.DialogHelper
import com.cardio.physician.ui.common.listeners.DialogProvider
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar
import com.cardio.physician.ui.common.utils.showAlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


abstract class BaseFragmentAuth(@LayoutRes layoutResId: Int) : Fragment(layoutResId), DialogProvider {
    protected val baseViewModel : BaseAuthViewModel by activityViewModels()
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

    override fun onResume() {
        super.onResume()
        hideProgress()
    }

    protected fun setUpToolbar(
        view: View, title: String, backBtnVisibility: Boolean = false,
        editProfile: Boolean = false,
    ) {
        activity.let {
            view.setUpToolbar(title, backBtnVisibility, editProfile, activity as AppCompatActivity)
        }
        view.findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun showLogout(title: String, description: String, onLogout: () -> Unit)
    {
        showAlertDialog(requireActivity() as AppCompatActivity,
            title, description,
            getString(R.string.ok),
            getString(R.string.cancel), true){ btnText: String, dialog: DialogInterface ->
            if(btnText.equals(getString(R.string.ok), true)){
                onLogout.invoke()
                baseViewModel.logoutFitnessTracker(activity)
                (requireActivity() as BaseActivity).signOut()
            }else dialog.dismiss()
        }
    }

    override fun provideDialogHelper(): DialogHelper {
        val requireActivity = requireActivity()
        if(requireActivity is DialogProvider)
            return requireActivity.provideDialogHelper()
        throw UnsupportedOperationException()
    }


}
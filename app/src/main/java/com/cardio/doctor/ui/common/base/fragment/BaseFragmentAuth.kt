package com.cardio.doctor.ui.common.base.fragment

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.ui.common.base.activity.BaseActivity
import com.cardio.doctor.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.doctor.ui.common.listeners.DialogHelper
import com.cardio.doctor.ui.common.listeners.DialogProvider
import com.cardio.doctor.ui.common.utils.extentions.setUpToolbar
import com.cardio.doctor.ui.common.utils.showAlertDialog

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

    protected fun setUpToolbar(view: View, title: String, backBtnVisibility: Boolean = false
                               , editProfile : Boolean = false) {
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
            getString(R.string.cancel),true){ btnText: String, dialog: DialogInterface ->
            if(btnText.equals(getString(R.string.ok),true)){
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
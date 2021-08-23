package com.cardio.doctor.base.fragment

import android.content.Context
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
import com.cardio.doctor.listeners.DialogHelper
import com.cardio.doctor.listeners.DialogProvider
import com.cardio.doctor.base.viewmodel.BaseViewModel

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


    protected fun setUpToolbar(view: View, title: String, backBtnVisibility: Boolean = false) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val backBtn = view.findViewById<ImageView>(R.id.backBtn)
        val toolbarTitle = view.findViewById<TextView>(R.id.toolbarTitle)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        backBtn.visibility = if(backBtnVisibility) View.VISIBLE else View.INVISIBLE
        if(!TextUtils.isEmpty(title)){
            toolbarTitle.text = title
        }

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
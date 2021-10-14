package com.cardio.doctor.ui.common.base.fragment

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.cardio.doctor.R
import com.cardio.doctor.ui.common.base.activity.BaseActivity
import com.cardio.doctor.ui.common.listeners.DialogHelper
import com.cardio.doctor.ui.common.listeners.DialogProvider
import com.cardio.doctor.ui.common.utils.extentions.setUpToolbar
import com.cardio.doctor.ui.common.utils.showToast

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    lateinit var binding: Binding
    private lateinit var dialogHelper: DialogHelper
    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }
    val parentActivity: BaseActivity?
        get() = activity as? BaseActivity

    override fun onAttach(context: Context) {
        dialogHelper = provideDialogHelper()
        super.onAttach(context)
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

    protected fun hideProgress() {
        dialogHelper.hideProgress()
    }

    fun provideDialogHelper(): DialogHelper {
        val requireActivity = requireActivity()
        if (requireActivity is DialogProvider)
            return requireActivity.provideDialogHelper()
        throw UnsupportedOperationException()
    }

    protected fun onError(msg:String?) {
        parentActivity?.let {
            showToast(it,msg?:"Something went wrong!!!")
        }
    }

    protected fun setUpToolbar(
        view: View, title: String, backBtnVisibility: Boolean = false, editProfile: Boolean = false
    ) {
        activity.let {
            view.setUpToolbar(title, backBtnVisibility, editProfile, activity as AppCompatActivity)
        }
        view.findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun launchWithMinDelay(function: () -> Unit) {
        val Delay_min = 300L
        handler.postDelayed({
            function.invoke()
        }, Delay_min)
    }


}
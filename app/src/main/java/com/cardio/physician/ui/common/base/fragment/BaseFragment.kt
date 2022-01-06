package com.cardio.physician.ui.common.base.fragment

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.listeners.DialogHelper
import com.cardio.physician.ui.common.listeners.DialogProvider
import com.cardio.physician.ui.common.utils.showToast

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

    open fun showProgress(showProgress: Boolean) {
        if (showProgress)
            dialogHelper.showProgress()
        else
            dialogHelper.hideProgress()
    }

    protected fun hideProgress() {
        dialogHelper.hideProgress()
    }

    private fun provideDialogHelper(): DialogHelper {
        val requireActivity = requireActivity()
        if (requireActivity is DialogProvider)
            return requireActivity.provideDialogHelper()
        throw UnsupportedOperationException()
    }

    protected fun onError(msg: String?,exception: Exception?) {
        parentActivity?.let {
            parentActivity?.onError(msg,exception)
        }
    }

    open fun onBackButtonClick() {
        findNavController()?.popBackStack()
    }

    fun launchWithMinDelay(function: () -> Unit) {
        val Delay_min = 300L
        handler.postDelayed({
            function.invoke()
        }, Delay_min)
    }


}
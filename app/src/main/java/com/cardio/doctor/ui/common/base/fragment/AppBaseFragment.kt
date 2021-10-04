package com.cardio.doctor.ui.common.base.fragment

import android.view.View
import com.cardio.doctor.R
import com.cardio.doctor.ui.common.listeners.DialogHelper
import com.cardio.doctor.ui.common.listeners.DialogProvider
import com.cardio.doctor.ui.common.utils.Timer
import com.cardio.doctor.ui.common.utils.customSnackBarFail

abstract class AppBaseFragment(layoutResId: Int) : BaseFragmentAuth(layoutResId){

    private var lastClickTime: Long = 0

    override fun provideDialogHelper(): DialogHelper {
        val requireActivity = requireActivity()
        if(requireActivity is DialogProvider)
            return requireActivity.provideDialogHelper()
        throw UnsupportedOperationException()
    }


    protected fun showAlertToExitFromApp(view: View) {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < Timer.DOUBLE_CLICK_TIME_DELTA) {
            requireActivity().finish()
            lastClickTime = 0
        }else {
            customSnackBarFail(requireContext(), view,
                getString(R.string.click_one_more_time_to_exit_the_app))
        }

        lastClickTime = clickTime
    }

    override fun onResume() {
        super.onResume()
        hideProgress()
    }
}
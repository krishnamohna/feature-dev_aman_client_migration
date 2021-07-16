package com.cardio.doctor.utils

import android.app.Dialog
import android.util.Log
import android.view.WindowManager
import com.cardio.doctor.R
import com.cardio.doctor.listeners.DialogHelper
import com.cardio.doctor.base.activity.BaseActivity
import com.cardio.doctor.utils.Log.Companion.TAG

class DialogHelperImpl(context: BaseActivity) : DialogHelper {
    private val dialog: Dialog = Dialog(context, R.style.CustomDialogTheme)

    override fun showProgress() {
        try {
            val customDialog = dialog
            Log.e(TAG,customDialog.toString())
            customDialog.setCancelable(false)
            customDialog.setContentView(R.layout.dialog_progress_bar)
            val lp = WindowManager.LayoutParams()
            val window = customDialog.window
            lp.copyFrom(window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT
            window.attributes = lp
            customDialog.show()
        } catch (e: Exception) {
            Log.e(TAG,e.printStackTrace().toString())
        }
    }

    override fun hideProgress() {
        val customDialog = dialog
        try {
            if (customDialog.isShowing) {
                customDialog.dismiss()
            }
        } catch (e: Exception) {
        }
    }
}
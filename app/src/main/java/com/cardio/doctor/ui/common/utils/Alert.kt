package com.cardio.doctor.ui.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.cardio.doctor.R
import com.google.android.material.snackbar.Snackbar


fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

@SuppressLint("UseCompatLoadingForDrawables")
fun customSnackBarFail(
    context: Context?,
    view: View,
    msg: String,
    duration: Int = 3000,
) {
    if (context != null && msg.isNotBlank()) {
        val snack = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE)
        val snackView = snack.view
        val textView =
            snackView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        //textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_alert_yellow, 0, 0, 0)
        textView.compoundDrawablePadding = context.resources.getDimensionPixelOffset(R.dimen._10sdp)
        textView.setTextColor(ContextCompat.getColor(context, R.color.white))
        textView.gravity = Gravity.CENTER_VERTICAL
        textView.maxLines = 5
        snack.view.background = context.getDrawable(R.drawable.bg_snackbar)
        ViewCompat.setElevation(snackView, 6f)
        snack.duration = duration
        snack.show()
    }
}

fun showAlertDialog(
    context: AppCompatActivity, title: String, description: String,
    btnOneTitle: String, btnTwoTitle: String,
    btnTwoVisibility: Boolean = false, mCallBack: (String, DialogInterface) -> Unit,
) {
    val builder = AlertDialog.Builder(context, R.style.CustomAlertDialogCommon)
    builder.setTitle(title)
    builder.setCancelable(false)
    builder.setMessage(description)
    builder.setPositiveButton(btnOneTitle) { dialogInterface, _ ->
        mCallBack.invoke(btnOneTitle, dialogInterface)
    }
    if (btnTwoVisibility) {
        builder.setNegativeButton(btnTwoTitle) { dialogInterface, _ ->
            mCallBack.invoke(btnTwoTitle, dialogInterface)
        }
    }
    val dialog = builder.create()
    dialog.show()
}

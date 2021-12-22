package com.cardio.physician.ui.common.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.cardio.physician.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.*


var mBottomSheetDialog: BottomSheetDialog? = null

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun showToast(context: Context, msg: Int) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
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

fun showConfirmAlertDialog(
    context: AppCompatActivity, title: String, description: String,
    mCallBack: (String, DialogInterface) -> Unit,
) {
    showAlertDialog(context,title,description,context.getString(R.string.yes),context.getString(R.string.no),true,mCallBack)
}

fun showAlertDialog(
    context: AppCompatActivity, title: String, description: String,
    btnOneTitle: String, btnTwoTitle: String,
    btnTwoVisibility: Boolean = false, mCallBack: (String, DialogInterface) -> Unit,
) {
    val builder = AlertDialog.Builder(context)
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

 fun showFilePickOptions(activity: AppCompatActivity,onTakePicture:()->Unit,onGalleryClick:()->Unit) {
    if (mBottomSheetDialog != null && mBottomSheetDialog!!.isShowing()) {
        mBottomSheetDialog!!.dismiss()
    }
    mBottomSheetDialog =
        BottomSheetDialog(activity, R.style.CustomBottomSheetDialogTheme)
    val sheetView: View =
        activity.layoutInflater.inflate(R.layout.bottom_sheet_image_picker, null)
    mBottomSheetDialog?.setContentView(sheetView)
    mBottomSheetDialog?.show()
    sheetView.findViewById<View>(R.id.tvTakePic).setOnClickListener {
        onTakePicture.invoke()
        dismissBottomSheet()
    }
    sheetView.findViewById<View>(R.id.tvPickImage).setOnClickListener {
        onGalleryClick.invoke()
        dismissBottomSheet()
    }
}

fun showPhysicianPickOption(activity: AppCompatActivity, onPatientClicked:()->Unit, onIllnessClicked:()->Unit){
    if (mBottomSheetDialog != null && mBottomSheetDialog!!.isShowing()) {
        mBottomSheetDialog!!.dismiss()
    }
    mBottomSheetDialog =
        BottomSheetDialog(activity, R.style.CustomBottomSheetDialogTheme)
    val sheetView: View =
        activity.layoutInflater.inflate(R.layout.bottom_sheet_physician_operation, null)
    mBottomSheetDialog?.setContentView(sheetView)
    mBottomSheetDialog?.show()
    sheetView.findViewById<View>(R.id.tvPatientLabel).setOnClickListener {
        onPatientClicked.invoke()
        dismissBottomSheet()
    }
    sheetView.findViewById<View>(R.id.tvIllnessLabel).setOnClickListener {
        onIllnessClicked.invoke()
        dismissBottomSheet()
    }
    sheetView.findViewById<View>(R.id.tvCancelLabel).setOnClickListener {
        dismissBottomSheet()
    }
}

fun openCalendarDialog(context: Context, calendar: Calendar, listener : DatePickerDialog.OnDateSetListener){
    val datePicker = DatePickerDialog(context, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    datePicker.show()
}

fun showDashboardFilter(
    activity: AppCompatActivity,
    onStartDateClicked: (view: AppCompatTextView) -> Unit,
    onEndDateClicked: (view: AppCompatTextView) -> Unit,
    onApplyClicked: () -> Unit,
    startDate: Long?,
    endDate: Long?
){
    if (mBottomSheetDialog != null && mBottomSheetDialog!!.isShowing()) {
        mBottomSheetDialog!!.dismiss()
    }
    mBottomSheetDialog =
        BottomSheetDialog(activity, R.style.CustomBottomSheetDialogTheme)
    val sheetView: View =
        activity.layoutInflater.inflate(R.layout.bottom_sheet_dashboard_filter, null)
    mBottomSheetDialog?.setContentView(sheetView)
    mBottomSheetDialog?.show()
    val startTextView = sheetView.findViewById<AppCompatTextView>(R.id.tv_start_date)
    startTextView.setOnClickListener {
        onStartDateClicked.invoke(it as AppCompatTextView)
    }
    startTextView.text = startDate?.let { if(it > 0) getDateFromTimeMills(it) else "From" }
    val endDateTextView = sheetView.findViewById<AppCompatTextView>(R.id.tv_end_date)
    endDateTextView.setOnClickListener {
        onEndDateClicked.invoke(it as AppCompatTextView)
    }
    endDateTextView.text = endDate?.let { if(it > 0) getDateFromTimeMills(it) else "To" }
    sheetView.findViewById<View>(R.id.btn_apply).setOnClickListener {
        onApplyClicked.invoke()
        dismissBottomSheet()
    }
}


fun dismissBottomSheet() {
    Handler(Looper.getMainLooper()).postDelayed(
        { if (mBottomSheetDialog != null && mBottomSheetDialog!!.isShowing) mBottomSheetDialog!!.dismiss() },
        500
    )
}
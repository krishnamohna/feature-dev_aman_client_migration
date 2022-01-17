package com.cardio.physician.ui.common.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.cardio.physician.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*
import kotlin.math.max
import kotlin.math.min


var mBottomSheetDialog: BottomSheetDialog? = null

fun showToast(context: Context, msg: String?) {
    msg?.let {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
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
    context?.let {
        showToast(it,msg)
    }
    /*if (context != null && msg.isNotBlank()) {
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
    }*/
}

fun showInfoAlertDialog(
    context: Activity, title: String, description: String,
    mCallBack: ((String, DialogInterface) -> Unit)?,
) {
    showAlertDialog(context,
        title,
        description,
        context.getString(R.string.ok),
        context.getString(R.string.no),
        false,
        mCallBack)
}

fun showConfirmAlertDialog(
    context: Activity, title: String, description: String,
    mCallBack: (String, DialogInterface) -> Unit,
) {
    showAlertDialog(context,
        title,
        description,
        context.getString(R.string.yes),
        context.getString(R.string.no),
        true,
        mCallBack)
}

fun Fragment.showToast(msg: String?){
    showToast(requireContext(),msg)
}

fun showAlertDialog(
    context: Activity, title: String, description: String,
    btnOneTitle: String, btnTwoTitle: String,
    btnTwoVisibility: Boolean = false, mCallBack: ((String, DialogInterface) -> Unit)?,
) {
    val builder = MaterialAlertDialogBuilder(context)
    if (title.isNotBlank())
        builder.setTitle(title)
    builder.setCancelable(false)
    builder.setMessage(description)
    builder.setPositiveButton(btnOneTitle) { dialogInterface, _ ->
        mCallBack?.invoke(btnOneTitle, dialogInterface)
    }
    if (btnTwoVisibility) {
        builder.setNegativeButton(btnTwoTitle) { dialogInterface, _ ->
            mCallBack?.invoke(btnTwoTitle, dialogInterface)
        }
    }
    val dialog = builder.create()
    dialog.show()
}

fun showFilePickOptions(
    activity: AppCompatActivity,
    onTakePicture: () -> Unit,
    onGalleryClick: () -> Unit,
) {
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

fun openCalendarDialog(context: Context, calendar: Calendar, startDate : Long?, endDate : Long?, startDateCalendar: Boolean, listener : DatePickerDialog.OnDateSetListener){
    val datePicker = DatePickerDialog(context, listener, calendar.get(Calendar.YEAR), calendar.get(
        Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(
        Calendar.DAY_OF_MONTH))
    if(startDateCalendar){
        if(endDate?:0L > 0L) datePicker.datePicker.maxDate = endDate!!
    }else{
        if(startDate?:0L > 0L) datePicker.datePicker.minDate = startDate!!
    }
    datePicker.show()
}

fun showDashboardFilter(
    activity: AppCompatActivity,
    onStartDateClicked: (view: AppCompatTextView) -> Unit,
    onEndDateClicked: (view: AppCompatTextView) -> Unit,
    onApplyClicked: () -> Unit,
    onClearClicked: () -> Unit,
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
    sheetView.findViewById<View>(R.id.tv_reset).setOnClickListener {
        startTextView.text = "From"
        endDateTextView.text = "To"
        onClearClicked.invoke()
        dismissBottomSheet()
    }
}

fun showFilePickOptionsForEditProfile(
    activity: Activity,
    onTakePicture: () -> Unit,
    onGalleryClick: () -> Unit,
    onRemovePhotoClick: () -> Unit,
    showRemovePhotoOption : Boolean=false
) {
    if (mBottomSheetDialog != null && mBottomSheetDialog!!.isShowing()) {
        mBottomSheetDialog!!.dismiss()
    }
    mBottomSheetDialog =
        BottomSheetDialog(activity, R.style.CustomBottomSheetDialogTheme)
    val sheetView: View =
        activity.layoutInflater.inflate(R.layout.bottom_sheet_image_picker_edit_screen, null)
    if(!showRemovePhotoOption){
        sheetView.findViewById<View>(R.id.tvRemovePic).visibility=View.GONE
    }
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
    sheetView.findViewById<View>(R.id.tvRemovePic).setOnClickListener {
        onRemovePhotoClick.invoke()
        dismissBottomSheet()
    }
}


fun dismissBottomSheet() {
    Handler(Looper.getMainLooper()).postDelayed(
        { if (mBottomSheetDialog != null && mBottomSheetDialog!!.isShowing) mBottomSheetDialog!!.dismiss() },
        500
    )
}


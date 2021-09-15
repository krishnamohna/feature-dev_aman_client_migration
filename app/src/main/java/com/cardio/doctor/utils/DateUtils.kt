package com.cardio.doctor.utils

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun getBirthDatePicker(
    context: Context?,
    mDate: Date?,
    callback: OnDateSetListener?,
): DatePickerDialog? {
    val calendar = Calendar.getInstance()
    if (mDate != null) calendar.time = mDate
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]
    val datePickerDialog = DatePickerDialog(context!!, callback, year, month, day)
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    return datePickerDialog
}

fun getMonthNumber(month: Int): String? {
    val monthNum = month + 1
    if (monthNum < 10)
        return "0$monthNum"
    else
        return monthNum.toString() + ""
}

fun getDate(date: Int): String? {
    /* if (date < 10)
         return "0" + date;
     else*/
    return date.toString() + ""
}

fun String.toDate(
    dateFormat: String = "dd-MM-yyyy", timeZone: TimeZone = TimeZone.getDefault(),
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun getStringFromDate(mDate: Date?): String? {
    if (mDate != null) {
        val dateFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
        return dateFormat.format(mDate)
    }
    return ""
}
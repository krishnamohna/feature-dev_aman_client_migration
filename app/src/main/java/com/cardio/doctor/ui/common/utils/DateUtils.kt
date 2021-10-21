package com.cardio.doctor.ui.common.utils

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.ui.common.utils.Constants.DATE_FORMAT_DD_MMM_YYYY
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import java.util.concurrent.TimeUnit

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
    //datePickerDialog.datePicker.minDate = (System.currentTimeMillis() -1000*60*60*24*365*100)

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

fun String.getNoYearsFromDate(): Int {
    var dateDob = toDate(Constants.DATE_FORMAT_DD_MMM_YYYY)
    var years = getDiffYears(dateDob, Date())
    return years
}

fun getDiffYears(first: Date?, last: Date): Int {
    val a: Calendar = getCalendar(first)
    val b: Calendar = getCalendar(last)
    var diff = b[YEAR] - a[YEAR]
    if (a[MONTH] > b[MONTH] ||
        a[MONTH] === b[MONTH] && a[DATE] > b[DATE]
    ) {
        diff--
    }
    return diff
}

fun getCalendar(date: Date?): Calendar {
    val cal = Calendar.getInstance(Locale.US)
    cal.time = date
    return cal
}

fun getCurrentDate(): String {
    val parser = SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY, Locale.getDefault())
    return parser.format(Date())
}

fun getDaysDiffrence(date: String?): Int {
    val parser = SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY, Locale.getDefault())
    var date=parser.parse(date)
    var currentDate=Date()
    val diff: Long = currentDate.getTime() - date.getTime()
    if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)>30){
        return 30
    }
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
}
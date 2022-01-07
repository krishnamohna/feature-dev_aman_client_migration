package com.cardio.physician.ui.common.utils

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import com.cardio.physician.domain.fitness.model.DateModel
import com.cardio.physician.ui.common.utils.DateFormat_.DATE_FORMAT_DD_MM
import com.cardio.physician.ui.common.utils.DateFormat_.DATE_FORMAT_DD_MMM_YYYY
import com.cardio.physician.ui.common.utils.DateFormat_.DATE_FORMAT_HH_MM
import com.google.android.gms.fitness.data.DataPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import java.util.concurrent.TimeUnit

fun getDatePicker(
    context: Context?,
    mDate: Date?,
    minDate:Date,
    callback: OnDateSetListener?,
): DatePickerDialog? {
    val calendar = Calendar.getInstance()
    if (mDate != null) calendar.time = mDate
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]
    val datePickerDialog = DatePickerDialog(context!!, callback, year, month, day)
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    //user add health log to date minus 30 days
    datePickerDialog.datePicker.minDate = (minDate.time)
    return datePickerDialog
}


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
    val calenderEnd=Calendar.getInstance()
    calenderEnd.add(Calendar.YEAR,-121)
    datePickerDialog.datePicker.minDate = calenderEnd.timeInMillis
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

fun String.datePickerStringToDate(
    dateFormat: String, timeZone: TimeZone = TimeZone.getDefault(),
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.US)
    parser.timeZone = timeZone
    return try {
        parser.parse(this)
    }catch (exp:java.lang.Exception){
        Date()
    }
}

fun getStringFromDate(mDate: Date?): String? {
    if (mDate != null) {
        val dateFormat = getDefaultDateFormatter()
        return dateFormat.format(mDate)
    }
    return ""
}

fun getDateFromTimeMills(timeMills: Long): String {
    val dateFormat = getDefaultDateFormatter()
    return dateFormat.format(Date(timeMills))
}


fun String.getNoYearsFromDate(): Int {
    var dateDob = datePickerStringToDate(DATE_FORMAT_DD_MMM_YYYY)
    return getDiffYears(dateDob, Date())
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
    return getDefaultDateFormatter().format(Date())
}

fun getDaysDifference(date: String?): Int {
    val parser = getDefaultDateFormatter()
    var date = parser.parse(date)
    var currentDate = Date()
    val diff: Long = currentDate.getTime() - date.getTime()
    if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 30) {
        return 30
    }
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
}

fun getDefaultDateFormatter() = SimpleDateFormat(DATE_FORMAT_DD_MMM_YYYY, Locale.US)
fun getDefaultTimeFormatter() = SimpleDateFormat(DATE_FORMAT_HH_MM, Locale.US)

fun DataPoint.getStartTimeString(): String = getDefaultDateFormatter()
    .format(this.getStartTime(TimeUnit.MILLISECONDS))

fun DataPoint.getEndTimeString(): String = getDefaultDateFormatter()
    .format(this.getEndTime(TimeUnit.MILLISECONDS))

fun getDatesOfLastDays(days: Int, listDates: MutableList<DateModel>) {
    for (i in (days-1) downTo 0) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -i)
        var now = Date()
        now.time = calendar.timeInMillis
        var date = getDefaultDateFormatter().format(now)
        listDates.add(DateModel(date, now.time))
    }
}

fun formatDate(inputFormat: String, inputDate: String): String? {
    var dateformat = SimpleDateFormat(inputFormat, Locale.US)
    return getDefaultDateFormatter().format(dateformat.parse(inputDate))
}

fun formatDateToGraph(dateInput:String):String{
   try {
       var date= getDefaultDateFormatter().parse(dateInput)
       return SimpleDateFormat(DATE_FORMAT_DD_MM, Locale.US).format(date)
   }catch (exp:Exception){
       return dateInput
   }
}

fun formatTimeStampToTimePassed(timeStamp: Long): String {
    val timeDiff = Math.abs(Calendar.getInstance().timeInMillis - timeStamp)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiff)
    val mins = TimeUnit.MILLISECONDS.toMinutes(timeDiff)
    val hours = TimeUnit.MILLISECONDS.toHours(timeDiff)
    val days = TimeUnit.MILLISECONDS.toDays(timeDiff)
    return when {
        seconds < 60 -> {
            "$seconds Seconds ago ${getDefaultTimeFormatter().format(Date(timeStamp))}"
        }
        mins < 60 -> {
            "$mins Minutes ago ${getDefaultTimeFormatter().format(Date(timeStamp))}"
        }
        hours < 24 -> {
            "$hours Hours ago ${getDefaultTimeFormatter().format(Date(timeStamp))}"
        }
        hours in 25..47 -> {
            "Yesterday ${getDefaultTimeFormatter().format(Date(timeStamp))}"
        }
        days < 31 -> {
            "$days Days ago ${getDefaultTimeFormatter().format(Date(timeStamp))}"
        }
        else -> {
            "${getDefaultDateFormatter().format(Date(timeDiff))} ${
                getDefaultTimeFormatter().format(timeStamp)
            }"
        }
    }
}
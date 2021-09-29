@file:Suppress("unused")

package com.cardio.doctor.ui.common.utils

import android.net.Uri
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import java.util.regex.Pattern


private const val USERNAME = "^[a-zA-Z]+[a-zA-Z ]{2,34}"//35
private const val USERID = "^(?=.*[a-zA-Z])[A-Z0-9a-z!@$%*#?&_^+.=-]{2,25}"
private const val MOBILE_NUMBER = "^[0-9]{8,12}"
private const val NUMERIC_VALUE = "^[0-9]{1,12}"
private const val PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Z0-9a-z!@$%*#?&_^+.=-]{8,20}$"
private const val WEBURL_REGX = "((https|http)://)((\\w|-)+)(([.]|[/])((\\w|-)+))+"

private val USERNAME_PATTERN: Pattern = Pattern.compile(USERNAME)
private val USERID_PATTERN: Pattern = Pattern.compile(USERID)
private val MOBILE_NUMBER_PATTERN: Pattern = Pattern.compile(MOBILE_NUMBER)
private val NUMERIC_PATTERN: Pattern = Pattern.compile(NUMERIC_VALUE)
private val WEBURL_PATTERN: Pattern = Pattern.compile(WEBURL_REGX)
private val PASSWORD_PATTERN: Pattern = Pattern.compile(PASSWORD)

fun isValidUserName(target: CharSequence): Boolean {
    return USERNAME_PATTERN.matcher(target).matches()
}

fun String.validUserNameLength(): Boolean {
    return length in 1..35
}

fun isValidUserId(target: CharSequence): Boolean {
    return USERID_PATTERN.matcher(target).matches()
}

fun String.validUserIdLength(): Boolean {
    return length in 1..25
}

fun isValidPassword(target: CharSequence): Boolean {
    return PASSWORD_PATTERN.matcher(target).matches()
}

fun isMatched(password: String, confirmPassword: String): Boolean {
    return password.equals(confirmPassword, false)
}

fun isValidEmail(target: CharSequence): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

fun validPhoneLength(target: CharSequence): Boolean {
    return target.replace(Regex("\\D"), "").length in 1..15
}

fun isValidMobileNumber(target: CharSequence): Boolean {
    return MOBILE_NUMBER_PATTERN.matcher(target).matches()
}

fun isNumericValue(target: CharSequence): Boolean {
    return NUMERIC_PATTERN.matcher(target).matches()
}

fun String.validPasswordLength(): Boolean {
    return length in 1..35
}

fun isValidWebUrl(target: CharSequence): Boolean {
    return WEBURL_PATTERN.matcher(target).matches()
}

fun setMaxLength(editText: EditText?, length: Int){
    if(editText !=null) {
        editText.filters = arrayOf<InputFilter>(LengthFilter(length))
        editText.setText("")
    }
}

fun isTextEmpty(target: String): String {
    return if(!TextUtils.isEmpty(target)) target else ""
}

fun <T> isEqual(first: List<T>, second: List<T>): Boolean {
    if (first.size != second.size) {
        return false
    }
    return first.zip(second).all { (x, y) -> x == y }
}

//fun String.removeSpace() = trim().replace("\\s+".toRegex(), replacement = "")


fun preventSpaceOnEditText(editText: EditText) {
    val filterSpace =
        InputFilter { source, _, _, _, _, _ -> if (source == " ") "" else null }
    editText.filters = arrayOf(filterSpace)
}

fun EditText.ignoreFirstWhiteSpace() {
    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        for (i in start until end) {
            if (Character.isWhitespace(source[i])) {
                if (dstart == 0) return@InputFilter ""
            }
        }
        null
    }

    filters = arrayOf(filter)
}

fun String.formatStringToAddThousandsCharacter(): String {
    var targetString = this
    try {
        val twoPartList = targetString.split(".")
        targetString = String.format("%,d", twoPartList[0].toLong()).plus(".").plus(twoPartList[1])
    } catch (e: Exception) {
    }
    return targetString
}

fun EditText.limitLength(maxLength: Int) {
    filters = arrayOf<InputFilter>(LengthFilter(maxLength))
}

fun String.convertIntoUri() : Uri {
    val imagePath = this
    return Uri.parse(imagePath)
}

fun View.setDoubleClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = DoubleClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
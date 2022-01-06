package com.cardio.physician.ui.common.utils.inputfilter

import android.text.InputFilter
import android.text.Spanned
import java.lang.String
import java.util.regex.Pattern

class DecimalDigitsInputFilter(private val digitsBeforeZero: Int, private val digitsAfterZero: Int) : InputFilter {
    private var mPattern: Pattern =
        Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int,
    ): CharSequence? {
        if (source.toString().length > 6) {
            return String.format("%.2f", source.toString().toFloat())
        }
        val matcher = mPattern.matcher(dest)
        if (!matcher.matches() && !handleDotAfterLimit(dest,source)) {
            //check if point is after digitsBeforeZero
            return ""
        } else {
            return null
        }
    }

    private fun handleDotAfterLimit(dest: Spanned, source: CharSequence): Boolean {
        if (dest.length < digitsBeforeZero && !dest.contains(".")) {
            //not need to handle in this method if length is smaller than digitsBeforeZero. Rejex will handle it
            return true
        }
        if (!dest.contains(".") && source==".") {
            return true
        }
        //check how many digits after digits
        if (dest.contains(".") && dest.split(".").last().count() < digitsAfterZero) {
            return true
        }
        return false
    }

}
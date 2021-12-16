package com.cardio.physician.ui.common.utils.inputfilter

import android.text.InputFilter
import android.text.Spanned
import java.lang.String
import java.util.regex.Pattern

class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) : InputFilter {
    private var mPattern: Pattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int,
    ): CharSequence? {
        if(source.toString().length>6){
            return String.format("%.2f", source.toString().toFloat())
        }
        val matcher = mPattern.matcher(dest)
         if (!matcher.matches()) {
             return ""
         }
         else {
             return null
         }
    }

}
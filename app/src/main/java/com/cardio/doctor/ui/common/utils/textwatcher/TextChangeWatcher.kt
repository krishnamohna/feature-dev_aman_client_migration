package com.cardio.doctor.ui.common.utils.textwatcher

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.GONE
import android.widget.EditText
import android.widget.TextView

class TextChangeWatcher( var input:EditText, private val errorTxt: TextView, private val label:TextView) :
    TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            errorTxt.visibility = GONE
          //  customAnimationForTextInput(input.context, label!!, s, before)
            if (s.isNotEmpty()) label.visibility = View.VISIBLE
            else  label.visibility = View.GONE
            input.requestFocus()
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }
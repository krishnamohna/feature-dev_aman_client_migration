package com.cardio.doctor.ui.common.utils.textwatcher

import android.widget.EditText
import android.widget.TextView
import javax.inject.Inject

class LabelVisiblityHelper @Inject constructor() {

    fun addView(input:EditText,error:TextView,label:TextView){
        input.addTextChangedListener(TextChangeWatcher(input,error,label))
    }

}
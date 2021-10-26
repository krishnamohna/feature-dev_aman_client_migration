package com.cardio.doctor.ui.common.utils.textwatcher

import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import com.cardio.doctor.ui.common.base.activity.BaseActivity
import javax.inject.Inject

class LabelVisiblityHelper @Inject constructor() {

    fun addView(
        input: EditText,
        error: TextView,
        label: TextView,
        scrollView: ScrollView?,
        parentActivity: BaseActivity?
    ){
        input.addTextChangedListener(TextChangeWatcher(error,label,scrollView,parentActivity))
    }

}
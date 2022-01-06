package com.cardio.physician.ui.common.utils.textwatcher

import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import com.cardio.physician.ui.common.base.activity.BaseActivity
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
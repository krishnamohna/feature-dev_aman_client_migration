package com.cardio.doctor.ui.common.utils.textwatcher.alphaVisibility

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

class AlphaVisibilityHelper {
    fun addEditTextWatcher(list: List<EditText>,onTextChange:()->Unit) {
        list.forEach {
            it.addTextChangedListener {
                onTextChange.invoke()
            }
        }
    }

}
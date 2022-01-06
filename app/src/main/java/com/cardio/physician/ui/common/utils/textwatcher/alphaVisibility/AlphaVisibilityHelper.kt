package com.cardio.physician.ui.common.utils.textwatcher.alphaVisibility

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

    fun addDiagnosisStep1Listner(listEdtFields: List<EditText>,onTextChange:()->Unit) {
        listEdtFields.forEach {
            it.addTextChangedListener {
                onTextChange.invoke()
            }
        }
    }

}
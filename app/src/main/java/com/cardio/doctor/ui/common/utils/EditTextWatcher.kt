package com.cardio.doctor.ui.common.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.cardio.doctor.R

class EditTextWatcher(
    private val context: Context,
    private val txtLabel: TextView
) : TextWatcher {
    override fun afterTextChanged(s: Editable) {}

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val up: Animation = AnimationUtils.loadAnimation(context, R.anim.txt_bottom_up)
        val down: Animation = AnimationUtils.loadAnimation(context, R.anim.txt_bottom_down)
        up.reset()
        down.reset()
        if (s.isNotEmpty()) txtLabel.visibility = View.VISIBLE
         else  txtLabel.visibility = View.GONE

        if (before == 0 && s.length == 1)  txtLabel.startAnimation(up)
        if (s.isEmpty()) txtLabel.startAnimation(down)
    }
}
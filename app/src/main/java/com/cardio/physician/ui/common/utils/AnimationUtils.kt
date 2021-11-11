package com.cardio.physician.ui.common.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.cardio.physician.R

fun customAnimationForTextInput( context: Context,txtLabel: TextView,s: CharSequence, before: Int){
    val up: Animation = AnimationUtils.loadAnimation(context, R.anim.txt_bottom_up)
    val down: Animation = AnimationUtils.loadAnimation(context, R.anim.txt_bottom_down)
    up.reset()
    down.reset()
    if (s.isNotEmpty()) txtLabel.visibility = View.VISIBLE
    else  txtLabel.visibility = View.GONE

    if (before == 0 && s.length == 1)  txtLabel.startAnimation(up)
    if (s.isEmpty()) txtLabel.startAnimation(down)
}


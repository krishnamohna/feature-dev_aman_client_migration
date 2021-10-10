package com.cardio.doctor.ui.common.utils.keyboard

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View

fun Activity.getRootView(rootView:Int): View {
    return findViewById<View>(rootView)
}
fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 
            dp, 
            this.resources.displayMetrics
    )
}
fun Activity.isKeyboardOpen(rootView: Int): Boolean {
    val visibleBounds = Rect()
    //finding screen height
    getRootView(rootView).getWindowVisibleDisplayFrame(visibleBounds)
    val screenHeight = getRootView(rootView).height
    //finding keyboard height
    val keypadHeight = screenHeight - visibleBounds.bottom
    if (keypadHeight > screenHeight * 0.15)
        return true
    else return false
//    val marginOfError = Math.round(this.convertDpToPx(50F))
//    return keypadHeight > marginOfError
}

fun Activity.isKeyboardClosed(rootView: Int): Boolean {
    return !this.isKeyboardOpen(rootView)
}
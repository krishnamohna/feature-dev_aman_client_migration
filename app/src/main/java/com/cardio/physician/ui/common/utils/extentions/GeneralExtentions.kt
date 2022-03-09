package com.cardio.physician.ui.common.utils.extentions

import android.content.Context
import androidx.fragment.app.Fragment
import com.cardio.physician.R
import com.cardio.physician.network.NetworkHelper
import com.cardio.physician.ui.common.utils.showToast
import java.util.*


fun Context.isConnectedOrThrowMsg(result:()->Unit) {
   if(!NetworkHelper(this).isNetworkConnected()){
       showToast(this, R.string.err_no_network_available)
   }else{
       result.invoke()
   }
}

fun Fragment.isConnectedOrThrowMsg(result:()->Unit){
    try{
        requireContext().isConnectedOrThrowMsg(result)
    }catch (exp:IllegalStateException){
        exp.printStackTrace()
    }
}

fun String.removeSpace(): String {
    return replace(" ","")
}

fun String.isAllLetter():Boolean{
    this.forEach {
        if(!it.isLetter()) return false
    }
    return true
}

fun String.isAllDigits():Boolean{
    this.forEach {
        if(!it.isDigit()) return false
    }
    return true
}
fun String?.inValidHealthLog(): Boolean {
    return isNullOrBlank() || this=="0"
}

fun String?.isValidHealthLog(): Boolean {
    return !inValidHealthLog()
}

fun Calendar.clearHoursMins(): Calendar {
    return apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND,0)
    }
}

package com.cardio.doctor.ui.common.utils.imagecrop

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment

fun cropImage(fragment: Fragment){
    EdmodoImageCroper().cropImage(fragment)
}

fun cropImageFromUri(uri: Uri,fragment: Fragment){
    UImageImageCroper().cropImage(fragment,uri)
}
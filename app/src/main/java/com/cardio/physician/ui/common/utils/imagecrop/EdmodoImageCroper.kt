package com.cardio.doctor.ui.common.utils.imagecrop

import android.net.Uri
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class EdmodoImageCroper  {

    fun cropImage(fragment: Fragment) {
        //library handle itsselft image picker and capture image
        openCroper(fragment)
    }

    private fun openCroper(fragment: Fragment){
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
            .start(fragment.requireContext(), fragment)
    }
}
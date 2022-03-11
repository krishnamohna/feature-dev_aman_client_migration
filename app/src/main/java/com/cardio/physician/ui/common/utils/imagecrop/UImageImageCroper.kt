package com.cardio.doctor.ui.common.utils.imagecrop

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.yalantis.ucrop.UCrop
import java.io.File

class UImageImageCroper  {

    fun cropImage(fragment: Fragment, uri: Uri) {
        val SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage+"+System.currentTimeMillis()+".jpg"
        UCrop.of(
            uri, Uri.fromFile(
                File(
                    fragment.requireContext().getCacheDir(),
                    SAMPLE_CROPPED_IMAGE_NAME
                )
            )
        ).withAspectRatio(1F, 1F)
            .withMaxResultSize(1000, 1000)
            .start(fragment.requireContext(), fragment);
    }


}
package com.cardio.physician.ui.common.utils.extentions

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.cardio.physician.R


/** ImageView Extensions */
fun ImageView.loadImage(
    context: Context,
    @DrawableRes image: Int,
    circleCrop: Boolean = false,
    cache: Boolean = false
) {
    Glide.with(context)
        .load(image)
        .transition(withCrossFade(DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true)
            .build()))
        .apply {
            if (circleCrop) circleCrop()
            if (!cache) {
                diskCacheStrategy(DiskCacheStrategy.NONE)
            }
        }
        .into(this)
}

fun ImageView.loadImage(imageUrl: Uri, circleCrop: Boolean = false, showProgress: Boolean = true) {
    Glide.with(context)
        .load(imageUrl)
        .transition(withCrossFade(DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true)
            .build()))
        .apply {
            if (circleCrop) circleCrop()
            diskCacheStrategy(DiskCacheStrategy.ALL)
            if (showProgress) {
                placeholder(getProgressDrawable(context))
            }
        }
        .error(R.drawable.ic_profile_placeholder)
        .into(this)
}

var factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
@SuppressLint("CheckResult")
fun ImageView.loadImage(imageUrl: String, circleCrop: Boolean = false, showProgress: Boolean = true,errorImage:Int=R.drawable.ic_profile_placeholder) {
    Glide.with(context)
        .load(imageUrl)
        .transition(withCrossFade(factory))
        .apply {
           // if (circleCrop) circleCrop()
          //  diskCacheStrategy(DiskCacheStrategy.ALL)
            if (showProgress) {
                placeholder(getProgressDrawable(context))
            }
        }
        .error(errorImage)
        .into(this)
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
}
package com.cardio.physician.ui.common.customviews.toolbar

import android.view.View
import android.widget.ImageView
import com.cardio.physician.R
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class TextRecogToolbarImp(val view: View) : IToolbar {

    var onBackClick: (() ->Unit)? = null

    override fun setToolbar() {
        view.setUpToolbar(view.context.getString(R.string.title_image_scanner))
        //change back button icon
        view?.run {
            findViewById<ImageView>(R.id.backBtn).setImageResource(R.drawable.ic_close)
            findViewById<ImageView>(R.id.backBtn).setOnClickListener { onBackClick?.invoke() }
        }
    }

}
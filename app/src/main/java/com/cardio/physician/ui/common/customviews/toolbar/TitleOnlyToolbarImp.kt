package com.cardio.physician.ui.common.customviews.toolbar

import android.view.View
import android.widget.ImageView
import com.cardio.physician.R
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class TitleOnlyToolbarImp constructor(
    val view: View,
    val title: String,
    val showBackButton: Boolean = false,
) : IToolbar {
    override fun setToolbar() {
        view.setUpToolbar(title)
        view.findViewById<ImageView>(R.id.backBtn).visibility = if (showBackButton)
            View.VISIBLE
        else
            View.INVISIBLE
    }
}
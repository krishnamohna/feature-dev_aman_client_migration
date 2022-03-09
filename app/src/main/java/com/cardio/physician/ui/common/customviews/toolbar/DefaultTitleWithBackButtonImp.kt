package com.cardio.physician.ui.common.customviews.toolbar

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cardio.physician.R
import com.cardio.physician.ui.common.customviews.toolbar.base.BaseToolbarImp

class DefaultTitleWithBackButtonImp(
    constraintLayout: ConstraintLayout,
    private val title: Int,
    private var onBackClick: (() -> Unit),
    private val useCloseIcon:Boolean=false
) :
    BaseToolbarImp<DefaultTitleWithBackButtonImp>(constraintLayout) {

    init {
        onBackButtonClick=onBackClick
    }

    override fun getTitle(): String = getContext().getString(title)

    override fun setToolbar() {
        super.setToolbar()
        if(useCloseIcon)
            view.findViewById<ImageView>(R.id.backBtn).setImageResource(R.drawable.ic_close)
    }
}
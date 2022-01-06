package com.cardio.physician.ui.common.customviews.toolbar

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cardio.physician.R
import com.cardio.physician.ui.common.customviews.toolbar.base.BaseToolbarImp
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class EditProfileToolBarImp(view: ConstraintLayout) : BaseToolbarImp<EditProfileToolBarImp>(view) {

    override fun setToolbar() {
        super.setToolbar()
        view.findViewById<ImageView>(R.id.backBtn).visibility = View.VISIBLE
    }

    override fun getTitle(): String {
        return view.context.getString(R.string.edit_profile)
    }
}
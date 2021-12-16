package com.cardio.physician.ui.common.base.toolbar

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class ProfileToolbarImp(val view: View) : IToolbar {

    override fun setToolbar() {
        view.setUpToolbar(view.context.getString(R.string.title_profile))
        view.findViewById<ImageView>(R.id.backBtn).visibility=View.INVISIBLE
        view.findViewById<ImageView>(R.id.imgEditProfile).visibility=View.VISIBLE
        view.findViewById<TextView>(R.id.toolbarTitle).gravity= Gravity.CENTER
    }

}
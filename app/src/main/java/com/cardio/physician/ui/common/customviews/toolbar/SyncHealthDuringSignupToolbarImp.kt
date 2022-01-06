package com.cardio.physician.ui.common.customviews.toolbar

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.cardio.physician.R
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class SyncHealthDuringSignupToolbarImp(val view: View) : IToolbar {

    override fun setToolbar() {
        view.setUpToolbar(view.context.getString(R.string.sync_health_data))
        view.findViewById<TextView>(R.id.toolbarTitle).gravity= Gravity.CENTER
    }

}
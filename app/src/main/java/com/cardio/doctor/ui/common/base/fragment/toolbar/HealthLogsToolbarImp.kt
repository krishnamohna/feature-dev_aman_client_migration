package com.cardio.doctor.ui.common.base.fragment.toolbar

import android.view.View
import android.widget.ImageView
import com.cardio.doctor.R
import com.cardio.doctor.ui.common.utils.extentions.setUpToolbar

class HealthLogsToolbarImp(val view: View) : IToolbar {

    var onBackClick: (() ->Unit)? = null

    override fun setToolbar() {
        view.setUpToolbar(view.context.getString(R.string.title_health_logs))
        //change back button icon
        view?.run {
            findViewById<ImageView>(R.id.backBtn).setImageResource(R.drawable.ic_back_v2)
            findViewById<ImageView>(R.id.backBtn).setOnClickListener { onBackClick?.invoke() }
        }
    }

}
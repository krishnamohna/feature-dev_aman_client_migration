package com.cardio.physician.ui.common.customviews.toolbar

import android.view.View
import android.widget.ImageView
import com.cardio.physician.R
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class SyncHealthToolbarImp(val view: View) : IToolbar {

    fun onBackButtonListener(onBackButtonClick: () -> Unit): SyncHealthToolbarImp {
        view.findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            onBackButtonClick.invoke()
        }
        return this
    }

    override fun setToolbar() {
        view.setUpToolbar(view.context.getString(R.string.sync_health_data))
        view.findViewById<ImageView>(R.id.backBtn).visibility = View.VISIBLE
    }

}
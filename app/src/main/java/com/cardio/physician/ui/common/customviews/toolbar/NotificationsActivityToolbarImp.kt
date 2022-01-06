package com.cardio.physician.ui.common.customviews.toolbar

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.cardio.physician.R
import com.cardio.physician.ui.common.customviews.toolbar.base.BaseToolbarImp
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class NotificationsActivityToolbarImp constructor(viewToolbar: ConstraintLayout) :
    BaseToolbarImp<NotificationsActivityToolbarImp>(viewToolbar) {

    override fun getTitle()= getContext().getString(R.string.title_notification_screen)
}
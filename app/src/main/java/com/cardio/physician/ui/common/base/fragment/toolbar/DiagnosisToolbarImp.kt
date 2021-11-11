package com.cardio.physician.ui.common.base.fragment.toolbar

import android.view.View
import android.widget.ImageView
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class DiagnosisToolbarImp(val view: View) : IToolbar {

    var onBackClick: (() ->Unit)? = null

    override fun setToolbar() {
        view.setUpToolbar(view.context.getString(R.string.title_diagnosis))
        //change back button icon
        view?.run {
            findViewById<ImageView>(R.id.backBtn).setImageResource(R.drawable.ic_close)
            findViewById<ImageView>(R.id.backBtn).setOnClickListener { onBackClick?.invoke() }
        }
    }

    fun setConnectButtonVisibility(visibile: Boolean) {
        view?.findViewById<View>(R.id.buttonConnect)?.visibility=if(visibile){
            View.VISIBLE
        }else
            View.GONE
    }


}
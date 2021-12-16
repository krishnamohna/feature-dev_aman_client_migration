package com.cardio.physician.ui.common.base.toolbar

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.extentions.loadImage
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

class DashBoardToolbarImp(val view: View) : IToolbar {

    override fun setToolbar() {
        view.setUpToolbar(view.context.getString(R.string.title_dashboard))
        view.findViewById<ImageView>(R.id.backBtn).visibility=View.INVISIBLE
        view.findViewById<TextView>(R.id.toolbarTitle).gravity= Gravity.CENTER
        view.findViewById<ImageView>(R.id.imgProfilePicToolbar).visibility=View.VISIBLE
        view.findViewById<ImageView>(R.id.iv_notification).visibility=View.VISIBLE
        //make it invisible from gone to keep title in center
        view.findViewById<ImageView>(R.id.imgEditProfile).visibility=View.INVISIBLE
    }

    fun setUserImage(imageUrl:String?){
        imageUrl?.let {
            view.findViewById<ImageView>(R.id.imgProfilePicToolbar).loadImage(it,true,true,R.drawable.ic_profile_placeholder_small)
        }
    }

}
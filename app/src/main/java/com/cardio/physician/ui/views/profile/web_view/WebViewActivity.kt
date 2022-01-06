package com.cardio.physician.ui.views.profile.web_view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.cardio.physician.R
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.utils.EXTRAS.WEB_URL

class WebViewActivity : BaseActivity() {

    companion object{
        fun start(activity: Activity,url:String){
            Intent().apply {
                putExtra(WEB_URL,url)
            }.run {
                activity.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
    }
}
package com.cardio.doctor.ui.common.utils.extentions

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.cardio.doctor.R
import com.cardio.doctor.ui.common.utils.isValidEmail

fun String.isEmailVerified(): Boolean {
    return isValidEmail(this)
}

fun View.setUpToolbar(title: String) {
    setUpToolbar(title, true, false, null)
}

fun View.setUpToolbar(
    title: String,
    backBtnVisibility: Boolean = false,
    editProfile: Boolean = false,
    activity: AppCompatActivity? = null
) {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    val backBtn = findViewById<ImageView>(R.id.backBtn)
    val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)
    val imgEdtProfile = findViewById<ImageView>(R.id.imgEditProfile)
    activity?.let {
        activity?.setSupportActionBar(toolbar)
        activity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    backBtn.visibility = if (title.isNotEmpty()) View.VISIBLE else View.GONE
    backBtn.visibility = if (backBtnVisibility) View.VISIBLE else View.INVISIBLE
    imgEdtProfile.visibility = if (editProfile) View.VISIBLE else View.GONE
    if (!TextUtils.isEmpty(title)) {
        toolbarTitle.text = title
    }
}
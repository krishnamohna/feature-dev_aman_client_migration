package com.cardio.physician.ui.common.customviews.toolbar.base

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cardio.physician.R
import com.cardio.physician.ui.common.utils.extentions.setUpToolbar

abstract class BaseToolbarImp<T>(val view: ConstraintLayout) :IToolbar {
   var onBackButtonClick: (() -> Unit)? = null
   override fun setToolbar() {
      view.setUpToolbar(getTitle())
      view.findViewById<ImageView>(R.id.backBtn)?.setOnClickListener {
         onBackButtonClick?.invoke()
      }
   }

   abstract fun getTitle(): String
   fun getContext(): Context = view.context

   fun registerBackButtonListener(onBackButtonClick: (() -> Unit)?): BaseToolbarImp<T> {
      this.onBackButtonClick = onBackButtonClick
      return this
   }
}
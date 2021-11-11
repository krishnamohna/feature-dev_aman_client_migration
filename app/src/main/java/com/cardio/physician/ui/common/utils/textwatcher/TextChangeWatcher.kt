package com.cardio.physician.ui.common.utils.textwatcher

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.GONE
import android.widget.ScrollView
import android.widget.TextView
import com.cardio.physician.R
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.utils.keyboard.isKeyboardOpen

val DELAY_SMALL_ANIMATION: Long=250
class TextChangeWatcher(
    private val errorTxt: TextView,
    private val label: TextView,
    private val scrollView: ScrollView?,
    private val parentActivity: BaseActivity?
) :
    TextWatcher {

    val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }
    
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        errorTxt.visibility = GONE
        if (s.isNotEmpty()) {
            if (label.visibility != View.VISIBLE) {
                label.visibility = View.VISIBLE
                var labelHeight=label.context.resources.getDimension(R.dimen.offset_scroll_label_up_medium).toInt()
                parentActivity?.let {
                    if(it.isKeyboardOpen(getScrollRootView()))
                        handler.postDelayed({
                            scrollView?.smoothScrollBy(0, labelHeight)
                        }, DELAY_SMALL_ANIMATION)
                }
            }
        } else {
            if (label.visibility != View.GONE) {
                label.visibility = View.GONE
            }
        }
    }

    private fun getScrollRootView(): Int {
        return R.id.clScrollViewContainer
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}
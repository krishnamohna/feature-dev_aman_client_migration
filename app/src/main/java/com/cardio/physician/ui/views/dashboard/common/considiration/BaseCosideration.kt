package com.cardio.physician.ui.views.dashboard.common.considiration

import android.text.SpannableString
import android.text.style.BulletSpan
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import com.cardio.doctor.ui.views.dashboard.common.considiration.Consideration
import com.cardio.physician.R
import com.cardio.physician.databinding.ItemConsidiraterationBulletTextviewBinding


abstract class BaseCosideration : Consideration {

    /*object Observation10 {
        const val OBSERVATION_READING_COUNT = 10F
        const val TWENTY_PERCENT_OF_READING_COUNT = OBSERVATION_READING_COUNT * 20 / 100
    }*/

    val listConsideration = mutableListOf<String>()

    fun addConsidirationView(viewGroup: LinearLayoutCompat, consideration: String) {
        val textView = ItemConsidiraterationBulletTextviewBinding.inflate(
            LayoutInflater.from(
                viewGroup.context
            )
        ).root.apply {
            setTextColor(context.getColor(R.color.black))
            typeface = ResourcesCompat.getFont(context, R.font.nunito_regular)
            val t1 = "$consideration"
            val s1 = SpannableString(t1)
            val bulletGap = context.resources.getDimension(R.dimen._6sdp).toInt()
            val bulletColor = context.getColor(R.color.black)
            val bulletRadius = context.resources.getDimension(R.dimen._6sdp).toInt()
            s1.setSpan(BulletSpan(bulletGap), 0, t1.length, 0)
            text = s1
        }
        val layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(
            0,
            viewGroup.context.resources.getDimension(R.dimen._8sdp).toInt(),
            0,
            0
        )
        viewGroup.addView(textView, layoutParams)
    }

    fun addErrorMsgToConsideration(msg: String) {
        if (!listConsideration.contains(msg)) {
            listConsideration.add(msg)
        }
    }
}
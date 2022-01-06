package com.cardio.physician.ui.views.diagnosis.step1.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class AilmentDropDownAdapter constructor(context: Context, resource: Int, array: Array<String>) :
    ArrayAdapter<String>(context, resource, array) {

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val tv = view as TextView
        if (position === 0) {
            tv.setTextColor(Color.GRAY)
        } else {
            tv.setTextColor(Color.BLACK)
        }
        return view
    }

}
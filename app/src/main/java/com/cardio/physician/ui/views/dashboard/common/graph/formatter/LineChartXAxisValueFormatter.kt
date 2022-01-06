package com.cardio.physician.ui.views.dashboard.common.graph.formatter

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class LineChartXAxisValueFormatter(private val dateLabels: List<String?>) : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val index = value.toInt()
        if(index<dateLabels.size && index>-1)
            return dateLabels.get(index)?:index.toString()
        else if(dateLabels.isNotEmpty() && index>dateLabels.size-1){
            return dateLabels.last().toString()
        }
        else if(dateLabels.isNotEmpty() && index<0){
            return dateLabels.first().toString()
        }
        return index.toString()
    }
}
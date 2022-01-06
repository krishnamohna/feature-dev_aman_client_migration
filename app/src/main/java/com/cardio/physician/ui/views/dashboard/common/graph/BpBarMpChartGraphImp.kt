package com.cardio.physician.ui.views.dashboard.common.graph

import android.content.Context
import android.graphics.Color
import android.view.View
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.ui.common.utils.Constants
import com.cardio.physician.ui.common.utils.Timer
import com.cardio.physician.ui.common.utils.Timer.Companion.CHART_ANIMATE_TIME
import com.cardio.physician.ui.common.utils.formatDateToGraph
import com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp
import com.cardio.physician.ui.views.dashboard.common.graph.base.BpGraph
import com.cardio.physician.ui.views.dashboard.common.graph.formatter.LineChartXAxisValueFormatter
import com.cardio.physician.ui.views.dashboard.common.graph.formatter.MyAxisValueFormatter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import javax.inject.Inject

class BpBarMpChartGraphImp @Inject constructor() : BaseGraphImp(), BpGraph {

    private lateinit var chart: BarChart

    override fun getGraphView(context: Context): View {
        chart = BarChart(context)
        init(chart)
        return chart
    }

    override fun showGraph(listHealthLogs: List<FitnessModel>?) {
        val values = mutableListOf<BarEntry>()
        val values2 = mutableListOf<BarEntry>()
        val dateLabels = mutableListOf<String?>()
        var totalValue = 0f
        var totalValue2 = 0f
        listHealthLogs?.forEachIndexed { index, fitnessModel ->
            fitnessModel.bloodPressureTopBp?.let {
                if(it == "0" || it.isBlank()) return@let
                totalValue += fitnessModel.bloodPressureBottomBp!!.toFloat()
                totalValue2 += fitnessModel.bloodPressureTopBp!!.toFloat()
                var lowerVal = fitnessModel.bloodPressureBottomBp!!.toFloat()
                var upperVal =fitnessModel.bloodPressureTopBp!!.toFloat() - lowerVal
                val range = floatArrayOf(lowerVal, upperVal)
                values.add(BarEntry(index.toFloat(), range))
                fitnessModel.date?.let { dateLabels.add(formatDateToGraph(it)) }
            }
        }
        //check if there are entries and then make  visible
        //check if there are entries and then make  visible
        if(dateLabels.isNotEmpty()){
            showGraphFilters()
            parentLayout?.visibility = View.VISIBLE //set avg value of graph
            setAverageValue((totalValue / dateLabels.size).toInt().toString())
            setAverageValue2((totalValue2 / dateLabels.size).toInt().toString())
        }else{
            parentLayout?.visibility = View.GONE
            return
        }
        chart.xAxis.valueFormatter = LineChartXAxisValueFormatter(dateLabels)
        val set1: BarDataSet
        if (chart.data != null &&
            chart.data.dataSetCount > 0
        ) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.invalidate()
            chart.animateX(CHART_ANIMATE_TIME)
        } else {
            set1 = BarDataSet(values, "")
            set1.setDrawIcons(false)
            set1.setDrawValues(false)
            set1.isHighlightEnabled=false
            set1.valueTextSize = 11f
            set1.setColors(*getColors())
            set1.stackLabels = arrayOf("", "", "")
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            //  data.setValueFormatter(MyValueFormatter())
            data.setValueTextColor(Color.BLACK)
            data.barWidth = 0.3f
            chart.data = data
        }
        chart.setFitBars(true)
        chart.notifyDataSetChanged();
        chart.invalidate()
    }

    private fun init(chart: BarChart) {
        // if more than 60 entries are displayed in the chart, no values will be
        chart.description.isEnabled = false
        chart.setMaxVisibleValueCount(10)
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)
        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(false)
        chart.isHighlightFullBarEnabled = false

        // change the position of the y-labels
        val rightAxis = chart.axisRight
        rightAxis.valueFormatter = MyAxisValueFormatter()
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
        rightAxis.typeface=typefaceBold
        rightAxis.enableGridDashedLine(10f, 10f, 0f)
        /* rightAxis.axisMaximum = 180f
         rightAxis.axisMinimum = 0f*/

        val leftAxis = chart.axisLeft
        leftAxis.enableGridDashedLine(10f, 10f, 0f)
//        leftAxis.axisMaximum = 180f
//        leftAxis.axisMinimum = 0f

        val xAxis = chart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.granularity = 1f // only intervals of 1 day
        xAxis.labelCount = Constants.CHART_LABEL_COUNT
        xAxis.typeface=typefaceBold
        xAxis.enableGridDashedLine(10f, 10f, 0f)
        // xAxis.axisMinimum = 0f

        val l = chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = LegendForm.NONE
    }

    val MATERIAL_COLORS = intArrayOf(
        Color.TRANSPARENT,
        ColorTemplate.rgb("#E23D3D"),
    )

    private fun getColors(): IntArray {
        // have as many colors as stack-values per entry
        val colors = IntArray(2)
        System.arraycopy(MATERIAL_COLORS, 0, colors, 0, 2)
        return colors
    }
}
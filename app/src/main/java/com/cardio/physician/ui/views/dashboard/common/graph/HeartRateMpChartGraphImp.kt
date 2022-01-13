package com.cardio.physician.ui.views.dashboard.common.graph

import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.view.View
import com.cardio.physician.R
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.ui.common.utils.Constants.CHART_LABEL_COUNT
import com.cardio.physician.ui.common.utils.Timer
import com.cardio.physician.ui.common.utils.formatDateToGraph
import com.cardio.physician.ui.views.dashboard.common.graph.base.BaseGraphImp
import com.cardio.physician.ui.views.dashboard.common.graph.base.HeartRateGraph
import com.cardio.physician.ui.views.dashboard.common.graph.customview.MyMarkerView
import com.cardio.physician.ui.views.dashboard.common.graph.formatter.LineChartXAxisValueFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Utils
import java.util.*
import javax.inject.Inject

class HeartRateMpChartGraphImp @Inject constructor() : BaseGraphImp(), HeartRateGraph,
    OnChartValueSelectedListener {

    private lateinit var chart: LineChart

    override fun getGraphView(context: Context): View {
        chart = LineChart(context)
        init(context)
        return chart
    }

    override fun showGraph(listHealthLogs: List<FitnessModel>?) {
        val values = mutableListOf<Entry>()
        var totalValue = 0f
        val dateLabels = mutableListOf<String?>()
        listHealthLogs?.forEachIndexed { index, fitnessModel ->
            fitnessModel.heartRate?.let {
                if(it == "0" || it.isBlank()) return@let
                values.add(Entry(index.toFloat(), it.toFloat()))
                totalValue += it.toFloat()
                fitnessModel.date?.let { dateLabels.add(formatDateToGraph(it)) }
            }
        }
        //check if there are entries and then make  visible
        if(dateLabels.isNotEmpty()){
            showGraphFilters()
            parentLayout?.visibility = View.VISIBLE //set avg value of graph
            setAverageValue((totalValue / dateLabels.size).toInt().toString())
        }else{
            parentLayout?.visibility = View.GONE
            return
        }
        chart.xAxis.valueFormatter = LineChartXAxisValueFormatter(dateLabels)
        val set1: LineDataSet
        if (chart.data != null &&
            chart.data.dataSetCount > 0
        ) {
            set1 = chart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.invalidate()
            chart.animateX(Timer.CHART_ANIMATE_TIME)
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "")
            set1.setDrawCircles(false)
            set1.setDrawValues(false)
            set1.disableDashedLine()
            set1.setDrawIcons(false)
            // draw dashed line
           // set1.enableDashedLine(10f, 5f, 0f)
            // black lines and points
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)
            // line thickness and point size
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            // draw points as solid circles
            set1.setDrawCircleHole(false)
            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f
            // text size of values
            set1.valueTextSize = 9f
            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)
            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> chart.axisLeft.axisMinimum }
            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                // val drawable = ContextCompat.getDrawable(chart.context, R.drawable.fade_red)
                // set1.fillDrawable = drawable
                set1.fillColor = Color.WHITE
            } else {
                set1.fillColor = Color.WHITE
            }
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets
            // create a data object with the data sets
            val data = LineData(dataSets)
            // set data
            chart.data = data
            chart.notifyDataSetChanged();
            chart.invalidate()
        }
    }

    private fun init(context: Context) {
        // background color
        chart.setBackgroundColor(Color.WHITE)
        // disable description text
        chart.getDescription().setEnabled(false)
        // enable touch gestures
        chart.setTouchEnabled(true)
        // set listeners
        chart.setOnChartValueSelectedListener(this)
        chart.setDrawGridBackground(false)
        // create marker to display box when values are selected
        val mv =
            MyMarkerView(
                context,
                R.layout.custom_marker_view)
        // Set the marker to the chart
        mv.setChartView(chart)
        chart.marker = mv
        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);
        // force pinch zoom along both axis
        chart.setPinchZoom(true)
        // // X-Axis Style // //
        var xAxis: XAxis = chart.xAxis
        xAxis.enableGridDashedLine(10f, 10f, 0f)
        xAxis.labelCount = CHART_LABEL_COUNT
        xAxis.granularity = 1f // only intervals of 1 day
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.typeface=typefaceBold
        xAxis.axisMinimum = 0f
        // Y-Axis Style
        var yAxis: YAxis = chart.getAxisLeft()
        yAxis.typeface=typefaceBold
        yAxis.setAxisMaximum(200f)
//        yAxis.axisMinimum = 0f
        // disable dual axis (only use LEFT axis)
        chart.axisRight.isEnabled = false
        // horizontal grid lines
         yAxis.enableGridDashedLine(10f, 10f, 0f)
        // axis range
        //  yAxis.axisMaximum = 200f
        //  yAxis.axisMinimum = -50f
        //ll2.typeface = tfRegular
        // draw points over time
        chart.animateX(1500)
        // get the legend (only possible after setting data)
        val legend: Legend = chart.getLegend()
        // draw legend entries as lines
        legend.form = Legend.LegendForm.NONE
        /*set limit lines*/
//        addLimitLines(yAxis)
    }

    private fun addLimitLines(yAxis: YAxis) {
        val ll1 = LimitLine(20f, "LOW")
        ll1.lineWidth = 3f
        ll1.disableDashedLine()
       //ll1.enableDashedLine(10f, 10f, 0f)
        ll1.lineColor=chart.context.resources.getColor(R.color.color_low_heart_rate)
        ll1.labelPosition = LimitLabelPosition.RIGHT_BOTTOM
        ll1.textSize = 10f

        val ll2 = LimitLine(70f, "OPT")
        ll2.lineWidth = 3f
        ll2.lineColor=chart.context.resources.getColor(R.color.color_optimal_heart_rate)
       // ll2.enableDashedLine(10f, 10f, 0f)
        ll2.labelPosition = LimitLabelPosition.RIGHT_BOTTOM
        ll2.textSize = 10f

        val ll3 = LimitLine(140f, "HIGH")
        ll3.lineWidth = 3f
        ll3.lineColor=chart.context.resources.getColor(R.color.color_high_heart_rate)
       // ll3.enableDashedLine(10f, 10f, 0f)
        ll3.labelPosition = LimitLabelPosition.RIGHT_BOTTOM
        ll3.textSize = 10f
        yAxis.addLimitLine(ll1)
        yAxis.addLimitLine(ll2)
        yAxis.addLimitLine(ll3)
        yAxis.setDrawLimitLinesBehindData(true)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
    }

    override fun onNothingSelected() {
    }


}
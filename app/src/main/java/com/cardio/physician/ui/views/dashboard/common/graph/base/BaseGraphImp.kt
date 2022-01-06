package com.cardio.physician.ui.views.dashboard.common.graph.base

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentDashboardBinding


abstract  class BaseGraphImp : BaseGraph {

    private var binding: FragmentDashboardBinding? = null
    internal var typefaceBold: Typeface? = null
    var parentLayout: ViewGroup? = null

    override fun addGraphToParent(parentLayout: ViewGroup, binding: FragmentDashboardBinding) {
        this.parentLayout= parentLayout
        this.binding=binding
        typefaceBold= ResourcesCompat.getFont(parentLayout.context, R.font.nunito_bold)
        val layoutParams = ConstraintLayout.LayoutParams(0,
            parentLayout.context.resources.getDimension(
                R.dimen.graph_height).toInt())
        var graph=getGraphView(parentLayout.context)
        var graphLabel= parentLayout.findViewById<View>(R.id.tvGraphLabel)
        var xAxisLabel= parentLayout.findViewById<View>(R.id.tvXaxisTitle)
       // graph.setBackgroundColor(parentLayout.context.resources.getColor(R.color.black))
        graph.layoutParams=layoutParams
        parentLayout.addView(graph, 0)
        val set = ConstraintSet()
        graph.setId(View.generateViewId())
        set.clone(parentLayout as ConstraintLayout)
        set.constrainDefaultHeight(graph.id, ConstraintSet.MATCH_CONSTRAINT_WRAP)
        set.constrainDefaultWidth(graph.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
        var margin8dp= parentLayout.context.resources.getDimension(R.dimen.margin_graph).toInt()
        var margin12dp= parentLayout.context.resources.getDimension(R.dimen._10sdp).toInt()
        var margin16dp= parentLayout.context.resources.getDimension(R.dimen._16sdp).toInt()
        var margin18dp= parentLayout.context.resources.getDimension(R.dimen._18sdp).toInt()
        set.connect(graph.getId(),
            ConstraintSet.TOP,
            graphLabel.id,
            ConstraintSet.BOTTOM,
            margin8dp)
        set.connect(graph.getId(),
            ConstraintSet.START,
            parentLayout.id,
            ConstraintSet.START,
            margin18dp)
        set.connect(graph.getId(), ConstraintSet.END, parentLayout.id, ConstraintSet.END, margin8dp)
        set.connect(graph.getId(),
            ConstraintSet.BOTTOM,
            parentLayout.id,
            ConstraintSet.BOTTOM,
            margin16dp)
        set.applyTo(parentLayout)
        this.parentLayout?.visibility=View.GONE
    }

    override fun invisibleWhileRefreshing() {
        if(parentLayout?.visibility!=View.GONE){
            parentLayout?.visibility=View.INVISIBLE
        }
    }

    abstract fun getGraphView(context: Context): View

    fun setAverageValue(avg: String){
        parentLayout?.findViewById<TextView>(R.id.tvAvgValue)?.let {
            it.setText(avg)
        }
    }
    //method is used of top bp average value
    fun setAverageValue2(avg: String){
        parentLayout?.findViewById<TextView>(R.id.tvAvgValue2)?.let {
            it.setText(avg)
        }
    }

    fun showGraphFilters(){
        //set visiblity of hemodynaic labels and filter
        binding?.tvHemoDynamicData?.visibility = View.VISIBLE
        binding?.ivEditGraphHeathlogs?.visibility=View.VISIBLE
        binding?.includeFilterDash?.cvFilterContainer?.visibility = View.VISIBLE
    }
}
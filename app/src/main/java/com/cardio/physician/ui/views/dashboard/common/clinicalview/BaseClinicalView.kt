package com.cardio.physician.ui.views.dashboard.common.clinicalview

import android.content.Context
import android.view.View
import com.cardio.physician.databinding.FragmentDashboardBinding

open class BaseClinicalView {

    var context: Context?=null

    open fun manageVisibilityOfViews(binding: FragmentDashboardBinding){
        //make basic info visible when diagnosis is there
        context=binding.includeBasicInfo.cvPatienDetail.context
        binding.includeBasicInfo.cvPatienDetail.visibility = View.VISIBLE
        binding.tvNoDiagnosis.visibility= View.GONE
        binding.tvAddDiagnosis.visibility= View.GONE
        binding.tvLabeldisease.visibility = View.VISIBLE
        binding.tvMyReportlabel.visibility=View.VISIBLE
        binding.tvReportDateDash.visibility=View.VISIBLE
    }
}
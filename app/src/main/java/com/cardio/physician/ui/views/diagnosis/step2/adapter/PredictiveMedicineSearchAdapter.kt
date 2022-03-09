package com.cardio.physician.ui.views.diagnosis.step2.adapter

import android.content.Context
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.cardio.physician.R
import com.cardio.physician.domain.diagnosis.MedicineModel


class PredictiveMedicineSearchAdapter(
    private val mContext: Context,
    private val itemLayout: Int,
    private var dataList: List<MedicineModel>,
    private val onMedicineSearch: (query: String) -> List<MedicineModel>,
) : ArrayAdapter<MedicineModel>(
    mContext, itemLayout, dataList
) {

    val filterSearch: Filter by lazy {
        FilterMedicine()
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): MedicineModel {
        return dataList[position]
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        if (view == null) {
            view = LayoutInflater.from(parent.context)
                .inflate(itemLayout, parent, false)
        }
        val strName = view!!.findViewById<View>(R.id.tvSearchedMed) as TextView
        strName.text = getFullMedName(position, parent.context)
        return view
    }

    private fun getFullMedName(position: Int, context: Context): CharSequence? {
        var medName = getMedName(position)
        val spanMedName = SpannableString(medName)

        val textSizeSmall: Int = context.resources.getDimension(R.dimen.text_very_small).toInt()
        var tradeName = "(${getItem(position).tradeName})"
        val spanTradeName = SpannableString(tradeName)
        spanTradeName.setSpan(
            AbsoluteSizeSpan(textSizeSmall),
            0,
            tradeName!!.length,
            SPAN_INCLUSIVE_INCLUSIVE
        )
        // let's put both spans together with a separator and all
        return TextUtils.concat(spanMedName, " ", spanTradeName)
    }

    fun getMedName(position: Int): String? {
        return getItem(position).name
    }

    override fun getFilter(): Filter {
        return FilterMedicine()
    }

    inner class FilterMedicine : Filter() {
        private var lastSearchQuery: CharSequence? = null
        private val lock = Any()
        override fun performFiltering(searchQuery: CharSequence?): FilterResults {
            val results = FilterResults()
            if (searchQuery == null || searchQuery.isEmpty() || lastSearchQuery == searchQuery) {
                synchronized(lock) {
                    results.values = ArrayList<String>()
                    results.count = 0
                }
            } else {
                val searchStrLowerCase = searchQuery.toString().toLowerCase()
                val matchValues =
                    getStringArrayFromModels(onMedicineSearch.invoke(searchStrLowerCase))
                results.values = matchValues
                results.count = matchValues.size
            }
            lastSearchQuery = searchQuery
            return results
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            dataList = if (results!!.values != null) {
                results.values as ArrayList<MedicineModel>
            } else emptyList()
            if (results.count > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }

    }

    private fun getStringArrayFromModels(list: List<MedicineModel>): List<MedicineModel> {
        var listStrings = mutableListOf<MedicineModel>()
        list.forEach {
            it.name?.let { it1 -> listStrings.add(it) }
        }
        return listStrings
    }

}
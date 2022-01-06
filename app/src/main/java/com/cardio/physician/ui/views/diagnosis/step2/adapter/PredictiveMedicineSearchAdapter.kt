package com.cardio.physician.ui.views.diagnosis.step2.adapter

import android.content.Context
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
    private var dataList: List<String>,
    private val onMedicineSearch: (query: String) -> List<MedicineModel>,
) : ArrayAdapter<String>(
    mContext, itemLayout, dataList) {

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): String {
        return dataList[position]
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        if (view == null) {
            view = LayoutInflater.from(parent.context)
                .inflate(itemLayout, parent, false)
        }
        val strName = view!!.findViewById<View>(R.id.tvSearchedMed) as TextView
        strName.text = getItem(position)
        return view
    }

    override fun getFilter(): Filter {
        return FilterMedicine()
    }

    inner class FilterMedicine : Filter() {
        private val lock = Any()
        override fun performFiltering(prefix: CharSequence?): FilterResults {
            val results = FilterResults()
            if (prefix == null || prefix.isEmpty()) {
                synchronized(lock) {
                    results.values = ArrayList<String>()
                    results.count = 0
                }
            } else {
                val searchStrLowerCase = prefix.toString().toLowerCase()
                val matchValues =
                    getStringArrayFromModels(onMedicineSearch.invoke(searchStrLowerCase))
                results.values = matchValues
                results.count = matchValues.size
        }
            return results
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            dataList = if (results!!.values != null) {
                results.values as ArrayList<String>
            } else emptyList()
            if (results.count > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }

    }

    private fun getStringArrayFromModels(list: List<MedicineModel>): List<String> {
        var listStrings = mutableListOf<String>()
        list.forEach {
            it.name?.let { it1 -> listStrings.add(it1) }
        }
        return listStrings
    }

}
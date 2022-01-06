package com.cardio.physician.ui.views.add_patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cardio.physician.R
import com.cardio.physician.databinding.ItemPatientBinding
import com.cardio.physician.domain.addpatient.PatientModel
import com.cardio.physician.domain.connection.ConnectionModel

class PatientAdapter(val onRecyclerViewItemClick : (view: View, position: Int)-> Unit?, val isFromIllness : Boolean, val emptyListView : (isEmpty: Boolean) -> Unit?) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>(), Filterable {

    val patientList : ArrayList<PatientModel> = ArrayList()
    val patientFilterList: ArrayList<PatientModel> = ArrayList()

    inner class PatientViewHolder(val binding: ItemPatientBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(){
            if(isFromIllness){
                binding.tvAdd.visibility = View.GONE
                binding.clMain.setOnClickListener {
                    onRecyclerViewItemClick.invoke(it, adapterPosition)
                }
            }else {
                binding.tvAdd.visibility = View.VISIBLE
                if (patientFilterList[adapterPosition].isAdded != 0) {
                    if(patientFilterList[adapterPosition].isAdded == 1){
                        binding.tvAdd.text = ""
                    }else{
                        binding.tvAdd.text = "Requested"
                    }
                } else {
                    binding.tvAdd.text = "Add"
                }
                binding.tvAdd.setOnClickListener {
                    if(patientFilterList[adapterPosition].isAdded == 0) {
                        patientFilterList[adapterPosition].isAdded = 2
                        notifyItemChanged(adapterPosition)
                        onRecyclerViewItemClick.invoke(it, adapterPosition)
                    }
                }
            }
            Glide.with(binding.root.context).load(patientFilterList[adapterPosition].imageUrl).error(R.drawable.drawable_user_placeholder).placeholder(R.drawable.drawable_user_placeholder).circleCrop().into(binding.ivUserImage)
            binding.tvUserName.text = "${patientFilterList[adapterPosition].firstName} ${patientFilterList[adapterPosition].lastName}"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = ItemPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return patientFilterList.size
    }

    fun updateData(updatedList: List<PatientModel>?){
        patientList.clear()
        patientFilterList.clear()
        updatedList?.let {
            patientList.addAll(it)
            patientFilterList.addAll(it)
        }

        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val search = p0.toString()
                val filterableList : ArrayList<PatientModel> = ArrayList()
                if(search.isEmpty()){
                    filterableList.addAll(patientList)
                }else{
                    for (connection in patientList){
                        if((connection.firstName?.lowercase() + " " + connection.lastName?.lowercase()).contains(search.lowercase())){
                            filterableList.add(connection)
                        }
                    }
                    patientFilterList.addAll(filterableList)
                }
                val filterResults = FilterResults()
                filterResults.values = filterableList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                patientFilterList.clear()
                patientFilterList.addAll(p1?.values as ArrayList<PatientModel>)
                emptyListView.invoke(patientFilterList.isEmpty())
                notifyDataSetChanged()
            }
        }
    }
}
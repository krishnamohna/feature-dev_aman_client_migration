package com.cardio.physician.ui.views.diagnosis.step2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cardio.physician.R
import com.cardio.physician.databinding.ItemMedicineAddedBinding
import com.cardio.physician.domain.diagnosis.MedicineModel

class MedRecylarAdapter : RecyclerView.Adapter<MedRecylarAdapter.MedViewHolder>() {

    var listMeds = mutableListOf<MedicineModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedViewHolder {
        return MedViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_medicine_added, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MedViewHolder, position: Int) {
        holder.bind(listMeds.get(position))
    }

    override fun getItemCount(): Int {
        return listMeds.size
    }

    fun addMed(it: MedicineModel) {
        listMeds.add(it)
        notifyItemInserted(listMeds.size - 1)
    }

    fun isMedSearchExist(searchedMed: String): Boolean {
        return listMeds.find {
            (it.searchedMed?:"").equals(searchedMed,true)
        } != null
    }

    fun isMedExist(medName: String): Boolean {
        return listMeds.find {
            it.name!!.equals(medName,true)
        } != null
    }

    private fun removeItem(adapterPosition: Int) {
        listMeds.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    inner class MedViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemMedicineAddedBinding.bind(itemView)
        fun bind(model: MedicineModel) {
            model.name?.let { binding.tvMed.setText(it) }
            binding.ivRemoveMed.setOnClickListener { removeItem(adapterPosition) }
        }

    }
}
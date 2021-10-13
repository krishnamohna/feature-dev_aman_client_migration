package com.cardio.doctor.ui.views.diagnosis.step2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cardio.doctor.R
import com.cardio.doctor.databinding.ItemMedicineSearchBinding
import com.cardio.doctor.domain.diagnosis.MedicineModel

class MedRecylarAdapter : RecyclerView.Adapter<MedRecylarAdapter.MedViewHolder>() {

    var listMeds = mutableListOf<MedicineModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedViewHolder {
        return MedViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_medicine_search, parent, false)
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

    private fun removeItem(adapterPosition: Int) {
        listMeds.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    inner class MedViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemMedicineSearchBinding.bind(itemView)
        fun bind(model: MedicineModel) {
            model.drugName?.let { binding.tvMed.setText(it) }
            binding.ivRemoveMed.setOnClickListener { removeItem(adapterPosition) }
        }

    }
}
package com.cardio.doctor.ui.views.diagnosis.step2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cardio.physician.R
import com.cardio.physician.databinding.ItemMedicineAddedBinding
import com.cardio.physician.domain.diagnosis.MedicineModel

class MedRecylarAdapter constructor(private val onItemClick: (MedicineModel) -> Unit) :
    RecyclerView.Adapter<MedRecylarAdapter.MedViewHolder>() {

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
            (it.searchedMed ?: "").equals(searchedMed, true)
        } != null
    }

    fun isMedExist(medName: String): Boolean {
        return listMeds.find {
            it.name!!.equals(medName, true)
        } != null
    }

    private fun removeItem(adapterPosition: Int) {
        listMeds.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    fun updateMedicine(it: MedicineModel) {
        indexOf(it)?.let { index ->
            listMeds[index] = it
            notifyItemChanged(index)
        }
    }

    private fun indexOf(it: MedicineModel): Int? {
        listMeds.forEachIndexed { index, medicineModel ->
            if (medicineModel.name == it.name)
                return index
        }
        return null
    }

    inner class MedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemMedicineAddedBinding.bind(itemView)
        fun bind(model: MedicineModel) {
            bindMedicineData(binding,model,itemView.context)
            binding.ivRemoveMed.setOnClickListener { removeItem(adapterPosition) }
            binding.materialCardViewMedicine.setOnClickListener {
                onItemClick.invoke(model)
            }
        }
    }
}

fun bindMedicineData(binding: ItemMedicineAddedBinding, model: MedicineModel, context: Context) {
    binding.tvMedFrequency.visibility = View.GONE
    binding.tvMedDosage.visibility = View.GONE
    model.name?.let { binding.tvMed.text = it }
    model.dosage?.let {
        binding.tvMedDosage.visibility = View.VISIBLE
        binding.tvMedDosage.text = "\u2022 $it ${context.getString(R.string.mg)} ${context.getString(R.string.tablet)}"
    }
    model.noOfTime?.let {
        binding.tvMedFrequency.visibility = View.VISIBLE
        model.noOfTablets?.let { noOfTablets ->
            binding.tvMedFrequency.text = "\u2022 ${getNoOfTabletTexts(noOfTablets,context)}, $it ${model.perDayOrWeek}"
        }
    }
}

fun getNoOfTabletTexts(tablets: String, context: Context): String {
    return try {
        if (tablets.toInt() > 1) {
            "$tablets ${context.getString(R.string.tablets)}"
        } else {
            "$tablets ${context.getString(R.string.tablet)}"
        }
    } catch (exp: Exception) {
        tablets
    }
}
package com.cardio.physician.ui.views.add_patient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cardio.physician.R
import com.cardio.physician.databinding.ItemPatientBinding
import com.cardio.physician.domain.addpatient.PatientModel

class PatientAdapter(val onRecyclerViewItemClick : (view: View, position: Int)-> Unit?, val isFromIllness : Boolean) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    val patientList : ArrayList<PatientModel> = ArrayList()

    inner class PatientViewHolder(val binding: ItemPatientBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(){
            if(isFromIllness){
                binding.tvAdd.visibility = View.GONE
                binding.clMain.setOnClickListener {
                    onRecyclerViewItemClick.invoke(it, adapterPosition)
                }
            }else {
                binding.tvAdd.visibility = View.VISIBLE
                if (patientList[adapterPosition].isAdded != 0) {
                    if(patientList[adapterPosition].isAdded == 1){
                        binding.tvAdd.text = ""
                    }else{
                        binding.tvAdd.text = "Requested"
                    }
                } else {
                    binding.tvAdd.text = "Add"
                }
                binding.tvAdd.setOnClickListener {
                    if(patientList[adapterPosition].isAdded == 0) {
                        patientList[adapterPosition].isAdded = 2
                        notifyItemChanged(adapterPosition)
                        onRecyclerViewItemClick.invoke(it, adapterPosition)
                    }
                }
            }
            Glide.with(binding.root.context).load(patientList[adapterPosition].imageUrl).error(R.drawable.drawable_user_placeholder).placeholder(R.drawable.drawable_user_placeholder).circleCrop().into(binding.ivUserImage)
            binding.tvUserName.text = "${patientList[adapterPosition].firstName} ${patientList[adapterPosition].lastName}"

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
        return patientList.size
    }

    fun updateData(updatedList: List<PatientModel>?){
        patientList.clear()
        updatedList?.let { patientList.addAll(it) }
        notifyDataSetChanged()
    }
}
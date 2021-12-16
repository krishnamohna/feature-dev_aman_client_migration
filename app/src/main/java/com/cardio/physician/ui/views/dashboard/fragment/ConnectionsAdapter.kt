package com.cardio.physician.ui.views.dashboard.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cardio.physician.R
import com.cardio.physician.databinding.ItemConnectionBinding
import com.cardio.physician.domain.addpatient.PatientModel
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.ui.common.utils.getCurrentDate
import com.cardio.physician.ui.common.utils.getDefaultDateFormatter
import java.util.*
import kotlin.collections.ArrayList

class ConnectionsAdapter(val recyclerViewListener : (view: View, position: Int)-> Unit?) :
    RecyclerView.Adapter<ConnectionsAdapter.ConnectionsViewHolder>(), Filterable {

    val connectionsList : ArrayList<ConnectionModel> = ArrayList()
    val connectionsListFiltered : ArrayList<ConnectionModel> = ArrayList()

    inner class ConnectionsViewHolder(val binding: ItemConnectionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            Glide.with(binding.root.context).load(connectionsListFiltered[adapterPosition].imageUrl).error(R.drawable.drawable_user_placeholder).placeholder(
                R.drawable.drawable_user_placeholder).circleCrop().into(binding.ivUserImage)
            binding.tvUserName.text = connectionsListFiltered[adapterPosition].firstName + " " + connectionsListFiltered[adapterPosition].lastName
            val date = Date()
            date.time = connectionsListFiltered[adapterPosition].timestamp!!
            binding.tvConnectionDate.text = getDefaultDateFormatter().format(date)
            binding.root.setOnClickListener {
                recyclerViewListener.invoke(binding.root, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionsViewHolder {
        val binding = ItemConnectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConnectionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConnectionsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return connectionsListFiltered.size
    }

    fun updateConnectionList(list: List<ConnectionModel>){
        connectionsList.addAll(list)
        connectionsListFiltered.addAll(connectionsList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val search = p0.toString()
                val filterableList : ArrayList<ConnectionModel> = ArrayList()
                if(search.isEmpty()){
                    filterableList.addAll(connectionsList)
                }else{
                    for (connection in connectionsList){
                        if((connection.firstName?.lowercase() + " " + connection.lastName?.lowercase()).contains(search.lowercase())){
                            filterableList.add(connection)
                        }
                    }
                    connectionsListFiltered.addAll(filterableList)
                }
                val filterResults = FilterResults()
                filterResults.values = filterableList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                connectionsListFiltered.clear()
                connectionsListFiltered.addAll(p1?.values as ArrayList<ConnectionModel>)
                notifyDataSetChanged()
            }
        }
    }
}
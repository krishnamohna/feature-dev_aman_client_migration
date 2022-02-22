package com.cardio.physician.ui.views.notifications.adapter

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import com.cardio.physician.databinding.ItemNotificationLayoutBinding
import com.cardio.physician.domain.notifications.NotificationModel
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.extentions.loadImage
import com.cardio.physician.ui.common.utils.formatTimeStampToTimePassed

class NotificationAdapter constructor(private val action: (AdapterAction, NotificationModel) -> Unit) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    enum class AdapterAction {
        ACTION_ACCEPT_REQUEST,
        ACTION_REJECT_REQUEST,
        ACTION_ITEM_CLICK,
    }

    val listNotifications = mutableListOf<NotificationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            ItemNotificationLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ).root
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return listNotifications.size
    }

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemNotificationLayoutBinding.bind(itemView)

        fun bind(notificationModel: NotificationModel) {
            //manage view visibility as per notification type
            // managerViewVisibility(notificationModel)
            binding.tvNotiUseerName.text =
                getMessage(notificationModel)
            if (!notificationModel.imageUrl.isNullOrBlank()) {
                binding.imgProfilePicToolbar.loadImage(notificationModel.imageUrl)
            }
            binding.tvNotiDate.text = formatTimeStampToTimePassed(notificationModel.timeStamp)
            //set listeners
            binding.ivNotiAcceptRequest.setOnClickListener {
                action.invoke(
                    AdapterAction.ACTION_ACCEPT_REQUEST,
                    notificationModel
                )
            }
            binding.ivNotiRejectRequest.setOnClickListener {
                action.invoke(
                    AdapterAction.ACTION_REJECT_REQUEST,
                    notificationModel
                )
            }
            binding.mcvRoot.setOnClickListener {
                action.invoke(
                    AdapterAction.ACTION_ITEM_CLICK,
                    notificationModel
                )
            }
        }

        private fun managerViewVisibility(notificationModel: NotificationModel) {
            notificationModel.let {
                if (it.type == FireStoreDocKey.NOTIFICATION_TYPE_REQUEST && notificationModel.requestStatus == null) {
                    binding.ivNotiAcceptRequest.visibility = View.GONE
                    binding.ivNotiRejectRequest.visibility = View.GONE
                } else {
                    binding.ivNotiAcceptRequest.visibility = View.GONE
                    binding.ivNotiRejectRequest.visibility = View.GONE
                }
            }
        }


        private fun getMessage(notificationModel: NotificationModel): SpannableStringBuilder {
            val message = when {
                notificationModel.type == FireStoreDocKey.NOTIFICATION_TYPE_REQUEST && notificationModel.requestStatus == true -> {
                    "has accepted your request."
                }
                /*notificationModel.type == FireStoreDocKey.NOTIFICATION_TYPE_REQUEST && notificationModel.requestStatus == false -> {
                    "is rejected by you."
                }
                notificationModel.type == FireStoreDocKey.NOTIFICATION_TYPE_REQUEST && notificationModel.requestStatus == null -> {
                    "has sent a request to add you as patient."
                }*/
                notificationModel.type == FireStoreDocKey.NOTIFICATION_TYPE_ADD_DIAGNOSIS -> {
                    "has added a diagnosis for you."
                }
                notificationModel.type == FireStoreDocKey.NOTIFICATION_TYPE_EDIT_DIAGNOSIS -> {
                    "has edited a diagnosis for you."
                }
                else ->
                    "Incompatible message type"
            }
            return if (notificationModel.getFullName().trim().isNotEmpty()) {
                SpannableStringBuilder().bold { append("Patient ${notificationModel.getFullName()}") }
                    .append(" ")
                    .append(message)
            } else {
                SpannableStringBuilder().bold { append("Patient ${notificationModel.email}") }
                    .append(" ")
                    .append(message)
            }
        }
    }

    fun getItem(position: Int): NotificationModel? {
        return listNotifications.get(position)
    }

    fun setData(it: List<NotificationModel>) {
        listNotifications.addAll(it)
        notifyDataSetChanged()
    }

    fun updateItem(notificationModel: NotificationModel) {
        listNotifications.find {
            it.documentId == notificationModel.documentId
        }?.also {
            it.requestStatus = notificationModel.requestStatus
            notifyItemChanged(listNotifications.indexOf(it))
        }
    }

}
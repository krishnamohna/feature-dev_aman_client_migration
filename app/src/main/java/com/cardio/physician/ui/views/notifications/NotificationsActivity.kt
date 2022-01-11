package com.cardio.physician.ui.views.notifications

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cardio.physician.databinding.ActivityNotificationsBinding
import com.cardio.physician.domain.notifications.NotificationModel
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity
import com.cardio.physician.ui.common.customviews.toolbar.NotificationsActivityToolbarImp
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.utils.BroadCastAction.ACTION_NOTIFICATION_UPDATE
import com.cardio.physician.ui.common.utils.PaginationListener
import com.cardio.physician.ui.common.utils.extentions.customObserver
import com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsActivity : BaseToolbarActivity() {

    companion object {
        fun start(activity: Activity) {
            Intent(activity, NotificationsActivity::class.java).run {
                activity.startActivity(this)
            }
        }
    }

    private val binding by viewBinding(ActivityNotificationsBinding::inflate)
    private val viewModel: NotificatonsViewModel by viewModels()
    private var isLastPage = false
    private var isLoading = false
    private var scrollListener: ScrollListener? = null
    private val adapterNotification: NotificationAdapter by lazy {
        NotificationAdapter { action, model ->
            onAdapterAction(action, model)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(notificationUpdateReciever)
    }

    private fun onAdapterAction(
        adapterAction: NotificationAdapter.AdapterAction,
        model: NotificationModel,
    ) {
        when (adapterAction) {
            NotificationAdapter.AdapterAction.ACTION_ACCEPT_REQUEST -> {
                viewModel.acceptRequest(model.userId, model.documentId, model)
            }
            NotificationAdapter.AdapterAction.ACTION_REJECT_REQUEST -> {
                viewModel.rejectRequest(model.userId, model.documentId, model)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViews()
        setObservers()
        setListener()
        init()
    }

    private fun setListener() {
        binding.swipeRefreshLayoutNotifications.setOnRefreshListener {
            binding.swipeRefreshLayoutNotifications.isRefreshing = false
            refresh()
        }
    }

    private fun setObservers() {
        val intentFilter = IntentFilter(ACTION_NOTIFICATION_UPDATE)
        registerReceiver(notificationUpdateReciever, intentFilter)
        viewModel.getLiveDataNotifications().customObserver(
            this,
            onLoading = {
                showProgress(it)
                isLoading = it
            },
            onSuccess = {
                it?.let {
                    adapterNotification.setData(it)
                    manageErrorViewVisibility()
                }
            },
            onError = { msg, exp ->
                //  onError(msg,exp)
                isLastPage = true
                manageErrorViewVisibility()
            }
        )
        viewModel.getLiveDataRequestAction().customObserver(
            this,
            onLoading = ::showProgress,
            onSuccess = {
                it?.let {
                    adapterNotification.updateItem(it)
                }
            },
            onError = ::onError
        )
    }

    private fun manageErrorViewVisibility() {
        if (adapterNotification.itemCount > 0) {
            binding.tvNoNotificationFound.visibility = View.GONE
        } else {
            binding.tvNoNotificationFound.visibility = View.VISIBLE
        }
    }

    private fun setViews() {
        binding.rvNotifications.layoutManager = LinearLayoutManager(this)
        binding.rvNotifications.adapter = adapterNotification
        addScrollListenerToRecyclerView()
    }

    private fun addScrollListenerToRecyclerView() {
        scrollListener?.let { binding.rvNotifications.removeOnScrollListener(it) }
        scrollListener = ScrollListener()
        binding.rvNotifications.addOnScrollListener(scrollListener!!)
    }

    private fun init() {
        loadNotifications()
    }

    private fun loadNotifications() {
        viewModel.getNotifications(adapterNotification?.listNotifications.lastOrNull())
    }

    override fun getToolbarImp(): IToolbar {
        return NotificationsActivityToolbarImp(binding.headerView.toolBarContainer).registerBackButtonListener {
            onBackPressed()
        }
    }

    private fun refresh() {
        binding.tvNoNotificationFound.visibility = View.GONE
        adapterNotification.listNotifications.clear()
        adapterNotification.notifyDataSetChanged()
        isLastPage = false
        init()
    }

    inner class ScrollListener :
        PaginationListener(binding.rvNotifications.layoutManager as LinearLayoutManager) {
        override fun loadMoreItems() {
            loadNotifications()
        }

        override fun isLastPage(): Boolean = isLastPage
        override fun isLoading(): Boolean = isLoading
    }

    private val notificationUpdateReciever: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            runOnUiThread {
                refresh()
            }
        }
    }
}
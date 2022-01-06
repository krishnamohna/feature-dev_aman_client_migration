package com.cardio.physician.ui.views.notifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cardio.physician.databinding.ActivityNotificationsBinding;
import com.cardio.physician.domain.notifications.NotificationModel;
import com.cardio.physician.ui.common.base.activity.BaseToolbarActivity;
import com.cardio.physician.ui.common.customviews.toolbar.NotificationsActivityToolbarImp;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.utils.PaginationListener;
import com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u0018\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0012\u0010 \u001a\u00020\u00192\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u0019H\u0014J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\b\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\'"}, d2 = {"Lcom/cardio/physician/ui/views/notifications/NotificationsActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseToolbarActivity;", "()V", "adapterNotification", "Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter;", "getAdapterNotification", "()Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter;", "adapterNotification$delegate", "Lkotlin/Lazy;", "binding", "Lcom/cardio/physician/databinding/ActivityNotificationsBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityNotificationsBinding;", "binding$delegate", "isLastPage", "", "isLoading", "viewModel", "Lcom/cardio/physician/ui/views/notifications/NotificatonsViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/notifications/NotificatonsViewModel;", "viewModel$delegate", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "init", "", "loadNotifications", "onAdapterAction", "adapterAction", "Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter$AdapterAction;", "model", "Lcom/cardio/physician/domain/notifications/NotificationModel;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "setObservers", "setViews", "Companion", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class NotificationsActivity extends com.cardio.physician.ui.common.base.activity.BaseToolbarActivity {
    @org.jetbrains.annotations.NotNull()
    public static final com.cardio.physician.ui.views.notifications.NotificationsActivity.Companion Companion = null;
    private final kotlin.Lazy binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private final kotlin.Lazy adapterNotification$delegate = null;
    
    public NotificationsActivity() {
        super();
    }
    
    private final com.cardio.physician.databinding.ActivityNotificationsBinding getBinding() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.notifications.NotificatonsViewModel getViewModel() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter getAdapterNotification() {
        return null;
    }
    
    private final void onAdapterAction(com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter.AdapterAction adapterAction, com.cardio.physician.domain.notifications.NotificationModel model) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    private final void setObservers() {
    }
    
    private final void setViews() {
    }
    
    private final void init() {
    }
    
    private final void loadNotifications() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/cardio/physician/ui/views/notifications/NotificationsActivity$Companion;", "", "()V", "start", "", "activity", "Landroid/app/Activity;", "app_qaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity) {
        }
    }
}
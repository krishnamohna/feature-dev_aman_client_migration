package com.cardio.physician.ui.views.notifications.adapter;

import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.cardio.physician.databinding.ItemNotificationLayoutBinding;
import com.cardio.physician.domain.notifications.NotificationModel;
import com.cardio.physician.ui.common.utils.FireStoreDocKey;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u001c\u001dB\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u001c\u0010\u0011\u001a\u00020\u00072\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0016J\u0014\u0010\u0017\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0019J\u000e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0006R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001e"}, d2 = {"Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter$NotificationViewHolder;", "action", "Lkotlin/Function2;", "Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter$AdapterAction;", "Lcom/cardio/physician/domain/notifications/NotificationModel;", "", "(Lkotlin/jvm/functions/Function2;)V", "listNotifications", "", "getListNotifications", "()Ljava/util/List;", "getItem", "position", "", "getItemCount", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "it", "", "updateItem", "notificationModel", "AdapterAction", "NotificationViewHolder", "app_qaDebug"})
public final class NotificationAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter.NotificationViewHolder> {
    private final kotlin.jvm.functions.Function2<com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter.AdapterAction, com.cardio.physician.domain.notifications.NotificationModel, kotlin.Unit> action = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.cardio.physician.domain.notifications.NotificationModel> listNotifications = null;
    
    public NotificationAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter.AdapterAction, ? super com.cardio.physician.domain.notifications.NotificationModel, kotlin.Unit> action) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.cardio.physician.domain.notifications.NotificationModel> getListNotifications() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter.NotificationViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.ui.views.notifications.adapter.NotificationAdapter.NotificationViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.cardio.physician.domain.notifications.NotificationModel getItem(int position) {
        return null;
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.cardio.physician.domain.notifications.NotificationModel> it) {
    }
    
    public final void updateItem(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationModel notificationModel) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter$AdapterAction;", "", "(Ljava/lang/String;I)V", "ACTION_ACCEPT_REQUEST", "ACTION_REJECT_REQUEST", "ACTION_ITEM_CLICK", "app_qaDebug"})
    public static enum AdapterAction {
        /*public static final*/ ACTION_ACCEPT_REQUEST /* = new ACTION_ACCEPT_REQUEST() */,
        /*public static final*/ ACTION_REJECT_REQUEST /* = new ACTION_REJECT_REQUEST() */,
        /*public static final*/ ACTION_ITEM_CLICK /* = new ACTION_ITEM_CLICK() */;
        
        AdapterAction() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u0012"}, d2 = {"Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter$NotificationViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/cardio/physician/ui/views/notifications/adapter/NotificationAdapter;Landroid/view/View;)V", "binding", "Lcom/cardio/physician/databinding/ItemNotificationLayoutBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ItemNotificationLayoutBinding;", "setBinding", "(Lcom/cardio/physician/databinding/ItemNotificationLayoutBinding;)V", "bind", "", "notificationModel", "Lcom/cardio/physician/domain/notifications/NotificationModel;", "getMessage", "Landroid/text/SpannableStringBuilder;", "managerViewVisibility", "app_qaDebug"})
    public final class NotificationViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private com.cardio.physician.databinding.ItemNotificationLayoutBinding binding;
        
        public NotificationViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.cardio.physician.databinding.ItemNotificationLayoutBinding getBinding() {
            return null;
        }
        
        public final void setBinding(@org.jetbrains.annotations.NotNull()
        com.cardio.physician.databinding.ItemNotificationLayoutBinding p0) {
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.cardio.physician.domain.notifications.NotificationModel notificationModel) {
        }
        
        private final void managerViewVisibility(com.cardio.physician.domain.notifications.NotificationModel notificationModel) {
        }
        
        private final android.text.SpannableStringBuilder getMessage(com.cardio.physician.domain.notifications.NotificationModel notificationModel) {
            return null;
        }
    }
}
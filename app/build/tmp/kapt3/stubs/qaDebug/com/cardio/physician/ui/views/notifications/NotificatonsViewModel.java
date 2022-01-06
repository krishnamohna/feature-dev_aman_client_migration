package com.cardio.physician.ui.views.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.cardio.physician.domain.notifications.NotificationModel;
import com.cardio.physician.domain.notifications.NotificationRepo;
import com.cardio.physician.network.Resource;
import com.cardio.physician.ui.common.base.viewmodel.BaseViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\tJ\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u0012J\u0012\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00070\u0012J\u0010\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\tJ\u001e\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\tR \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/cardio/physician/ui/views/notifications/NotificatonsViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseViewModel;", "notificationRepo", "Lcom/cardio/physician/domain/notifications/NotificationRepo;", "(Lcom/cardio/physician/domain/notifications/NotificationRepo;)V", "mutalbleLiveDataNotifications", "Landroidx/lifecycle/MutableLiveData;", "Lcom/cardio/physician/network/Resource;", "", "Lcom/cardio/physician/domain/notifications/NotificationModel;", "mutalbleRequestAction", "acceptRequest", "", "senderId", "", "documentId", "notificationModel", "getLiveDataNotifications", "Landroidx/lifecycle/LiveData;", "getLiveDataRequestAction", "getNotifications", "lastNotificationModel", "rejectRequest", "app_qaDebug"})
public final class NotificatonsViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseViewModel {
    private final com.cardio.physician.domain.notifications.NotificationRepo notificationRepo = null;
    private final androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.notifications.NotificationModel>>> mutalbleLiveDataNotifications = null;
    private final androidx.lifecycle.MutableLiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.notifications.NotificationModel>> mutalbleRequestAction = null;
    
    @javax.inject.Inject()
    public NotificatonsViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationRepo notificationRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.util.List<com.cardio.physician.domain.notifications.NotificationModel>>> getLiveDataNotifications() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.cardio.physician.domain.notifications.NotificationModel>> getLiveDataRequestAction() {
        return null;
    }
    
    public final void getNotifications(@org.jetbrains.annotations.Nullable()
    com.cardio.physician.domain.notifications.NotificationModel lastNotificationModel) {
    }
    
    public final void acceptRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String senderId, @org.jetbrains.annotations.NotNull()
    java.lang.String documentId, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationModel notificationModel) {
    }
    
    public final void rejectRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String senderId, @org.jetbrains.annotations.NotNull()
    java.lang.String documentId, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.domain.notifications.NotificationModel notificationModel) {
    }
}
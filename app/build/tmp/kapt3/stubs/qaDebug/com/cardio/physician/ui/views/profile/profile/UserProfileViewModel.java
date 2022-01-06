package com.cardio.physician.ui.views.profile.profile;

import android.app.Application;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.network.NetworkHelper;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.utils.ENUM;
import com.cardio.physician.ui.common.utils.Preference;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.google.firebase.firestore.DocumentSnapshot;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Suppress(names = {"UNUSED_PARAMETER"})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u001fJ\u0006\u0010!\u001a\u00020\u001dR\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006\""}, d2 = {"Lcom/cardio/physician/ui/views/profile/profile/UserProfileViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "repository", "Lcom/cardio/physician/data/remote/profile/UserProfileRepository;", "application", "Landroid/app/Application;", "networkHelper", "Lcom/cardio/physician/network/NetworkHelper;", "(Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/data/remote/profile/UserProfileRepository;Landroid/app/Application;Lcom/cardio/physician/network/NetworkHelper;)V", "_getUserProfilePic", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "Landroid/net/Uri;", "_userDetailDocument", "Lcom/google/firebase/firestore/DocumentSnapshot;", "getUserProfilePic", "Landroidx/lifecycle/LiveData;", "getGetUserProfilePic", "()Landroidx/lifecycle/LiveData;", "userDetailDocument", "getUserDetailDocument", "validationChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "getValidationChannel", "()Lkotlinx/coroutines/channels/Channel;", "getImageDownloadUrl", "", "url", "", "getSelectedHealthKit", "getUserDetail", "app_qaDebug"})
public final class UserProfileViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel {
    private final com.cardio.physician.data.remote.profile.UserProfileRepository repository = null;
    private final com.cardio.physician.network.NetworkHelper networkHelper = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.google.firebase.firestore.DocumentSnapshot>> _userDetailDocument = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.firestore.DocumentSnapshot>> userDetailDocument = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<android.net.Uri>> _getUserProfilePic = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<android.net.Uri>> getUserProfilePic = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> validationChannel = null;
    
    @javax.inject.Inject()
    public UserProfileViewModel(@org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.local.UserManager userManager, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.data.remote.profile.UserProfileRepository repository, @org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.cardio.physician.network.NetworkHelper networkHelper) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.firestore.DocumentSnapshot>> getUserDetailDocument() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<android.net.Uri>> getGetUserProfilePic() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> getValidationChannel() {
        return null;
    }
    
    public final void getUserDetail() {
    }
    
    public final void getImageDownloadUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedHealthKit() {
        return null;
    }
}
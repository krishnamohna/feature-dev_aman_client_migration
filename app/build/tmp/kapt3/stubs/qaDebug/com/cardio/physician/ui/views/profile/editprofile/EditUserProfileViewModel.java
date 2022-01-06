package com.cardio.physician.ui.views.profile.editprofile;

import android.app.Application;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import androidx.lifecycle.LiveData;
import com.cardio.physician.R;
import com.cardio.physician.data.local.UserManager;
import com.cardio.physician.data.remote.profile.UserProfileRepository;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.domain.user.SignUpUserType;
import com.cardio.physician.network.NetworkHelper;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.AppCardioPatient;
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent;
import com.google.firebase.firestore.DocumentSnapshot;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Suppress(names = {"UNUSED_PARAMETER"})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u000eJ\u0006\u00102\u001a\u00020\u000eJ\u0006\u00103\u001a\u000200J\r\u00104\u001a\u0004\u0018\u000105\u00a2\u0006\u0002\u00106J1\u00107\u001a\u0002002\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010>J\u0006\u0010?\u001a\u000200J.\u0010@\u001a\u0002002\f\u0010A\u001a\b\u0012\u0002\b\u0003\u0018\u00010B2\b\u0010C\u001a\u0004\u0018\u00010D2\u0006\u0010E\u001a\u00020<2\u0006\u0010F\u001a\u00020GJ\u0006\u0010H\u001a\u000200J\u0014\u0010I\u001a\u0002002\n\u0010J\u001a\u00060Kj\u0002`LH\u0002Jd\u0010M\u001a\u0002002\u0006\u0010N\u001a\u00020\u000e2\u0006\u0010O\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\u000e2\u0006\u0010U\u001a\u00020\u000e2\b\u0010V\u001a\u0004\u0018\u00010\u00102\b\u0010W\u001a\u0004\u0018\u00010\u000e2\b\u0010X\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010Y\u001a\u0002002\b\u0010Z\u001a\u0004\u0018\u00010\u00102\u0006\u0010W\u001a\u00020\u000eJ\u0092\u0001\u0010[\u001a\u0002002\u0006\u0010N\u001a\u00020\u000e2\u0006\u0010O\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\u000e2\u0006\u0010U\u001a\u00020\u000e2\b\u0010V\u001a\u0004\u0018\u00010\u00102\b\u0010W\u001a\u0004\u0018\u00010\u000e2\b\u0010X\u001a\u0004\u0018\u00010\u000e2!\u0010\\\u001a\u001d\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(`\u0012\u0004\u0012\u0002000]H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010aR\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\r0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0019R\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0019R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006b"}, d2 = {"Lcom/cardio/physician/ui/views/profile/editprofile/EditUserProfileViewModel;", "Lcom/cardio/physician/ui/common/base/viewmodel/BaseAuthViewModel;", "userManager", "Lcom/cardio/physician/data/local/UserManager;", "repository", "Lcom/cardio/physician/data/remote/profile/UserProfileRepository;", "application", "Landroid/app/Application;", "networkHelper", "Lcom/cardio/physician/network/NetworkHelper;", "(Lcom/cardio/physician/data/local/UserManager;Lcom/cardio/physician/data/remote/profile/UserProfileRepository;Landroid/app/Application;Lcom/cardio/physician/network/NetworkHelper;)V", "_editProfileResponse", "Lcom/cardio/physician/ui/common/utils/livedata/SingleLiveEvent;", "Lcom/cardio/physician/network/Resource;", "", "_getUserProfilePic", "Landroid/net/Uri;", "_send_email_verification", "_uploadUserProfilePic", "_userDetailDocument", "Lcom/google/firebase/firestore/DocumentSnapshot;", "_userGender", "editProfileResponse", "Landroidx/lifecycle/LiveData;", "getEditProfileResponse", "()Landroidx/lifecycle/LiveData;", "firebaseUri", "getFirebaseUri", "()Landroid/net/Uri;", "setFirebaseUri", "(Landroid/net/Uri;)V", "gender", "getUserProfilePic", "getGetUserProfilePic", "live_data_send_email_verification", "getLive_data_send_email_verification", "uploadUserProfilePic", "getUploadUserProfilePic", "userDetailDocument", "getUserDetailDocument", "userGender", "getUserGender", "validationChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "getValidationChannel", "()Lkotlinx/coroutines/channels/Channel;", "getImageDownloadUrl", "", "url", "getSelectedHealthKit", "getUserDetail", "isEmailVerified", "", "()Ljava/lang/Boolean;", "queueValidationRequest", "status", "Lcom/cardio/physician/network/Status;", "message", "edtResource", "", "tvResourceId", "(Lcom/cardio/physician/network/Status;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removePhoto", "selectGender", "parent", "Landroid/widget/AdapterView;", "view", "Landroid/view/View;", "pos", "id", "", "sendEmailVerificationLink", "showFailureException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "updateUserDetailOnFireStore", "firstName", "lastName", "email", "countryCode", "phoneNumber", "dob", "height", "heartRate", "selectedImageUri", "fileName", "userType", "uploadProfileImage", "deviceUri", "validateFields", "isEmailEdited", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "newEmail", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_qaDebug"})
public final class EditUserProfileViewModel extends com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel {
    private final com.cardio.physician.data.remote.profile.UserProfileRepository repository = null;
    private final com.cardio.physician.network.NetworkHelper networkHelper = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<com.google.firebase.firestore.DocumentSnapshot>> _userDetailDocument = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<com.google.firebase.firestore.DocumentSnapshot>> userDetailDocument = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.String>> _send_email_verification = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> live_data_send_email_verification = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<android.net.Uri>> _uploadUserProfilePic = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<android.net.Uri>> uploadUserProfilePic = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<android.net.Uri>> _getUserProfilePic = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<android.net.Uri>> getUserProfilePic = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.String>> _userGender = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> userGender = null;
    private final com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent<com.cardio.physician.network.Resource<java.lang.String>> _editProfileResponse = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> editProfileResponse = null;
    private java.lang.String gender = "";
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri firebaseUri;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> validationChannel = null;
    
    @javax.inject.Inject()
    public EditUserProfileViewModel(@org.jetbrains.annotations.NotNull()
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
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> getLive_data_send_email_verification() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<android.net.Uri>> getUploadUserProfilePic() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<android.net.Uri>> getGetUserProfilePic() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> getUserGender() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.cardio.physician.network.Resource<java.lang.String>> getEditProfileResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getFirebaseUri() {
        return null;
    }
    
    public final void setFirebaseUri(@org.jetbrains.annotations.Nullable()
    android.net.Uri p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.channels.Channel<com.cardio.physician.domain.common.model.ValidationModel> getValidationChannel() {
        return null;
    }
    
    public final void getUserDetail() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isEmailVerified() {
        return null;
    }
    
    public final void uploadProfileImage(@org.jetbrains.annotations.Nullable()
    android.net.Uri deviceUri, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName) {
    }
    
    public final void getImageDownloadUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void selectGender(@org.jetbrains.annotations.Nullable()
    android.widget.AdapterView<?> parent, @org.jetbrains.annotations.Nullable()
    android.view.View view, int pos, long id) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object validateFields(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String countryCode, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String dob, @org.jetbrains.annotations.NotNull()
    java.lang.String height, @org.jetbrains.annotations.NotNull()
    java.lang.String heartRate, @org.jetbrains.annotations.Nullable()
    android.net.Uri selectedImageUri, @org.jetbrains.annotations.Nullable()
    java.lang.String fileName, @org.jetbrains.annotations.Nullable()
    java.lang.String userType, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> isEmailEdited, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p12) {
        return null;
    }
    
    public final void updateUserDetailOnFireStore(@org.jetbrains.annotations.NotNull()
    java.lang.String firstName, @org.jetbrains.annotations.NotNull()
    java.lang.String lastName, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String countryCode, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String dob, @org.jetbrains.annotations.NotNull()
    java.lang.String height, @org.jetbrains.annotations.NotNull()
    java.lang.String heartRate, @org.jetbrains.annotations.Nullable()
    android.net.Uri selectedImageUri, @org.jetbrains.annotations.Nullable()
    java.lang.String fileName, @org.jetbrains.annotations.Nullable()
    java.lang.String userType) {
    }
    
    private final java.lang.Object queueValidationRequest(com.cardio.physician.network.Status status, java.lang.String message, int edtResource, int tvResourceId, kotlin.coroutines.Continuation<? super kotlin.Unit> p4) {
        return null;
    }
    
    private final void showFailureException(java.lang.Exception exception) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedHealthKit() {
        return null;
    }
    
    public final void sendEmailVerificationLink() {
    }
    
    public final void removePhoto() {
    }
}
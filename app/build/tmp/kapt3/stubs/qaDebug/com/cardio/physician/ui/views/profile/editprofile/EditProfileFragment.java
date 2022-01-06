package com.cardio.physician.ui.views.profile.editprofile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cardio.physician.R;
import com.cardio.physician.databinding.FragmentEditProfileBinding;
import com.cardio.physician.domain.common.model.UserModel;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.domain.user.SignUpUserType;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment;
import com.cardio.physician.ui.common.customviews.toolbar.EditProfileToolBarImp;
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar;
import com.cardio.physician.ui.common.utils.*;
import com.cardio.physician.ui.common.utils.inputfilter.DecimalDigitsInputFilter;
import com.cardio.physician.ui.views.change_email.ChangeEmailActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.*;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001TB\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010&\u001a\u00020\rH\u0002J\u0018\u0010\'\u001a\u00020\r2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\rH\u0002J\n\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0016\u0010/\u001a\u00020\r2\f\u00100\u001a\b\u0012\u0004\u0012\u00020201H\u0002J(\u00103\u001a\u00020\r2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\tH\u0002J\u0010\u0010:\u001a\u00020\r2\u0006\u00104\u001a\u000205H\u0002J\"\u0010;\u001a\u00020\r2\u0006\u0010<\u001a\u0002072\u0006\u0010=\u001a\u0002072\b\u0010>\u001a\u0004\u0018\u00010\u001cH\u0017J\u0012\u0010?\u001a\u00020\r2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J&\u0010B\u001a\u0004\u0018\u00010A2\u0006\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\u001a\u0010I\u001a\u00020\r2\u0006\u0010@\u001a\u00020A2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\b\u0010J\u001a\u00020\rH\u0002J\b\u0010K\u001a\u00020\rH\u0002J\u0012\u0010L\u001a\u00020\r2\b\u0010M\u001a\u0004\u0018\u00010NH\u0002J\b\u0010O\u001a\u00020\rH\u0002J\b\u0010P\u001a\u00020+H\u0002J\u0012\u0010Q\u001a\u00020\r2\b\u0010R\u001a\u0004\u0018\u00010SH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R5\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0017\u001a\u0016\u0012\u0012\u0012\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\t0\t0\u00190\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#\u00a8\u0006U"}, d2 = {"Lcom/cardio/physician/ui/views/profile/editprofile/EditProfileFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseToolBarFragment;", "Lcom/cardio/physician/databinding/FragmentEditProfileBinding;", "Landroid/view/View$OnClickListener;", "()V", "birthDate", "Ljava/util/Date;", "isEmailEdited", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "email", "", "()Lkotlin/jvm/functions/Function1;", "setEmailEdited", "(Lkotlin/jvm/functions/Function1;)V", "navArgs", "Lcom/cardio/physician/ui/views/profile/editprofile/EditProfileFragmentArgs;", "getNavArgs", "()Lcom/cardio/physician/ui/views/profile/editprofile/EditProfileFragmentArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "requestMultiplePermissions", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "resultLauncher", "Landroid/content/Intent;", "selectedImageUri", "Landroid/net/Uri;", "userType", "viewModel", "Lcom/cardio/physician/ui/views/profile/editprofile/EditUserProfileViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/profile/editprofile/EditUserProfileViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkPermission", "enableButtonClick", "alpha", "", "clickable", "", "fetchImage", "getToolbarImp", "Lcom/cardio/physician/ui/common/customviews/toolbar/base/IToolbar;", "handleApiCallback", "apiResponse", "Lcom/cardio/physician/network/Resource;", "", "manageViewVisibility", "validationModel", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "bgDrawable", "", "tvValidatorVisibility", "message", "manageViewsForValidation", "onActivityResult", "requestCode", "resultCode", "data", "onClick", "view", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "setListener", "setObservers", "setViewVisiblitiesForData", "extrasUserModel", "Lcom/cardio/physician/domain/common/model/UserModel;", "setViews", "shouldShowRemovePhotoOption", "showUserDetailOnUI", "documentReference", "Lcom/google/firebase/firestore/DocumentSnapshot;", "TextChangeWatcher", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class EditProfileFragment extends com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment<com.cardio.physician.databinding.FragmentEditProfileBinding> implements android.view.View.OnClickListener {
    private java.lang.String userType;
    private android.net.Uri selectedImageUri;
    private final kotlin.Lazy viewModel$delegate = null;
    private java.util.Date birthDate;
    private final androidx.navigation.NavArgsLazy navArgs$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> isEmailEdited;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultLauncher;
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> requestMultiplePermissions = null;
    
    public EditProfileFragment() {
        super();
    }
    
    private final com.cardio.physician.ui.views.profile.editprofile.EditUserProfileViewModel getViewModel() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.profile.editprofile.EditProfileFragmentArgs getNavArgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> isEmailEdited() {
        return null;
    }
    
    public final void setEmailEdited(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setViews() {
    }
    
    private final void setListener() {
    }
    
    private final void setObservers() {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View view) {
    }
    
    private final void handleApiCallback(com.cardio.physician.network.Resource<? extends java.lang.Object> apiResponse) {
    }
    
    private final void showUserDetailOnUI(com.google.firebase.firestore.DocumentSnapshot documentReference) {
    }
    
    private final void checkPermission() {
    }
    
    private final void fetchImage() {
    }
    
    private final boolean shouldShowRemovePhotoOption() {
        return false;
    }
    
    @android.annotation.SuppressLint(value = {"Assert"})
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void manageViewsForValidation(com.cardio.physician.domain.common.model.ValidationModel validationModel) {
    }
    
    private final void manageViewVisibility(com.cardio.physician.domain.common.model.ValidationModel validationModel, int bgDrawable, int tvValidatorVisibility, java.lang.String message) {
    }
    
    private final void enableButtonClick(float alpha, boolean clickable) {
    }
    
    private final void setViewVisiblitiesForData(com.cardio.physician.domain.common.model.UserModel extrasUserModel) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar getToolbarImp() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J(\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J(\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/cardio/physician/ui/views/profile/editprofile/EditProfileFragment$TextChangeWatcher;", "Landroid/text/TextWatcher;", "view", "Landroid/view/View;", "errorTxt", "Landroid/widget/TextView;", "(Lcom/cardio/physician/ui/views/profile/editprofile/EditProfileFragment;Landroid/view/View;Landroid/widget/TextView;)V", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onTextChanged", "before", "app_qaDebug"})
    public final class TextChangeWatcher implements android.text.TextWatcher {
        private android.view.View view;
        private final android.widget.TextView errorTxt = null;
        
        public TextChangeWatcher(@org.jetbrains.annotations.NotNull()
        android.view.View view, @org.jetbrains.annotations.NotNull()
        android.widget.TextView errorTxt) {
            super();
        }
        
        @java.lang.Override()
        public void beforeTextChanged(@org.jetbrains.annotations.NotNull()
        java.lang.CharSequence s, int start, int count, int after) {
        }
        
        @java.lang.Override()
        public void onTextChanged(@org.jetbrains.annotations.NotNull()
        java.lang.CharSequence s, int start, int before, int count) {
        }
        
        @java.lang.Override()
        public void afterTextChanged(@org.jetbrains.annotations.Nullable()
        android.text.Editable p0) {
        }
    }
}
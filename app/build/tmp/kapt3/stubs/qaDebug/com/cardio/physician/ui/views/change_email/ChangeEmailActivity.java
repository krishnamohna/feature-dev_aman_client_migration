package com.cardio.physician.ui.views.change_email;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import com.cardio.physician.R;
import com.cardio.physician.databinding.ActivityChangeEmailBinding;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.ui.common.base.activity.BaseActivity;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u00012B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\u0016\u0010\u0017\u001a\u00020\u00122\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J(\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0012\u0010$\u001a\u00020\u00122\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010\'\u001a\u00020\u00122\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020\u0012H\u0002J\b\u0010+\u001a\u00020\u0012H\u0002J \u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u000bH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\t\u001a\u0004\b\u000e\u0010\u000f\u00a8\u00063"}, d2 = {"Lcom/cardio/physician/ui/views/change_email/ChangeEmailActivity;", "Lcom/cardio/physician/ui/common/base/activity/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "binding", "Lcom/cardio/physician/databinding/ActivityChangeEmailBinding;", "getBinding", "()Lcom/cardio/physician/databinding/ActivityChangeEmailBinding;", "binding$delegate", "Lkotlin/Lazy;", "oldPasswordVisible", "", "viewModel", "Lcom/cardio/physician/ui/views/change_email/ChangeEmailViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/change_email/ChangeEmailViewModel;", "viewModel$delegate", "enableButtonClick", "", "alpha", "", "clickable", "enableSaveBtn", "handleApiCallback", "apiResponse", "Lcom/cardio/physician/network/Resource;", "", "manageViewVisibility", "validationModel", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "bgDrawable", "", "tvValidatorVisibility", "message", "", "manageViewsForValidation", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "setObservers", "showPasswordImage", "imgNewPassword", "Landroidx/appcompat/widget/AppCompatImageView;", "editText", "Landroidx/appcompat/widget/AppCompatEditText;", "isPasswordVisible", "TextChangeWatcher", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class ChangeEmailActivity extends com.cardio.physician.ui.common.base.activity.BaseActivity implements android.view.View.OnClickListener {
    private final kotlin.Lazy binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private boolean oldPasswordVisible = false;
    
    public ChangeEmailActivity() {
        super();
    }
    
    private final com.cardio.physician.databinding.ActivityChangeEmailBinding getBinding() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.change_email.ChangeEmailViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setListener() {
    }
    
    private final void setObservers() {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    private final void showPasswordImage(androidx.appcompat.widget.AppCompatImageView imgNewPassword, androidx.appcompat.widget.AppCompatEditText editText, boolean isPasswordVisible) {
    }
    
    private final void handleApiCallback(com.cardio.physician.network.Resource<? extends java.lang.Object> apiResponse) {
    }
    
    private final void enableButtonClick(float alpha, boolean clickable) {
    }
    
    private final void enableSaveBtn() {
    }
    
    private final void manageViewsForValidation(com.cardio.physician.domain.common.model.ValidationModel validationModel) {
    }
    
    private final void manageViewVisibility(com.cardio.physician.domain.common.model.ValidationModel validationModel, int bgDrawable, int tvValidatorVisibility, java.lang.String message) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J(\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J(\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/cardio/physician/ui/views/change_email/ChangeEmailActivity$TextChangeWatcher;", "Landroid/text/TextWatcher;", "view", "Landroid/view/View;", "errorTxt", "Landroid/widget/TextView;", "(Lcom/cardio/physician/ui/views/change_email/ChangeEmailActivity;Landroid/view/View;Landroid/widget/TextView;)V", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onTextChanged", "before", "app_qaDebug"})
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
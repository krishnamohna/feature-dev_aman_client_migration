package com.cardio.physician.ui.views.forgot_password;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.cardio.physician.R;
import com.cardio.physician.network.api.Constants;
import com.cardio.physician.databinding.FragmentForgotPasswordBinding;
import com.cardio.physician.domain.common.model.ValidationModel;
import com.cardio.physician.network.Resource;
import com.cardio.physician.network.Status;
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001.B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0016\u0010\u0017\u001a\u00020\u00122\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J(\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0012\u0010$\u001a\u00020\u00122\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010\'\u001a\u00020\u0012H\u0016J\u001a\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020&2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u0012H\u0002J\b\u0010-\u001a\u00020\u0012H\u0002R#\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006/"}, d2 = {"Lcom/cardio/physician/ui/views/forgot_password/ForgotPasswordFragment;", "Lcom/cardio/physician/ui/common/base/fragment/BaseFragmentAuth;", "Landroid/view/View$OnClickListener;", "()V", "binding", "Lcom/cardio/physician/databinding/FragmentForgotPasswordBinding;", "kotlin.jvm.PlatformType", "getBinding", "()Lcom/cardio/physician/databinding/FragmentForgotPasswordBinding;", "binding$delegate", "Lcom/cardio/physician/ui/common/utils/viewbinding/FragmentViewBindingDelegate;", "viewModel", "Lcom/cardio/physician/ui/views/forgot_password/ForgotPasswordViewModel;", "getViewModel", "()Lcom/cardio/physician/ui/views/forgot_password/ForgotPasswordViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "enableButtonClick", "", "alpha", "", "clickable", "", "handleApiCallback", "apiResponse", "Lcom/cardio/physician/network/Resource;", "", "manageViewVisibility", "validationModel", "Lcom/cardio/physician/domain/common/model/ValidationModel;", "bgDrawable", "", "tvValidatorVisibility", "message", "", "manageViewsForValidation", "onClick", "v", "Landroid/view/View;", "onResume", "onViewCreated", "view", "savedInstanceState", "Landroid/os/Bundle;", "setListeners", "setObservers", "TextChangeWatcher", "app_qaDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class ForgotPasswordFragment extends com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth implements android.view.View.OnClickListener {
    private final com.cardio.physician.ui.common.utils.viewbinding.FragmentViewBindingDelegate binding$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    
    public ForgotPasswordFragment() {
        super(0);
    }
    
    private final com.cardio.physician.databinding.FragmentForgotPasswordBinding getBinding() {
        return null;
    }
    
    private final com.cardio.physician.ui.views.forgot_password.ForgotPasswordViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void setListeners() {
    }
    
    private final void setObservers() {
    }
    
    private final void manageViewsForValidation(com.cardio.physician.domain.common.model.ValidationModel validationModel) {
    }
    
    private final void manageViewVisibility(com.cardio.physician.domain.common.model.ValidationModel validationModel, int bgDrawable, int tvValidatorVisibility, java.lang.String message) {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    private final void handleApiCallback(com.cardio.physician.network.Resource<? extends java.lang.Object> apiResponse) {
    }
    
    private final void enableButtonClick(float alpha, boolean clickable) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J(\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016J(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/cardio/physician/ui/views/forgot_password/ForgotPasswordFragment$TextChangeWatcher;", "Landroid/text/TextWatcher;", "view", "Landroid/view/View;", "(Lcom/cardio/physician/ui/views/forgot_password/ForgotPasswordFragment;Landroid/view/View;)V", "afterTextChanged", "", "p0", "Landroid/text/Editable;", "beforeTextChanged", "s", "", "start", "", "count", "after", "onTextChanged", "before", "app_qaDebug"})
    public final class TextChangeWatcher implements android.text.TextWatcher {
        private android.view.View view;
        
        public TextChangeWatcher(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
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